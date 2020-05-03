package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.Order;
import com.codimiracle.application.platform.huidu.entity.po.OrderDetails;
import com.codimiracle.application.platform.huidu.entity.vo.OrderVO;
import com.codimiracle.application.platform.huidu.enumeration.OrderStatus;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.middleware.content.pojo.po.Comment;
import com.codimiracle.web.mybatis.contract.support.vo.Service;
import org.joda.money.Money;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface OrderService extends Service<String, Order, OrderVO> {

    void changeStatus(String userId, String orderNumber, OrderStatus from, OrderStatus to);

    OrderVO findByOrderNumberIntegrally(String orderNumber);

    PageSlice<OrderVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    void chargebackByOrderNumber(String orderNumber);

    Order findByOrderNumber(String orderNumber);

    Money shipmentPrediction(String addressId, List<OrderDetails> orderDetails);

    void cancel(String userId, String orderNumber, OrderStatus from);

    void evaluate(String userId, String orderNumber, Comment comment);
}
