package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.ArrivedHistory;
import com.codimiracle.application.platform.huidu.entity.vo.ArrivedHistoryVO;
import com.codimiracle.application.platform.huidu.mapper.ArrivedHistoryMapper;
import com.codimiracle.application.platform.huidu.service.ArrivedHistoryService;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ArrivedHistoryServiceImpl extends AbstractService<String, ArrivedHistory, ArrivedHistoryVO> implements ArrivedHistoryService {
    @Resource
    private ArrivedHistoryMapper arrivedHistoryMapper;

    @Override
    public ArrivedHistory signin(String signerId, String motto, Date signingDate) {
        ArrivedHistory arrivedHistory = findLastArrivedHistory(signerId, signingDate);
        if (Objects.nonNull(arrivedHistory)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(signingDate.getTime());
            LocalDate signingLocalDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            calendar.setTimeInMillis(arrivedHistory.getSignTime().getTime());
            LocalDate lastSignedLocalDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            if (signingLocalDate.compareTo(lastSignedLocalDate) == 0) {
                //已经签到了
                return arrivedHistory;
            } else {
                ArrivedHistory arrive = new ArrivedHistory();
                arrive.setSignTime(signingDate);
                arrive.setUserId(signerId);
                arrive.setMotto(motto);
                //前一天已经签到
                if (lastSignedLocalDate.plusDays(1).compareTo(signingLocalDate) == 0) {
                    //续签
                    arrive.setDays(arrivedHistory.getDays() + 1);
                } else {
                    //断签
                    arrive.setDays(1);
                }
                save(arrive);
                return arrive;
            }
        }
        ArrivedHistory thatDayArrived = new ArrivedHistory();
        thatDayArrived.setSignTime(signingDate);
        thatDayArrived.setMotto(motto);
        thatDayArrived.setDays(1);
        thatDayArrived.setUserId(signerId);
        save(thatDayArrived);
        return thatDayArrived;
    }

    @Override
    public ArrivedHistory findByThatDay(String signerId, Date date) {
        return arrivedHistoryMapper.selectByThatDay(signerId, date);
    }

    @Override
    public ArrivedHistoryVO findByThatDayIntegrally(String signerId, Date date) {
        return arrivedHistoryMapper.selectByThatDayIntegrally(signerId, date);
    }

    @Override
    public Map<String, Boolean> retriveHistoryMap(String userId) {
        List<Map<String, Object>> mapList = arrivedHistoryMapper.retriveSignedMapByUserId(userId);
        Map<String, Boolean> map = new HashMap<>();
        mapList.forEach((m) -> map.put(DateFormatUtils.format((Date) m.get("key"), "yyyy-MM-dd"), Objects.equals(m.get("value"), 1L)));
        return map;
    }

    @Override
    public PageSlice<ArrivedHistoryVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(arrivedHistoryMapper.selectAllIntegrally(filter, sorter, page));
    }

    private ArrivedHistory findLastArrivedHistory(String signerId, Date signingDate) {
        return arrivedHistoryMapper.selectLastArrivedHistory(signerId, signingDate);
    }
}
