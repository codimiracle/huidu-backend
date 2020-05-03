package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.CollectionStatisticsVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.enumeration.CategoryType;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper extends Mapper<Category, CategoryVO> {
    CategoryVO selectByIdIntegrally(@Param("id") String id);

    List<CategoryVO> selectAllIntegrally(@Param("type") CategoryType type, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdsLogically(List<String> id);

    void deleteByIdLogically(String id);

    List<CategoryVO> selectRelativeCategoriesByBookType(BookType bookType);

    List<CollectionStatisticsVO> selectCollectionStatisticsByCollectionId(@Param("collectionId") String collectionId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);
}