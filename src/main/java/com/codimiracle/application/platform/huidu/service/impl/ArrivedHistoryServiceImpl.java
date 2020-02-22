package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.ArrivedHistory;
import com.codimiracle.application.platform.huidu.mapper.ArrivedHistoryMapper;
import com.codimiracle.application.platform.huidu.service.ArrivedHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ArrivedHistoryServiceImpl extends AbstractService<String, ArrivedHistory> implements ArrivedHistoryService {
    @Resource
    private ArrivedHistoryMapper arrivedHistoryMapper;

}
