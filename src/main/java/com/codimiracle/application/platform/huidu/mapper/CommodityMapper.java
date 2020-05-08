package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.Commodity;
import com.codimiracle.application.platform.huidu.entity.vo.CommodityVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface CommodityMapper extends Mapper<Commodity, CommodityVO> {
    CommodityVO selectByIdIntegrally(String id);

    void deleteByIdsLogically(List<String> ids);

    void incrementSalesById(@Param("commodityId") String commodityId, @Param("quantity") Integer quantity);
}