package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface TagService extends Service<String, Tag> {

    TagVO findByIdIntegrally(String id);

    PageSlice<TagVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    List<Tag> findByTagNames(List<String> tagNames);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    Tag findByTagName(String name);
}
