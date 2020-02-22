package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 阅读用户
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 章节id
     */
    @Column(name = "episode_id")
    private Integer episodeId;

    /**
     * 阅读时间
     */
    @Column(name = "read_time")
    private Date readTime;

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
     * 获取阅读用户
     *
     * @return user_id - 阅读用户
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置阅读用户
     *
     * @param userId 阅读用户
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取章节id
     *
     * @return episode_id - 章节id
     */
    public Integer getEpisodeId() {
        return episodeId;
    }

    /**
     * 设置章节id
     *
     * @param episodeId 章节id
     */
    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    /**
     * 获取阅读时间
     *
     * @return read_time - 阅读时间
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * 设置阅读时间
     *
     * @param readTime 阅读时间
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }
}