package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;
import com.codimiracle.application.platform.huidu.mapper.TagMapper;
import com.codimiracle.application.platform.huidu.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class TagServiceImpl extends AbstractService<String, Tag> implements TagService {
    @Resource
    private TagMapper tagMapper;

    @Override
    public TagVO findByIdIntegrally(String id) {
        return tagMapper.selectByIdIntegrally(id);
    }

    @Override
    public PageSlice<TagVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(tagMapper.selectAllIntegrally(filter, sorter, page));
    }

    @Override
    public List<Tag> findByTagNames(List<String> tagNames) {
        return tagMapper.selectByTagNames(tagNames);
    }
}
