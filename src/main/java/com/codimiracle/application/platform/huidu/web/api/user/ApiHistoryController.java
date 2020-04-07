package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.ReadingHistoryDTO;
import com.codimiracle.application.platform.huidu.entity.po.History;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;
import com.codimiracle.application.platform.huidu.service.HistoryService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user/histories")
public class ApiHistoryController {
    @Resource
    private HistoryService historyService;

    @PostMapping
    public ApiResponse reading(@AuthenticationPrincipal User user, @Valid @RequestBody ReadingHistoryDTO readingHistoryDTO) {
        History history = History.from(readingHistoryDTO);
        history.setUserId(user.getId());
        history.setReadTime(new Date());
        historyService.save(history);
        return RestfulUtil.success();
    }


    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        historyService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<HistoryVO> slice = historyService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
