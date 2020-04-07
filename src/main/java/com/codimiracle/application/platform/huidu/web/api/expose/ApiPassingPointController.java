package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.PassingPoint;
import com.codimiracle.application.platform.huidu.service.PassingPointService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/passing/point")
public class ApiPassingPointController {
    @Resource
    private PassingPointService passingPointService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody PassingPoint passingPoint) {
        passingPointService.save(passingPoint);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        passingPointService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@Valid @RequestBody PassingPoint passingPoint) {
        passingPointService.update(passingPoint);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        PassingPoint passingPoint = passingPointService.findById(id);
        return RestfulUtil.success(passingPoint);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<PassingPoint> slice = passingPointService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
