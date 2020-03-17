package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.OrderDetails;
import com.codimiracle.application.platform.huidu.entity.vo.OrderDetailsVO;
import com.codimiracle.application.platform.huidu.mapper.OrderDetailsMapper;
import com.codimiracle.application.platform.huidu.service.OrderDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class OrderDetailsServiceImpl extends AbstractService<String, OrderDetails> implements OrderDetailsService {
    @Resource
    private OrderDetailsMapper orderDetailsMapper;

    @Override
    public List<OrderDetailsVO> findByOrderNumberIntegrally(String orderNumber) {
        return orderDetailsMapper.selectByOrderNumber(orderNumber);
    }
}
