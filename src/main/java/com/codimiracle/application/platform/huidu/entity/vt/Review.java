package com.codimiracle.application.platform.huidu.entity.vt;/*
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

import com.codimiracle.application.platform.huidu.entity.dto.ReviewDTO;
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class Review {
    /**
     * 内容id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 内容类型（Topic: 话题, Comment: 评论, Review: 点评, Book: 图书）
     */
    private ContentType type;

    /**
     * 拥有者id
     */
    @Column(name = "owner_id")
    private String ownerId;

    /**
     * 评论数
     */
    private Integer comments;

    /**
     * 评分
     */
    private Float rate;

    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 转发数
     */
    private Long reposts;

    /**
     * 内容id
     */
    @Id
    @Column(name = "content_id")
    private String contentId;

    /**
     * 标题
     */
    private String title;

    /**
     * 字数
     */
    private Integer words;

    /**
     * 内容源类型（html: HTML代码, plaintext: 纯文本）
     */
    @Column(name = "content_type")
    private String contentType;

    /**
     * 内容源
     */
    @Column(name = "content_source")
    private String contentSource;

    /**
     * 内容状态
     */
    private ContentStatus status;

    /**
     * 阅读数
     */
    @Column(name = "`reads`")
    private Integer reads;

    /**
     * 删除标识
     */
    private Boolean deleted;

    private List<ContentReference> referenceList;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public static Review from(ReviewDTO reviewDTO) {
        if (Objects.isNull(reviewDTO)) {
            return null;
        }
        Review review = new Review();
        review.setRate(reviewDTO.getRate());
        review.setType(ContentType.Review);
        review.setTitle(reviewDTO.getTitle());
        review.setContentSource(reviewDTO.getContent().getSource());
        review.setStatus(ContentStatus.valueOfCode(reviewDTO.getStatus()));
        review.setContentType(reviewDTO.getContent().getType());
        review.setWords(reviewDTO.getWords());
        review.setReferenceList(Arrays.stream(reviewDTO.getReferences()).map(bookId -> {
            ContentReference reference = new ContentReference();
            reference.setRefId(bookId);
            reference.setType(ContentType.Book);
            return reference;
        }).limit(1).collect(Collectors.toList()));
        return review;
    }
}
