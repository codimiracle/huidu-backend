package com.codimiracle.application.platform.huidu.helper;/*
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

import com.codimiracle.application.platform.huidu.contract.ServiceException;
import com.codimiracle.application.platform.huidu.enumeration.PaymentType;
import com.codimiracle.application.platform.huidu.service.UserAccountService;
import org.joda.money.Money;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HuiduPaymentRefunder implements PaymentRefunder {

    @Resource
    private UserAccountService userAccountService;

    @Override
    public void refund(PaymentType type, Money charge, String payerId) {
        if (type != PaymentType.Huidu) {
            throw new ServiceException("暂不支持该支付方式的退款！");
        }
        userAccountService.refund(charge, payerId);
    }
}
