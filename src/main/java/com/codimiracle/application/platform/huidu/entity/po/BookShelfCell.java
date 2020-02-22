package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.*;

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
    public Integer getId() {
        return id;
    }

    /**
     * 设置书架书籍id
     *
     * @param id 书架书籍id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取图书id
     *
     * @return book_id - 图书id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * 设置图书id
     *
     * @param bookId 图书id
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取书架id
     *
     * @return shelf_id - 书架id
     */
    public Integer getShelfId() {
        return shelfId;
    }

    /**
     * 设置书架id
     *
     * @param shelfId 书架id
     */
    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
    }

    /**
     * 获取读书进度
     *
     * @return progress - 读书进度
     */
    public Float getProgress() {
        return progress;
    }

    /**
     * 设置读书进度
     *
     * @param progress 读书进度
     */
    public void setProgress(Float progress) {
        this.progress = progress;
    }
}