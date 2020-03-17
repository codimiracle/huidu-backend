package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;
import com.codimiracle.application.platform.huidu.mapper.TagMapper;
import com.codimiracle.application.platform.huidu.service.CategoryTagsService;
import com.codimiracle.application.platform.huidu.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class TagServiceImpl extends AbstractService<String, Tag> implements TagService {
    @Resource
    private TagMapper tagMapper;

    @Resource
    private CategoryTagsService categoryTagsService;

    @Override
    public TagVO findByIdIntegrally(String id) {
        TagVO tagVO = tagMapper.selectByIdIntegrally(id);
        tagVO.setCategories(categoryTagsService.findCategoryByTagId(tagVO.getId()));
        return tagVO;
    }

    @Override
    public PageSlice<TagVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<TagVO> slice = extractPageSlice(tagMapper.selectAllIntegrally(filter, sorter, page));
        List<TagVO> list = slice.getList();
        list.stream().forEach(tagVO -> {
            tagVO.setCategories(categoryTagsService.findCategoryByTagId(tagVO.getId()));
        });
        return slice;
    }

    @Override
    public List<Tag> findByTagNames(List<String> tagNames) {
        if (tagNames.size() == 0) {
            return Collections.emptyList();
        }
        return tagMapper.selectByTagNames(tagNames);
    }

    @Override
    public void deleteByIdLogically(String id) {
        tagMapper.deleteByIdLogically(id);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        tagMapper.deleteByIdsLogically(ids);
    }
}
