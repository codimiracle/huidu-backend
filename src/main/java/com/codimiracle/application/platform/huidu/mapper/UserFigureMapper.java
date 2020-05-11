package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.FigureTag;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.FigureTagVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserFigureMapper extends Mapper<FigureTag, FigureTagVO> {
    List<UserProtectedVO> selectSimilarUserByUserIdProtectly(@Param("userId") String userId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<CategoryVO> selectSimilarCategoryByUserIdIntegrally(String userId);

    List<CategoryVO> selectSametastCategoryByUserIdIntegrally(String userId);

    List<CategoryVO> selectSimilarCategoryByAvgIntegrally();

    List<CategoryVO> selectSametasteCategoryByAvgIntegrally();

    void incrementScoreBy(@Param("figureTagId") String figureTagId, @Param("weight") float weight);

    void deleteByIdsLogically(List<String> ids);
}