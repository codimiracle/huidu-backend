package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.service.BookAudioEpisodeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.base.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/audio-books")
public class ApiAudioBookController {
    @Autowired
    private BookController bookController;

    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id, @RequestParam(value = "player", required = false) String player) {
        if (Objects.nonNull(player)) {
            bookController.playsIncrement(id);
        }
        return bookController.entity(BookType.AudioBook, id);
    }

    @GetMapping("/{id}/last-updated-episode")
    public ApiResponse lastPublishedEpisode(@PathVariable("id") String bookId) {
        return RestfulUtil.entity(bookAudioEpisodeService.findLastPublishedEpisodeByBookId(bookId));
    }

    @GetMapping("/{id}/catalogs")
    public ApiResponse catalogs(@PathVariable("id") String id) {
        return bookController.publishedAudioCatalogs(id);
    }

    @GetMapping("/publish-years")
    public ApiResponse publishYears() {
        return bookController.publishYears(BookType.AudioBook);
    }

    @GetMapping("/hots")
    public ApiResponse hotCollection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return bookController.hotCollection(BookType.AudioBook, filter, sorter, page);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return bookController.collection(BookType.AudioBook, filter, sorter, page);
    }
}
