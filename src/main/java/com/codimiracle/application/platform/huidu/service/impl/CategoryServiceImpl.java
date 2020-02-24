package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.po.CategoryTags;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.mapper.CategoryMapper;
import com.codimiracle.application.platform.huidu.service.CategoryService;
import com.codimiracle.application.platform.huidu.service.CategoryTagsService;
import com.codimiracle.application.platform.huidu.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractService<String, Category> implements CategoryService {
    @Resource
    CategoryTagsService categoryTagsService;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private TagService tagService;

    @Override
    public void save(Category model) {
        super.save(model);
        model.getTags().stream().filter((t) -> Objects.isNull(t.getId())).forEach(tagService::save);
        List<CategoryTags> categoryTagsList = model.getTags().stream().map((t) -> CategoryTags.valueOf(model, t)).collect(Collectors.toList());
        categoryTagsService.save(categoryTagsList);
    }

    @Override
    public void save(List<Category> models) {
        super.save(models);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        categoryMapper.deleteByIdsLogically(ids);
    }

    @Override
    public CategoryVO findByIdIntegrally(String id) {
        return categoryMapper.selectByIdIntegrally(id);
    }

    @Override
    public PageSlice<CategoryVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(categoryMapper.selectAllIntegrally(filter, sorter, page));
    }

    @Override
    public void deleteByIdLogically(String id) {
        categoryMapper.deleteByIdLogically(id);
    }

    @Override
    public void update(Category model) {
        //找出已有关联
        List<CategoryTags> categoryTagsList = categoryTagsService.findByCategoryId(model.getId());
        //保存新的 Tag 对象
        List<Tag> newTags = model.getTags().stream().filter((tag -> Objects.isNull(tag.getId()))).collect(Collectors.toList());
        newTags.forEach(tagService::save);
        //将已有 Tag 关联从新启用
        Map<String, Tag> map = model.getTags().stream().collect(Collectors.toMap(Tag::getId, (tag) -> tag));
        categoryTagsList.forEach((categoryTags -> {
            if (!map.containsKey(categoryTags.getTagId())) {
                categoryTags.setDeleted(true);
            } else {
                categoryTags.setDeleted(false);
            }
        }));
        List<CategoryTags> newCategoryTagsList = newTags.stream().map((tag) -> CategoryTags.valueOf(model, tag)).collect(Collectors.toList());
        categoryTagsList.forEach(categoryTagsService::update);
        //保存新的关联
        newCategoryTagsList.forEach(categoryTagsService::save);
        //保存自身
        super.update(model);

    }
}
