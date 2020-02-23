package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "book_shelf_cell")
public class BookShelfCell {
    /**
     * 书架书籍id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 书架id
     */
    @Column(name = "shelf_id")
    private Integer shelfId;

    /**
     * 读书进度
     */
    private Float progress;

    /**
     * 获取书架书籍id
     *
     * @return id - 书架书籍id
     */
    /**
     * 设置书架书籍id
     *
     * @param id 书架书籍id
     */
    /**
     * 获取图书id
     *
     * @return book_id - 图书id
     */
    /**
     * 设置图书id
     *
     * @param bookId 图书id
     */
    /**
     * 获取书架id
     *
     * @return shelf_id - 书架id
     */
    /**
     * 设置书架id
     *
     * @param shelfId 书架id
     */
    /**
     * 获取读书进度
     *
     * @return progress - 读书进度
     */
    /**
     * 设置读书进度
     *
     * @param progress 读书进度
     */
}