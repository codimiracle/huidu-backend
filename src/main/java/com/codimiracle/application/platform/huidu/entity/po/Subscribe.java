package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
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
}