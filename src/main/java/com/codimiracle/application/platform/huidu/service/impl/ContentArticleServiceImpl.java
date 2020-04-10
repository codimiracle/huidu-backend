package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.ContentArticle;
import com.codimiracle.application.platform.huidu.entity.po.ContentExamination;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.ArticleVO;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.mapper.ContentArticleMapper;
import com.codimiracle.application.platform.huidu.service.ContentArticleService;
import com.codimiracle.application.platform.huidu.service.ContentExaminationService;
import com.codimiracle.application.platform.huidu.service.ContentLikesService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ContentArticleServiceImpl extends AbstractService<String, ContentArticle> implements ContentArticleService {
    @Resource
    private ContentArticleMapper contentArticleMapper;

    @Resource
    private ContentLikesService contentLikesService;

    @Resource
    private ContentExaminationService contentExaminationService;

    private void examinate(String id, String reason, String userId, ContentStatus status) {
        ContentArticle contentArticle = findById(id);
        if (contentArticle.getStatus() != ContentStatus.Examining) {
            throw new ServiceException("无法审查该内容");
        }
        ContentExamination examination = new ContentExamination();
        examination.setFromStatus(contentArticle.getStatus().getStatus());
        examination.setToStatus(status.getStatus());
        examination.setTargetContentId(id);
        examination.setReason(reason);
        examination.setUserId(userId);
        examination.setExamineTime(new Date());
        contentExaminationService.save(examination);

        ContentArticle updatingContentArticle = new ContentArticle();
        updatingContentArticle.setContentId(id);
        updatingContentArticle.setStatus(status);
        update(updatingContentArticle);
    }

    @Override
    public void passExamination(String id, String reason, String userId) {
        examinate(id, reason, userId, ContentStatus.Publish);
    }

    @Override
    public void rejectExamination(String id, String reason, String userId) {
        examinate(id, reason, userId, ContentStatus.Rejected);
    }


    @Override
    public ArticleVO findByIdIntegrally(ContentType type, String id) {
        return inflateContentExamination(inflateLikedState(contentArticleMapper.selectByIdIntegrally(type, id)));
    }

    public ArticleVO findByIdIntegrally(String id) {
        return findByIdIntegrally(null, id);
    }

    private ArticleVO inflateTargetContent(ArticleVO articleVO) {
        if (Objects.nonNull(articleVO)) {
            articleVO.setTargetContent(findByIdIntegrally(articleVO.getTargetContentId()));
        }
        return articleVO;
    }

    private PageSlice<ArticleVO> inflateTargetContent(PageSlice<ArticleVO> slice) {
        slice.getList().forEach(this::inflateTargetContent);
        return slice;
    }

    private ArticleVO inflateContentExamination(ArticleVO articleVO) {
        if (Objects.isNull(articleVO)) {
            return null;
        }
        articleVO.setExamination(contentExaminationService.findLastExaminationByContentId(articleVO.getContentId()));
        return articleVO;
    }

    private ArticleVO inflateLikedState(ArticleVO articleVO) {
        if (Objects.isNull(articleVO)) {
            return null;
        }
        Object pricipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (pricipal instanceof User) {
            User user = (User) pricipal;
            articleVO.setLiked(contentLikesService.isLiked(user.getId(), articleVO.getContentId()));
        }
        return articleVO;
    }

    private PageSlice<ArticleVO> inflateLikedState(PageSlice<ArticleVO> slice) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            slice.getList().forEach((e) -> {
                e.setLiked(contentLikesService.isLiked(user.getId(), e.getContentId()));
            });
        }
        return slice;
    }

    @Override
    public PageSlice<ArticleVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        return inflateLikedState(extractPageSlice(contentArticleMapper.selectAllIntegrally(null, filter, sorter, page)));
    }

    @Override
    public PageSlice<ArticleVO> findAllIntegrally(ContentType type, Filter filter, Sorter sorter, Page page) {
        return inflateLikedState(extractPageSlice(contentArticleMapper.selectAllIntegrally(type, filter, sorter, page)));
    }

    @Override
    public PageSlice<ArticleVO> findHotIntegrally(ContentType type, Filter filter, Sorter sorter, Page page) {
        return inflateLikedState(extractPageSlice(contentArticleMapper.selectHotIntegrally(type, filter, sorter, page)));
    }

    @Override
    public PageSlice<ArticleVO> findFocusArticleByTypeAndReferenceId(ContentType type, String referenceType, String bookId, Filter filter, Sorter sorter, Page page) {
        return inflateLikedState(extractPageSlice(contentArticleMapper.selectFocusArticleByTypeAndReferenceId(type, referenceType, bookId, filter, sorter, page)));
    }

    @Override
    public PageSlice<ArticleVO> findAllIntegrallyWithTargetContent(ContentType type, Filter filter, Sorter sorter, Page page) {
        return inflateTargetContent(inflateLikedState(extractPageSlice(contentArticleMapper.selectAllIntegrally(type, filter, sorter, page))));
    }
}
