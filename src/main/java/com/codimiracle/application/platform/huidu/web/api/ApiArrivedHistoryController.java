package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.ArrivedHistory;
import com.codimiracle.application.platform.huidu.service.ArrivedHistoryService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//arrived/history")
public class ApiArrivedHistoryController {
    @Resource
    private ArrivedHistoryService arrivedHistoryService;

    @PostMapping
    public ApiResponse create(@RequestBody ArrivedHistory arrivedHistory) {
        arrivedHistoryService.save(arrivedHistory);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        arrivedHistoryService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody ArrivedHistory arrivedHistory) {
        arrivedHistoryService.update(arrivedHistory);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        ArrivedHistory arrivedHistory = arrivedHistoryService.findById(id);
        return RestfulUtil.success(arrivedHistory);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<ArrivedHistory> slice = arrivedHistoryService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
