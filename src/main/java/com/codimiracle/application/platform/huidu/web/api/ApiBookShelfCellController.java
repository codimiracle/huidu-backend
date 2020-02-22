package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.BookShelfCell;
import com.codimiracle.application.platform.huidu.service.BookShelfCellService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//book/shelf/cell")
public class ApiBookShelfCellController {
    @Resource
    private BookShelfCellService bookShelfCellService;

    @PostMapping
    public ApiResponse create(@RequestBody BookShelfCell bookShelfCell) {
        bookShelfCellService.save(bookShelfCell);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        bookShelfCellService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody BookShelfCell bookShelfCell) {
        bookShelfCellService.update(bookShelfCell);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookShelfCell bookShelfCell = bookShelfCellService.findById(id);
        return RestfulUtil.success(bookShelfCell);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<BookShelfCell> slice = bookShelfCellService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
