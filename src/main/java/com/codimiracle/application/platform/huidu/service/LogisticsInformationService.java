package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.LogisticsInformation;
import com.codimiracle.application.platform.huidu.entity.vo.LogisticsInformationVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;


/**
 * @author Codimiracle
 */
public interface LogisticsInformationService extends Service<String, LogisticsInformation, LogisticsInformationVO> {

    LogisticsInformationVO findByIdIntegrally(String id);

    LogisticsInformationVO findByOrderNumberIntegrally(String orderNumber);
}
