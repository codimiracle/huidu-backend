package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.CategoryTags;

import java.util.List;

public interface CategoryTagsMapper extends Mapper<CategoryTags> {
    List<CategoryTags> selectByCategoryId(String categoryId);

}