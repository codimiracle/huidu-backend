package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.mapper.ContentReferenceMapper;
import com.codimiracle.application.platform.huidu.service.ContentReferenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ContentReferenceServiceImpl extends AbstractService<String, ContentReference> implements ContentReferenceService {
    @Resource
    private ContentReferenceMapper contentReferenceMapper;

}
