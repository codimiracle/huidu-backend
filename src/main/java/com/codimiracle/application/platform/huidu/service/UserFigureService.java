package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.FigureTag;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.FigureTagVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;

/**
 * Created by Codimiracle on 2020/03/17.
 */
public interface UserFigureService extends Service<String, FigureTag, FigureTagVO> {

    void deleteByIdLogically(String figureTagId);

    PageSlice<UserProtectedVO> findSimilarUserByUserIdProtectly(String userId, Filter filter, Sorter sorter, Page page);

    List<CategoryVO> findSimilarCategoryByUserIdIntegrally(String id);

    List<CategoryVO> findSametasteCategoryByUserIdIntegrally(String id);

    List<CategoryVO> findSimilarCategoryByAvgIntegrally();

    List<CategoryVO> findSametasteCategoryByAvgIntegrally();

    FigureTag findByUserIdAndTagId(String userId, String tagId);

    void incrementScoreBy(String id, float weight);

    void deleteByIdsLogically(List<String> ids);
}
