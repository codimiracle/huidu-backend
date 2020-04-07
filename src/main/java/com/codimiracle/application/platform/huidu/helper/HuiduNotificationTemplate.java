package com.codimiracle.application.platform.huidu.helper;

import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.po.Notification;
import com.codimiracle.application.platform.huidu.entity.po.Subscribe;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.enumeration.NotificationType;
import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;
import com.codimiracle.application.platform.huidu.service.BookService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class HuiduNotificationTemplate implements NotificationTemplate {

    @Resource
    private BookService bookService;

    @Override
    public Notification generateBy(SubscribeType type, String receiverId, String targetId) {
        if (type == SubscribeType.BookUpdated) {
            BookVO bookVO = bookService.findByIdIntegrally(targetId);
            Notification notification = new Notification();
            notification.setReceiverId(receiverId);
            notification.setType(NotificationType.Subscribe);
            String name = Objects.isNull(bookVO.getTitle()) ? bookVO.getMetadata().getName() : bookVO.getTitle();
            notification.setMessage(String.format("您已经订阅 %s", name));
            return notification;
        }
        return null;
    }

    @Override
    public Notification generateCancelBy(Subscribe subscribe) {
        if (subscribe.getType() == SubscribeType.BookUpdated) {
            BookVO bookVO = bookService.findByIdIntegrally(subscribe.getBookId());
            Notification notification = new Notification();
            notification.setReceiverId(subscribe.getSubscriberId());
            notification.setType(NotificationType.Subscribe);
            String name = Objects.isNull(bookVO.getTitle()) ? bookVO.getMetadata().getName() : bookVO.getTitle();
            notification.setMessage(String.format("你已取消订阅 %s，后续将不会收到通知！", name));
        }
        return null;
    }

    @Override
    public Notification generateNormal(String receiverId, String message) {
        Notification notification = new Notification();
        notification.setReceiverId(receiverId);
        notification.setMessage(message);
        notification.setType(NotificationType.Message);
        return notification;
    }
}
