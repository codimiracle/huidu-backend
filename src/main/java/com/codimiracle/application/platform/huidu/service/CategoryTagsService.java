package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.CategoryTags;

import java.util.List;


public interface CategoryTagsService extends Service<String, CategoryTags> {

    List<CategoryTags> findByCategoryId(String categoryId);
}
