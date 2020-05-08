package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.Order;
import com.codimiracle.application.platform.huidu.entity.vo.OrderVO;
import com.codimiracle.application.platform.huidu.enumeration.OrderStatus;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface OrderMapper extends Mapper<Order, OrderVO> {
    OrderVO selectByOrderNumberIntegrally(String orderNumber);

    void changeStatus(@Param("orderNumber") String orderNumber, @Param("from") OrderStatus from, @Param("to") OrderStatus to);
}