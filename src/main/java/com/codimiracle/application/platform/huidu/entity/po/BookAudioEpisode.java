package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.BookAudioEpisodeDTO;
import com.codimiracle.application.platform.huidu.enumeration.BookAudioEpisodeStatus;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Table(name = "book_audio_episode")
public class BookAudioEpisode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 章节标题
     */
    private String title;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "content_id")
    private String contentId;

    /**
     * 文字章节id
     */
    @Column(name = "episode_id")
    private String episodeId;

    @Column(name = "media_number")
    private Integer mediaNumber;
    /**
     * 持续时间
     */
    private Long duration;

    /**
     * 章节状态（Draft: 草稿, Examining: 审批, rejected: 驳回, Publish: 发布）
     */
    private BookAudioEpisodeStatus status;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * 音频流url
     */
    @Column(name = "stream_url")
    private String streamUrl;

    /**
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public static BookAudioEpisode from(BookAudioEpisodeDTO bookAudioEpisodeDTO) {
        if (Objects.isNull(bookAudioEpisodeDTO)) {
            return null;
        }
        BookAudioEpisode bookAudioEpisode = new BookAudioEpisode();
        BeanUtils.copyProperties(bookAudioEpisodeDTO, bookAudioEpisode);
        bookAudioEpisode.setStatus(BookAudioEpisodeStatus.valueOfCode(bookAudioEpisodeDTO.getStatus()));
        return bookAudioEpisode;
    }
}