package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.mapper.ContentMapper;
import com.codimiracle.application.platform.huidu.service.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ContentServiceImpl extends AbstractService<String, Content> implements ContentService {
    @Resource
    private ContentMapper contentMapper;

}
