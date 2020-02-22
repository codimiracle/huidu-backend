package com.codimiracle.application.platform.huidu.web.api;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.po.OrderDetails;
import com.codimiracle.application.platform.huidu.service.OrderDetailsService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@RestController
@RequestMapping("api//order/details")
public class ApiOrderDetailsController {
    @Resource
    private OrderDetailsService orderDetailsService;

    @PostMapping
    public ApiResponse create(@RequestBody OrderDetails orderDetails) {
        orderDetailsService.save(orderDetails);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        orderDetailsService.deleteById(id);
        return RestfulUtil.success();
    }

    @PutMapping
    public ApiResponse update(@RequestBody OrderDetails orderDetails) {
        orderDetailsService.update(orderDetails);
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        OrderDetails orderDetails = orderDetailsService.findById(id);
        return RestfulUtil.success(orderDetails);
    }

    @GetMapping
    public ApiResponse collection(@ModelAttribute Page page) {
        PageSlice<OrderDetails> slice = orderDetailsService.findAll(page);
        return RestfulUtil.list(slice);
    }
}
