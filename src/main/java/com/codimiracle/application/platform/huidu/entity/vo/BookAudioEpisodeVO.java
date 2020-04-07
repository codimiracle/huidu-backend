package com.codimiracle.application.platform.huidu.entity.vo;/*
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

import java.util.Date;

@Data
public class BookAudioEpisodeVO {
    private String id;

    /**
     * 章节标题
     */
    private String title;

    /**
     * 文字章节id
     */
    private String episodeId;

    private Integer mediaNumber;
    /**
     * 持续时间
     */
    private String duration;

    /**
     * 章节状态（Draft: 草稿, Examining: 审批, rejected: 驳回, Publish: 发布）
     */
    private String status;

    private String bookId;

    private String commodityId;

    /**
     * 音频流url
     */
    private String streamUrl;

    /**
     * 下一章节id
     */
    private String next;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private BookVO book;
    private BookEpisodeVO episode;
    private CommodityVO commodity;
}
