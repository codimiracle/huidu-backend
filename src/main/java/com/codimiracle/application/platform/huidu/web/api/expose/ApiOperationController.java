package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.Operation;
import com.codimiracle.application.platform.huidu.service.OperationService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/operation")
public class ApiOperationController {
    @Resource
    private OperationService operationService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody Operation operation) {
        operationService.save(operation);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        operationService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@Valid @RequestBody Operation operation) {
        operationService.update(operation);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        Operation operation = operationService.findById(id);
        return RestfulUtil.success(operation);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<Operation> slice = operationService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
