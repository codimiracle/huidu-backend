package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.base.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/electronic-books")
public class ApiElectronicBookController {
    @Autowired
    private BookController bookController;

    @Resource
    private BookEpisodeService bookEpisodeService;

    @GetMapping("/{id}")
    public ApiResponse entity(@AuthenticationPrincipal User user, @PathVariable String id, @RequestParam(value = "details", required = false) boolean details) {
        return bookController.entity(user, BookType.ElectronicBook, id, details);
    }

    @GetMapping("/{id}/last-updated-episode")
    public ApiResponse lastPublishedEpisode(@PathVariable("id") String bookId) {
        return RestfulUtil.entity(bookEpisodeService.findLastPublishedEpisodeByBookId(bookId));
    }

    @GetMapping("/{id}/review-stars")
    public ApiResponse reviewStars(@PathVariable("id") String bookId) {
        return bookController.reviewStars(bookId);
    }
    @GetMapping("/{id}/catalogs")
    public ApiResponse catalogs(@PathVariable("id") String id) {
        return bookController.publishedCatalogs(id);
    }

    @GetMapping("/publish-years")
    public ApiResponse publishYears() {
        return bookController.publishYears(BookType.ElectronicBook);
    }

    @GetMapping("/hots")
    public ApiResponse hotCollection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return bookController.hotCollection(BookType.ElectronicBook, filter, sorter, page);
    }

    @GetMapping("/search")
    public ApiResponse searchCollection(@RequestParam("q") String query) {
        Filter filter = new Filter();
        filter.put("metadataName", new String[]{query});
        return collection(filter, null, new Page());
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return bookController.publishCollection(BookType.ElectronicBook, filter, sorter, page);
    }
}
