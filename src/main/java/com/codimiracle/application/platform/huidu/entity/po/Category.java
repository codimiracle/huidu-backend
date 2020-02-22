package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    /**
     * 获取类别id
     *
     * @return id - 类别id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置类别id
     *
     * @param id 类别id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类别名称
     *
     * @return name - 类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类别名称
     *
     * @param name 类别名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类别描述
     *
     * @return description - 类别描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置类别描述
     *
     * @param description 类别描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取额外数据
     *
     * @return extra - 额外数据
     */
    public String getExtra() {
        return extra;
    }

    /**
     * 设置额外数据
     *
     * @param extra 额外数据
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * @return deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}