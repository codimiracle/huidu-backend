package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Tag {
    /**
     * 标签id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 类别id
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 获取标签id
     *
     * @return id - 标签id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置标签id
     *
     * @param id 标签id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标签名称
     *
     * @return name - 标签名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置标签名称
     *
     * @param name 标签名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类别id
     *
     * @return category_id - 类别id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置类别id
     *
     * @param categoryId 类别id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}