package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StatisticsMapper {
    List<PaperBookSalesVO> findPaperBookSalesBetween(Date start, Date end);

    List<BookCategoryCountVO> findBookCategoryCount();

    List<UserBookPreferenceVO> findUserBookPreference();

    PlatformDataVO statisticsPlatformData();

    CreativeDataVO statisticsCreativeStatistics(@Param("userId") String userId);

    List<BookReadingStatisticsVO> statisticsBookReadingStatistics(@Param("userId") String userId);

    List<CreativeCategoryVO> statisticsCreativeCategory(String userId);
}
