package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.CollectionDTO;
import com.codimiracle.application.platform.huidu.entity.po.Category;
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

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/classification/collections")
public class ApiBackendCollectionController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;

    @PostMapping
    public ApiResponse create(@RequestBody CollectionDTO collectionDTO) {
        Category category = Category.from(collectionDTO);
        category.setTags(TagUtil.mutateToPersistent(tagService, Arrays.asList(collectionDTO.getTags())));
        BeanUtils.copyProperties(collectionDTO, category);
        categoryService.save(category);
        return RestfulUtil.entity(categoryService.findByIdIntegrally(CategoryType.Collection, category.getId()));
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
    public ApiResponse update(@PathVariable String id, @RequestBody CollectionDTO collectionDTO) {
        Category category = new Category();
        category.setId(id);
        BeanUtils.copyProperties(collectionDTO, category);
        category.setTags(TagUtil.mutateToPersistent(tagService, Arrays.asList(collectionDTO.getTags())));
        categoryService.update(category);
        return RestfulUtil.entity(categoryService.findByIdIntegrally(CategoryType.Collection, id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        CategoryVO categoryVO = categoryService.findByIdIntegrally(CategoryType.Collection, id);
        return RestfulUtil.success(categoryVO);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<CategoryVO> slice = categoryService.findAllIntegrally(CategoryType.Collection, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
