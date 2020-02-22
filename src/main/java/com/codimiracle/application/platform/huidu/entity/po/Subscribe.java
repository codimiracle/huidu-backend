package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Subscribe {
    /**
     * 订阅id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订阅类型（electronic-book: 电子书更新, audio-book: 有声书更新）
     */
    private String type;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 最后更新章节
     */
    @Column(name = "last_update_episode_id")
    private Integer lastUpdateEpisodeId;

    /**
     * 获取订阅id
     *
     * @return id - 订阅id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置订阅id
     *
     * @param id 订阅id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订阅类型（electronic-book: 电子书更新, audio-book: 有声书更新）
     *
     * @return type - 订阅类型（electronic-book: 电子书更新, audio-book: 有声书更新）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置订阅类型（electronic-book: 电子书更新, audio-book: 有声书更新）
     *
     * @param type 订阅类型（electronic-book: 电子书更新, audio-book: 有声书更新）
     */
    public void setType(String type) {
        this.type = type;
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
     * 获取最后更新章节
     *
     * @return last_update_episode_id - 最后更新章节
     */
    public Integer getLastUpdateEpisodeId() {
        return lastUpdateEpisodeId;
    }

    /**
     * 设置最后更新章节
     *
     * @param lastUpdateEpisodeId 最后更新章节
     */
    public void setLastUpdateEpisodeId(Integer lastUpdateEpisodeId) {
        this.lastUpdateEpisodeId = lastUpdateEpisodeId;
    }
}