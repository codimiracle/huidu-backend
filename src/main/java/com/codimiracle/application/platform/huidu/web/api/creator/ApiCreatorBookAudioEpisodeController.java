package com.codimiracle.application.platform.huidu.web.api.creator;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.BookAudioEpisodeDTO;
import com.codimiracle.application.platform.huidu.entity.po.BookAudioEpisode;
import com.codimiracle.application.platform.huidu.entity.po.ReferenceData;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.service.BookAudioEpisodeService;
import com.codimiracle.application.platform.huidu.service.ReferenceDataService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/creator/audio-books/{book_id}/episodes")
public class ApiCreatorBookAudioEpisodeController {
    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;
    @Resource
    private ReferenceDataService referenceDataService;

    @PostMapping
    public ApiResponse create(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId, @RequestBody BookAudioEpisodeDTO bookAudioEpisodeDTO) {
        BookAudioEpisode bookAudioEpisode = BookAudioEpisode.from(bookAudioEpisodeDTO);
        bookAudioEpisode.setOwnerId(user.getId());
        bookAudioEpisode.setBookId(bookId);
        bookAudioEpisode.setCreateTime(new Date());
        bookAudioEpisode.setUpdateTime(bookAudioEpisode.getCreateTime());
        if (Objects.nonNull(bookAudioEpisode.getStreamUrl())) {
            bookAudioEpisode.setDuration(getDurationByStreamUrl(bookAudioEpisode.getStreamUrl()));
        }
        bookAudioEpisodeService.save(bookAudioEpisode);
        return RestfulUtil.entity(bookAudioEpisodeService.findByIdIntegrally(bookAudioEpisode.getId()));
    }

    private long getDurationByStreamUrl(String streamUrl) {
        String referenceId = streamUrl.substring(streamUrl.lastIndexOf("/") + 1);
        ReferenceData referenceData = referenceDataService.findById(referenceId);
        File file = new File(referenceData.getFilePath());
        AudioFileFormat baseFileFormat = null;
        try {
            baseFileFormat = new MpegAudioFileReader().getAudioFileFormat(file);
            Map properties = baseFileFormat.properties();
            return (Long) properties.get("duration") / 1000000;
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@AuthenticationPrincipal User user, @PathVariable String id) {
        BookAudioEpisode episode = bookAudioEpisodeService.findById(id);
        if (!Objects.equals(user.getId(), episode.getOwnerId())) {
            return RestfulUtil.fail("权限不足！");
        }
        bookAudioEpisodeService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@AuthenticationPrincipal User user, @PathVariable("id") String id, @RequestBody BookAudioEpisodeDTO bookAudioEpisodeDTO) {
        BookAudioEpisode originalEpisode = bookAudioEpisodeService.findById(id);
        if (!Objects.equals(originalEpisode.getOwnerId(), user.getId())) {
            return RestfulUtil.fail("权限不足!");
        }
        BookAudioEpisode bookAudioEpisode = BookAudioEpisode.from(bookAudioEpisodeDTO);
        bookAudioEpisode.setId(id);
        if (Objects.nonNull(bookAudioEpisode.getStreamUrl())) {
            bookAudioEpisode.setDuration(getDurationByStreamUrl(bookAudioEpisode.getStreamUrl()));
        }
        bookAudioEpisode.setUpdateTime(new Date());
        bookAudioEpisodeService.update(bookAudioEpisode);
        return RestfulUtil.entity(bookAudioEpisodeService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@AuthenticationPrincipal User user, @PathVariable String id) {
        BookAudioEpisode bookAudioEpisode = bookAudioEpisodeService.findById(id);
        if (!Objects.equals(user.getId(), bookAudioEpisode.getOwnerId())) {
            return RestfulUtil.fail("权限不足！");
        }
        return RestfulUtil.entity(bookAudioEpisodeService.findByIdIntegrally(id));
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
        PageSlice<BookAudioEpisodeVO> slice = bookAudioEpisodeService.findAllIntegrally(bookId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
