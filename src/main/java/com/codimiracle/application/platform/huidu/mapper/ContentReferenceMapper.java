package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.entity.vo.ReferenceVO;

import java.util.List;

public interface ContentReferenceMapper extends Mapper<ContentReference> {
    int deleteByContentId(String id);

    void deleteByContentIds(String ids);

    List<ContentReference> findByContentId(String contentId);
}