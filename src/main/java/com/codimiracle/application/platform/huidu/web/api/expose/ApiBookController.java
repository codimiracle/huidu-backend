package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.service.BuryingPointService;
import com.codimiracle.application.platform.huidu.web.api.base.BookController;
import com.codimiracle.web.basic.contract.ApiResponse;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class ApiBookController {
    @Autowired
    private BookController bookController;
    @Resource
    private BuryingPointService buryingPointService;

    @GetMapping("/{id}")
    public ApiResponse entity(@AuthenticationPrincipal User user, @PathVariable("id") String bookId) {
        return bookController.entity(null, bookId);
    }

    @GetMapping("/suggestion")
    public ApiResponse suggestion(String keyword) {
        Filter filter = new Filter();
        filter.put("keyword", new String[]{keyword});
        return bookController.publishCollection(null, filter, null, new Page());
    }
}
