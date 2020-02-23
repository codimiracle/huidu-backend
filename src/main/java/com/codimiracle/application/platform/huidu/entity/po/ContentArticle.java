package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "content_article")
public class ContentArticle {
    /**
     * 内容id
     */
    @Id
    @Column(name = "content_id")
    private Integer contentId;

    /**
     * 内容对内容id
     */
    @Column(name = "target_content_id")
    private Integer targetContentId;

    /**
     * 标题
     */
    private String title;

    /**
     * 字数
     */
    private String words;

    /**
     * 内容源类型（html: HTML代码, plaintext: 纯文本）
     */
    @Column(name = "content_type")
    private String contentType;

    /**
     * 内容源
     */
    @Column(name = "content_source")
    private String contentSource;

    /**
     * 内容状态
     */
    private String status;

    /**
     * 阅读数
     */
    private String reads;
}