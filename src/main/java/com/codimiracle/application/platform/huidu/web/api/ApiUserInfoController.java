package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.UserInfo;
import com.codimiracle.application.platform.huidu.service.UserInfoService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//user/info")
public class ApiUserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @PostMapping
    public ApiResponse create(@RequestBody UserInfo userInfo) {
        userInfoService.save(userInfo);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        userInfoService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody UserInfo userInfo) {
        userInfoService.update(userInfo);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        UserInfo userInfo = userInfoService.findById(id);
        return RestfulUtil.success(userInfo);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<UserInfo> slice = userInfoService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
