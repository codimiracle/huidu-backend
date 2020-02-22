package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.mapper.TagMapper;
import com.codimiracle.application.platform.huidu.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class TagServiceImpl extends AbstractService<String, Tag> implements TagService {
    @Resource
    private TagMapper tagMapper;

}
