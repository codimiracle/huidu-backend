package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.CartItem;
import com.codimiracle.application.platform.huidu.entity.vo.CartItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCartMapper extends Mapper<CartItem> {
    void deleteByIdLogically(String id);

    void settleByIdLogically(String id);

    CartItemVO selectByIdIntegrally(String id);

    List<CartItemVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<CartItemVO> selectByIdsIntegrally(@Param("ids") List<String> ids);

    void deleteByIdsLogically(@Param("ids") List<String> ids);

    Boolean existsByUserIdAndCommodityId(String userId, String commodityId);
}