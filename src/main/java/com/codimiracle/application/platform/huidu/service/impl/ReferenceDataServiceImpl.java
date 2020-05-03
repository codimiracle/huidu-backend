package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.ReferenceData;
import com.codimiracle.application.platform.huidu.mapper.ReferenceDataMapper;
import com.codimiracle.application.platform.huidu.service.ReferenceDataService;
import com.codimiracle.web.mybatis.contract.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ReferenceDataServiceImpl extends AbstractService<String, ReferenceData> implements ReferenceDataService {
    @Resource
    private ReferenceDataMapper referenceDataMapper;

}
