package com.codimiracle.application.platform.huidu.inflater;

import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.web.middleware.content.inflation.OwnerInflatable;
import com.codimiracle.web.middleware.content.inflation.OwnerInflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerInflaterImpl implements OwnerInflater {
    @Autowired
    private UserService userService;

    @Override
    public void inflate(OwnerInflatable inflatingPersistentObject) {
        inflatingPersistentObject.setOwner(userService.findByIdProtectly(inflatingPersistentObject.getOwnerId()));
    }
}
