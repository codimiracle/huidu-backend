package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 操作类型（modify: 修改, create: 创建, delete: 删除）
     */
    private String type;

    /**
     * 描述文字
     */
    private String describe;

    /**
     * 操作目标类型
     */
    @Column(name = "target_type")
    private String targetType;

    /**
     * 操作目标id
     */
    @Column(name = "target_id")
    private Integer targetId;

    /**
     * 操作等级（admin: 管理员, user: 用户, anonymous: 匿名用户）
     */
    private String level;

    /**
     * 操作者id
     */
    @Column(name = "operator_id")
    private Integer operatorId;

    /**
     * 操作时间
     */
    @Column(name = "create_time")
    private Date createTime;
}