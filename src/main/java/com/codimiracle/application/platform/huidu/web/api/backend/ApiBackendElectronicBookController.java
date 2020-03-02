package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.ElectronicBookDTO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/contents/electronic-books")
public class ApiBackendElectronicBookController {
    @Autowired
    private BookController bookController;

    @PostMapping
    public ApiResponse create(@RequestBody ElectronicBookDTO electronicBookDTO) {
        return bookController.create(electronicBookDTO.toBookDTO());
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
    public ApiResponse update(@PathVariable String id, @RequestBody ElectronicBookDTO electronicBookDTO) {
        return bookController.update(id, electronicBookDTO.toBookDTO());
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        return bookController.entity(BookType.ElectronicBook, id);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return bookController.collection(BookType.ElectronicBook, filter, sorter, page);
    }
}
