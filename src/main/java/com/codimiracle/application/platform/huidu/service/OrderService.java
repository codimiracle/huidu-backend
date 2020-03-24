package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Order;
import com.codimiracle.application.platform.huidu.entity.po.OrderDetails;
import com.codimiracle.application.platform.huidu.entity.vo.OrderVO;
import com.codimiracle.application.platform.huidu.entity.vt.Comment;
import com.codimiracle.application.platform.huidu.enumeration.OrderStatus;
import org.joda.money.Money;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface OrderService extends Service<String, Order> {

    void changeStatus(String userId, String orderNumber, OrderStatus from, OrderStatus to);

    OrderVO findByOrderNumberIntegrally(String orderNumber);

    PageSlice<OrderVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    void chargebackByOrderNumber(String orderNumber);

    Order findByOrderNumber(String orderNumber);

    Money shipmentPrediction(String addressId, List<OrderDetails> orderDetails);

    void cancel(String userId, String orderNumber, OrderStatus from);

    void complete(String userId, String orderNumber, OrderStatus valueOfCode);

    void evaluate(String userId, String orderNumber, Comment comment);
}
