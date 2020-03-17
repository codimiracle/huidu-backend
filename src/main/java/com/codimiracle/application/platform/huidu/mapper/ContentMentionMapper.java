package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.ContentMention;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;

import java.util.List;

public interface ContentMentionMapper extends Mapper<ContentMention> {
    List<UserProtectedVO> selectByContentIdIntegrally(String contentId);
}