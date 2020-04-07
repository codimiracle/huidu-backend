package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
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
    private Boolean deleted;

}