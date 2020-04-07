package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.contract.Conditioner;
import com.codimiracle.application.platform.huidu.entity.po.ContentLikes;
import com.codimiracle.application.platform.huidu.mapper.ContentLikesMapper;
import com.codimiracle.application.platform.huidu.service.ContentLikesService;
import com.codimiracle.application.platform.huidu.service.ContentService;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ContentLikesServiceImpl extends AbstractService<String, ContentLikes> implements ContentLikesService {
    @Resource
    private ContentLikesMapper contentLikesMapper;
    @Resource
    private ContentService contentService;
    private Interner<String> likeLock = Interners.newWeakInterner();

    public ContentLikes findByUserIdAndContentId(String userId, String contentId) throws TooManyResultsException {
        Condition condition = Conditioner.conditionOf(ContentLikes.class);
        condition.createCriteria().andEqualTo("contentId", contentId).andEqualTo("userId", userId);
        List<ContentLikes> contentLikesList = findByCondition(condition);
        if (contentLikesList.isEmpty()) {
            return null;
        } else if (contentLikesList.size() != 1) {
            throw new TooManyResultsException();
        } else {
            return contentLikesList.get(0);
        }
    }

    @Override
    public void save(ContentLikes model) {
        super.save(model);
        if (model.isLiked()) {
            contentService.likesIncrement(model.getContentId());
        }
    }

    @Override
    public void update(ContentLikes model) {
        super.update(model);
        if (model.isLiked()) {
            contentService.likesIncrement(model.getContentId());
        } else {
            contentService.likesDecrement(model.getContentId());
        }
    }

    @Override
    public void like(String likerId, String contentId) {
        String lock = likeLock.intern(String.format("user-%s-like-act-%s", likerId, contentId));
        synchronized (lock) {
            ContentLikes contentLikes = findByUserIdAndContentId(likerId, contentId);
            if (Objects.isNull(contentLikes)) {
                contentLikes = new ContentLikes();
                contentLikes.setContentId(contentId);
                contentLikes.setLiked(true);
                contentLikes.setUserId(likerId);
                save(contentLikes);
            } else if (!contentLikes.isLiked()) {
                contentLikes.setLiked(true);
                update(contentLikes);
            }
        }
    }

    @Override
    public void unlike(String unlikerId, String contentId) {
        String lock = likeLock.intern(String.format("user-%s-like-act-%s", unlikerId, contentId));
        synchronized (lock) {
            ContentLikes contentLikes = findByUserIdAndContentId(unlikerId, contentId);
            if (Objects.nonNull(contentLikes) && contentLikes.isLiked()) {
                contentLikes.setLiked(false);
                update(contentLikes);
            }
        }
    }

    @Override
    public boolean isLiked(String likerId, String contentId) {
        ContentLikes contentLikes = findByUserIdAndContentId(likerId, contentId);
        return Objects.nonNull(contentLikes) && contentLikes.isLiked();
    }
}
