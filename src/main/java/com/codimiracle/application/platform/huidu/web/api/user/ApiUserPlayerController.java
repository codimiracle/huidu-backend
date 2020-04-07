package com.codimiracle.application.platform.huidu.web.api.user;


import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.entity.dto.ReadingHistoryDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;
import com.codimiracle.application.platform.huidu.service.HistoryService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/user/player")
public class ApiUserPlayerController {

    @Resource
    private HistoryService historyService;

    @PostMapping("/history")
    public ApiResponse playing(@AuthenticationPrincipal User user, @Valid @RequestBody ReadingHistoryDTO readingHistoryDTO) {
        if (Objects.nonNull(readingHistoryDTO.getAudioEpisodeId())) {
            historyService.accessAudioEpisode(user.getId(), readingHistoryDTO.getBookId(), readingHistoryDTO.getAudioEpisodeId(), readingHistoryDTO.getProgress());
            return RestfulUtil.success();
        }
        return RestfulUtil.fail("没有指定章节号！");
    }

    @GetMapping("/history/last-read")
    public ApiResponse lastRead(@AuthenticationPrincipal User user, @RequestParam("book_id") String bookId) {
        HistoryVO historyVO = historyService.findLastReadByUserIdAndBookIdIntegrallyOrFirstEpisode(user.getId(), bookId);
        return RestfulUtil.entity(historyVO);
    }

    @GetMapping("/history")
    public ApiResponse episodeReadHistory(@AuthenticationPrincipal User user, @RequestParam("book_id") String bookId, @RequestParam("episode_id") String episodeId) {
        HistoryVO historyVO = historyService.findByUserIdAndBookIdAndAudioEpisodeIdOrNewIntegrally(user.getId(), bookId, episodeId);
        return RestfulUtil.entity(historyVO);
    }
}
