package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.LogisticsInformation;
import com.codimiracle.application.platform.huidu.mapper.LogisticsInformationMapper;
import com.codimiracle.application.platform.huidu.service.LogisticsInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class LogisticsInformationServiceImpl extends AbstractService<String, LogisticsInformation> implements LogisticsInformationService {
    @Resource
    private LogisticsInformationMapper logisticsInformationMapper;

}
