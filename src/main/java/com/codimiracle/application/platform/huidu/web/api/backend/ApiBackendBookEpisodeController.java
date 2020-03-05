package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.BookEpisodeDTO;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/contents/electronic-books/{book_id}/episodes")
public class ApiBackendBookEpisodeController {
    @Resource
    private BookEpisodeService bookEpisodeService;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId, @RequestBody BookEpisodeDTO bookEpisodeDTO) {
        bookEpisodeDTO.setBookId(bookId);
        BookEpisode episode = BookEpisode.from(bookEpisodeDTO);
        episode.setCreateTime(new Date());
        episode.setUpdateTime(episode.getCreateTime());
        episode.setOwnerId(user.getId());
        BookEpisodeVO existsBookEpisode = bookEpisodeService.findByEpisodeNumberIntegrally(episode.getBookId(), episode.getEpisodeNumber());
        if (Objects.nonNull(existsBookEpisode)) {
            return RestfulUtil.fail("章节号对应的章节已经存在！");
        }
        bookEpisodeService.save(episode);
        return RestfulUtil.entity(bookEpisodeService.findByIdIntegrally(episode.getId()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        bookEpisodeService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse delete(String[] ids) {
        bookEpisodeService.deleteByIdsLogically(Arrays.asList(ids));
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @RequestBody BookEpisodeDTO bookEpisodeDTO) {
        BookEpisode episode = BookEpisode.from(bookEpisodeDTO);
        Objects.requireNonNull(episode, "没有需要更新的数据！");
        episode.setId(id);
        episode.setUpdateTime(new Date());
        BookEpisodeVO existsBookEpisode = bookEpisodeService.findByEpisodeNumberIntegrally(episode.getBookId(), episode.getEpisodeNumber());
        if (Objects.nonNull(existsBookEpisode) && !Objects.equals(existsBookEpisode.getId(), id)) {
            return RestfulUtil.fail("章节号对应的章节已经存在！");
        }
        bookEpisodeService.update(episode);
        return RestfulUtil.entity(bookEpisodeService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        BookEpisodeVO bookEpisodeVO = bookEpisodeService.findByIdIntegrally(id);
        return RestfulUtil.entity(bookEpisodeVO);
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

    @GetMapping
    public ApiResponse collection(@PathVariable("book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookEpisodeVO> slice = bookEpisodeService.findAllIntegrally(bookId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
