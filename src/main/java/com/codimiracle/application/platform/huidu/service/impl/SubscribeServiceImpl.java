package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Subscribe;
import com.codimiracle.application.platform.huidu.mapper.SubscribeMapper;
import com.codimiracle.application.platform.huidu.service.SubscribeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class SubscribeServiceImpl extends AbstractService<String, Subscribe> implements SubscribeService {
    @Resource
    private SubscribeMapper subscribeMapper;

}
