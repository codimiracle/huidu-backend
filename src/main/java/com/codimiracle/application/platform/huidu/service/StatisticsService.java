package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.vo.*;

import java.util.List;

public interface StatisticsService {
    List<PaperBookSalesVO> statisticsPaperBookSales();

    List<BookCategoryCountVO> statisticsBookCategoryCount();

    List<UserBookPreferenceVO> statisticsUserBookPreference();

    PlatformDataVO statisticsPlatformData();

    CreativeDataVO statisticsCreativeStatistics(String userId);

    List<BookReadingStatisticsVO> statisticsBookReadingStatistics(String userId);

    List<CreativeCategoryVO> statisticsCreativeCategory(String userId);
}
