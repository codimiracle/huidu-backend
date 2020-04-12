package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Table(name = "user_figure")
public class FigureTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "tag_id")
    private String tagId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "score")
    private BigDecimal score;

    @Transient
    private Tag tag;
}