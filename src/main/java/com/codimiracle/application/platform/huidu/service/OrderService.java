package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Order;
import com.codimiracle.application.platform.huidu.entity.vo.OrderVO;
import com.codimiracle.application.platform.huidu.enumeration.PaymentType;


/**
 * @author Codimiracle
 */
public interface OrderService extends Service<String, Order> {

    OrderVO findByOrderNumberIntegrally(String orderNumber);

    PageSlice<OrderVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    void chargebackByOrderNumber(String orderNumber);

    Order findByOrderNumber(String orderNumber);
}
