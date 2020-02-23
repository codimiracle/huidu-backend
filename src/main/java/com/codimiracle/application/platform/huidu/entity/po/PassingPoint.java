package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;

@Data
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
}