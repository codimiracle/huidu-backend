package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "book_tags")
public class BookTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private String bookId;

    /**
     * 标签id
     */
    @Column(name = "tag_id")
    private String tagId;

    /**
     * 删除标识
     */
    private boolean deleted;

    public static BookTags valueOf(Book model, Tag t) {
        BookTags bookTags = new BookTags();
        bookTags.setBookId(model.getId());
        bookTags.setTagId(t.getId());
        return bookTags;
    }

}