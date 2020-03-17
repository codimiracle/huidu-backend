package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.ContentArticle;
import com.codimiracle.application.platform.huidu.entity.po.ContentExamination;
import com.codimiracle.application.platform.huidu.entity.vo.ArticleVO;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.mapper.ContentArticleMapper;
import com.codimiracle.application.platform.huidu.service.ContentArticleService;
import com.codimiracle.application.platform.huidu.service.ContentExaminationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ContentArticleServiceImpl extends AbstractService<String, ContentArticle> implements ContentArticleService {
    @Resource
    private ContentArticleMapper contentArticleMapper;

    @Resource
    private ContentExaminationService contentExaminationService;

    private void examinate(String id, String reason, String userId, ContentStatus status) {
        ContentArticle contentArticle = findById(id);
        if (contentArticle.getStatus() == ContentStatus.Examining) {
            throw new ServiceException("无法审查该内容");
        }
        ContentExamination examination = new ContentExamination();
        examination.setFromStatus(contentArticle.getStatus().getStatus());
        examination.setToStatus(status.getStatus());
        examination.setTargetContentId(id);
        examination.setReason(reason);
        examination.setUserId(userId);
        contentExaminationService.save(examination);

        ContentArticle updatingContentArticle = new ContentArticle();
        updatingContentArticle.setContentId(id);
        updatingContentArticle.setStatus(status);
        update(contentArticle);
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
        return contentArticleMapper.selectByIdIntegrally(type, id);
    }

    @Override
    public List<ArticleVO> findAllIntegrally() {
        return contentArticleMapper.selectAllIntegrally();
    }

    @Override
    public PageSlice<ArticleVO> findAllIntegrally(ContentType type, Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(contentArticleMapper.selectAllIntegrally(type, filter, sorter, page));
    }

}
