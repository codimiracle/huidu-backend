package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "book_audio_episode")
public class BookAudioEpisode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 章节标题
     */
    private String title;

    /**
     * 文字章节id
     */
    @Column(name = "episode_id")
    private Integer episodeId;

    /**
     * 持续时间
     */
    private String duration;

    /**
     * 章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     */
    private String status;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * 音频流url
     */
    @Column(name = "stream_url")
    private String streamUrl;

    /**
     * 下一章节id
     */
    @Column(name = "next_episode_id")
    private Integer nextEpisodeId;

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
     * 获取章节标题
     *
     * @return title - 章节标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置章节标题
     *
     * @param title 章节标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取文字章节id
     *
     * @return episode_id - 文字章节id
     */
    public Integer getEpisodeId() {
        return episodeId;
    }

    /**
     * 设置文字章节id
     *
     * @param episodeId 文字章节id
     */
    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    /**
     * 获取持续时间
     *
     * @return duration - 持续时间
     */
    public String getDuration() {
        return duration;
    }

    /**
     * 设置持续时间
     *
     * @param duration 持续时间
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * 获取章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     *
     * @return status - 章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     *
     * @param status 章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return book_id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * @param bookId
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * @return commodity_id
     */
    public Integer getCommodityId() {
        return commodityId;
    }

    /**
     * @param commodityId
     */
    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    /**
     * 获取音频流url
     *
     * @return stream_url - 音频流url
     */
    public String getStreamUrl() {
        return streamUrl;
    }

    /**
     * 设置音频流url
     *
     * @param streamUrl 音频流url
     */
    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    /**
     * 获取下一章节id
     *
     * @return next_episode_id - 下一章节id
     */
    public Integer getNextEpisodeId() {
        return nextEpisodeId;
    }

    /**
     * 设置下一章节id
     *
     * @param nextEpisodeId 下一章节id
     */
    public void setNextEpisodeId(Integer nextEpisodeId) {
        this.nextEpisodeId = nextEpisodeId;
    }

    /**
     * 获取删除标识
     *
     * @return deleted - 删除标识
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识
     *
     * @param deleted 删除标识
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}