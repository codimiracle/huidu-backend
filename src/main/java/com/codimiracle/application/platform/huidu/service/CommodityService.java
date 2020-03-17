package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Commodity;
import com.codimiracle.application.platform.huidu.entity.vo.CommodityVO;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface CommodityService extends Service<String, Commodity> {

    CommodityVO findByIdIntegrally(String id);

    PageSlice<CommodityVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);
}
