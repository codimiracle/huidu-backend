package com.codimiracle.application.platform.huidu.web.api.base;

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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@Component
public class BookAudioEpisodeController {
    @Resource
    private BookAudioEpisodeService bookAudioEpisodeService;
    @Resource
    private ReferenceDataService referenceDataService;

    public ApiResponse create(@AuthenticationPrincipal User user, @PathVariable("book_id") String bookId, @Valid @RequestBody BookAudioEpisodeDTO bookAudioEpisodeDTO) {
        BookAudioEpisode bookAudioEpisode = BookAudioEpisode.from(bookAudioEpisodeDTO);
        bookAudioEpisode.setOwnerId(user.getId());
        bookAudioEpisode.setBookId(bookId);
        bookAudioEpisode.setCreateTime(new Date());
        bookAudioEpisode.setUpdateTime(bookAudioEpisode.getCreateTime());
        BookAudioEpisodeVO existsBookAudioEpisode = bookAudioEpisodeService.findByMediaNumberIntegrally(bookAudioEpisode.getBookId(), bookAudioEpisode.getMediaNumber());
        if (Objects.nonNull(existsBookAudioEpisode)) {
            return RestfulUtil.fail("章节号对应的章节已经存在！");
        }
        if (Objects.nonNull(bookAudioEpisode.getStreamUrl())) {
            bookAudioEpisode.setDuration(getDurationByStreamUrl(bookAudioEpisode.getStreamUrl()));
        }
        bookAudioEpisodeService.save(bookAudioEpisode);
        return RestfulUtil.entity(bookAudioEpisodeService.findByIdIntegrally(bookAudioEpisode.getId()));
    }

    private long getDurationByStreamUrl(String streamUrl) {
        String referenceId = streamUrl.substring("reference-data:".length());
        ReferenceData referenceData = referenceDataService.findById(referenceId);
        File file = new File(referenceData.getFilePath());
        AudioFileFormat baseFileFormat = null;
        try {
            baseFileFormat = new MpegAudioFileReader().getAudioFileFormat(file);
            Map properties = baseFileFormat.properties();
            return (Long) properties.get("duration") / 1000;
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ApiResponse delete(@PathVariable String id) {
        bookAudioEpisodeService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    public ApiResponse update(@PathVariable("id") String id, @Valid @RequestBody BookAudioEpisodeDTO bookAudioEpisodeDTO) {
        BookAudioEpisode bookAudioEpisode = bookAudioEpisodeService.findById(id);
        BookAudioEpisode updatingBookAudioEpisode = BookAudioEpisode.from(bookAudioEpisodeDTO);
        updatingBookAudioEpisode.setId(id);
        BookAudioEpisodeVO existsBookAudioEpisode = bookAudioEpisodeService.findByMediaNumberIntegrally(bookAudioEpisode.getBookId(), updatingBookAudioEpisode.getMediaNumber());
        if (Objects.nonNull(existsBookAudioEpisode) && !Objects.equals(existsBookAudioEpisode.getId(), id)) {
            return RestfulUtil.fail("章节号对应的章节已经存在！");
        }
        if (Objects.nonNull(updatingBookAudioEpisode.getStreamUrl())) {
            updatingBookAudioEpisode.setDuration(getDurationByStreamUrl(updatingBookAudioEpisode.getStreamUrl()));
        }
        updatingBookAudioEpisode.setUpdateTime(new Date());
        bookAudioEpisodeService.update(updatingBookAudioEpisode);
        return RestfulUtil.entity(bookAudioEpisodeService.findByIdIntegrally(id));
    }

    public ApiResponse entity(@PathVariable String id) {
        BookAudioEpisodeVO bookAudioEpisodeVO = bookAudioEpisodeService.findByIdIntegrally(id);
        return RestfulUtil.entity(bookAudioEpisodeVO);
    }

    public ApiResponse collection(@PathVariable("book_id") String bookId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<BookAudioEpisodeVO> slice = bookAudioEpisodeService.findAllIntegrally(bookId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }

    public ApiResponse lastEpisodeNumber(String bookId) {
        Integer lastMediaNumber = bookAudioEpisodeService.findLastMediaNumberByBookId(bookId);
        return RestfulUtil.success(lastMediaNumber);
    }
}
