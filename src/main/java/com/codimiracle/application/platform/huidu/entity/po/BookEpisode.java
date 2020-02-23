package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
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
    /**
     * 设置章节id
     *
     * @param id 章节id
     */
    /**
     * 获取章节标题
     *
     * @return title - 章节标题
     */
    /**
     * 设置章节标题
     *
     * @param title 章节标题
     */
    /**
     * @return commodity_id
     */
    /**
     * @param commodityId
     */
    /**
     * 获取内容源类型（html: HTML代码）
     *
     * @return content_type - 内容源类型（html: HTML代码）
     */
    /**
     * 设置内容源类型（html: HTML代码）
     *
     * @param contentType 内容源类型（html: HTML代码）
     */
    /**
     * 获取内容源
     *
     * @return content_source - 内容源
     */
    /**
     * 设置内容源
     *
     * @param contentSource 内容源
     */
    /**
     * 获取字数
     *
     * @return words - 字数
     */
    /**
     * 设置字数
     *
     * @param words 字数
     */
    /**
     * 获取章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     *
     * @return status - 章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     */
    /**
     * 设置章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     *
     * @param status 章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     */
    /**
     * @return book_id
     */
    /**
     * @param bookId
     */
    /**
     * 获取删除标识
     *
     * @return deleted - 删除标识
     */
    /**
     * 设置删除标识
     *
     * @param deleted 删除标识
     */
    /**
     * @return create_time
     */
    /**
     * @param createTime
     */
    /**
     * @return update_time
     */
    /**
     * @param updateTime
     */
}