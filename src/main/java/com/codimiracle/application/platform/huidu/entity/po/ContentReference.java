package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "content_reference")
public class ContentReference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 引用类型（content: 内容, book: 图书）
     */
    private String type;

    /**
     * 图书id 或者 内容id
     */
    @Column(name = "ref_id")
    private Integer refId;

    @Column(name = "content_id")
    private Integer contentId;
}