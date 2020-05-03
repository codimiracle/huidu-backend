package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.enumeration.BookAudioEpisodeStatus;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.service.BookAudioEpisodeService;
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
@RequestMapping("/api/audio-books/{book_id}/episodes")
public class ApiBookAudioEpisodeController {
    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;

    @Resource
    private HistoryService historyService;

    @GetMapping("/first-episode")
    public ApiResponse firstEpisode(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId) {
        //返回第一章
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeService.findByMediaNumberIntegrally(bookId, 1);
        if (Objects.nonNull(bookAudioEpisodeVO) && !Objects.equals(bookAudioEpisodeVO.getStatus(), BookAudioEpisodeStatus.Publish.toString())) {
            return RestfulUtil.entity(null);
        }
        return RestfulUtil.entity(bookAudioEpisodeVO);
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeService.findByIdIntegrally(id);
        return RestfulUtil.entity(bookAudioEpisodeVO);
    }

    @GetMapping
    public ApiResponse collection(@PathVariable("book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.nonNull(filter) ? filter : new Filter();
        filter.put("status", new String[]{ContentStatus.Publish.toString()});
        PageSlice<BookAudioEpisodeVO> slice = bookAudioEpisodeService.findAllIntegrally(bookId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
