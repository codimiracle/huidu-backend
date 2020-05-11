package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.embedded.CommunityFocus;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.entity.vo.ReviewVO;
import com.codimiracle.application.platform.huidu.entity.vo.TopicVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.middleware.content.pojo.vo.CommentVO;

public interface PopularService {

    PageSlice<BookVO> findPopularBooksIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<TopicVO> findPopularTopicIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<ReviewVO> findPopularReviewIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<CommentVO> findPopularCommentIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<CommunityFocus> findCommunityFocus(Filter filter, Sorter sorter, Page page);
}
