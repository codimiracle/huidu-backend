package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Content {
    /**
     * 内容id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 内容类型（topic: 话题, comment: 评论, review: 点评, book: 图书）
     */
    private String type;

    /**
     * 拥有者id
     */
    @Column(name = "owner_id")
    private Integer ownerId;

    /**
     * 评论数
     */
    private Integer comments;

    /**
     * 评分
     */
    private Float rate;

    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 转发数
     */
    private Long reposts;

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
     * 获取内容id
     *
     * @return id - 内容id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置内容id
     *
     * @param id 内容id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取内容类型（topic: 话题, comment: 评论, review: 点评, book: 图书）
     *
     * @return type - 内容类型（topic: 话题, comment: 评论, review: 点评, book: 图书）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置内容类型（topic: 话题, comment: 评论, review: 点评, book: 图书）
     *
     * @param type 内容类型（topic: 话题, comment: 评论, review: 点评, book: 图书）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取拥有者id
     *
     * @return owner_id - 拥有者id
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * 设置拥有者id
     *
     * @param ownerId 拥有者id
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取评论数
     *
     * @return comments - 评论数
     */
    public Integer getComments() {
        return comments;
    }

    /**
     * 设置评论数
     *
     * @param comments 评论数
     */
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /**
     * 获取评分
     *
     * @return rate - 评分
     */
    public Float getRate() {
        return rate;
    }

    /**
     * 设置评分
     *
     * @param rate 评分
     */
    public void setRate(Float rate) {
        this.rate = rate;
    }

    /**
     * 获取点赞数
     *
     * @return likes - 点赞数
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * 设置点赞数
     *
     * @param likes 点赞数
     */
    public void setLikes(Long likes) {
        this.likes = likes;
    }

    /**
     * 获取转发数
     *
     * @return reposts - 转发数
     */
    public Long getReposts() {
        return reposts;
    }

    /**
     * 设置转发数
     *
     * @param reposts 转发数
     */
    public void setReposts(Long reposts) {
        this.reposts = reposts;
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