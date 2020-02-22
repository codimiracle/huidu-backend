package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "passing_point")
public class PassingPoint {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 途经内容
     */
    private String name;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 途经状态（doing: 正在进行, done: 完成）
     */
    private String status;

    @Column(name = "logistics_infomation_id")
    private Integer logisticsInfomationId;

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
     * 获取途经内容
     *
     * @return name - 途经内容
     */
    public String getName() {
        return name;
    }

    /**
     * 设置途经内容
     *
     * @param name 途经内容
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * 获取途经状态（doing: 正在进行, done: 完成）
     *
     * @return status - 途经状态（doing: 正在进行, done: 完成）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置途经状态（doing: 正在进行, done: 完成）
     *
     * @param status 途经状态（doing: 正在进行, done: 完成）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return logistics_infomation_id
     */
    public Integer getLogisticsInfomationId() {
        return logisticsInfomationId;
    }

    /**
     * @param logisticsInfomationId
     */
    public void setLogisticsInfomationId(Integer logisticsInfomationId) {
        this.logisticsInfomationId = logisticsInfomationId;
    }
}