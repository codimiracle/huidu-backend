package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Activity;
import com.codimiracle.application.platform.huidu.mapper.ActivityMapper;
import com.codimiracle.application.platform.huidu.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ActivityServiceImpl extends AbstractService<String, Activity> implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;

}
