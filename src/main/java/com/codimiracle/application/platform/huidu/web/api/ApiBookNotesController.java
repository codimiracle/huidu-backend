package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.BookNotes;
import com.codimiracle.application.platform.huidu.service.BookNotesService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//book/notes")
public class ApiBookNotesController {
    @Resource
    private BookNotesService bookNotesService;

    @PostMapping
    public ApiResponse create(@RequestBody BookNotes bookNotes) {
        bookNotesService.save(bookNotes);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        bookNotesService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody BookNotes bookNotes) {
        bookNotesService.update(bookNotes);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookNotes bookNotes = bookNotesService.findById(id);
        return RestfulUtil.success(bookNotes);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<BookNotes> slice = bookNotesService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
