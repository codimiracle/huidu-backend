package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.BookEpisodeDTO;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Table(name = "book_episode")
public class BookEpisode {
    /**
     * 章节id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 章节标题
     */
    private String title;

    @Column(name = "commodity_id")
    private Integer commodityId;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "content_id")
    private String contentId;

    @Column(name = "episode_number")
    private Integer episodeNumber;

    /**
     * 内容源类型（html: HTML代码）
     */
    @Column(name = "content_type")
    private String contentType;

    /**
     * 内容源
     */
    @Column(name = "content_source")
    private String contentSource;

    /**
     * 字数
     */
    private Integer words;

    /**
     * 章节状态（Draft: 草稿, Examining: 审批, rejected: 驳回, Publish: 发布）
     */
    private ContentStatus status;

    @Column(name = "book_id")
    private String bookId;

    /**
     * 删除标识
     */
    private Boolean deleted;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public static BookEpisode from(BookEpisodeDTO bookEpisodeDTO) {
        if (Objects.isNull(bookEpisodeDTO)) {
            return null;
        }
        BookEpisode episode = new BookEpisode();
        BeanUtils.copyProperties(bookEpisodeDTO, episode);
        episode.setContentType(bookEpisodeDTO.getContent().getType());
        episode.setContentSource(bookEpisodeDTO.getContent().getSource());
        episode.setStatus(ContentStatus.valueOfCode(bookEpisodeDTO.getStatus()));
        return episode;
    }
}