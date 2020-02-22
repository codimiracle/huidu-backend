package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.Commodity;
import com.codimiracle.application.platform.huidu.service.CommodityService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//commodity")
public class ApiCommodityController {
    @Resource
    private CommodityService commodityService;

    @PostMapping
    public ApiResponse create(@RequestBody Commodity commodity) {
        commodityService.save(commodity);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        commodityService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody Commodity commodity) {
        commodityService.update(commodity);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        Commodity commodity = commodityService.findById(id);
        return RestfulUtil.success(commodity);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<Commodity> slice = commodityService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
