package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.ContentArticle;
import com.codimiracle.application.platform.huidu.entity.vo.ArticleVO;
import com.codimiracle.application.platform.huidu.entity.vo.TopicVO;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface ContentArticleService extends Service<String, ContentArticle> {

    void passExamination(String id, String reason, String userId);

    void rejectExamination(String id, String reason, String userId);

    ArticleVO findByIdIntegrally(ContentType type, String id);

    List<ArticleVO> findAllIntegrally();

    PageSlice<ArticleVO> findAllIntegrally(ContentType type, Filter filter, Sorter sorter, Page page);

    PageSlice<ArticleVO> findHotIntegrally(ContentType topic, Filter filter, Sorter sorter, Page page);

    PageSlice<ArticleVO> findFocusArticleByTypeAndReferenceId(String book, String bookId, Filter filter, Sorter sorter, Page page);
}
