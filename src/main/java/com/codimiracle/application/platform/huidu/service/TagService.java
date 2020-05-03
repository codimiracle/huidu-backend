package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface TagService extends Service<String, Tag, TagVO> {

    List<Tag> findByTagNames(List<String> tagNames);

    void deleteByIdsLogically(List<String> ids);

    Tag findByTagName(String name);
}
