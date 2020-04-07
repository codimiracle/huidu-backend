package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.enumeration.BookStatus;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.enumeration.CategoryType;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.CategoryService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {
    @Resource
    private CategoryService categoryService;

    @Resource
    private BookService bookService;

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        CategoryVO categoryVO = categoryService.findByIdIntegrally(id);
        return RestfulUtil.entity(categoryVO);
    }

    @GetMapping("/{id}/items")
    public ApiResponse items(@PathVariable("id") String categoryId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("status", new String[]{BookStatus.Serializing.toString(), BookStatus.Ended.toString(), BookStatus.Paused.toString()});
        PageSlice<BookVO> slice = bookService.findByCategoryIdIntegrally(categoryId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/{id}/album")
    public ApiResponse album(@PathVariable("id") String categoryId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("status", new String[]{BookStatus.Serializing.toString(), BookStatus.Ended.toString(), BookStatus.Paused.toString()});
        PageSlice<BookVO> slice = bookService.findByCollectionIdIntegrally(categoryId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    @GetMapping("/{id}/album/most-read")
    public ApiResponse albumMostRead(@PathVariable("id") String categoryId) {
        Page page = new Page();
        page.setLimit(10);
        page.setPage(1);
        Sorter sorter = new Sorter();
        sorter.setField("hotDegree");
        sorter.setOrder("descend");
        return album(categoryId, null, sorter, page);
    }

    @GetMapping("/{id}/items/most-read")
    public ApiResponse mostRead(@PathVariable("id") String categoryId) {
        Page page = new Page();
        page.setLimit(10);
        page.setPage(1);
        return items(categoryId, null, null, page);
    }

    @GetMapping("/{id}/items/recommends")
    public ApiResponse recommends(@PathVariable("id") String categoryId) {
        return mostRead(categoryId);
    }

    @GetMapping("/related-by-book-type")
    public ApiResponse relativeCategories(String type) {
        return RestfulUtil.success(categoryService.findRelativeCategoriesByBookType(BookType.valueOfCode(type)));
    }

    @GetMapping("/suggestion")
    public ApiResponse suggestion(@RequestParam("keyword") String keyword) {
        Filter filter = new Filter();
        filter.put("name", new String[]{keyword});
        Sorter sorter = null;
        Page page = new Page();
        page.setLimit(10);
        page.setPage(1);
        return collection(filter, sorter, page);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<CategoryVO> slice = categoryService.findAllIntegrally(CategoryType.Category, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
