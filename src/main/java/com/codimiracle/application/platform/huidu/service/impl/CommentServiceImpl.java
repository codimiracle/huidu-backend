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
    public void save(Comment entity) {
        Content content = Comment.extractContent(entity);
        Content originalContent = contentService.findById(entity.getTargetContentId());
        if (entity.getStatus() == ContentStatus.Publish) {
            contentService.commentsIncrement(entity.getTargetContentId());
            //首次评论
            if (originalContent.getComments() > 0) {
                contentService.rateIncrementBy(entity.getTargetContentId(), entity.getRate());
            } else {
                contentService.rateIncrementBy(entity.getTargetContentId(), entity.getRate() * 2);
            }
        }
        contentService.save(content);
        BeanUtils.copyProperties(content, entity);
        ContentArticle article = Comment.extractArticle(entity);
        article.setContentId(content.getId());
        contentArticleService.save(article);
        BeanUtils.copyProperties(article, entity);
        Optional.ofNullable(entity.getMentions()).ifPresent((mentions) -> {
            mentions.forEach((m) -> {
                m.setContentId(content.getId());
                contentMentionService.save(m);
            });
        });
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

    private PageSlice<CommentVO> paddingAssociation(PageSlice<ArticleVO> slice, String likerId) {
        List<CommentVO> list = slice.getList().stream().map((articleVO -> {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(articleVO, commentVO);
            commentVO.setMentions(contentMentionService.findByContentId(articleVO.getContentId()));
            if (Objects.nonNull(likerId)) {
                commentVO.setLiked(contentLikesService.isLiked(likerId, commentVO.getContentId()));
            } else {
                commentVO.setLiked(false);
            }
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
        return paddingAssociation(slice, null);
    }

    @Override
    public PageSlice<CommentVO> findAllIntegrally(String likerId, Filter filter, Sorter sorter, Page page) {
        PageSlice<ArticleVO> slice = contentArticleService.findAllIntegrally(ContentType.Comment, filter, sorter, page);
        return paddingAssociation(slice, likerId);
    }

    @Override
    public void deleteByIdLogically(String id) {
        contentService.deleteByIdLogically(id);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        contentService.deleteByIdsLogically(ids);
    }
}
