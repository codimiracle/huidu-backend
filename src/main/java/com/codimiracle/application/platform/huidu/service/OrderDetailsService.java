package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.OrderDetails;
import com.codimiracle.application.platform.huidu.entity.vo.OrderDetailsVO;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface OrderDetailsService extends Service<String, OrderDetails> {

    List<OrderDetailsVO> findByOrderNumberIntegrally(String orderNumber);
}
