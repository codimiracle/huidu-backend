package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Notification;
import com.codimiracle.application.platform.huidu.entity.vo.NotificationVO;
import com.codimiracle.application.platform.huidu.mapper.NotificationMapper;
import com.codimiracle.application.platform.huidu.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class NotificationServiceImpl extends AbstractService<String, Notification> implements NotificationService {
    @Value("${huidu.notification.system-notifier}")
    private String systemNotifierId;

    @Resource
    private NotificationMapper notificationMapper;


    @Override
    public void notify(Notification notification) {
        // 以系统通知的方式通知用户
        notification.setSenderId(this.systemNotifierId);
        notification.setCreateTime(new Date());
        notification.setUpdateTime(notification.getCreateTime());
        save(notification);
    }

    @Override
    public PageSlice<NotificationVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(notificationMapper.selectAllIntegrally(filter, sorter, page));
    }

    @Override
    public void deleteByIdLogically(String id) {
        notificationMapper.deleteByIdLogically(id);
    }

    @Override
    public void markAsRead(String id) {
        notificationMapper.markAsRead(id);
    }

    @Override
    public NotificationVO findByIdIntegrally(String id) {
        return notificationMapper.selectByIdIntegrally(id);
    }

    @Override
    public void markAsReadBulk(String userId, List<String> ids) {
        ids.stream().forEach((id) -> {
            Notification notification = findById(id);
            if (Objects.nonNull(notification) && Objects.equals(notification.getReceiverId(), userId)) {
                markAsRead(id);
            } else {
                throw new ServiceException("存在不存在的条目！");
            }
        });
    }
}
