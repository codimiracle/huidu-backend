package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "content_likes")
public class ContentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 点赞用户
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 点赞内容
     */
    @Column(name = "content_id")
    private String contentId;

    /**
     * 点赞（1：已点赞，0：取消点赞）
     */
    private boolean liked;

}