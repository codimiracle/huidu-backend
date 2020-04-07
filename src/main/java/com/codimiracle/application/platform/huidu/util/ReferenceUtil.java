package com.codimiracle.application.platform.huidu.util;

import com.codimiracle.application.platform.huidu.entity.po.ContentReference;
import com.codimiracle.application.platform.huidu.service.ContentReferenceService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReferenceUtil {
    public static void mutateToPersistent(ContentReferenceService contentReferenceService, String contentId, List<ContentReference> contentReferences) {
        Map<String, ContentReference> contentReferenceMap = contentReferences.stream().collect(Collectors.toMap((contentReference -> String.format("%s-%s", contentReference.getType(), contentReference.getRefId())), c -> c));
        contentReferenceMap.values().forEach(contentReference -> {
            ContentReference cr = contentReferenceService.findByRefIdAndType(contentId, contentReference.getRefId(), contentReference.getType());
            if (Objects.nonNull(cr)) {
                contentReference.setId(cr.getId());
                contentReference.setDeleted(cr.isDeleted());
                contentReference.setContentId(cr.getContentId());
            }
        });
    }
}
