package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.CategoryTags;
import com.codimiracle.application.platform.huidu.mapper.CategoryTagsMapper;
import com.codimiracle.application.platform.huidu.service.CategoryTagsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CategoryTagsServiceImpl extends AbstractService<String, CategoryTags> implements CategoryTagsService {
    @Resource
    private CategoryTagsMapper categoryTagsMapper;

    @Override
    public List<CategoryTags> findByCategoryId(String categoryId) {
        return categoryTagsMapper.selectByCategoryId(categoryId);
    }
}
