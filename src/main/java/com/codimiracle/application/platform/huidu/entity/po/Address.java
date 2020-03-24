package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.vo.AddressDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

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
    private String userId;

    /**
     * 默认标识
     */
    private boolean defaulted;
    /**
     * 删除标识
     */
    private Boolean deleted;

    public static Address from(AddressDTO addressDTO) {
        if (Objects.isNull(addressDTO)) {
            return null;
        }
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        address.setReceiverName(addressDTO.getReceiver().getName());
        address.setReceiverPhone(addressDTO.getReceiver().getPhone());
        return address;
    }
}