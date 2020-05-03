package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.ApiResponse;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.notification.middleware.pojo.po.Subscription;
import com.codimiracle.web.notification.middleware.service.SubscriptionService;
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
    private SubscriptionService subscriptionService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody Subscription subscription) {
        subscriptionService.save(subscription);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        subscriptionService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@Valid @RequestBody Subscription subscription) {
        subscriptionService.update(subscription);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        Subscription subscription = subscriptionService.findById(id);
        return RestfulUtil.success(subscription);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<Subscription> slice = subscriptionService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
