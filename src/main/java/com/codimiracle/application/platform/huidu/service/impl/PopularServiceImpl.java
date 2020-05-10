package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.entity.vo.ReviewVO;
import com.codimiracle.application.platform.huidu.entity.vo.TopicVO;
import com.codimiracle.application.platform.huidu.mapper.PopularMapper;
import com.codimiracle.application.platform.huidu.service.PopularService;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.middleware.content.pojo.vo.ContentArticleVO;
import com.codimiracle.web.mybatis.contract.AbstractUnsupportedOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PopularServiceImpl extends AbstractUnsupportedOperationService<String, Object> implements PopularService {
    @Autowired
    private PopularMapper popularMapper;

    @Override
    public PageSlice<BookVO> findPopularBooksIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(popularMapper.selectPopularBooksIntegrally(filter, sorter, page));
    }

    private PageSlice<TopicVO> mappingToTopicVO(PageSlice<ContentArticleVO> articleVO) {

        return null;
    }

    private PageSlice<ReviewVO> mappingToReviewVO(PageSlice<ContentArticleVO> articleVO) {
        return null;
    }

    @Override
    public PageSlice<TopicVO> findPopularTopicIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ContentArticleVO> objectPageSlice = extractPageSlice(popularMapper.selectPopularTopicsIntegrally(filter, sorter, page));
        return mappingToTopicVO(objectPageSlice);
    }

    @Override
    public PageSlice<ReviewVO> findPopularReviewIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ContentArticleVO> objectPageSlice = extractPageSlice(popularMapper.selectPopularReviewsIntegrally(filter, sorter, page));
        return mappingToReviewVO(objectPageSlice);
    }
}
