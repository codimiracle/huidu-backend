package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.OrderDetails;
import com.codimiracle.application.platform.huidu.entity.vo.OrderDetailsVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface OrderDetailsMapper extends Mapper<OrderDetails, OrderDetailsVO> {
    List<OrderDetailsVO> selectByOrderNumber(String orderNumber);
}