package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Notification;
import com.codimiracle.application.platform.huidu.mapper.NotificationMapper;
import com.codimiracle.application.platform.huidu.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class NotificationServiceImpl extends AbstractService<String, Notification> implements NotificationService {
    @Resource
    private NotificationMapper notificationMapper;

}
