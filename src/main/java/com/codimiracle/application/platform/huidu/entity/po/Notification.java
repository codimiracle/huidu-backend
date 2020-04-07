package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.enumeration.NotificationType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Notification {
    /**
     * 通知id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 发送者
     */
    @Column(name = "sender_id")
    private String senderId;

    /**
     * 接收者
     */
    @Column(name = "receiver_id")
    private String receiverId;

    /**
     * 通知类型（subscribe: 订阅通知, message: 消息）
     */
    private NotificationType type;

    /**
     * 通知内容
     */
    private String message;

    /**
     * 通知交互链接
     */
    private String link;

    /**
     * 已读标识（0: 未读, 1:已读）
     */
    @Column(name = "`read`")
    private boolean read;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}