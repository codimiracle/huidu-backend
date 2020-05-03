package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.entity.dto.BulkAcceptationDTO;
import com.codimiracle.application.platform.huidu.entity.dto.BulkRejectionDTO;
import com.codimiracle.application.platform.huidu.entity.dto.ExaminationDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.ApiResponse;
import com.codimiracle.web.middleware.content.service.ExaminationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/backend/contents")
public class ApiBackendContentController {

    @Resource
    private ExaminationService examinationService;

    @PostMapping("/{id}/reject")
    public ApiResponse reject(@AuthenticationPrincipal User user, @PathVariable("id") String contentId, @Valid @RequestBody ExaminationDTO examinationDTO) {
        examinationService.reject(contentId, user.getId(), examinationDTO.getReason());
        return RestfulUtil.success();
    }

    @PostMapping("/reject")
    public ApiResponse bulkReject(@AuthenticationPrincipal User user, @Valid @RequestBody BulkRejectionDTO bulkRejectionDTO) {
        //examinationService.reject(Arrays.asList(bulkRejectionDTO.getIds()), bulkRejectionDTO.getReason(), user.getId());
        return RestfulUtil.success();
    }

    @PostMapping("/{id}/accept")
    public ApiResponse accept(@AuthenticationPrincipal User user, @PathVariable("id") String contentId, @Valid @RequestBody ExaminationDTO examinationDTO) {
        examinationService.accept(contentId, user.getId(), examinationDTO.getReason());
        return RestfulUtil.success();
    }

    @PostMapping("/accept")
    public ApiResponse bulkAccept(@AuthenticationPrincipal User user, @Valid @RequestBody BulkAcceptationDTO bulkAcceptationDTO) {
        //examinationService.accept(Arrays.asList(bulkAcceptationDTO.getIds()), user.getId(), bulkAcceptationDTO.getReason());
        return RestfulUtil.success();
    }
}
