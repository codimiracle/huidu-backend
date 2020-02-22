package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
     * 获取地区
     *
     * @return region - 地区
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置地区
     *
     * @param region 地区
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取邮政编码
     *
     * @return postcode - 邮政编码
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 设置邮政编码
     *
     * @param postcode 邮政编码
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * 获取收件人
     *
     * @return receiver_name - 收件人
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 设置收件人
     *
     * @param receiverName 收件人
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * 获取联系电话
     *
     * @return receiver_phone - 联系电话
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * 设置联系电话
     *
     * @param receiverPhone 联系电话
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * 获取所属用户
     *
     * @return user_id - 所属用户
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置所属用户
     *
     * @param userId 所属用户
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
}