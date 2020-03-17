package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.OrderDetails;
import com.codimiracle.application.platform.huidu.entity.vo.OrderDetailsVO;

import java.util.List;

public interface OrderDetailsMapper extends Mapper<OrderDetails> {
    List<OrderDetailsVO> selectByOrderNumber(String orderNumber);
}