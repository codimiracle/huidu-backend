package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.Commodity;
import com.codimiracle.application.platform.huidu.entity.vo.CommodityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper extends Mapper<Commodity> {
    CommodityVO selectByIdIntegrally(String id);

    List<CommodityVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    void incrementSalesById(@Param("commodityId") String commodityId, @Param("quantity") Integer quantity);
}