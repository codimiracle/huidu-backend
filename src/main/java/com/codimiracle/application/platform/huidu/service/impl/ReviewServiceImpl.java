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

import com.codimiracle.application.platform.huidu.entity.vo.ReviewVO;
import com.codimiracle.application.platform.huidu.entity.vt.Review;
import com.codimiracle.application.platform.huidu.service.ReviewService;
import com.codimiracle.application.platform.huidu.util.ReferenceUtil;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.middleware.content.pojo.po.Content;
import com.codimiracle.web.middleware.content.pojo.po.ContentArticle;
import com.codimiracle.web.middleware.content.pojo.po.ContentReference;
import com.codimiracle.web.middleware.content.pojo.vo.ContentArticleVO;
import com.codimiracle.web.middleware.content.service.ArticleService;
import com.codimiracle.web.middleware.content.service.CommentService;
import com.codimiracle.web.middleware.content.service.ContentService;
import com.codimiracle.web.middleware.content.service.ReferenceService;
import com.codimiracle.web.mybatis.contract.AbstractUnsupportedOperationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl extends AbstractUnsupportedOperationService<String, Review> implements ReviewService {
    @Resource
    private ContentService contentService;
    @Resource
    private CommentService commentService;
    @Resource
    private ArticleService articleService;
    @Resource
    private ReferenceService referenceService;

    private void saveContentPart(Review review) {
        Content content = new Content();
        BeanUtils.copyProperties(review, content);
        contentService.save(content);
        BeanUtils.copyProperties(content, review);
    }

    private void saveArticlePart(Review review) {
        ContentArticle article = new ContentArticle();
        BeanUtils.copyProperties(review, article);
        article.setContentId(review.getId());
        articleService.save(article);
        BeanUtils.copyProperties(article, review);
    }

    private void saveReferences(Review review) {
        review.getReferenceList().forEach((r) -> {
            r.setContentId(review.getId());
            referenceService.save(r);
        });
    }

    @Override
    public void save(Review entity) {
        saveContentPart(entity);
        saveArticlePart(entity);
        saveReferences(entity);
    }

    @Override
    public void save(List<Review> entities) {
        entities.forEach(this::save);
    }

    private void updateReferences(Review review) {
        // 找出已有引用
        List<ContentReference> allContentReference = referenceService.findByContentId(review.getId());
        ReferenceUtil.mutateToPersistent(referenceService, review.getId(), review.getReferenceList());
        // 新引用
        List<ContentReference> newContentReference = review.getReferenceList().stream().filter(contentReference -> Objects.isNull(contentReference.getId())).collect(Collectors.toList());
        //重用旧引用
        Map<String, ContentReference> reusingContentReferenceMap = review.getReferenceList().stream().collect(Collectors.toMap(contentReference -> String.format("%s-%s", contentReference.getReferenceTargetType(), contentReference.getReferenceTargetId()), c -> c));
        allContentReference.forEach(r -> {
            String key = String.format("%s-%s", r.getReferenceTargetType(), r.getReferenceTargetId());
            if (!reusingContentReferenceMap.containsKey(key)) {
                r.setDeleted(true);
            } else {
                r.setDeleted(false);
            }
        });
        //保存新应用
        newContentReference.forEach((r) -> {
            r.setContentId(review.getId());
            referenceService.save(r);
        });
        //更新旧引用
        allContentReference.forEach(referenceService::update);
    }

    @Override
    public void update(Review entity) {
        Content content = new Content();
        BeanUtils.copyProperties(entity, content);
        contentService.update(content);
        ContentArticle article = new ContentArticle();
        BeanUtils.copyProperties(entity, article);
        articleService.update(article);
        updateReferences(entity);
    }

    @Override
    public Review findById(String id) {
        Review review = new Review();
        Content content = contentService.findById(id);
        ContentArticle article = articleService.findById(id);
        List<ContentReference> references = referenceService.findByContentId(id);
        BeanUtils.copyProperties(content, review);
        BeanUtils.copyProperties(article, review);
        review.setReferenceList(references);
        return review;
    }

    @Override
    public void deleteByIdLogically(String id) {
        contentService.deleteByIdLogically(id);
    }

    @Override
    public PageSlice<ReviewVO> findHotIntegrally(Filter filter, Sorter sorter, Page page) {
        return null;
    }

    private ReviewVO mutate(ContentArticleVO articleVO) {
        if (Objects.isNull(articleVO)) {
            return null;
        }
        ReviewVO reviewVO = new ReviewVO();
        BeanUtils.copyProperties(articleVO, reviewVO);
        Filter filter = new Filter();
        filter.put("targetContentId", new String[]{reviewVO.getContentId()});
        //reviewVO.setHotCommentList(commentService.findHotIntegrally(filter, null, new Page(1, 1)).getList());
        reviewVO.setReferenceList(referenceService.findByContentIdIntegrally(articleVO.getContentId()));
        return reviewVO;
    }

    public PageSlice<ReviewVO> mutate(PageSlice<ContentArticleVO> slice) {
        PageSlice<ReviewVO> transformedSlice = new PageSlice<>();
        transformedSlice.setLimit(slice.getLimit());
        transformedSlice.setTotal(slice.getTotal());
        transformedSlice.setPage(slice.getPage());
        transformedSlice.setList(slice.getList().stream().map(this::mutate).collect(Collectors.toList()));
        return transformedSlice;
    }

    @Override
    public ReviewVO findByIdIntegrally(String id) {
        ContentArticleVO articleVO = articleService.findByIdIntegrally(id);
        return mutate(articleVO);
    }

    @Override
    public PageSlice<ReviewVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ContentArticleVO> articleVOList = articleService.findAllIntegrally(filter, sorter, page);
        PageSlice<ReviewVO> slice = new PageSlice<>();
        slice.setList(articleVOList.getList().stream().map(this::mutate).collect(Collectors.toList()));
        slice.setPage(articleVOList.getPage());
        slice.setLimit(articleVOList.getLimit());
        slice.setTotal(articleVOList.getTotal());
        return slice;
    }

    @Override
    public PageSlice<ReviewVO> findHotReviewIntegrally(Filter filter, Sorter sorter, Page page) {
        //return mutate(articleService.findHotIntegrally(ContentType.Review, filter, sorter, page));
        return null;
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        ids.forEach(contentService::deleteByIdLogically);
    }
}
