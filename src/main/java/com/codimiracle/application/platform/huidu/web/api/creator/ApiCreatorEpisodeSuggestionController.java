package com.codimiracle.application.platform.huidu.web.api.creator;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/episodes")
public class ApiCreatorEpisodeSuggestionController {
    @Resource
    private BookEpisodeService bookEpisodeService;

    @GetMapping("/suggestion")
    public ApiResponse suggestion(@RequestParam("keyword") String keyword) {
        Filter filter = new Filter();
        filter.put("title", new String[]{keyword});
        Sorter sorter = null;
        Page page = new Page();
        page.setPage(1);
        page.setLimit(10);
        PageSlice<BookEpisodeVO> slice = bookEpisodeService.findAllIntegrally(null, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    public ApiResponse collection(@PathVariable(value = "book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("status", new String[] {ContentStatus.Publish.toString()});
        PageSlice<BookEpisodeVO> slice = bookEpisodeService.findAllIntegrally(bookId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
