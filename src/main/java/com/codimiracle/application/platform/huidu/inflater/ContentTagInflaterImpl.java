package com.codimiracle.application.platform.huidu.inflater;

import com.codimiracle.application.platform.huidu.service.TagService;
import com.codimiracle.web.middleware.content.inflation.ContentTagInflatable;
import com.codimiracle.web.middleware.content.inflation.ContentTagInflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentTagInflaterImpl implements ContentTagInflater {
    @Autowired
    private TagService tagService;

    @Override
    public void inflate(ContentTagInflatable inflatingPersistentObject) {
        inflatingPersistentObject.setTag(tagService.findByIdIntegrally(inflatingPersistentObject.getTagId()));
    }
}
