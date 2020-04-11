package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.po.ContentExamination;
import com.codimiracle.application.platform.huidu.entity.po.Subscribe;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.Catalogs;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;
import com.codimiracle.application.platform.huidu.helper.NotificationTemplate;
import com.codimiracle.application.platform.huidu.mapper.BookEpisodeMapper;
import com.codimiracle.application.platform.huidu.service.*;
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
public class BookEpisodeServiceImpl extends AbstractService<String, BookEpisode> implements BookEpisodeService {
    @Resource
    private BookEpisodeMapper bookEpisodeMapper;
    @Resource
    private BookService bookService;
    @Resource
    private BookMetadataService bookMetadataService;
    @Resource
    private ContentService contentService;
    @Resource
    private NotificationService notificationService;
    @Resource
    private SubscribeService subscribeService;
    @Resource
    private ContentExaminationService contentExaminationService;
    @Resource
    private NotificationTemplate notificationTemplate;

    @Resource
    private CommodityService commodityService;
    @Resource
    private CategoryService categoryService;

    private BookEpisodeVO mutate(BookEpisodeVO episodeVO) {
        if (Objects.nonNull(episodeVO)) {
            episodeVO.setBook(bookService.findByIdIntegrally(episodeVO.getBookId()));
            episodeVO.setCommodity(commodityService.findByIdIntegrally(episodeVO.getCommodityId()));
            episodeVO.setNext(bookEpisodeMapper.selectEpisodeIdByEpisodeNumber(episodeVO.getBookId(), episodeVO.getEpisodeNumber() + 1));
            episodeVO.setExamination(contentExaminationService.findLastExaminationByContentId(episodeVO.getContentId()));
        }
        return episodeVO;
    }

    private PageSlice<BookEpisodeVO> mutate(PageSlice<BookEpisodeVO> slice) {
        slice.getList().forEach(this::mutate);
        return slice;
    }

    @Override
    public void save(BookEpisode model) {
        Content content = new Content();
        content.setOwnerId(model.getOwnerId());
        content.setType(ContentType.Episode);
        content.setCreateTime(new Date());
        content.setUpdateTime(content.getCreateTime());
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
            List<Subscribe> subscribes = subscribeService.findBySubscribeTypeAndBookId(SubscribeType.BookUpdated, bookEpisode.getBookId());
            subscribes.stream().map(e -> notificationTemplate.generateNotificationBy(SubscribeType.BookUpdated, e.getSubscriberId(), e.getBookId(), bookEpisode.getTitle())).forEach(notification -> notificationService.notify(notification));
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
        examination.setUserId(userId);
        examination.setExamineTime(new Date());
        contentExaminationService.save(examination);

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
