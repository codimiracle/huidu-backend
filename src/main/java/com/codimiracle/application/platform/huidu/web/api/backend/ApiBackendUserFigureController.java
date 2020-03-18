package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.service.TagService;
import com.codimiracle.application.platform.huidu.service.UserFigureService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/api/backend/user-figures/{user_id}")
public class ApiBackendUserFigureController {
    @Resource
    private UserFigureService userFigureService;

    @Resource
    private TagService tagService;

    @GetMapping("/similar-users")
    public ApiResponse similarUserCollection(@PathVariable("user_id") String userId, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<UserProtectedVO> slice = userFigureService.findSimilarUserByUserIdProtectly(userId, filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
