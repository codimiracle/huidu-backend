package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.BookShelf;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;
import com.codimiracle.application.platform.huidu.service.BookShelfService;
import com.codimiracle.application.platform.huidu.service.SubscribeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user/shelves/default")
public class ApiBookShelfController {
    @Resource
    private BookShelfService bookShelfService;

    @PostMapping("/join")
    public ApiResponse join(@AuthenticationPrincipal User user, @RequestParam("book_id") String bookId) {
        bookShelfService.join(user.getId(), bookId);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        bookShelfService.deleteById(id);
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
