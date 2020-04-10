package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.vo.BookCategoryCountVO;
import com.codimiracle.application.platform.huidu.entity.vo.PaperBookSalesVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserBookPreferenceVO;

import java.util.List;

public interface StatisticsService {
    List<PaperBookSalesVO> statisticsPaperBookSales();

    List<BookCategoryCountVO> statisticsBookCategoryCount();

    List<UserBookPreferenceVO> statisticsUserBookPreference();

}
