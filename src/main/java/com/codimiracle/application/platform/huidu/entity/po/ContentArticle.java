package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "content_article")
public class ContentArticle {
    /**
     * 内容id
     */
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

    /**
     * 获取内容id
     *
     * @return content_id - 内容id
     */
    public Integer getContentId() {
        return contentId;
    }

    /**
     * 设置内容id
     *
     * @param contentId 内容id
     */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /**
     * 获取内容对内容id
     *
     * @return target_content_id - 内容对内容id
     */
    public Integer getTargetContentId() {
        return targetContentId;
    }

    /**
     * 设置内容对内容id
     *
     * @param targetContentId 内容对内容id
     */
    public void setTargetContentId(Integer targetContentId) {
        this.targetContentId = targetContentId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取字数
     *
     * @return words - 字数
     */
    public String getWords() {
        return words;
    }

    /**
     * 设置字数
     *
     * @param words 字数
     */
    public void setWords(String words) {
        this.words = words;
    }

    /**
     * 获取内容源类型（html: HTML代码, plaintext: 纯文本）
     *
     * @return content_type - 内容源类型（html: HTML代码, plaintext: 纯文本）
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 设置内容源类型（html: HTML代码, plaintext: 纯文本）
     *
     * @param contentType 内容源类型（html: HTML代码, plaintext: 纯文本）
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 获取内容源
     *
     * @return content_source - 内容源
     */
    public String getContentSource() {
        return contentSource;
    }

    /**
     * 设置内容源
     *
     * @param contentSource 内容源
     */
    public void setContentSource(String contentSource) {
        this.contentSource = contentSource;
    }

    /**
     * 获取内容状态
     *
     * @return status - 内容状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置内容状态
     *
     * @param status 内容状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取阅读数
     *
     * @return reads - 阅读数
     */
    public String getReads() {
        return reads;
    }

    /**
     * 设置阅读数
     *
     * @param reads 阅读数
     */
    public void setReads(String reads) {
        this.reads = reads;
    }
}