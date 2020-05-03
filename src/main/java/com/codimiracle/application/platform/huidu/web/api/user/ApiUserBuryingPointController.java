package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.service.BuryingPointService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.ApiResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/api/user/burying-points")
public class ApiUserBuryingPointController {

    @Resource
    private BuryingPointService buryingPointService;

    @GetMapping("hit-tag")
    public ApiResponse hitTag(@AuthenticationPrincipal User user, @RequestParam("tag_id") String tagId, @RequestParam("score") Float score) {
        buryingPointService.forTag(user.getId(), tagId, score);
        return RestfulUtil.success();
    }
}
