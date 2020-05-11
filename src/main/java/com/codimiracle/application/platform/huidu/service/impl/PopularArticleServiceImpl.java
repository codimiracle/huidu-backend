package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.vo.ReviewVO;
import com.codimiracle.application.platform.huidu.entity.vo.TopicVO;
import com.codimiracle.application.platform.huidu.mapper.PopularMapper;
import com.codimiracle.application.platform.huidu.service.PopularArticleService;
import com.codimiracle.application.platform.huidu.service.ReviewService;
import com.codimiracle.application.platform.huidu.service.TopicService;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.middleware.content.pojo.vo.CommentVO;
import com.codimiracle.web.middleware.content.pojo.vo.ContentArticleVO;
import com.codimiracle.web.middleware.content.service.ArticleService;
import com.codimiracle.web.middleware.content.service.CommentService;
import com.codimiracle.web.mybatis.contract.PageSliceExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@Service
@Transactional
public class PopularArticleServiceImpl extends PageSliceExtractor implements PopularArticleService {

    @Resource
    private PopularMapper popularMapper;

    @Resource
    private ArticleService articleService;
    @Resource
    private CommentService commentService;
    @Resource
    private TopicService topicService;
    @Resource
    private ReviewService reviewService;

    @Override
    public PageSlice<TopicVO> findPopularTopicIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ContentArticleVO> mutated = articleService.inflate(extractPageSlice(popularMapper.selectPopularTopicsIntegrally(filter, sorter, page)));
        return mappingToTopicVO(mutated);
    }

    private PageSlice<TopicVO> mappingToTopicVO(PageSlice<ContentArticleVO> mutated) {
        PageSlice<TopicVO> slice = new PageSlice<>();
        slice.setPage(mutated.getPage());
        slice.setLimit(mutated.getLimit());
        slice.setTotal(mutated.getTotal());
        slice.setList(mutated.getList().stream().map(contentArticleVO -> {
            return topicService.findByIdIntegrally(contentArticleVO.getContentId());
        }).collect(Collectors.toList()));
        return slice;
    }

    @Override
    public PageSlice<ReviewVO> findPopularReviewIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ContentArticleVO> mutated = articleService.inflate(extractPageSlice(popularMapper.selectPopularReviewsIntegrally(filter, sorter, page)));
        return mappingToReviewVO(mutated);
    }

    private PageSlice<ReviewVO> mappingToReviewVO(PageSlice<ContentArticleVO> mutated) {
        PageSlice<ReviewVO> slice = new PageSlice<>();
        slice.setPage(mutated.getPage());
        slice.setLimit(mutated.getLimit());
        slice.setTotal(mutated.getTotal());
        slice.setList(mutated.getList().stream().map(contentArticleVO -> {
            return reviewService.findByIdIntegrally(contentArticleVO.getContentId());
        }).collect(Collectors.toList()));
        return slice;
    }

    private PageSlice<CommentVO> mappingToCommentVO(PageSlice<ContentArticleVO> articlePageSlice) {
        PageSlice<CommentVO> pageSlice = new PageSlice<>();
        pageSlice.setList(articlePageSlice.getList().stream().map(contentArticleVO -> {
            return commentService.findByIdIntegrally(contentArticleVO.getContentId());
        }).collect(Collectors.toList()));
        return pageSlice;
    }

    @Override
    public PageSlice<CommentVO> findPopularCommentIntegrally(Filter filter, Sorter sorter, Page page) {
        return mappingToCommentVO(popularMapper.selectPopularCommentIntegrally(filter, sorter, page));
    }
}
