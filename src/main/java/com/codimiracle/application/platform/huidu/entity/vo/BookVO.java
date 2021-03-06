package com.codimiracle.application.platform.huidu.entity.vo;/*
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

import java.util.List;
import java.util.Optional;

@Data
public class BookVO extends ContentVO {
    private String id;
    private String title;
    private String cover;
    private String ownerId;
    private String description;
    private String teller;
    private String publishYear;
    private Integer episodes;
    private String status;
    private Integer money;
    private String commodityId;
    private CommodityVO commodity;
    private String metadataId;
    private BookMetadataVO metadata;
    private String categoryId;
    private CategoryVO category;
    private List<TagVO> tags;
    private Integer plays;
    private Integer reads;
    private ExaminationVO examination;
    private Float reviewRate;
    private boolean joinedCart;
    private boolean joinedShelf;

    public float getAvgRate() {
        return Optional.ofNullable(reviewRate).orElse(0.0f) + Optional.ofNullable(getRate()).orElse(0.0f) / 2;
    }
}
