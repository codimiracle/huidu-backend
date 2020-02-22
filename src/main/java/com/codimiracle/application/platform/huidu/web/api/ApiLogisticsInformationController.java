package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.LogisticsInformation;
import com.codimiracle.application.platform.huidu.service.LogisticsInformationService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//logistics/information")
public class ApiLogisticsInformationController {
    @Resource
    private LogisticsInformationService logisticsInformationService;

    @PostMapping
    public ApiResponse create(@RequestBody LogisticsInformation logisticsInformation) {
        logisticsInformationService.save(logisticsInformation);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        logisticsInformationService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody LogisticsInformation logisticsInformation) {
        logisticsInformationService.update(logisticsInformation);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        LogisticsInformation logisticsInformation = logisticsInformationService.findById(id);
        return RestfulUtil.success(logisticsInformation);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<LogisticsInformation> slice = logisticsInformationService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
