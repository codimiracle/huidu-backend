package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 地区
     */
    private String region;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String postcode;

    /**
     * 收件人
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 联系电话
     */
    @Column(name = "receiver_phone")
    private String receiverPhone;

    /**
     * 所属用户
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 删除标识
     */
    private Boolean deleted;

    /**
     * @return id
     */
    /**
     * @param id
     */
    /**
     * 获取地区
     *
     * @return region - 地区
     */
    /**
     * 设置地区
     *
     * @param region 地区
     */
    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    /**
     * 获取邮政编码
     *
     * @return postcode - 邮政编码
     */
    /**
     * 设置邮政编码
     *
     * @param postcode 邮政编码
     */
    /**
     * 获取收件人
     *
     * @return receiver_name - 收件人
     */
    /**
     * 设置收件人
     *
     * @param receiverName 收件人
     */
    /**
     * 获取联系电话
     *
     * @return receiver_phone - 联系电话
     */
    /**
     * 设置联系电话
     *
     * @param receiverPhone 联系电话
     */
    /**
     * 获取所属用户
     *
     * @return user_id - 所属用户
     */
    /**
     * 设置所属用户
     *
     * @param userId 所属用户
     */
    /**
     * 获取删除标识
     *
     * @return deleted - 删除标识
     */
    /**
     * 设置删除标识
     *
     * @param deleted 删除标识
     */
}