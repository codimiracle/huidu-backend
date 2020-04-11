package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.BookAudioEpisode;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.po.ContentExamination;
import com.codimiracle.application.platform.huidu.entity.po.Subscribe;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.AudioCatalogs;
import com.codimiracle.application.platform.huidu.enumeration.BookAudioEpisodeStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;
import com.codimiracle.application.platform.huidu.helper.NotificationTemplate;
import com.codimiracle.application.platform.huidu.mapper.BookAudioEpisodeMapper;
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
public class BookAudioEpisodeServiceImpl extends AbstractService<String, BookAudioEpisode> implements BookAudioEpisodeService {
    @Resource
    private BookAudioEpisodeMapper bookAudioEpisodeMapper;
    @Resource
    private BookEpisodeService bookEpisodeService;
    @Resource
    private ContentService contentService;
    @Resource
    private NotificationTemplate notificationTemplate;
    @Resource
    private NotificationService notificationService;
    @Resource
    private SubscribeService subscribeService;
    @Resource
    private BookMetadataService bookMetadataService;
    @Resource
    private ContentExaminationService contentExaminationService;

    @Resource
    private BookService bookService;
    @Resource
    private CommodityService commodityService;

    private void updateStatistics(ContentStatus originalStatus, BookAudioEpisode bookAudioEpisode) {
        //转为发布状态
        if (!Objects.equals(originalStatus.getStatus(), ContentStatus.Publish) && Objects.equals(bookAudioEpisode.getStatus(), ContentStatus.Publish)) {
            bookService.incrementEpisodes(bookAudioEpisode.getBookId());
        }
        //由发布转为其它状态
        if (Objects.equals(originalStatus.getStatus(), ContentStatus.Publish) && !Objects.equals(bookAudioEpisode.getStatus(), ContentStatus.Publish)) {
            bookService.decrementEpisodes(bookAudioEpisode.getBookId());
        }
    }

    @Override
    public void update(BookAudioEpisode model) {
        BookAudioEpisode bookAudioEpisode = findById(model.getId());
        if (Objects.isNull(bookAudioEpisode)) {
            throw new ServiceException("无法处理该请求！");
        }
        updateStatistics(bookAudioEpisode.getStatus(), model);
        //修改为发布状态时发送通知
        if (!Objects.equals(bookAudioEpisode.getStatus(), ContentStatus.Publish) && Objects.equals(model.getStatus(), ContentStatus.Publish)) {
            List<Subscribe> subscribes = subscribeService.findBySubscribeTypeAndBookId(SubscribeType.BookUpdated, bookAudioEpisode.getBookId());
            subscribes.stream().map(e -> notificationTemplate.generateNotificationBy(SubscribeType.BookUpdated, e.getSubscriberId(), e.getBookId(), bookAudioEpisode.getTitle())).forEach(notification -> notificationService.notify(notification));
        }
        super.update(model);
    }

    @Override
    public void save(BookAudioEpisode model) {
        Content content = new Content();
        content.setType(ContentType.AudioEpisode);
        content.setOwnerId(model.getOwnerId());
        content.setCreateTime(new Date());
        content.setUpdateTime(content.getCreateTime());
        contentService.save(content);
        model.setContentId(content.getId());
        super.save(model);
    }

    private BookAudioEpisodeVO mutate(BookAudioEpisodeVO bookAudioEpisodeVO) {
        if (Objects.nonNull(bookAudioEpisodeVO)) {
            bookAudioEpisodeVO.setBook(bookService.findByIdIntegrally(bookAudioEpisodeVO.getBookId()));
            bookAudioEpisodeVO.setCommodity(commodityService.findByIdIntegrally(bookAudioEpisodeVO.getCommodityId()));
            bookAudioEpisodeVO.setEpisode(bookEpisodeService.findByIdIntegrally(bookAudioEpisodeVO.getEpisodeId()));
            bookAudioEpisodeVO.setNext(bookAudioEpisodeMapper.selectPublishedAudioEpisodeIdByMediaNumber(bookAudioEpisodeVO.getBookId(), bookAudioEpisodeVO.getMediaNumber() + 1));
            bookAudioEpisodeVO.setExamination(contentExaminationService.findLastExaminationByContentId(bookAudioEpisodeVO.getContentId()));
        }
        return bookAudioEpisodeVO;
    }

