package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.BookTags;
import com.codimiracle.application.platform.huidu.entity.po.FigureTag;
import com.codimiracle.application.platform.huidu.service.BookTagsService;
import com.codimiracle.application.platform.huidu.service.BuryingPointService;
import com.codimiracle.application.platform.huidu.service.UserFigureService;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class BuryingPointServiceImpl implements BuryingPointService {
    private static final float TAG_ACCESS_WEIGHT = 0.2f;
    private static final float BOOK_ACCESS_WEIGHT = 0.5f;
    private static final float READING_ACCESS_WEIGHT = 0.5f;
    Interner<String> figureUpdatingLock = Interners.newWeakInterner();
    @Resource
    private UserFigureService userFigureService;
    @Resource
    private BookTagsService bookTagsService;

    private void forTag(String userId, String tagId, float weight) {
        String lock = figureUpdatingLock.intern(String.format("user-%s-figure-tag-%s", userId, tagId));
        synchronized (lock) {
            // 用户或标签无效啥都不干
            if (Objects.isNull(tagId) || Objects.isNull(userId)) {
                return;
            }
            FigureTag figureTag = userFigureService.findByUserIdAndTagId(userId, tagId);
            if (Objects.isNull(figureTag)) {
                figureTag = new FigureTag();
                figureTag.setUserId(userId);
                figureTag.setTagId(tagId);
                figureTag.setScore(BigDecimal.valueOf(weight));
                userFigureService.save(figureTag);
            } else {
                //从新启用画像标签
                if (figureTag.isDeleted()) {
                    FigureTag updatingFigureTag = new FigureTag();
                    updatingFigureTag.setId(figureTag.getId());
                    updatingFigureTag.setDeleted(false);
                    updatingFigureTag.setScore(BigDecimal.ZERO);
                    userFigureService.update(updatingFigureTag);
                }
                userFigureService.incrementScoreBy(figureTag.getTagId(), weight);
            }
        }
    }

    @Override
    public void forTag(String userId, String tagId) {
        forTag(userId, tagId, TAG_ACCESS_WEIGHT);
    }

    private void forBook(String userId, String bookId, float weight) {
        List<BookTags> bookTagList = bookTagsService.findByBookId(bookId);
        bookTagList.forEach((bookTags) -> {
            forTag(userId, bookTags.getTagId(), weight);
        });
    }

    @Override
    public void forBookDetails(String userId, String bookId) {
        forBook(userId, bookId, BOOK_ACCESS_WEIGHT);
    }

    @Override
    public void forReading(String userId, String bookId) {
        forBook(userId, bookId, READING_ACCESS_WEIGHT);
    }
}
