package com.codimiracle.application.platform.huidu.service.impl;/*
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

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.po.ContentArticle;
import com.codimiracle.application.platform.huidu.entity.vo.ArticleVO;
import com.codimiracle.application.platform.huidu.entity.vo.CommentVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.entity.vt.Comment;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl extends AbstractUnsupportedOperationServiece<String, Comment> implements CommentService {
    @Resource
    private ContentService contentService;
    @Resource
    private ContentArticleService contentArticleService;
    @Resource
    private ContentMentionService contentMentionService;
    @Resource
    private ContentLikesService contentLikesService;

    @Override
    public void updateStatistics(Comment comment) {
        Content targetContent = contentService.findById(comment.getTargetContentId());
        if (comment.getStatus() == ContentStatus.Publish) {
            contentService.commentsIncrement(comment.getTargetContentId());
            // 仅对图书进行累积评分
            if (targetContent.getType() == ContentType.Book) {
                //首次评论
                if (targetContent.getComments() == 0) {
                    contentService.rateIncrementBy(comment.getTargetContentId(), comment.getRate());
                } else {
                    contentService.rateIncrementBy(comment.getTargetContentId(), comment.getRate() * 2);
                }
            }
        }
    }

    private void saveContentPart(Comment comment) {
        Content content = Comment.extractContent(comment);
        contentService.save(content);
        // 把主键数据写入到 Comment 中
        BeanUtils.copyProperties(content, comment);
    }

    private void saveArticlePart(Comment comment) {
        ContentArticle article = Comment.extractArticle(comment);
        article.setContentId(comment.getId());
        contentArticleService.save(article);
        BeanUtils.copyProperties(article, comment);
    }

    private void saveMentionPart(Comment comment) {
        Optional.ofNullable(comment.getMentions()).ifPresent((mentions) -> {
            mentions.forEach((m) -> {
                m.setContentId(comment.getId());
                contentMentionService.save(m);
            });
        });
    }


    @Override
    public void save(Comment entity) {
        saveContentPart(entity);
        saveArticlePart(entity);
        updateStatistics(entity);
        saveMentionPart(entity);
    }

    @Override
    public void save(List<Comment> entities) {
        entities.forEach(this::save);
    }

    @Override
    public void update(Comment entity) {
        Content content = Comment.extractContent(entity);
        content.setUpdateTime(new Date());
        ContentArticle article = Comment.extractArticle(entity);
        contentService.update(content);
        contentArticleService.update(article);
    }

    @Override
    public CommentVO findByIdIntegrally(String id) {
        ArticleVO articleVO = contentArticleService.findByIdIntegrally(ContentType.Comment, id);
        if (Objects.nonNull(articleVO)) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(articleVO, commentVO);
            List<UserProtectedVO> mentions = contentMentionService.findByContentId(id);
            commentVO.setMentions(mentions);
            return commentVO;
        }
        return null;
    }

    private PageSlice<CommentVO> mutate(PageSlice<ArticleVO> slice) {
        List<CommentVO> list = slice.getList().stream().map((articleVO -> {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(articleVO, commentVO);
            commentVO.setMentions(contentMentionService.findByContentId(articleVO.getContentId()));
            return commentVO;
        })).collect(Collectors.toList());
        PageSlice<CommentVO> commentPageSlice = new PageSlice<>();
        commentPageSlice.setList(list);
        commentPageSlice.setTotal(slice.getTotal());
        commentPageSlice.setLimit(slice.getLimit());
        commentPageSlice.setPage(slice.getPage());
        return commentPageSlice;
    }

    @Override
    public PageSlice<CommentVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ArticleVO> slice = contentArticleService.findAllIntegrally(ContentType.Comment, filter, sorter, page);
        return mutate(slice);
    }

    @Override
    public void deleteByIdLogically(String id) {
        contentService.deleteByIdLogically(id);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        contentService.deleteByIdsLogically(ids);
    }

    @Override
    public PageSlice<CommentVO> findHotIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ArticleVO> slice = contentArticleService.findHotIntegrally(ContentType.Comment, filter, sorter, page);
        return mutate(slice);
    }

    @Override
    public PageSlice<CommentVO> findAllIntegrallyWithTargetContent(Filter filter, Sorter sorter, Page page) {
        return mutate(contentArticleService.findAllIntegrallyWithTargetContent(ContentType.Comment, filter, sorter, page));
    }
}
