package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.CategoryTags;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;
import com.codimiracle.web.mybatis.contract.Service;

import java.util.List;


public interface CategoryTagsService extends Service<String, CategoryTags> {

    List<CategoryTags> findByCategoryId(String categoryId);

    List<CategoryVO> findCategoryByTagId(String id);

    List<TagVO> findTagByCategoryId(String id);
}
