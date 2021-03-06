package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.CategoryTags;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;

import java.util.List;

public interface CategoryTagsMapper extends Mapper<CategoryTags> {
    List<CategoryTags> selectByCategoryId(String categoryId);

    List<CategoryVO> selectCategoryByTagId(String id);

    List<TagVO> selectTagByCategoryId(String id);
}