package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.BookShelf;
import com.codimiracle.application.platform.huidu.service.BookShelfService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//book/shelf")
public class ApiBookShelfController {
    @Resource
    private BookShelfService bookShelfService;

    @PostMapping
    public ApiResponse create(@RequestBody BookShelf bookShelf) {
        bookShelfService.save(bookShelf);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        bookShelfService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody BookShelf bookShelf) {
        bookShelfService.update(bookShelf);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookShelf bookShelf = bookShelfService.findById(id);
        return RestfulUtil.success(bookShelf);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<BookShelf> slice = bookShelfService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
