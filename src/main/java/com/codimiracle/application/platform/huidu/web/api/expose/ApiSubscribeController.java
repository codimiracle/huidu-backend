package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.Subscribe;
import com.codimiracle.application.platform.huidu.service.SubscribeService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/subscribe")
public class ApiSubscribeController {
    @Resource
    private SubscribeService subscribeService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody Subscribe subscribe) {
        subscribeService.save(subscribe);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        subscribeService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@Valid @RequestBody Subscribe subscribe) {
        subscribeService.update(subscribe);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        Subscribe subscribe = subscribeService.findById(id);
        return RestfulUtil.success(subscribe);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<Subscribe> slice = subscribeService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
