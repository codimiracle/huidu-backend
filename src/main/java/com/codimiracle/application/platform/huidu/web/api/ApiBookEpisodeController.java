package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//book/episode")
public class ApiBookEpisodeController {
    @Resource
    private BookEpisodeService bookEpisodeService;

    @PostMapping
    public ApiResponse create(@RequestBody BookEpisode bookEpisode) {
        bookEpisodeService.save(bookEpisode);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        bookEpisodeService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody BookEpisode bookEpisode) {
        bookEpisodeService.update(bookEpisode);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookEpisode bookEpisode = bookEpisodeService.findById(id);
        return RestfulUtil.success(bookEpisode);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<BookEpisode> slice = bookEpisodeService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
