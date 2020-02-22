package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.mapper.CategoryMapper;
import com.codimiracle.application.platform.huidu.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractService<String, Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

}
