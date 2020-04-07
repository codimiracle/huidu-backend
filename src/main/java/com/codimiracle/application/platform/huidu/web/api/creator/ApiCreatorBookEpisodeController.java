package com.codimiracle.application.platform.huidu.web.api.creator;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.BookEpisodeDTO;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.base.BookEpisodeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/creator/electronic-books/{book_id}/episodes")
public class ApiCreatorBookEpisodeController {
    @Resource
    private BookEpisodeService bookEpisodeService;

    @Autowired
    private BookEpisodeController bookEpisodeController;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId, @Valid @RequestBody BookEpisodeDTO bookEpisodeDTO) {
        return bookEpisodeController.create(user, bookId, bookEpisodeDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@AuthenticationPrincipal User user, @PathVariable String id) {
        BookEpisode originalEpisode = bookEpisodeService.findById(id);
        if (Objects.isNull(originalEpisode) || !Objects.equals(user.getId(), originalEpisode.getOwnerId())) {
            return RestfulUtil.fail("权限不足！");
        }
        return bookEpisodeController.delete(id);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@AuthenticationPrincipal User user, @PathVariable String id, @Valid @RequestBody BookEpisodeDTO bookEpisodeDTO) {
        BookEpisode originalEpisode = bookEpisodeService.findById(id);
        if (Objects.isNull(originalEpisode) || !Objects.equals(originalEpisode.getOwnerId(), user.getId())) {
            return RestfulUtil.fail("没有找到该章节！");
        }
        ContentStatus status = ContentStatus.valueOfCode(bookEpisodeDTO.getStatus());
        if (status == ContentStatus.Draft || status == ContentStatus.Examining) {
            return bookEpisodeController.update(id, bookEpisodeDTO);
        }
        return RestfulUtil.fail("不能直接发布书籍！");
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        return bookEpisodeController.entity(id);
    }

    @GetMapping("/last-updated-episode")
    public ApiResponse lastUpdateEpisode(@PathVariable("book_id") String bookId) {
        return bookEpisodeController.lastUpdateEpisode(bookId);
    }

    @GetMapping("/last-episode-number")
    public ApiResponse lastEpisodeNumber(@PathVariable("book_id") String bookId) {
        return bookEpisodeController.lastEpisodeNumber(bookId);
    }

    @GetMapping
    public ApiResponse collection(@PathVariable("book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return bookEpisodeController.collection(bookId, filter, sorter, page);
    }
}
