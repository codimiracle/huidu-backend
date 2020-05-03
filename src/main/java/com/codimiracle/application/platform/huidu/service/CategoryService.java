package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.CollectionStatisticsVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.enumeration.CategoryType;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface CategoryService extends Service<String, Category, CategoryVO> {

    CategoryVO findByIdIntegrally(String id);

    PageSlice<CategoryVO> findAllIntegrally(CategoryType type, Filter filter, Sorter sorter, Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    List<CategoryVO> findByIdsIntegrally(List<String> ids);

    List<CategoryVO> findRelativeCategoriesByBookType(BookType bookType);

    PageSlice<CollectionStatisticsVO> findCollectionStatisticsByCollectionId(String collectionId, Filter filter, Sorter sorter, Page page);
}
