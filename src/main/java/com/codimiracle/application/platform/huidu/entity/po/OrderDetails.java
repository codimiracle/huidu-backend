package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.OrderDetailsDTO;
import com.codimiracle.application.platform.huidu.enumeration.CommodityType;
import com.codimiracle.application.platform.huidu.typehandler.MoneyTypeHandler;
import lombok.Data;
import org.joda.money.Money;
import org.springframework.beans.BeanUtils;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.*;
import java.util.Objects;

@Data
@Table(name = "order_details")
public class OrderDetails {
    /**
     * 订单详情id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "commodity_id")
    private String commodityId;


    /**
     * 购买项名称
     */
    @Column(name = "commodity_name")
    private String commodityName;

    /**
     * 购买项类型
     */
    @Column(name = "commodity_type")
    private CommodityType commodityType;

    /**
     * 购买项图片
     */
    @Column(name = "commodity_picture")
    private String commodityPicture;

    /**
     * 购买项介绍
     */
    @Column(name = "commodity_introduction")
    private String commodityIntroduction;

    /**
     * 购买项净重量(g)
     */
    @Column(name = "commodity_weight")
    private Double commodityWeight;

    /**
     * 购买项价格
     */
    @Column(name = "commodity_prices")
    @ColumnType(typeHandler = MoneyTypeHandler.class)
    private Money commodityPrices;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 小计
     */
    @ColumnType(typeHandler = MoneyTypeHandler.class)
    private Money prices;

    @Transient
    private String cartItemId;

    @Column(name = "order_number")
    private String orderNumber;

    public static OrderDetails from(OrderDetailsDTO orderDetailsDTO) {
        if (Objects.isNull(orderDetailsDTO)) {
            return null;
        }
        OrderDetails orderDetails = new OrderDetails();
        BeanUtils.copyProperties(orderDetailsDTO, orderDetails);
        return orderDetails;
    }

}