package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.ContentDTO;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Data
public class Content {
    /**
     * 内容id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 内容类型（Topic: 话题, Comment: 评论, Review: 点评, Book: 图书）
     */
    private ContentType type;

    /**
     * 拥有者id
     */
    @Column(name = "owner_id")
    private String ownerId;

    /**
     * 评论数
     */
    private Integer comments;

    /**
     * 评分
     */
    private Float rate;

    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 转发数
     */
    private Long reposts;

    /**
     * 删除标识
     */
    private Boolean deleted;

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

    public static Content from(ContentDTO contentDTO) {
        if (Objects.isNull(contentDTO)) {
            return null;
        }
        Content content = new Content();
        BeanUtils.copyProperties(contentDTO, content);
        return content;
    }
}