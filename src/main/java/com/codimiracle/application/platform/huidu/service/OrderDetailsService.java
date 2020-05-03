package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.OrderDetails;
import com.codimiracle.application.platform.huidu.entity.vo.OrderDetailsVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface OrderDetailsService extends Service<String, OrderDetails, OrderDetailsVO> {

    List<OrderDetailsVO> findByOrderNumberIntegrally(String orderNumber);

    List<OrderDetails> findByOrderNumber(String orderNumber);

}
