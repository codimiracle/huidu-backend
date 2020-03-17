package com.codimiracle.application.platform.huidu.web.api.base;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.LogisticsInformationDTO;
import com.codimiracle.application.platform.huidu.entity.dto.OrderringDTO;
import com.codimiracle.application.platform.huidu.entity.po.LogisticsInformation;
import com.codimiracle.application.platform.huidu.entity.po.Order;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.OrderVO;
import com.codimiracle.application.platform.huidu.enumeration.OrderStatus;
import com.codimiracle.application.platform.huidu.service.LogisticsInformationService;
import com.codimiracle.application.platform.huidu.service.OrderService;
import com.codimiracle.application.platform.huidu.util.OrderNumberUtil;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@Component
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private LogisticsInformationService logisticsInformationService;

    @PostMapping("/orderring")
    public synchronized ApiResponse orderring(@AuthenticationPrincipal User user, @RequestBody OrderringDTO orderringDTO) {
        Order order = Order.form(orderringDTO);
        order.setOwnerId(user.getId());
        order.setStatus(OrderStatus.AwaitingPayment);
        order.setCreateTime(new Date());
        order.setOrderNumber(OrderNumberUtil.getOrderNumber(user.getId(), order.getType()));
        orderService.save(order);
        return RestfulUtil.entity(orderService.findByOrderNumberIntegrally(order.getOrderNumber()));
    }

    @DeleteMapping("/{order_number}/chargeback")
    public ApiResponse delete(@PathVariable String orderNumber) {
        orderService.chargebackByOrderNumber(orderNumber);
        return RestfulUtil.success();
    }

    @PostMapping("/{order_number}/logistics-information")
    public ApiResponse updateLogisticsInformation(@PathVariable("order_number") String orderNumber, @RequestBody LogisticsInformationDTO logisticsInformationDTO) {
        Order order = orderService.findById(orderNumber);
        LogisticsInformation logisticsInformation = LogisticsInformation.from(logisticsInformationDTO);
        logisticsInformation.setId(order.getLogisticsInformationId());
        if (Objects.isNull(order.getLogisticsInformationId())) {
            logisticsInformationService.save(logisticsInformation);
            Order updatedOrder = new Order();
            updatedOrder.setStatus(OrderStatus.AwaitingDelivery);
            updatedOrder.setOrderNumber(orderNumber);
            updatedOrder.setLogisticsInformationId(logisticsInformation.getId());
            orderService.update(updatedOrder);
        } else {
            logisticsInformationService.update(logisticsInformation);
        }
        return RestfulUtil.entity(logisticsInformationService.findByIdIntegrally(logisticsInformation.getId()));
    }

    @GetMapping("/{order_number}")
    public ApiResponse entity(@PathVariable("order_number") String orderNumber) {
        OrderVO orderVO = orderService.findByOrderNumberIntegrally(orderNumber);
        return RestfulUtil.entity(orderVO);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<OrderVO> slice = orderService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
