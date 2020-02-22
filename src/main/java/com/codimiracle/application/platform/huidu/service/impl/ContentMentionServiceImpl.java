package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.ContentMention;
import com.codimiracle.application.platform.huidu.mapper.ContentMentionMapper;
import com.codimiracle.application.platform.huidu.service.ContentMentionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ContentMentionServiceImpl extends AbstractService<String, ContentMention> implements ContentMentionService {
    @Resource
    private ContentMentionMapper contentMentionMapper;

}
