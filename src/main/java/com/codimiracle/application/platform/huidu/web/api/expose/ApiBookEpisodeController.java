package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.service.HistoryService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/electronic-books/{book_id}/episodes")
public class ApiBookEpisodeController {
    @Resource
    private BookEpisodeService bookEpisodeService;

    @Resource
    private HistoryService historyService;

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookEpisodeVO bookEpisodeVO = bookEpisodeService.findByIdIntegrally(id);
        return RestfulUtil.entity(bookEpisodeVO);
    }

    @GetMapping("/first-episode")
    public ApiResponse firstEpisode(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId) {
        BookEpisodeVO episodeVO = bookEpisodeService.findByEpisodeNumberIntegrally(bookId, 1);
        if (Objects.nonNull(episodeVO) && !Objects.equals(episodeVO.getStatus(), ContentStatus.Publish.toString())) {
            return RestfulUtil.entity(null);
        }
        return RestfulUtil.entity(episodeVO);
    }

    @GetMapping("/last-update-episode")
    public ApiResponse lastUpdateEpisode(@PathVariable("book_id") String bookId) {
        BookEpisodeVO lastUpdateEpisodeIntegrally = bookEpisodeService.findLastUpdateEpisodeByBookIdIntegrally(bookId);
        return RestfulUtil.entity(lastUpdateEpisodeIntegrally);
    }

    @GetMapping("/last-episode-number")
    public ApiResponse lastEpisodeNumber(@PathVariable("book_id") String bookId) {
        Integer lastEpisodeNumber = bookEpisodeService.findLastEpisodeNumberByBookId(bookId);
        return RestfulUtil.success(Objects.nonNull(lastEpisodeNumber) ? lastEpisodeNumber : 0);
    }

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

    @GetMapping
    public ApiResponse collection(@PathVariable(value = "book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.nonNull(filter) ? filter : new Filter();
        filter.put("status", new String[]{ContentStatus.Publish.toString()});
        PageSlice<BookEpisodeVO> slice = bookEpisodeService.findAllIntegrally(bookId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
