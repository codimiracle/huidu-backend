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

import com.codimiracle.application.platform.huidu.entity.dto.TopicDTO;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.web.middleware.content.pojo.po.ContentReference;
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
public class Topic {
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

    public static Topic from(TopicDTO topicDTO) {
        if (Objects.isNull(topicDTO)) {
            return null;
        }
        Topic topic = new Topic();
        topic.setType(ContentType.Topic);
        topic.setTitle(topicDTO.getTitle());
        topic.setContentSource(topicDTO.getContent().getSource());
        if (Objects.nonNull(topicDTO.getStatus())) {
            topic.setStatus(ContentStatus.valueOfCode(topicDTO.getStatus()));
        } else {
            topic.setStatus(ContentStatus.Draft);
        }
        topic.setContentType(topicDTO.getContent().getType());
        topic.setWords(topicDTO.getWords());
        topic.setReferenceList(Arrays.stream(topicDTO.getReferences()).map(bookId -> {
            ContentReference reference = new ContentReference();
            reference.setReferenceTargetId(bookId);
            reference.setReferenceTargetType(ContentType.Book.toString());
            return reference;
        }).collect(Collectors.toList()));
        return topic;
    }
}
