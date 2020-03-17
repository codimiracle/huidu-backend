package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.ArrivedHistory;
import com.codimiracle.application.platform.huidu.entity.vo.ArrivedHistoryVO;

import java.util.Date;
import java.util.Map;


/**
 * @author Codimiracle
 */
public interface ArrivedHistoryService extends Service<String, ArrivedHistory> {

    ArrivedHistory signin(String signerId, Date signedDate);

    ArrivedHistory findByThatDay(String signerId, Date thatDay);

    ArrivedHistoryVO findByThatDayIntegrally(String signerId, Date thatDay);

    Map<String, Boolean> retriveHistoryMap(String signerId);
}
