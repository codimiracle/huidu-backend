package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;

public interface PopularContentService {
    PageSlice<BookVO> findPopularBooksIntegrally(Filter filter, Sorter sorter, Page page);
}
