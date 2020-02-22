package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.BookAudioEpisode;
import com.codimiracle.application.platform.huidu.service.BookAudioEpisodeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//book/audio/episode")
public class ApiBookAudioEpisodeController {
    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;

    @PostMapping
    public ApiResponse create(@RequestBody BookAudioEpisode bookAudioEpisode) {
        bookAudioEpisodeService.save(bookAudioEpisode);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        bookAudioEpisodeService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody BookAudioEpisode bookAudioEpisode) {
        bookAudioEpisodeService.update(bookAudioEpisode);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookAudioEpisode bookAudioEpisode = bookAudioEpisodeService.findById(id);
        return RestfulUtil.success(bookAudioEpisode);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<BookAudioEpisode> slice = bookAudioEpisodeService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
