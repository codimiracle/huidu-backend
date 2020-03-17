package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.ReadingHistoryDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 阅读用户
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private String bookId;

    /**
     * 章节id
     */
    @Column(name = "episode_id")
    private String episodeId;

    /**
     * 章节id
     */
    @Column(name = "audio_episode_id")
    private String audioEpisodeId;

    /**
     * 阅读时间
     */
    @Column(name = "read_time")
    private Date readTime;

    private Float progress;

    public static History from(ReadingHistoryDTO readingHistoryDTO) {
        if (Objects.isNull(readingHistoryDTO)) {
            return null;
        }
        History history = new History();
        history.setBookId(readingHistoryDTO.getBookId());
        history.setEpisodeId(readingHistoryDTO.getEpisodeId());
        history.setAudioEpisodeId(readingHistoryDTO.getAudioEpisodeId());
        return history;
    }
}