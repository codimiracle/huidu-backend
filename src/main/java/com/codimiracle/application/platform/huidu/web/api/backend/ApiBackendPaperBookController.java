package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.PaperBookDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.enumeration.CommodityType;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.base.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/shopping/paper-books")
public class ApiBackendPaperBookController {
    @Autowired
    private BookController bookController;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @RequestBody PaperBookDTO paperBookDTO) {
        if (Objects.nonNull(paperBookDTO.getCommodity())) {
            paperBookDTO.getCommodity().setType(CommodityType.MaterialObject.getType());
        }
        return bookController.create(user, paperBookDTO.toBookDTO());
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
    public ApiResponse update(@PathVariable String id, @RequestBody PaperBookDTO paperBookDTO) {
        return bookController.update(id, paperBookDTO.toBookDTO());
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        return bookController.entity(BookType.PaperBook, id);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return bookController.collection(BookType.PaperBook, filter, sorter, page);
    }
}
