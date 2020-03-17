package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.entity.vo.ReferenceVO;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface ContentReferenceService extends Service<String, ContentReference> {

    void deleteByContentId(String id);

    void deleteByContentIds(String ids);

    List<ContentReference> findByContentId(String contentId);

    List<ReferenceVO> findByContentIdIntegrally(String contentId);
}
