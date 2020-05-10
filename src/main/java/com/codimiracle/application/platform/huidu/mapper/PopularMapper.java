package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.middleware.content.pojo.vo.ContentArticleVO;
import com.codimiracle.web.mybatis.contract.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface PopularMapper extends Mapper<Object> {

    List<BookVO> selectPopularBooksIntegrally(Filter filter, Sorter sorter, Page page);

    List<ContentArticleVO> selectPopularTopicsIntegrally(Filter filter, Sorter sorter, Page page);

    List<ContentArticleVO> selectPopularReviewsIntegrally(Filter filter, Sorter sorter, Page page);
}