package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.entity.dto.BookMetadataDTO;
import com.codimiracle.application.platform.huidu.entity.po.BookMetadata;
import com.codimiracle.application.platform.huidu.entity.vo.BookMetadataVO;
import com.codimiracle.application.platform.huidu.service.BookMetadataService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * Created by CodeGenerator on 2020/02/24.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/book-metadatas")
public class ApiBookMetadataController {
    @Resource
    private BookMetadataService bookMetadataService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody BookMetadataDTO bookMetadataDTO) {
        BookMetadata bookMetadata = BookMetadata.from(bookMetadataDTO);
        bookMetadataService.save(bookMetadata);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        bookMetadataService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse delete(String[] ids) {
        bookMetadataService.deleteByIdsLogically(Arrays.asList(ids));
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@PathVariable String id, @Valid @RequestBody BookMetadataDTO bookMetadataDTO) {
        BookMetadata bookMetadata = BookMetadata.from(bookMetadataDTO);
        bookMetadata.setId(id);
        bookMetadataService.update(bookMetadata);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookMetadataVO bookMetadataVO = bookMetadataService.findByIdIntegrally(id);
        return RestfulUtil.success(bookMetadataVO);
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
        PageSlice<BookMetadataVO> slice = bookMetadataService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
