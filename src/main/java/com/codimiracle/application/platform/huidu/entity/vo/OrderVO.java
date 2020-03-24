package com.codimiracle.application.platform.huidu.entity.vo;
/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import lombok.Data;
import org.joda.money.Money;

import java.util.Date;
import java.util.List;

@Data
public class OrderVO {

    private String orderNumber;

    /**
     * 订单所有者
     */
    private UserProtectedVO owner;

    /**
     * 订单类型（electronic-book: 购买电子书, audio-book: 有声书, paper-book: 纸质书, recharge: 充值）
     */
    private String type;

    /**
     * 支付类型（wechat: 微信, alipay: 支付宝, huidu: 用户账户）
     */
    private String payType;

    private AddressVO address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date deliverTime;

    /**
     * 交易完成时间
     */
    private Date closingTime;

    /**
     * 总金额
     */
    private Money totalMoney;

    /**
     * 运费
     */
    private Money shipmentMoney;

    /**
     * 订单状态（awaiting-payment: 待支付, awaiting-shipment: 待发货, awaiting-delivery: 待收货, awaiting-evaluation: 待评价, canceled: 订单取消, completed: 交易完成）
     */
    private String status;

    private String logisticsInformationId;

    /**
     * 物流信息
     */
    private LogisticsInformationVO logisticsInformation;

    private List<OrderDetailsVO> detailsList;
}
