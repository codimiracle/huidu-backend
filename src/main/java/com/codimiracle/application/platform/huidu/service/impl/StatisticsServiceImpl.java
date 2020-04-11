package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.vo.*;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.mapper.StatisticsMapper;
import com.codimiracle.application.platform.huidu.service.StatisticsService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private StatisticsMapper statisticsMapper;

    @Override
    public List<PaperBookSalesVO> statisticsPaperBookSales() {
        // 找出一周的销量
        Calendar calendar = Calendar.getInstance();
        Date end = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 6);
        Date start = calendar.getTime();
        Map<String, PaperBookSalesVO> map = new HashMap<>();
        String pattern = "yyyy-MM-dd";
        IntStream.range(0, 7).forEach((times) -> {
            calendar.setTime(start);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + times);
            Date date = calendar.getTime();
            String key = DateFormatUtils.format(date, pattern);
            PaperBookSalesVO paperBookSalesVO = new PaperBookSalesVO();
            paperBookSalesVO.setQuantity(0);
            paperBookSalesVO.setAmount(BigDecimal.ZERO);
            paperBookSalesVO.setDate(date);
            map.put(key, paperBookSalesVO);
        });
        List<PaperBookSalesVO> paperBookSalesList = statisticsMapper.findPaperBookSalesBetween(start, end);
        paperBookSalesList.forEach((e) -> {
            map.put(DateFormatUtils.format(e.getDate(), pattern), e);
        });
        return map.values().stream().sorted(Comparator.comparing(PaperBookSalesVO::getDate)).collect(Collectors.toList());
    }

    @Override
    public List<BookCategoryCountVO> statisticsBookCategoryCount() {
        Map<String, BookCategoryCountVO> map = Arrays.stream(BookType.values()).collect(Collectors.toMap(BookType::getType, (type) -> {
            BookCategoryCountVO bookCategoryCountVO = new BookCategoryCountVO();
            bookCategoryCountVO.setCount(0);
            bookCategoryCountVO.setType(type.getType());
            return bookCategoryCountVO;
        }));
        statisticsMapper.findBookCategoryCount().forEach((bookCategoryCountVO -> {
            map.put(bookCategoryCountVO.getType(), bookCategoryCountVO);
        }));
        return new ArrayList<>(map.values());
    }

    @Override
    public List<UserBookPreferenceVO> statisticsUserBookPreference() {
        return statisticsMapper.findUserBookPreference();
    }

    @Override
    public PlatformDataVO statisticsPlatformData() {
        return statisticsMapper.statisticsPlatformData();
    }

    @Override
    public CreativeDataVO statisticsCreativeStatistics(String userId) {
        return statisticsMapper.statisticsCreativeStatistics(userId);
    }

    @Override
    public List<BookReadingStatisticsVO> statisticsBookReadingStatistics(String userId) {
        return statisticsMapper.statisticsBookReadingStatistics(userId);
    }

    @Override
    public List<CreativeCategoryVO> statisticsCreativeCategory(String userId) {
        return statisticsMapper.statisticsCreativeCategory(userId);
    }
}
