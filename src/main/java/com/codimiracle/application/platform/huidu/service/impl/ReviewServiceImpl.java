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
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.entity.vo.ArticleVO;
import com.codimiracle.application.platform.huidu.entity.vo.ReviewVO;
import com.codimiracle.application.platform.huidu.entity.vt.Review;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.service.ContentArticleService;
import com.codimiracle.application.platform.huidu.service.ContentReferenceService;
import com.codimiracle.application.platform.huidu.service.ContentService;
import com.codimiracle.application.platform.huidu.service.ReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl extends AbstractUnsupportedOperationServiece<String, Review> implements ReviewService {
    @Resource
    ContentService contentService;
    @Resource
    ContentArticleService contentArticleService;
    @Resource
    ContentReferenceService contentReferenceService;

    @Override
    public void save(Review entity) {
        Content content = new Content();
        BeanUtils.copyProperties(entity, content);
        contentService.save(content);
        BeanUtils.copyProperties(content, entity);
        ContentArticle article = new ContentArticle();
        BeanUtils.copyProperties(entity, article);
        article.setContentId(content.getId());
        contentArticleService.save(article);
        BeanUtils.copyProperties(article, entity);
        entity.getReferenceList().forEach((r) -> {
            r.setContentId(content.getId());
            contentReferenceService.save(r);
        });
    }

    @Override
    public void save(List<Review> entities) {
        entities.forEach(this::save);
    }

    @Override
    public void update(Review entity) {
        Content content = new Content();
        BeanUtils.copyProperties(entity, content);
        contentService.update(content);
        ContentArticle article = new ContentArticle();
        BeanUtils.copyProperties(entity, article);
        contentArticleService.update(article);
        entity.getReferenceList().forEach((r) -> {
            r.setContentId(content.getId());
            contentReferenceService.save(r);
        });
    }

    @Override
    public Review findById(String id) {
        Review review = new Review();
        Content content = contentService.findById(id);
        ContentArticle article = contentArticleService.findById(id);
        List<ContentReference> references = contentReferenceService.findByContentId(id);
        BeanUtils.copyProperties(content, review);
        BeanUtils.copyProperties(article, review);
        review.setReferenceList(references);
        return review;
    }

    @Override
    public void update(Review review, List<ContentReference> oldRefers) {
        oldRefers.stream().map(ContentReference::getId).reduce((a, b) -> a + ',' + b).ifPresent(contentReferenceService::deleteByIds);
        update(review);
    }

    @Override
    public int deleteByIdLogically(String id) {
        return contentService.deleteByIdLogically(id);
    }

    @Override
    public ReviewVO findByIdIntegrally(String id) {
        ReviewVO reviewVO = new ReviewVO();
        ArticleVO articleVO = contentArticleService.findByIdIntegrally(ContentType.Review, id);
        BeanUtils.copyProperties(articleVO, reviewVO);
        reviewVO.setReferences(contentReferenceService.findByContentIdIntegrally(id));
        return reviewVO;
    }

    @Override
    public List<ReviewVO> findAllIntegrally() {
        List<ReviewVO> reviewVOList = new ArrayList<>();
        List<ArticleVO> articleVOList = contentArticleService.findAllIntegrally();
        for (ArticleVO articleVO : articleVOList) {
            ReviewVO reviewVO = new ReviewVO();
            BeanUtils.copyProperties(articleVO, reviewVO);
            reviewVO.setReferences(contentReferenceService.findByContentIdIntegrally(reviewVO.getContentId()));
            reviewVOList.add(reviewVO);
        }
        return reviewVOList;
    }

    @Override
    public PageSlice<ReviewVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        List<ReviewVO> reviewVOList = new ArrayList<>();
        PageSlice<ArticleVO> articleVOList = contentArticleService.findAllIntegrally(ContentType.Review, filter, sorter, page);
        for (ArticleVO articleVO : articleVOList.getList()) {
            ReviewVO reviewVO = new ReviewVO();
            BeanUtils.copyProperties(articleVO, reviewVO);
            reviewVO.setReferences(contentReferenceService.findByContentIdIntegrally(reviewVO.getContentId()));
            reviewVOList.add(reviewVO);
        }
        PageSlice<ReviewVO> slice = new PageSlice<>();
        slice.setList(reviewVOList);
        slice.setPage(articleVOList.getPage());
        slice.setLimit(articleVOList.getLimit());
        slice.setTotal(articleVOList.getTotal());
        return slice;
    }
}
