package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.CartItemDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Table(name = "user_cart")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "commodity_id")
    private String commodityId;

    @Column(name = "user_id")
    private String userId;

    private Integer quantity;

    @Column(name = "join_time")
    private Date joinTime;

    /**
     * 已结账
     */
    private boolean settled;
    /**
     * 删除标识
     */
    private boolean deleted;

    public static CartItem from(CartItemDTO cartItemDTO) {
        if (Objects.isNull(cartItemDTO)) {
            return null;
        }
        CartItem cartItem = new CartItem();
        BeanUtils.copyProperties(cartItemDTO, cartItem);
        return cartItem;
    }
}