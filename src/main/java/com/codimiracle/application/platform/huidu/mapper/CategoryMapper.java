package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.CollectionStatisticsVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.enumeration.CategoryType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {
    CategoryVO selectByIdIntegrally(@Param("id") String id);

    List<CategoryVO> selectAllIntegrally(@Param("type") CategoryType type, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdsLogically(List<String> id);

    void deleteByIdLogically(String id);

    List<CategoryVO> selectRelativeCategoriesByBookType(BookType bookType);

    List<CollectionStatisticsVO> selectCollectionStatisticsByCollectionId(@Param("collectionId") String collectionId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);
}