package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.OrderringDTO;
import com.codimiracle.application.platform.huidu.entity.dto.RechargeDTO;
import com.codimiracle.application.platform.huidu.enumeration.OrderStatus;
import com.codimiracle.application.platform.huidu.enumeration.OrderType;
import com.codimiracle.application.platform.huidu.enumeration.PaymentType;
import com.codimiracle.application.platform.huidu.util.CodeUtil;
import com.codimiracle.application.platform.huidu.util.HuiduMoneyUtil;
import com.codimiracle.application.platform.huidu.util.OrderNumberUtil;
import lombok.Data;
import org.joda.money.Money;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Table(name = "`order`")
public class Order {
    @Id
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 订单所有者id
     */
    @Column(name = "owner_id")
    private String ownerId;

    /**
     * 订单类型（electronic-book: 购买电子书, audio-book: 有声书, paper-book: 纸质书, recharge: 充值）
     */
    private OrderType type;

    /**
     * 支付类型（wechat: 微信, alipay: 支付宝, huidu: 用户账户）
     */
    @Column(name = "pay_type")
    private PaymentType payType;

    /**
     * 收货地址id
     */
    @Column(name = "address_id")
    private String addressId;

    @Column(name = "address_region")
    private String addressRegion;

    @Column(name = "address_address")
    private String addressAddress;

    @Column(name = "address_receiver_name")
    private String addressReceiverName;

    @Column(name = "address_receiver_phone")
    private String addressReceiverPhone;

    @Column(name = "address_postcode")
    private String addressPostcode;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 发货时间
     */
    @Column(name = "deliver_time")
    private Date deliverTime;

    /**
     * 交易完成时间
     */
    @Column(name = "closing_time")
    private Date closingTime;

    /**
     * 总金额
     */
    @Column(name = "total_money")
    private Money totalMoney;

    /**
     * 运费
     */
    @Column(name = "shipment_money")
    private Money shipmentMoney;

    /**
     * 订单状态（awaiting-payment: 待支付, awaiting-shipment: 待发货, awaiting-delivery: 待收货, awaiting-evaluation: 待评价, canceled: 订单取消, completed: 交易完成）
     *
     * @see OrderStatus
     */
    private OrderStatus status;

    /**
     * 物流信息id
     */
    @Column(name = "logistics_information_id")
    private String logisticsInformationId;

    @Transient
    private List<OrderDetails> detailsList;

    public static Order form(OrderringDTO orderringDTO) {
        if (Objects.isNull(orderringDTO)) {
            return null;
        }
        Order order = new Order();
        BeanUtils.copyProperties(orderringDTO, order);
        List<OrderDetails> detailsList = Optional.ofNullable(orderringDTO.getItems()).map(orderDetailsDTOList -> orderDetailsDTOList.stream().map(OrderDetails::from).collect(Collectors.toList())).orElse(Collections.emptyList());
        order.setDetailsList(detailsList);
        order.setType(OrderType.valueOfCode(orderringDTO.getType()));
        //充值订单
        if (order.getType() == OrderType.Recharge) {
            order.setTotalMoney(HuiduMoneyUtil.huicoinMoney(BigDecimal.valueOf(orderringDTO.getCharge())));
            order.setShipmentMoney(HuiduMoneyUtil.huicoinMoney(BigDecimal.ZERO));
        }
        return order;
    }
}