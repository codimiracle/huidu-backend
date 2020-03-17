package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.AudioBookDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.base.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/contents/audio-books")
public class ApiBackendAudioBookController {
    @Autowired
    private BookController bookController;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @RequestBody AudioBookDTO audioBookDTO) {
        return bookController.create(user, audioBookDTO.toBookDTO());
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        return bookController.delete(id);
    }

    @DeleteMapping
    public ApiResponse delete(String[] ids) {
        bookController.delete(ids);
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @RequestBody AudioBookDTO audioBookDTO) {
        return bookController.update(id, audioBookDTO.toBookDTO());
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        return bookController.entity(BookType.AudioBook, id);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return bookController.collection(BookType.AudioBook, filter, sorter, page);
    }
}
