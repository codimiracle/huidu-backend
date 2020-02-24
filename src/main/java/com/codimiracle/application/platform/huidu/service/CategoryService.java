package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface CategoryService extends Service<String, Category> {

    CategoryVO findByIdIntegrally(String id);

    PageSlice<CategoryVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);
}
