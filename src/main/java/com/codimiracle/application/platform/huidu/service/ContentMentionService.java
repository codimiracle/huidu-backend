package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.ContentMention;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface ContentMentionService extends Service<String, ContentMention> {

    List<UserProtectedVO> findByContentId(String id);
}
