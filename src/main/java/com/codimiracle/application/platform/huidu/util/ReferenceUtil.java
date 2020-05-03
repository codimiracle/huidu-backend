package com.codimiracle.application.platform.huidu.util;

import com.codimiracle.web.middleware.content.pojo.po.ContentReference;
import com.codimiracle.web.middleware.content.service.ReferenceService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReferenceUtil {
    public static void mutateToPersistent(ReferenceService contentReferenceService, String contentId, List<ContentReference> contentReferences) {
        Map<String, ContentReference> contentReferenceMap = contentReferences.stream().collect(Collectors.toMap((contentReference -> String.format("%s-%s", contentReference.getReferenceTargetType(), contentReference.getReferenceTargetId())), c -> c));
        contentReferenceMap.values().forEach(contentReference -> {
            ContentReference cr = contentReferenceService.findByContentIdAndReferenceTarget(contentId, contentReference.getReferenceTargetId(), contentReference.getReferenceTargetType());
            if (Objects.nonNull(cr)) {
                contentReference.setId(cr.getId());
                contentReference.setDeleted(cr.getDeleted());
                contentReference.setContentId(cr.getContentId());
            }
        });
    }
}
