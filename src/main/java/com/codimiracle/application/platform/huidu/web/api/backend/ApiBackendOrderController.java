package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.dto.LogisticsInformationDTO;
import com.codimiracle.application.platform.huidu.service.LogisticsInformationService;
import com.codimiracle.application.platform.huidu.service.OrderService;
import com.codimiracle.application.platform.huidu.web.api.base.OrderController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/shopping/orders")
public class ApiBackendOrderController {
    @Resource
    private OrderController orderController;

    @Resource
    private OrderService orderService;

    @Resource
    private LogisticsInformationService logisticsInformationService;

    @DeleteMapping("/{order_number}/chargeback")
    public ApiResponse delete(@PathVariable String orderNumber) {
        return orderController.delete(orderNumber);
    }

    @PostMapping("/{order_number}/logistics-information")
    public ApiResponse updateLogisticsInformation(@PathVariable("order_number") String orderNumber, @RequestBody LogisticsInformationDTO logisticsInformationDTO) {
        return orderController.updateLogisticsInformation(orderNumber, logisticsInformationDTO);
    }

    @GetMapping("/{order_number}")
    public ApiResponse entity(@PathVariable("order_number") String orderNumber) {
        return orderController.entity(orderNumber);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        return orderController.collection(filter, sorter, page);
    }
}
