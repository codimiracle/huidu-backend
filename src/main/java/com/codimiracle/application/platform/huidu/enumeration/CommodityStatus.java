package com.codimiracle.application.platform.huidu.enumeration;/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, Publish, distribute, sublicense, and/or sell
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

public enum CommodityStatus {
    /**
     * 上架
     */
    PutOnSale("put-on-sale"),
    /**
     * 下架
     */
    PullOffShelves("pull-off-shelves"),
    /**
     * 现货
     */
    InStock("in-stock"),
    /**
     * 告罄
     */
    SoldOut("sold-out");

    private String status;

    CommodityStatus(String status) {
        this.status = status;
    }

    public static CommodityStatus valueOfCode(String code) {
        return Arrays.stream(CommodityStatus.values()).filter((e) -> Objects.equals(e.status, code)).findFirst().orElseGet(() -> Enum.valueOf(CommodityStatus.class, code));
    }

    @Override
    public String toString() {
        return this.status;
    }
}
