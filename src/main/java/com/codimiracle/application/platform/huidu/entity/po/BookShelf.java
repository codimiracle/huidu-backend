package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.*;

@Table(name = "book_shelf")
public class BookShelf {
    /**
     * 书架id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 书架名称
     */
    private String name;

    /**
     * 书架所有者id
     */
    @Column(name = "owner_id")
    private Integer ownerId;

    /**
     * 获取书架id
     *
     * @return id - 书架id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置书架id
     *
     * @param id 书架id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取书架名称
     *
     * @return name - 书架名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置书架名称
     *
     * @param name 书架名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取书架所有者id
     *
     * @return owner_id - 书架所有者id
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * 设置书架所有者id
     *
     * @param ownerId 书架所有者id
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}