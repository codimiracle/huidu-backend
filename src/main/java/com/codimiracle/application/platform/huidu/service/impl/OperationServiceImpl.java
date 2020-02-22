package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Operation;
import com.codimiracle.application.platform.huidu.mapper.OperationMapper;
import com.codimiracle.application.platform.huidu.service.OperationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class OperationServiceImpl extends AbstractService<String, Operation> implements OperationService {
    @Resource
    private OperationMapper operationMapper;

}
