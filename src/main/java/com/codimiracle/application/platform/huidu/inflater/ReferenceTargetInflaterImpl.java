package com.codimiracle.application.platform.huidu.inflater;

import com.codimiracle.web.middleware.content.inflation.ReferenceTargetInflatable;
import com.codimiracle.web.middleware.content.inflation.ReferenceTargetInflater;
import org.springframework.stereotype.Component;

@Component
public class ReferenceTargetInflaterImpl implements ReferenceTargetInflater {
    @Override
    public void inflate(ReferenceTargetInflatable inflatingPersistentObject) {
        inflatingPersistentObject.setReferenceTarget(null);
    }
}
