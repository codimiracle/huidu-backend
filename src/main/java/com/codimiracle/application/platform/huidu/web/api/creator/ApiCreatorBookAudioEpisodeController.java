package com.codimiracle.application.platform.huidu.web.api.creator;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.BookAudioEpisodeDTO;
import com.codimiracle.application.platform.huidu.entity.po.BookAudioEpisode;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.service.BookAudioEpisodeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.base.BookAudioEpisodeController;
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
@RequestMapping("/api/creator/audio-books/{book_id}/episodes")
public class ApiCreatorBookAudioEpisodeController {

    @Autowired
    private BookAudioEpisodeController bookAudioEpisodeController;

    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId, @Valid @RequestBody BookAudioEpisodeDTO bookAudioEpisodeDTO) {
        return bookAudioEpisodeController.create(user, bookId, bookAudioEpisodeDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@AuthenticationPrincipal User user, @PathVariable String id) {
        BookAudioEpisode episode = bookAudioEpisodeService.findById(id);
        if (Objects.isNull(episode) || !Objects.equals(user.getId(), episode.getOwnerId())) {
            return RestfulUtil.fail("权限不足！");
        }
        return bookAudioEpisodeController.delete(id);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@AuthenticationPrincipal User user, @PathVariable("id") String id, @Valid @RequestBody BookAudioEpisodeDTO bookAudioEpisodeDTO) {
        BookAudioEpisode originalEpisode = bookAudioEpisodeService.findById(id);
        if (Objects.isNull(originalEpisode) || !Objects.equals(originalEpisode.getOwnerId(), user.getId())) {
            return RestfulUtil.fail("权限不足!");
        }
        return bookAudioEpisodeController.update(id, bookAudioEpisodeDTO);
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@AuthenticationPrincipal User user, @PathVariable String id) {
        BookAudioEpisode bookAudioEpisode = bookAudioEpisodeService.findById(id);
        if (Objects.isNull(bookAudioEpisode) || !Objects.equals(user.getId(), bookAudioEpisode.getOwnerId())) {
            return RestfulUtil.fail("权限不足！");
        }
        return bookAudioEpisodeController.entity(id);
    }

    @GetMapping("/last-updated-episode")
    public ApiResponse lastUpdateEpisode(@PathVariable("book_id") String bookId) {
        BookAudioEpisodeVO lastUpdatedEpisode = bookAudioEpisodeService.findLastUpdateEpisodeByBookIdIntegrally(bookId);
        return RestfulUtil.entity(lastUpdatedEpisode);
    }

    @GetMapping
    public ApiResponse collection(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("ownerId", new String[]{user.getId()});
        return bookAudioEpisodeController.collection(bookId, filter, sorter, page);
    }
}
