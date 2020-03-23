package com.codimiracle.application.platform.huidu.web.api.user;/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.CommentDTO;
import com.codimiracle.application.platform.huidu.entity.dto.OrderringDTO;
import com.codimiracle.application.platform.huidu.entity.dto.ShipmentPredictionDTO;
import com.codimiracle.application.platform.huidu.entity.po.Order;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.service.OrderService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.base.OrderController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/user/orders")
public class ApiUserOrderController {
    @Resource
    private OrderController orderController;
    @Resource
    private OrderService orderService;

    @PostMapping("/shipment")
    public ApiResponse shipmentPrediction(@RequestBody ShipmentPredictionDTO shipmentPredictionDTO) {
        return orderController.shipmentPrediction(shipmentPredictionDTO);
    }

    @PostMapping("/orderring")
    public ApiResponse orderring(@AuthenticationPrincipal User user, @RequestBody OrderringDTO orderringDTO) {
        return orderController.orderring(user, orderringDTO);
    }

    @PostMapping("/{order_number}/evaluate")
    public ApiResponse evaluate(@AuthenticationPrincipal User user, @PathVariable("order_number") String orderNumber, @RequestBody CommentDTO commentDTO) {
        return orderController.evaluate(user, orderNumber, commentDTO);
    }

    @PostMapping("/{order_number}/cancel")
    public ApiResponse switchToCancel(@AuthenticationPrincipal User user, @PathVariable("order_number") String orderNumber, @RequestParam("status") String status) {
        return orderController.switchToCancel(user, orderNumber, status);
    }

    @PostMapping("/{order_number}/receive")
    public ApiResponse switchToEvaluate(@AuthenticationPrincipal User user, @PathVariable("order_number") String orderNumber, @RequestParam("status") String status) {
        return orderController.switchToEvaluate(user, orderNumber, status);
    }

    @GetMapping("/{order_number}")
    public ApiResponse entity(@AuthenticationPrincipal User user, @PathVariable("order_number") String orderNumber) {
        Order order = orderService.findByOrderNumber(orderNumber);
        if (Objects.isNull(order) || !Objects.equals(user.getId(), order.getOwnerId())) {
            return RestfulUtil.fail("订单不存在！");
        }
        return orderController.entity(orderNumber);
    }

    @GetMapping
    public ApiResponse collection(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("ownerId", new String[]{user.getId()});
        return orderController.collection(filter, sorter, page);
    }
}
