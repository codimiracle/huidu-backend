package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.PassingPoint;
import com.codimiracle.application.platform.huidu.entity.vo.PassingPointVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface PassingPointService extends Service<String, PassingPoint, PassingPointVO> {

    List<PassingPointVO> findByLogisticsInformationId(String id);
}
