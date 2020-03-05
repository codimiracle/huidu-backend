package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.CategoryDTO;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.enumeration.CategoryType;
import com.codimiracle.application.platform.huidu.service.CategoryService;
import com.codimiracle.application.platform.huidu.service.TagService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.util.TagUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/classification/categories")
public class ApiBackendCategoryController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;

    @PostMapping
    public ApiResponse create(@RequestBody CategoryDTO categoryDTO) {
        Category category = Category.from(categoryDTO);
        category.setTags(TagUtil.mutateToPersistent(tagService, Arrays.asList(categoryDTO.getTags())));
        BeanUtils.copyProperties(categoryDTO, category);
        categoryService.save(category);
        return RestfulUtil.entity(categoryService.findByIdIntegrally(category.getId()));
    }

    private void mergeTagList(CategoryDTO categoryDTO, Category category) {
        List<Tag> tagList = tagService.findByTagNames(Arrays.asList(categoryDTO.getTags()));
        tagList = TagUtil.merge(tagList, Arrays.asList(categoryDTO.getTags()), Tag::getName, Tag::new);
        category.setTags(tagList);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        categoryService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse deleteBulk(String[] ids) {
        categoryService.deleteByIdsLogically(Arrays.asList(ids));
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(id);
        BeanUtils.copyProperties(categoryDTO, category);
        mergeTagList(categoryDTO, category);
        categoryService.update(category);
        return RestfulUtil.entity(categoryService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        CategoryVO categoryVO = categoryService.findByIdIntegrally(id);
        return RestfulUtil.success(categoryVO);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<CategoryVO> slice = categoryService.findAllIntegrally(CategoryType.Category, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
