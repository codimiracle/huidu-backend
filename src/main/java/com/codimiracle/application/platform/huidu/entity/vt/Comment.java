package com.codimiracle.application.platform.huidu.entity.vt;/*
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

import com.codimiracle.application.platform.huidu.entity.dto.CommentDTO;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.po.ContentArticle;
import com.codimiracle.application.platform.huidu.entity.po.ContentMention;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class Comment {
    /**
     * 内容id
     */
    private String id;

    /**
     * 内容类型（Topic: 话题, Comment: 评论, Review: 点评, Book: 图书）
     */
    private ContentType type;

    /**
     * 拥有者id
     */
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
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 内容id
     */
    private String contentId;

    /**
     * 内容对内容id
     */
    private String targetContentId;

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
    private String contentType;

    /**
     * 内容源
     */
    private String contentSource;

    /**
     * 内容状态
     */
    private ContentStatus status;

    /**
     * 阅读数
     */
    private Integer reads;

    private List<ContentMention> mentions;

    public static Content extractContent(Comment comment) {
        Content content = new Content();
        BeanUtils.copyProperties(comment, content);
        return content;
    }

    public static ContentArticle extractArticle(Comment comment) {
        ContentArticle article = new ContentArticle();
        BeanUtils.copyProperties(comment, article);
        return article;
    }

    public static Comment from(CommentDTO commentDTO) {
        if (Objects.isNull(commentDTO)) {
            return null;
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        //设定好类型
        comment.setType(ContentType.Comment);
        //设定好相应的状态
        comment.setStatus(ContentStatus.valueOfCode(commentDTO.getStatus()));
        comment.setContentType(commentDTO.getContent().getType());
        comment.setContentSource(commentDTO.getContent().getSource());
        Optional.ofNullable(commentDTO.getMentions()).ifPresent((mentions) -> {
            List<ContentMention> list = mentions.stream().map((userId) -> {
                ContentMention mention = new ContentMention();
                mention.setMentionUserId(userId);
                return mention;
            }).collect(Collectors.toList());
            comment.setMentions(list);
        });
        return comment;
    }
}
