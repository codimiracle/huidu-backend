package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.vo.ContentVO;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.mapper.ContentMapper;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.ContentService;
import com.codimiracle.application.platform.huidu.service.ReviewService;
import com.codimiracle.application.platform.huidu.service.TopicService;
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
    private BookService bookService;

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
                return  bookService.findByContentIdIntegrally(contentVO.getContentId());
            }
            return contentVO;
        }).collect(Collectors.toList()));
        return slice;
    }
}
