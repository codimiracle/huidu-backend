package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Commodity;
import com.codimiracle.application.platform.huidu.mapper.CommodityMapper;
import com.codimiracle.application.platform.huidu.service.CommodityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class CommodityServiceImpl extends AbstractService<String, Commodity> implements CommodityService {
    @Resource
    private CommodityMapper commodityMapper;

}
