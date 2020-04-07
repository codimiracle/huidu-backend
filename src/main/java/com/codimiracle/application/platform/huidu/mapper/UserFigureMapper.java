package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.FigureTag;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.FigureTagVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFigureMapper extends Mapper<FigureTag> {
    List<FigureTagVO> selectAllTagIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdIntegrally(@Param("figureId") String figureTagId);

    List<UserProtectedVO> selectSimilarUserByUserIdProtectly(@Param("userId") String userId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<CategoryVO> selectSimilarCategoryByUserIdIntegrally(String userId);

    List<CategoryVO> selectSametastCategoryByUserIdIntegrally(String userId);

    List<CategoryVO> selectSimilarCategoryByAvgIntegrally();

    List<CategoryVO> selectSametasteCategoryByAvgIntegrally();

    void incrementScoreBy(String figureTagId, float weight);
}