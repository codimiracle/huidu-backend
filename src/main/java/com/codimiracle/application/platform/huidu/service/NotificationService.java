package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Notification;
import com.codimiracle.application.platform.huidu.entity.vo.NotificationVO;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface NotificationService extends Service<String, Notification> {

    PageSlice<NotificationVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    void deleteByIdLogically(String id);

    void markAsRead(String id);

    NotificationVO findByIdIntegrally(String id);

    void markAsReadBulk(String userId, List<String> ids);
}
