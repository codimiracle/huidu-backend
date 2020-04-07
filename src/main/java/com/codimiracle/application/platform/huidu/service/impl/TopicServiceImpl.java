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
import com.codimiracle.application.platform.huidu.util.ReferenceUtil;
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
public class TopicServiceImpl extends AbstractUnsupportedOperationServiece<String, Topic> implements TopicService {
    @Resource
    ContentService contentService;
    @Resource
    ContentArticleService contentArticleService;
    @Resource
    ContentReferenceService contentReferenceService;

    private void saveContentPart(Topic topic) {
        Content content = new Content();
        BeanUtils.copyProperties(topic, content);
        contentService.save(content);
        BeanUtils.copyProperties(content, topic);
    }

    private void saveArticlePart(Topic topic) {
        ContentArticle article = new ContentArticle();
        BeanUtils.copyProperties(topic, article);
        article.setContentId(topic.getId());
        contentArticleService.save(article);
        BeanUtils.copyProperties(article, topic);
    }

    private void saveReference(Topic topic) {
        topic.getReferenceList().forEach((r) -> {
            r.setContentId(topic.getId());
            contentReferenceService.save(r);
        });
    }

    @Override
    public void save(Topic entity) {
        saveContentPart(entity);
        saveArticlePart(entity);
        saveReference(entity);
    }

    @Override
    public void save(List<Topic> entities) {
        entities.forEach(this::save);
    }

    private void updateReferences(Topic topic) {
        // 找出已有引用
        List<ContentReference> allContentReference = contentReferenceService.findByContentId(topic.getId());
        ReferenceUtil.mutateToPersistent(contentReferenceService, topic.getId(), topic.getReferenceList());
        // 新引用
        List<ContentReference> newContentReference = topic.getReferenceList().stream().filter(contentReference -> Objects.isNull(contentReference.getId())).collect(Collectors.toList());
        //重用旧引用
        Map<String, ContentReference> reusingContentReferenceMap = topic.getReferenceList().stream().collect(Collectors.toMap(contentReference -> String.format("%s-%s", contentReference.getType(), contentReference.getRefId()), c -> c));
        allContentReference.forEach(r -> {
            String key = String.format("%s-%s", r.getType(), r.getRefId());
            if (!reusingContentReferenceMap.containsKey(key)) {
                r.setDeleted(true);
            } else {
                r.setDeleted(false);
            }
        });
        //保存新引用
        newContentReference.forEach((r) -> {
            r.setContentId(topic.getId());
            contentReferenceService.save(r);
        });
        //更新旧引用
        allContentReference.forEach(contentReferenceService::update);
    }

    @Override
    public void update(Topic entity) {
        Content content = new Content();
        BeanUtils.copyProperties(entity, content);
        contentService.update(content);
        ContentArticle article = new ContentArticle();
        BeanUtils.copyProperties(entity, article);
        contentArticleService.update(article);
        updateReferences(entity);
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
    public PageSlice<TopicVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ArticleVO> slice = contentArticleService.findAllIntegrally(ContentType.Topic, filter, sorter, page);
        return mutate(slice);
    }

    @Override
    public PageSlice<TopicVO> findHotIntegrally(Filter filter, Sorter sorter, Page page) {
        return mutate(contentArticleService.findHotIntegrally(ContentType.Topic, filter, sorter, page));
    }

    @Override
    public PageSlice<TopicVO> findFocusTopicByReferenceIdIntegrally(String bookId, Filter filter, Sorter sorter, Page page) {
        PageSlice<ArticleVO> slice = contentArticleService.findFocusArticleByTypeAndReferenceId(ContentType.Topic, "book", bookId, filter, sorter, page);
        return mutate(slice);
    }
}
