package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.ArrivedHistory;
import com.codimiracle.application.platform.huidu.entity.vo.ArrivedHistoryVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.Date;
import java.util.Map;


/**
 * @author Codimiracle
 */
public interface ArrivedHistoryService extends Service<String, ArrivedHistory, ArrivedHistoryVO> {

    ArrivedHistory signin(String signerId, String motto, Date signedDate);

    ArrivedHistory findByThatDay(String signerId, Date thatDay);

    ArrivedHistoryVO findByThatDayIntegrally(String signerId, Date thatDay);

    Map<String, Boolean> retriveHistoryMap(String signerId);

    PageSlice<ArrivedHistoryVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);
}
