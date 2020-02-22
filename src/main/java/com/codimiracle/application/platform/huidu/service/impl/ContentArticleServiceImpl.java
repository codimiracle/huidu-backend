package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.ContentArticle;
import com.codimiracle.application.platform.huidu.mapper.ContentArticleMapper;
import com.codimiracle.application.platform.huidu.service.ContentArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ContentArticleServiceImpl extends AbstractService<String, ContentArticle> implements ContentArticleService {
    @Resource
    private ContentArticleMapper contentArticleMapper;

}
