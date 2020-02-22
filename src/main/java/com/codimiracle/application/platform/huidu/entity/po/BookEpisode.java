package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "book_episode")
public class BookEpisode {
    /**
     * 章节id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 章节标题
     */
    private String title;

    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * 内容源类型（html: HTML代码）
     */
    @Column(name = "content_type")
    private String contentType;

    /**
     * 内容源
     */
    @Column(name = "content_source")
    private String contentSource;

    /**
     * 字数
     */
    private Integer words;

    /**
     * 章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     */
    private String status;

    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 删除标识
     */
    private Boolean deleted;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取章节id
     *
     * @return id - 章节id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置章节id
     *
     * @param id 章节id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取章节标题
     *
     * @return title - 章节标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置章节标题
     *
     * @param title 章节标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return commodity_id
     */
    public Integer getCommodityId() {
        return commodityId;
    }

    /**
     * @param commodityId
     */
    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    /**
     * 获取内容源类型（html: HTML代码）
     *
     * @return content_type - 内容源类型（html: HTML代码）
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 设置内容源类型（html: HTML代码）
     *
     * @param contentType 内容源类型（html: HTML代码）
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
     * 获取字数
     *
     * @return words - 字数
     */
    public Integer getWords() {
        return words;
    }

    /**
     * 设置字数
     *
     * @param words 字数
     */
    public void setWords(Integer words) {
        this.words = words;
    }

    /**
     * 获取章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     *
     * @return status - 章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     *
     * @param status 章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return book_id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * @param bookId
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取删除标识
     *
     * @return deleted - 删除标识
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识
     *
     * @param deleted 删除标识
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}