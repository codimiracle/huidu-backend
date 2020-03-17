package com.codimiracle.application.platform.huidu.enumeration;/*
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

import java.util.Arrays;
import java.util.Objects;

public enum OrderStatus {
    /**
     * 待支付
     */
    AwaitingPayment("awaiting-payment"),
    /**
     * 订单发货
     */
    AwaitingShipment("awaiting-shipment"),
    /**
     * 待收货
     */
    AwaitingDelivery("awaiting-delivery"),
    /**
     * 待评价
     */
    AwaitingEvaluation("awaiting-evaluation"),
    /**
     * 订单取消
     */
    Canceled("canceled"),
    /**
     * 订单完成
     */
    Completed("completed");
    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public static OrderStatus valueOfCode(String code) {
        return Arrays.stream(OrderStatus.values()).filter((e) -> Objects.equals(e.status, code)).findFirst().orElseGet(() -> Enum.valueOf(OrderStatus.class, code));
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
