package com.codimiracle.application.platform.huidu.web.api.base;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.BookEpisodeDTO;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@Component
public class BookEpisodeController {
    @Resource
    private BookEpisodeService bookEpisodeService;

    public ApiResponse create(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId, @Valid @RequestBody BookEpisodeDTO bookEpisodeDTO) {
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

    public ApiResponse delete(@PathVariable String id) {
        bookEpisodeService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    public ApiResponse delete(String[] ids) {
        bookEpisodeService.deleteByIdsLogically(Arrays.asList(ids));
        return RestfulUtil.success();
    }

    public ApiResponse update(@PathVariable String id, @Valid @RequestBody BookEpisodeDTO bookEpisodeDTO) {
        BookEpisode bookEpisode = bookEpisodeService.findById(id);
        BookEpisode updatingEpisode = BookEpisode.from(bookEpisodeDTO);
        Objects.requireNonNull(updatingEpisode, "没有需要更新的数据！");
        updatingEpisode.setId(id);
        updatingEpisode.setUpdateTime(new Date());
        BookEpisodeVO existsBookEpisode = bookEpisodeService.findByEpisodeNumberIntegrally(bookEpisode.getBookId(), updatingEpisode.getEpisodeNumber());
        if (Objects.nonNull(existsBookEpisode) && !Objects.equals(existsBookEpisode.getId(), id)) {
            return RestfulUtil.fail("章节号对应的章节已经存在！");
        }
        bookEpisodeService.update(updatingEpisode);
        return RestfulUtil.entity(bookEpisodeService.findByIdIntegrally(id));
    }

    public ApiResponse entity(@PathVariable String id) {
        BookEpisodeVO bookEpisodeVO = bookEpisodeService.findByIdIntegrally(id);
        return RestfulUtil.entity(bookEpisodeVO);
    }

    public ApiResponse lastUpdateEpisode(@PathVariable("book_id") String bookId) {
        BookEpisodeVO lastUpdateEpisodeIntegrally = bookEpisodeService.findLastUpdateEpisodeByBookIdIntegrally(bookId);
        return RestfulUtil.entity(lastUpdateEpisodeIntegrally);
    }

    public ApiResponse lastEpisodeNumber(@PathVariable("book_id") String bookId) {
        Integer lastEpisodeNumber = bookEpisodeService.findLastEpisodeNumberByBookId(bookId);
        return RestfulUtil.success(Objects.nonNull(lastEpisodeNumber) ? lastEpisodeNumber : 0);
    }

    public ApiResponse collection(@PathVariable("book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookEpisodeVO> slice = bookEpisodeService.findAllIntegrally(bookId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
