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

public enum PaymentType {
    /**
     * 使用荟读账号余额支付
     */
    Huidu("huidu", 0),
    /**
     * 使用支付宝支付
     */
    Alipay("aplipay", 1),
    /**
     * 使用微信支付
     */
    Wechat("wechat", 2);
    private final String type;
    private final int code;

    PaymentType(String type, int code) {
        this.type = type;
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return type;
    }

    public static PaymentType valueOfCode(String code) {
        return Arrays.stream(PaymentType.values()).filter((e) -> Objects.equals(e.type, code)).findFirst().orElseGet(() ->Enum.valueOf(PaymentType.class, code));
    }
}
