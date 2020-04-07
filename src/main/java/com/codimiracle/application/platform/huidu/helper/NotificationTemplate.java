package com.codimiracle.application.platform.huidu.helper;

import com.codimiracle.application.platform.huidu.entity.po.Notification;
import com.codimiracle.application.platform.huidu.entity.po.Subscribe;
import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;

public interface NotificationTemplate {
    Notification generateBy(SubscribeType type, String receiverId, String targetId);
    Notification generateCancelBy(Subscribe subscribe);

    Notification generateNormal(String receiverId, String message);

}
