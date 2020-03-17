package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Subscribe {
    /**
     * 订阅id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 订阅类型（put-on-sales：图书上架通知，content-comment：内容评论通知，）
     */
    private String type;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "content_id")
    private String contentId;

    @Column(name = "commodity_id")
    private String commodityId;

    @Column(name = "subscriber_id")
    private String subscriberId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}