    @Override
    public PageSlice<BookAudioEpisodeVO> findAllIntegrally(String bookId, Filter filter, Sorter sorter, Page page) {
        PageSlice<BookAudioEpisodeVO> bookAudioEpisodeVOPageSlice = extractPageSlice(bookAudioEpisodeMapper.selectAllIntegrally(bookId, filter, sorter, page));
        bookAudioEpisodeVOPageSlice.getList().forEach((this::mutate));
        return bookAudioEpisodeVOPageSlice;
    }

    @Override
    public BookAudioEpisodeVO findByIdIntegrally(String id) {
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeMapper.selectByIdIntegrally(id);
        mutate(bookAudioEpisodeVO);
        return bookAudioEpisodeVO;
    }

    @Override
    public void deleteByIdLogically(String id) {
        bookAudioEpisodeMapper.deleteByIdLogically(id);
    }

    @Override
    public BookAudioEpisodeVO findLastUpdateEpisodeByBookIdIntegrally(String bookId) {
        return bookAudioEpisodeMapper.selectLastUpdateEpisodeByBookId(bookId);
    }

    @Override
    public BookAudioEpisodeVO findByMediaNumberIntegrally(String bookId, Integer mediaNumber) {
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeMapper.selectByMediaNumberIntegrally(bookId, mediaNumber);
        mutate(bookAudioEpisodeVO);
        return bookAudioEpisodeVO;
    }

    @Override
    public List<AudioCatalogs> findCatalogsByBookIdAndStatus(String id, BookAudioEpisodeStatus status) {
        return bookAudioEpisodeMapper.selectCatalogsByBookIdAndStatus(id, status);
    }

    @Override
    public BookAudioEpisodeVO findLastPublishedEpisodeByBookId(String bookId) {
        return bookAudioEpisodeMapper.selectLastPublishedEpisodeByBookId(bookId);
    }

    private void examinate(String contentId, String reason, String userId, ContentStatus status) {
        BookAudioEpisode bookAudioEpisode = findByContentId(contentId);
        if (!Objects.equals(bookAudioEpisode.getStatus(), ContentStatus.Examining)) {
            throw new ServiceException("无法审查该内容！");
        }
        ContentExamination examination = new ContentExamination();
        examination.setFromStatus(bookAudioEpisode.getStatus().toString());
        examination.setToStatus(status.getStatus());
        examination.setTargetContentId(contentId);
        examination.setReason(reason);
        examination.setUserId(userId);
        examination.setExamineTime(new Date());
        contentExaminationService.save(examination);

        BookAudioEpisode updatingBookAudioEpisode = new BookAudioEpisode();
        updatingBookAudioEpisode.setId(bookAudioEpisode.getId());
        updatingBookAudioEpisode.setStatus(status);
        update(updatingBookAudioEpisode);
    }

    private BookAudioEpisode findByContentId(String contentId) {
        return findBy("contentId", contentId);
    }

    @Override
    public void passExamination(String contentId, String reason, String userId) {
        examinate(contentId, reason, userId, ContentStatus.Publish);
    }

    @Override
    public void rejectExamination(String contentId, String reason, String userId) {
        examinate(contentId, reason, userId, ContentStatus.Rejected);
    }

    @Override
    public Integer findLastMediaNumberByBookId(String bookId) {
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeMapper.selectLastEpisodeByBookIdIntegrally(bookId);
        return Objects.nonNull(bookAudioEpisodeVO) ? bookAudioEpisodeVO.getMediaNumber() : 0;
    }

    @Override
    public Integer findLastPublishedMediaNumberByBookId(String bookId) {
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeMapper.selectLastPublishedEpisodeByBookId(bookId);
        return Objects.nonNull(bookAudioEpisodeVO) ? bookAudioEpisodeVO.getMediaNumber() : 0;
    }
}
