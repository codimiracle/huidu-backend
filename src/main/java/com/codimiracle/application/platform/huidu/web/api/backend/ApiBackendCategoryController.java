package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.BulkDeletionDTO;
import com.codimiracle.application.platform.huidu.entity.dto.CategoryDTO;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.enumeration.CategoryType;
import com.codimiracle.application.platform.huidu.service.CategoryService;
import com.codimiracle.application.platform.huidu.service.TagService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.util.TagUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

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
    public ApiResponse create(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = Category.from(categoryDTO);
        category.setTags(TagUtil.mutateToPersistent(tagService, Arrays.asList(categoryDTO.getTags())));
        categoryService.save(category);
        return RestfulUtil.entity(categoryService.findByIdIntegrally(category.getId()));
    }

    @DeleteMapping
    public ApiResponse deleteBulk(@Valid @RequestBody BulkDeletionDTO bulkDeletionDTO) {
        categoryService.deleteByIdsLogically(Arrays.asList(bulkDeletionDTO.getIds()));
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        categoryService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = Category.from(categoryDTO);
        category.setId(id);
        category.setTags(TagUtil.mutateToPersistent(tagService, Arrays.asList(categoryDTO.getTags())));
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
