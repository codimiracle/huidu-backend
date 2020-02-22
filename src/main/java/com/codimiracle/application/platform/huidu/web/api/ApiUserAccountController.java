package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.UserAccount;
import com.codimiracle.application.platform.huidu.service.UserAccountService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//user/account")
public class ApiUserAccountController {
    @Resource
    private UserAccountService userAccountService;

    @PostMapping
    public ApiResponse create(@RequestBody UserAccount userAccount) {
        userAccountService.save(userAccount);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        userAccountService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody UserAccount userAccount) {
        userAccountService.update(userAccount);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        UserAccount userAccount = userAccountService.findById(id);
        return RestfulUtil.success(userAccount);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<UserAccount> slice = userAccountService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
