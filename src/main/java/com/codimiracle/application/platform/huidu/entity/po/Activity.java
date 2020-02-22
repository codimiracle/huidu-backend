package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Activity {
    /**
     * 活动id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 活动横幅
     */
    private String banner;

    /**
     * 活动状态（activated: 已激活，deactivated: 未激活）
     */
    private String status;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private Integer bookId;

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
     * 获取活动id
     *
     * @return id - 活动id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置活动id
     *
     * @param id 活动id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取活动横幅
     *
     * @return banner - 活动横幅
     */
    public String getBanner() {
        return banner;
    }

    /**
     * 设置活动横幅
     *
     * @param banner 活动横幅
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    /**
     * 获取活动状态（activated: 已激活，deactivated: 未激活）
     *
     * @return status - 活动状态（activated: 已激活，deactivated: 未激活）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置活动状态（activated: 已激活，deactivated: 未激活）
     *
     * @param status 活动状态（activated: 已激活，deactivated: 未激活）
     */
    public void setStatus(String status) {
        this.status = status;
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