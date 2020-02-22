package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.PassingPoint;
import com.codimiracle.application.platform.huidu.mapper.PassingPointMapper;
import com.codimiracle.application.platform.huidu.service.PassingPointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class PassingPointServiceImpl extends AbstractService<String, PassingPoint> implements PassingPointService {
    @Resource
    private PassingPointMapper passingPointMapper;

}
