package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.Catalogs;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.mapper.BookEpisodeMapper;
import com.codimiracle.application.platform.huidu.service.*;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.middleware.content.pojo.po.Content;
import com.codimiracle.web.middleware.content.pojo.po.ContentExamination;
import com.codimiracle.web.middleware.content.service.ContentService;
import com.codimiracle.web.middleware.content.service.ExaminationService;
import com.codimiracle.web.mybatis.contract.ServiceException;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import com.codimiracle.web.notification.middleware.service.SubscriptionService;
import com.codimiracle.web.notification.middleware.template.NotificationTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookEpisodeServiceImpl extends AbstractService<String, BookEpisode, BookEpisodeVO> implements BookEpisodeService {
    @Resource
    private BookEpisodeMapper bookEpisodeMapper;
    @Resource
    private BookService bookService;
    @Resource
    private BookMetadataService bookMetadataService;
    @Resource
    private ContentService contentService;
    @Resource
    private SubscriptionService subscriptionService;
    @Resource
    private ExaminationService examinationService;
    @Resource
    private NotificationTemplate notificationTemplate;

    @Resource
    private CommodityService commodityService;
    @Resource
    private CategoryService categoryService;

    @Override
    protected BookEpisodeVO mutate(BookEpisodeVO episodeVO) {
        if (Objects.nonNull(episodeVO)) {
            episodeVO.setBook(bookService.findByIdIntegrally(episodeVO.getBookId()));
            episodeVO.setCommodity(commodityService.findByIdIntegrally(episodeVO.getCommodityId()));
            episodeVO.setNext(bookEpisodeMapper.selectEpisodeIdByEpisodeNumber(episodeVO.getBookId(), episodeVO.getEpisodeNumber() + 1));
            episodeVO.setExamination(examinationService.findLastByContentIdIntegrally(episodeVO.getContentId()));
        }
        return episodeVO;
    }

    @Override
    public void save(BookEpisode model) {
        Content content = new Content();
        content.setOwnerId(model.getOwnerId());
        content.setType(ContentType.Episode.toString());
        contentService.save(content);
        model.setContentId(content.getId());
        super.save(model);
    }

    private void updateStatistics(ContentStatus originalStatus, BookEpisode bookEpisode) {
        //转为发布状态
        if (!Objects.equals(originalStatus.getStatus(), ContentStatus.Publish) && Objects.equals(bookEpisode.getStatus(), ContentStatus.Publish)) {
            String metadataId = bookService.findMetadataIdByBookId(bookEpisode.getBookId());
            if (Objects.nonNull(metadataId)) {
                bookMetadataService.incrementWordsBy(metadataId, bookEpisode.getWords());
            }
            bookService.incrementEpisodes(bookEpisode.getBookId());
        }
        //由发布转为其它状态
        if (Objects.equals(originalStatus.getStatus(), ContentStatus.Publish) && !Objects.equals(bookEpisode.getStatus(), ContentStatus.Publish)) {
            String metadataId = bookService.findMetadataIdByBookId(bookEpisode.getBookId());
            if (Objects.nonNull(metadataId)) {
                bookMetadataService.decrementWordsBy(metadataId, bookEpisode.getWords());
            }
            bookService.decrementEpisodes(bookEpisode.getBookId());
        }
    }

    @Override
    public void update(BookEpisode model) {
        BookEpisode bookEpisode = findById(model.getId());
        // 更新统计信息
        updateStatistics(bookEpisode.getStatus(), model);
        //初次修改为发布状态时发送通知
        if (Objects.nonNull(bookEpisode) && !Objects.equals(model.getStatus(), bookEpisode.getStatus()) && !Objects.equals(model.getStatus(), ContentStatus.Publish)) {
            //TODO
        }
        super.update(model);
    }

    @Override
    public BookEpisodeVO findByIdIntegrally(String id) {
        return mutate(bookEpisodeMapper.selectByIdIntegrally(id));
    }

    @Override
    public PageSlice<BookEpisodeVO> findAllIntegrally(String bookId, Filter filter, Sorter sorter, Page page) {
        return mutate(extractPageSlice(bookEpisodeMapper.selectAllIntegrally(bookId, filter, sorter, page)));
    }

    @Override
    public void deleteByIdLogically(String id) {
        bookEpisodeMapper.deleteByIdLogically(id);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        bookEpisodeMapper.deleteByIdsLogically(ids);
    }

    @Override
    public BookEpisodeVO findLastUpdateEpisodeByBookIdIntegrally(String id) {
        return mutate(bookEpisodeMapper.selectLastUpdateEpisodeIntegrally(id));
    }

    @Override
    public BookEpisodeVO findLastEpisodeByBookId(String id) {
        return mutate(bookEpisodeMapper.selectLastEpisodeByBookIdIntegrally(id));
    }

    @Override
    public Integer findLastEpisodeNumberByBookId(String bookId) {
        BookEpisodeVO bookEpisodeVO = bookEpisodeMapper.selectLastEpisodeByBookIdIntegrally(bookId);
        return Objects.nonNull(bookEpisodeVO) ? bookEpisodeVO.getEpisodeNumber() : 0;
    }

    @Override
    public Integer findLastPublishedEpisodeNumberByBookId(String bookId) {
        BookEpisodeVO bookEpisodeVO = bookEpisodeMapper.selectLastPublishedEpisodeByBookId(bookId);
        return Objects.nonNull(bookEpisodeVO) ? bookEpisodeVO.getEpisodeNumber() : 0;
    }

    @Override
    public BookEpisodeVO findByEpisodeNumberIntegrally(String bookId, Integer episodeNumber) {
        return mutate(bookEpisodeMapper.selectByEpisodeNumberIntegrally(bookId, episodeNumber));
    }

    @Override
    public List<Catalogs> findCatalogsByBookIdAndStatus(String id, ContentStatus status) {
        return bookEpisodeMapper.selectCatalogsByBookId(id, status);
    }

    @Override
    public BookEpisodeVO findLastPublishedEpisodeByBookId(String bookId) {
        return mutate(bookEpisodeMapper.selectLastPublishedEpisodeByBookId(bookId));
    }

    private void examinate(String contentId, String reason, String userId, ContentStatus status) {
        BookEpisode bookEpisode = findByContentId(contentId);
        if (!Objects.equals(bookEpisode.getStatus(), ContentStatus.Examining)) {
            throw new ServiceException("无法审查该内容！");
        }
        ContentExamination examination = new ContentExamination();
        examination.setFromStatus(bookEpisode.getStatus().toString());
        examination.setToStatus(status.getStatus());
        examination.setTargetContentId(contentId);
        examination.setReason(reason);
        examination.setExaminerId(userId);
        examination.setExaminedAt(new Date());
        examinationService.save(examination);

        BookEpisode updatingBookEpisode = new BookEpisode();
        updatingBookEpisode.setId(bookEpisode.getId());
        updatingBookEpisode.setStatus(status);
        update(updatingBookEpisode);
    }

    private BookEpisode findByContentId(String id) {
        return findBy("contentId", id);
    }

    @Override
    public void passExamination(String contentId, String reason, String userId) {
        examinate(contentId, reason, userId, ContentStatus.Publish);
    }

    @Override
    public void rejectExamination(String contentId, String reason, String userId) {
        examinate(contentId, reason, userId, ContentStatus.Rejected);
    }
}
