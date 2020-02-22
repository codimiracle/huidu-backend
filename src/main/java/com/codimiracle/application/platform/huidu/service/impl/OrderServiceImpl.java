package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Order;
import com.codimiracle.application.platform.huidu.mapper.OrderMapper;
import com.codimiracle.application.platform.huidu.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class OrderServiceImpl extends AbstractService<String, Order> implements OrderService {
    @Resource
    private OrderMapper orderMapper;

}
