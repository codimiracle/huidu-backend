package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.entity.dto.BulkAcceptationDTO;
import com.codimiracle.application.platform.huidu.entity.dto.BulkRejectionDTO;
import com.codimiracle.application.platform.huidu.entity.dto.ExaminationDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.service.ContentService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping("/api/backend/contents")
public class ApiBackendContentController {

    @Resource
    private ContentService contentService;

    @PostMapping("/{id}/reject")
    public ApiResponse reject(@AuthenticationPrincipal User user, @PathVariable("id") String contentId, @Valid @RequestBody ExaminationDTO examinationDTO) {
        contentService.rejectById(contentId, examinationDTO.getReason(), user.getId());
        return RestfulUtil.success();
    }

    @PostMapping("/reject")
    public ApiResponse bulkReject(@AuthenticationPrincipal User user, @Valid @RequestBody BulkRejectionDTO bulkRejectionDTO) {
        contentService.rejectByIds(Arrays.asList(bulkRejectionDTO.getIds()), bulkRejectionDTO.getReason(), user.getId());
        return RestfulUtil.success();
    }

    @PostMapping("/{id}/accept")
    public ApiResponse accept(@AuthenticationPrincipal User user, @PathVariable("id") String contentId, @Valid @RequestBody ExaminationDTO examinationDTO) {
        contentService.acceptById(contentId, examinationDTO.getReason(), user.getId());
        return RestfulUtil.success();
    }

    @PostMapping("/accept")
    public ApiResponse bulkAccept(@AuthenticationPrincipal User user, @Valid @RequestBody BulkAcceptationDTO bulkAcceptationDTO) {
        contentService.acceptByIds(Arrays.asList(bulkAcceptationDTO.getIds()), bulkAcceptationDTO.getReason(), user.getId());
        return RestfulUtil.success();
    }
}
