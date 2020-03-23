package com.codimiracle.application.platform.huidu.service.impl;/*
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

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.po.ContentArticle;
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.entity.vo.ArticleVO;
import com.codimiracle.application.platform.huidu.entity.vo.TopicVO;
import com.codimiracle.application.platform.huidu.entity.vt.Topic;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.service.ContentArticleService;
import com.codimiracle.application.platform.huidu.service.ContentReferenceService;
import com.codimiracle.application.platform.huidu.service.ContentService;
import com.codimiracle.application.platform.huidu.service.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class TopicServiceImpl extends AbstractUnsupportedOperationServiece<String, Topic> implements TopicService {
    @Resource
    ContentService contentService;
    @Resource
    ContentArticleService contentArticleService;
    @Resource
    ContentReferenceService contentReferenceService;

    @Override
    public void save(Topic entity) {
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
    public void save(List<Topic> entities) {
        entities.forEach(this::save);
    }

    @Override
    public void update(Topic entity) {
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
    public Topic findById(String id) {
        Topic topic = new Topic();
        Content content = contentService.findById(id);
        ContentArticle article = contentArticleService.findById(id);
        List<ContentReference> references = contentReferenceService.findByContentId(id);
        BeanUtils.copyProperties(content, topic);
        BeanUtils.copyProperties(article, topic);
        topic.setReferenceList(references);
        return topic;
    }

    @Override
    public void update(Topic topic, List<ContentReference> oldRefers) {
        oldRefers.stream().map(ContentReference::getId).reduce((a, b) -> a + ',' + b).ifPresent(contentReferenceService::deleteByIds);
        update(topic);
    }

    @Override
    public int deleteByIdLogically(String id) {
        return contentService.deleteByIdLogically(id);
    }

    private TopicVO mutate(ArticleVO articleVO) {
        if (Objects.isNull(articleVO)) {
            return null;
        }
        TopicVO topicVO = new TopicVO();
        BeanUtils.copyProperties(articleVO, topicVO);
        topicVO.setReferences(contentReferenceService.findByContentIdIntegrally(articleVO.getContentId()));
        return topicVO;
    }

    private PageSlice<TopicVO> mutate(PageSlice<ArticleVO> slice) {
        PageSlice<TopicVO> mutateSlice = new PageSlice<>();
        mutateSlice.setList(slice.getList().stream().map(this::mutate).collect(Collectors.toList()));
        mutateSlice.setPage(slice.getPage());
        mutateSlice.setLimit(slice.getLimit());
        mutateSlice.setTotal(slice.getTotal());
        return mutateSlice;
    }

    @Override
    public TopicVO findByIdIntegrally(String id) {
        ArticleVO articleVO = contentArticleService.findByIdIntegrally(ContentType.Topic, id);
        return mutate(articleVO);
    }

    @Override
    public List<TopicVO> findAllIntegrally() {
        List<TopicVO> topicVOList = new ArrayList<>();
        List<ArticleVO> articleVOList = contentArticleService.findAllIntegrally();
        for (ArticleVO articleVO : articleVOList) {
            TopicVO topicVO = new TopicVO();
            BeanUtils.copyProperties(articleVO, topicVO);
            topicVO.setReferences(contentReferenceService.findByContentIdIntegrally(topicVO.getContentId()));
            topicVOList.add(topicVO);
        }
        return topicVOList;
    }

    @Override
    public PageSlice<TopicVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ArticleVO> slice = contentArticleService.findAllIntegrally(ContentType.Topic, filter, sorter, page);
        return mutate(slice);
    }

    @Override
    public PageSlice<TopicVO> findHotIntegrally(Filter filter, Sorter sorter, Page page) {
        return mutate(contentArticleService.findHotIntegrally(ContentType.Topic, filter, sorter, page));
    }

    @Override
    public PageSlice<TopicVO> findTopIntegrally(Filter filter, Sorter sorter, Page page) {
        return findHotIntegrally(filter, sorter, page);
    }

    @Override
    public PageSlice<TopicVO> findFocusTopicByReferenceIdIntegrally(String bookId, Filter filter, Sorter sorter, Page page) {
        PageSlice<ArticleVO> slice = contentArticleService.findFocusArticleByTypeAndReferenceId("book", bookId, filter, sorter, page);
        return mutate(slice);
    }
}
