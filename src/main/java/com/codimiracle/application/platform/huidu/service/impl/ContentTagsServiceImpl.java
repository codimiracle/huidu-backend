package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.ContentTags;
import com.codimiracle.application.platform.huidu.mapper.ContentTagsMapper;
import com.codimiracle.application.platform.huidu.service.ContentTagsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ContentTagsServiceImpl extends AbstractService<String, ContentTags> implements ContentTagsService {
    @Resource
    private ContentTagsMapper contentTagsMapper;

}
