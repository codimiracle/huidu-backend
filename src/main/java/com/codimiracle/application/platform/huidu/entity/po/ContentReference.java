package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Table(name = "content_reference")
public class ContentReference {
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
     * 获取引用类型（content: 内容, book: 图书）
     *
     * @return type - 引用类型（content: 内容, book: 图书）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置引用类型（content: 内容, book: 图书）
     *
     * @param type 引用类型（content: 内容, book: 图书）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取图书id 或者 内容id
     *
     * @return ref_id - 图书id 或者 内容id
     */
    public Integer getRefId() {
        return refId;
    }

    /**
     * 设置图书id 或者 内容id
     *
     * @param refId 图书id 或者 内容id
     */
    public void setRefId(Integer refId) {
        this.refId = refId;
    }
}