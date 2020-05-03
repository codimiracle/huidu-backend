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

import com.codimiracle.application.platform.huidu.entity.embedded.ContentSource;
import com.codimiracle.web.middleware.content.pojo.vo.ContentExaminationVO;
import lombok.Data;

import java.util.Date;

@Data
public class BookEpisodeVO {
    private String id;
    /**
     * 章节标题
     */
    private String title;

    private String commodityId;

    private String contentId;
    /**
     * 对应购买项
     */
    private CommodityVO commodity;

    private Integer episodeNumber;

    private ContentExaminationVO examination;

    private UserProtectedVO owner;

    private ContentSource content;

    /**
     * 字数
     */
    private Integer words;

    /**
     * 章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）
     */
    private String status;

    private String bookId;
    /**
     * 图书信息
     */
    private BookVO book;

    /**
     * 下一章章节 id
     */
    private String next;

    private Date createTime;
    private Date updateTime;
}
