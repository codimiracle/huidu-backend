package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user_figure")
public class FigureTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tag_id")
    private String tagId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "deleted")
    private boolean deleted;

    @Transient
    private Integer score;

    @Transient
    private Tag tag;
}