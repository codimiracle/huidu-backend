package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.*;

@Table(name = "content_tags")
public class ContentTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "tag_id")
    private Integer tagId;

    /**
     * 删除标识
     */
    private Integer deleted;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return content_id
     */
    public Integer getContentId() {
        return contentId;
    }

    /**
     * @param contentId
     */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /**
     * @return tag_id
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * @param tagId
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    /**
     * 获取删除标识
     *
     * @return deleted - 删除标识
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识
     *
     * @param deleted 删除标识
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}