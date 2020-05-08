package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.CategoryTags;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;
import com.codimiracle.web.mybatis.contract.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface CategoryTagsMapper extends Mapper<CategoryTags> {
    List<CategoryTags> selectByCategoryId(String categoryId);

    List<CategoryVO> selectCategoryByTagId(String id);

    List<TagVO> selectTagByCategoryId(String id);
}