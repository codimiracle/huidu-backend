package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.History;
import com.codimiracle.application.platform.huidu.mapper.HistoryMapper;
import com.codimiracle.application.platform.huidu.service.HistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class HistoryServiceImpl extends AbstractService<String, History> implements HistoryService {
    @Resource
    private HistoryMapper historyMapper;

}
