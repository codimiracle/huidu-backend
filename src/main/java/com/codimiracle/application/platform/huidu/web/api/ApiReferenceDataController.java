package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.ReferenceData;
import com.codimiracle.application.platform.huidu.service.ReferenceDataService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//reference/data")
public class ApiReferenceDataController {
    @Resource
    private ReferenceDataService referenceDataService;

    @PostMapping
    public ApiResponse create(@RequestBody ReferenceData referenceData) {
        referenceDataService.save(referenceData);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        referenceDataService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody ReferenceData referenceData) {
        referenceDataService.update(referenceData);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        ReferenceData referenceData = referenceDataService.findById(id);
        return RestfulUtil.success(referenceData);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<ReferenceData> slice = referenceDataService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
