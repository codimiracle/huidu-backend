package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.vo.BookCategoryCountVO;
import com.codimiracle.application.platform.huidu.entity.vo.PaperBookSalesVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserBookPreferenceVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.mapper.StatisticsMapper;
import com.codimiracle.application.platform.huidu.service.StatisticsService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
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
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);
        Date start = calendar.getTime();
        Map<String, PaperBookSalesVO> map = new HashMap<>();
        String dayAndMonth = DateFormatUtils.format(end, "yyyy-MM");
        String format = dayAndMonth + "-%02d";
        IntStream.range(end.getDate() - 7, end.getDate()).forEach((date) -> {
            String key = String.format(format, date);
            PaperBookSalesVO paperBookSalesVO = new PaperBookSalesVO();
            try {
                paperBookSalesVO.setDate(DateUtils.parseDate(key, "yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            map.put(String.format(key, date), paperBookSalesVO);
        });
        List<PaperBookSalesVO> paperBookSalesList = statisticsMapper.findPaperBookSalesBetween(start, end);
        paperBookSalesList.forEach((e) -> {
            map.put(DateFormatUtils.format(e.getDate(), "yyyy-MM-dd"), e);
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
}
