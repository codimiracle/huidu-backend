package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
     * 获取操作类型（modify: 修改, create: 创建, delete: 删除）
     *
     * @return type - 操作类型（modify: 修改, create: 创建, delete: 删除）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置操作类型（modify: 修改, create: 创建, delete: 删除）
     *
     * @param type 操作类型（modify: 修改, create: 创建, delete: 删除）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取描述文字
     *
     * @return describe - 描述文字
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置描述文字
     *
     * @param describe 描述文字
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取操作目标类型
     *
     * @return target_type - 操作目标类型
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * 设置操作目标类型
     *
     * @param targetType 操作目标类型
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    /**
     * 获取操作目标id
     *
     * @return target_id - 操作目标id
     */
    public Integer getTargetId() {
        return targetId;
    }

    /**
     * 设置操作目标id
     *
     * @param targetId 操作目标id
     */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    /**
     * 获取操作等级（admin: 管理员, user: 用户, anonymous: 匿名用户）
     *
     * @return level - 操作等级（admin: 管理员, user: 用户, anonymous: 匿名用户）
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置操作等级（admin: 管理员, user: 用户, anonymous: 匿名用户）
     *
     * @param level 操作等级（admin: 管理员, user: 用户, anonymous: 匿名用户）
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取操作者id
     *
     * @return operator_id - 操作者id
     */
    public Integer getOperatorId() {
        return operatorId;
    }

    /**
     * 设置操作者id
     *
     * @param operatorId 操作者id
     */
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取操作时间
     *
     * @return create_time - 操作时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置操作时间
     *
     * @param createTime 操作时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}