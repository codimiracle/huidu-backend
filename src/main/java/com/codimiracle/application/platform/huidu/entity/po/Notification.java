package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Notification {
    /**
     * 通知id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 发送者
     */
    @Column(name = "sender_id")
    private Integer senderId;

    /**
     * 接收者
     */
    @Column(name = "receiver_id")
    private Integer receiverId;

    /**
     * 通知类型（subscribe: 订阅通知, message: 消息）
     */
    private String type;

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
    private Boolean read;

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

    /**
     * 获取通知id
     *
     * @return id - 通知id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置通知id
     *
     * @param id 通知id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取发送者
     *
     * @return sender_id - 发送者
     */
    public Integer getSenderId() {
        return senderId;
    }

    /**
     * 设置发送者
     *
     * @param senderId 发送者
     */
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    /**
     * 获取接收者
     *
     * @return receiver_id - 接收者
     */
    public Integer getReceiverId() {
        return receiverId;
    }

    /**
     * 设置接收者
     *
     * @param receiverId 接收者
     */
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 获取通知类型（subscribe: 订阅通知, message: 消息）
     *
     * @return type - 通知类型（subscribe: 订阅通知, message: 消息）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置通知类型（subscribe: 订阅通知, message: 消息）
     *
     * @param type 通知类型（subscribe: 订阅通知, message: 消息）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取通知内容
     *
     * @return message - 通知内容
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置通知内容
     *
     * @param message 通知内容
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取通知交互链接
     *
     * @return link - 通知交互链接
     */
    public String getLink() {
        return link;
    }

    /**
     * 设置通知交互链接
     *
     * @param link 通知交互链接
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 获取已读标识（0: 未读, 1:已读）
     *
     * @return read - 已读标识（0: 未读, 1:已读）
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * 设置已读标识（0: 未读, 1:已读）
     *
     * @param read 已读标识（0: 未读, 1:已读）
     */
    public void setRead(Boolean read) {
        this.read = read;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}