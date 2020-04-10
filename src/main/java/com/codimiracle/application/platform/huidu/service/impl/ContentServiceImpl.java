package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.vo.ContentVO;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.mapper.ContentMapper;
import com.codimiracle.application.platform.huidu.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ContentServiceImpl extends AbstractService<String, Content> implements ContentService {
    @Resource
    private ContentMapper contentMapper;

    @Resource
    private TopicService topicService;

    @Resource
    private ReviewService reviewService;

    @Resource
    private BookEpisodeService bookEpisodeService;

    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;

    @Resource
    private BookService bookService;

    @Resource
    private ContentArticleService contentArticleService;

    @Override
    public int deleteByIdLogically(String id) {
        return contentMapper.deleteByIdLogically(id);
    }

    @Override
    public ContentVO findByIdIntegrally(String id) {
        return contentMapper.findByIdIntegrally(id);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        contentMapper.deleteByIdsLogically(ids);
    }

    @Override
    public void likesIncrement(String contentId) {
        contentMapper.likesIncrement(contentId);
    }

    @Override
    public void likesDecrement(String contentId) {
        contentMapper.likesDecrement(contentId);
    }

    @Override
    public void commentsIncrement(String contentId) {
        contentMapper.commentsIncrement(contentId);
    }

    @Override
    public void commentsDecrement(String contentId) {
        contentMapper.commentsDecrement(contentId);
    }

    @Override
    public void rateIncrementBy(String contentId, Float rate) {
        contentMapper.rateIncrementBy(contentId, rate);
    }

    @Override
    public void rateDecrementBy(String contentId, Float rate) {
        contentMapper.rateDecrementBy(contentId, rate);
    }

    @Override
    public PageSlice<ContentVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ContentVO> slice = extractPageSlice(contentMapper.selectAllIntegrally(filter, sorter, page));
        slice.setList(slice.getList().stream().map(contentVO -> {
            if (Objects.equals(ContentType.Topic.toString(), contentVO.getType())) {
                return topicService.findByIdIntegrally(contentVO.getContentId());
            }
            if (Objects.equals(ContentType.Review.toString(), contentVO.getType())) {
                return reviewService.findByIdIntegrally(contentVO.getContentId());
            }
            if (Objects.equals(ContentType.Book.toString(), contentVO.getType())) {
                return bookService.findByContentIdIntegrally(contentVO.getContentId());
            }
            return contentVO;
        }).collect(Collectors.toList()));
        return slice;
    }

    @Override
    public void rejectById(String id, String reason, String userId) {
        Content content = findById(id);
        switch (content.getType()) {
            case Book:
                bookService.rejectExamination(id, reason, userId);
                break;
            case Episode:
                bookEpisodeService.rejectExamination(id, reason, userId);
                break;
            case AudioEpisode:
                bookAudioEpisodeService.rejectExamination(id, reason, userId);
                break;
            case Topic:
            case Review:
            case Comment:
                contentArticleService.rejectExamination(id, reason, userId);
                break;
            default:
                throw new ServiceException("无法审查内容，请确认内容状态正确！");
        }
    }

    @Override
    public void rejectByIds(List<String> ids, String reason, String userId) {
        ids.forEach((id) -> {
            rejectById(id, reason, userId);
        });
    }

    @Override
    public void acceptById(String id, String reason, String userId) {
        Content content = findById(id);
        switch (content.getType()) {
            case Book:
                bookService.passExamination(id, reason, userId);
                break;
            case Episode:
                bookEpisodeService.passExamination(id, reason, userId);
                break;
            case AudioEpisode:
                bookAudioEpisodeService.passExamination(id, reason, userId);
                break;
            case Topic:
            case Review:
            case Comment:
                contentArticleService.passExamination(id, reason, userId);
                break;
            default:
                throw new ServiceException("无法审查内容，请确认内容是否存在！");
        }
    }

    @Override
    public void acceptByIds(List<String> ids, String reason, String userId) {
        ids.forEach((id) -> {
            acceptById(id, reason, userId);
        });
    }
}
