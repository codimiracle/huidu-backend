package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.CartItem;
import com.codimiracle.application.platform.huidu.entity.vo.CartItemVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCartMapper extends Mapper<CartItem, CartItemVO> {
    void settleByIdLogically(String id);

    CartItemVO selectByIdIntegrally(String id);

    List<CartItemVO> selectByIdsIntegrally(@Param("ids") List<String> ids);

    void deleteByIdsLogically(@Param("ids") List<String> ids);

    Boolean existsByUserIdAndCommodityId(String userId, String commodityId);
}