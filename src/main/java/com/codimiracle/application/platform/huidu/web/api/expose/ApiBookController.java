package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.web.api.base.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class ApiBookController {
    @Autowired
    private BookController bookController;

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable("id") String bookId) {
        return bookController.entity(null, bookId);
    }

    @GetMapping("/suggestion")
    public ApiResponse suggestion(String keyword) {
        Filter filter = new Filter();
        filter.put("keyword", new String[]{keyword});
        return bookController.publishCollection(null, filter, null, new Page());
    }
}
