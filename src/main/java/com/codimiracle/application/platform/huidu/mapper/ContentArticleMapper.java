package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.ContentArticle;
import com.codimiracle.application.platform.huidu.entity.vo.ArticleVO;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContentArticleMapper extends Mapper<ContentArticle> {
    ArticleVO selectByIdIntegrally(@Param("type") ContentType type, @Param("id") String id);

    List<ArticleVO> selectAllIntegrally();

    List<ArticleVO> selectAllIntegrally(@Param("type") ContentType type, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<ArticleVO> selectHotIntegrally(ContentType type, Filter filter, Sorter sorter, Page page);

    List<ArticleVO> selectFocusArticleByTypeAndReferenceId(String type, String referenceId, Filter filter, Sorter sorter, Page page);
}