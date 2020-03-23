package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.embedded.CommunityFocus;
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;

import java.util.List;

public interface ContentReferenceMapper extends Mapper<ContentReference> {
    int deleteByContentId(String id);

    void deleteByContentIds(String ids);

    List<CommunityFocus> selectCommunityFocus(Filter filter, Sorter sorter, Page page);

    List<ContentReference> findByContentId(String contentId);
}