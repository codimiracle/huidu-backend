package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.po.CategoryTags;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.CollectionStatisticsVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.enumeration.CategoryType;
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
        if (Objects.nonNull(model.getTags())) {
            model.getTags().stream().filter((t) -> Objects.isNull(t.getId())).forEach(tagService::save);
            List<CategoryTags> categoryTagsList = model.getTags().stream().map((t) -> CategoryTags.valueOf(model, t)).collect(Collectors.toList());
            categoryTagsService.save(categoryTagsList);
        }
        super.save(model);
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
    public List<CategoryVO> findByIdsIntegrally(List<String> ids) {
        return ids.stream().map(this::findByIdIntegrally).collect(Collectors.toList());
    }

    @Override
    public List<CategoryVO> findRelativeCategoriesByBookType(BookType bookType) {
        return categoryMapper.selectRelativeCategoriesByBookType(bookType);
    }

    @Override
    public PageSlice<CollectionStatisticsVO> findCollectionStatisticsByCollectionId(String collectionId, Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(categoryMapper.selectCollectionStatisticsByCollectionId(collectionId, filter, sorter, page));
    }

    private void mutate(CategoryVO categoryVO) {
        if (Objects.nonNull(categoryVO)) {
            categoryVO.setTags(categoryTagsService.findTagByCategoryId(categoryVO.getId()));
        }
    }

    @Override
    public CategoryVO findByIdIntegrally(String id) {
        CategoryVO categoryVO = categoryMapper.selectByIdIntegrally(id);
        mutate(categoryVO);
        return categoryVO;
    }

    @Override
    public PageSlice<CategoryVO> findAllIntegrally(CategoryType type, Filter filter, Sorter sorter, Page page) {
        PageSlice<CategoryVO> slice = extractPageSlice(categoryMapper.selectAllIntegrally(type, filter, sorter, page));
        slice.getList().stream().forEach(this::mutate);
        return slice;
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
