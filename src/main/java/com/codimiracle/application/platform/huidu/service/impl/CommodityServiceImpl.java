package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Commodity;
import com.codimiracle.application.platform.huidu.entity.vo.CommodityVO;
import com.codimiracle.application.platform.huidu.mapper.CommodityMapper;
import com.codimiracle.application.platform.huidu.service.CommodityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class CommodityServiceImpl extends AbstractService<String, Commodity> implements CommodityService {
    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public CommodityVO findByIdIntegrally(String id) {
        return commodityMapper.selectByIdIntegrally(id);
    }

    @Override
    public PageSlice<CommodityVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(commodityMapper.selectAllIntegrally(filter, sorter, page));
    }

    @Override
    public void deleteByIdLogically(String id) {
        commodityMapper.deleteByIdLogically(id);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        commodityMapper.deleteByIdsLogically(ids);
    }

    @Override
    public void incrementSalesById(String commodityId, Integer quantity) {
        commodityMapper.incrementSalesById(commodityId, quantity);
    }
}
