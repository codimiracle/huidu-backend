package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.entity.vo.ReviewVO;
import com.codimiracle.application.platform.huidu.entity.vo.TopicVO;
import com.codimiracle.application.platform.huidu.service.PopularArticleService;
import com.codimiracle.application.platform.huidu.service.PopularContentService;
import com.codimiracle.application.platform.huidu.service.PopularService;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.AbstractUnsupportedOperationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class PopularServiceImpl extends AbstractUnsupportedOperationService<String, Object> implements PopularService {

    @Resource
    private PopularContentService popularContentService;
    @Resource
    private PopularArticleService popularArticleService;

    @Override
    public PageSlice<BookVO> findPopularBooksIntegrally(Filter filter, Sorter sorter, Page page) {
        return popularContentService.findPopularBooksIntegrally(filter, sorter, page);
    }

    @Override
    public PageSlice<TopicVO> findPopularTopicIntegrally(Filter filter, Sorter sorter, Page page) {
        return popularArticleService.findPopularTopicIntegrally(filter, sorter, page);
    }

    @Override
    public PageSlice<ReviewVO> findPopularReviewIntegrally(Filter filter, Sorter sorter, Page page) {
        return popularArticleService.findPopularReviewIntegrally(filter, sorter, page);
    }
}
