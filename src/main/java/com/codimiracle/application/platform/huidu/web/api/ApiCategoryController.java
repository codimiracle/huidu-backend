package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.service.CategoryService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//category")
public class ApiCategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping
    public ApiResponse create(@RequestBody Category category) {
        categoryService.save(category);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        categoryService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody Category category) {
        categoryService.update(category);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        Category category = categoryService.findById(id);
        return RestfulUtil.success(category);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<Category> slice = categoryService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
