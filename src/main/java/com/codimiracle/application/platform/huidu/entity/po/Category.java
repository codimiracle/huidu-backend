package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Category {
    /**
     * 类别id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别描述
     */
    private String description;

    /**
     * 额外数据
     */
    private String extra;

    private Boolean deleted;
}