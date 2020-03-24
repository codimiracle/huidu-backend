package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;
import com.codimiracle.application.platform.huidu.service.BookAudioEpisodeService;
import com.codimiracle.application.platform.huidu.service.HistoryService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
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

    @GetMapping("/last-read")
    public ApiResponse entity(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId) {
        HistoryVO historyVO;
        if (Objects.nonNull(user)) {
            historyVO = historyService.findByUserIdAndBookIdIntegrally(user.getId(), bookId);
            if (Objects.nonNull(historyVO)) {
                return RestfulUtil.entity(historyVO);
            }
        }

        //返回第一章
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeService.findByMediaNumberIntegrally(bookId, 1);
        if (Objects.isNull(bookAudioEpisodeVO)) {
            //无数据
            return RestfulUtil.entity(null);
        }
        historyVO = new HistoryVO();
        historyVO.setReadTime(null);
        historyVO.setAudioEpisode(bookAudioEpisodeVO);
        return RestfulUtil.entity(historyVO);
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeService.findByIdIntegrally(id);
        return RestfulUtil.entity(bookAudioEpisodeVO);
    }

    @GetMapping
    public ApiResponse collection(@PathVariable("book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookAudioEpisodeVO> slice = bookAudioEpisodeService.findAllIntegrally(bookId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
