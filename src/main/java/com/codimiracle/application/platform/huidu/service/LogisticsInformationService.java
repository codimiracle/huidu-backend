package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.LogisticsInformation;
import com.codimiracle.application.platform.huidu.entity.vo.LogisticsInformationVO;


/**
 * @author Codimiracle
 */
public interface LogisticsInformationService extends Service<String, LogisticsInformation> {

    LogisticsInformationVO findByIdIntegrally(String id);

    LogisticsInformationVO findByOrderNumberIntegrally(String orderNumber);
}
