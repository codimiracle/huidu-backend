package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
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
}