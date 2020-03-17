package com.codimiracle.application.platform.huidu.entity.dto;
/*
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

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommodityDTO {
    /**
     * 购买项名称
     */
    private String name;

    /**
     * 购买项类型
     */
    private String type;

    /**
     * 购买项图片
     */
    private String picture;

    /**
     * 购买项介绍
     */
    private String introduction;

    /**
     * 重量(g)
     */
    private Double weight;

    /**
     * 初始库存
     */
    private Integer stock;

    /**
     * 运费
     */
    private BigDecimal shipment;

    /**
     * 额外数据
     */
    private String extra;

    private BigDecimal prices;

    private String status;
}
