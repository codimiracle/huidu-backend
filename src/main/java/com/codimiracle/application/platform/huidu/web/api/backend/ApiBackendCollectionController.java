package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.CollectionDTO;
import com.codimiracle.application.platform.huidu.entity.po.Category;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.CollectionStatisticsVO;
import com.codimiracle.application.platform.huidu.enumeration.CategoryType;
import com.codimiracle.application.platform.huidu.service.CategoryService;
import com.codimiracle.application.platform.huidu.service.TagService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.util.TagUtil;
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
        categoryService.save(category);
        return RestfulUtil.entity(categoryService.findByIdIntegrally(category.getId()));
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
        Category category = Category.from(collectionDTO);
        category.setId(id);
        category.setTags(TagUtil.mutateToPersistent(tagService, Arrays.asList(collectionDTO.getTags())));
        categoryService.update(category);
        return RestfulUtil.entity(categoryService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        CategoryVO categoryVO = categoryService.findByIdIntegrally(id);
        return RestfulUtil.success(categoryVO);
    }

    @GetMapping("/suggestion")
    public ApiResponse suggestion(@RequestParam("keyword") String keyword) {
        Filter filter = new Filter();
        filter.put("name", new String[]{keyword});
        Page page = new Page();
        page.setPage(1);
        page.setLimit(10);
        return collection(filter, null, page);
    }
    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<CategoryVO> slice = categoryService.findAllIntegrally(CategoryType.Collection, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/{id}/statistics")
    public ApiResponse statistics(@PathVariable("id") String collectionId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<CollectionStatisticsVO> slice = categoryService.findCollectionStatisticsByCollectionId(collectionId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
