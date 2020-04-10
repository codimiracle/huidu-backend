package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.vo.BookCategoryCountVO;
import com.codimiracle.application.platform.huidu.entity.vo.PaperBookSalesVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserBookPreferenceVO;

import java.util.Date;
import java.util.List;

public interface StatisticsMapper {
    List<PaperBookSalesVO> findPaperBookSalesBetween(Date start, Date end);

    List<BookCategoryCountVO> findBookCategoryCount();

    List<UserBookPreferenceVO> findUserBookPreference();

}
