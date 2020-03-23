package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.FigureTag;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.FigureTagVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;

import java.util.List;

/**
 * Created by Codimiracle on 2020/03/17.
 */
public interface UserFigureService extends Service<String, FigureTag> {

    PageSlice<FigureTagVO> findAllTagIntegrally(Filter filter, Sorter sorter, Page page);

    void deleteByIdIntegrally(String figureTagId);

    PageSlice<UserProtectedVO> findSimilarUserByUserIdProtectly(String userId, Filter filter, Sorter sorter, Page page);


    List<CategoryVO> findSimilarCategoryByUserIdIntegrally(String id);

    List<CategoryVO> findSametasteCategoryByUserIdIntegrally(String id);

    List<CategoryVO> findSimilarCategoryByAvgIntegrally();

    List<CategoryVO> findSametasteCategoryByAvgIntegrally();

}
