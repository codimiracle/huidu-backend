package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.PassingPoint;
import com.codimiracle.application.platform.huidu.entity.vo.PassingPointVO;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface PassingPointService extends Service<String, PassingPoint> {

    List<PassingPointVO> findByLogisticsInformationId(String id);
}
