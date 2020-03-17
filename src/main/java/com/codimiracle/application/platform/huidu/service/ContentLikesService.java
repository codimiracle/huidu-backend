package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.ContentLikes;

public interface ContentLikesService extends Service<String, ContentLikes> {

    void like(String likerId, String contentId);

    void unlike(String unlikerId, String contentId);

    boolean isLiked(String likerId, String contentId);
}
