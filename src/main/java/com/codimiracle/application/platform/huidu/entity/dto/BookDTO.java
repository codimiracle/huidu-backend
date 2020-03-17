package com.codimiracle.application.platform.huidu.entity.dto;/*
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

@Data
public class BookDTO {
    /**
     * 有声书标题
     */
    private String title;

    /**
     * 有声书描述
     */
    private String description;

    /**
     * 有声书封面
     */
    private String cover;

    /**
     * 讲述人
     */
    private String teller;

    private String metadataId;
    /**
     * 书籍元数据id
     */
    private BookMetadataDTO metadata;

    /**
     * 类别id
     */
    private String categoryId;
    private CategoryDTO category;

    /**
     * 发布年份
     */
    private String publishYear;

    /**
     * 图书类型（electronic-book: 电子书，audio-book: 有声书，paper-book = 纸质书）
     */
    private String type;

    /**
     * 图书状态（examining：审批, serializing: 连载中, paused: 停更, ended: 完结）
     */
    private String status;

    private String commodityId;
    private CommodityDTO commodity;

    private String[] tags;

}
