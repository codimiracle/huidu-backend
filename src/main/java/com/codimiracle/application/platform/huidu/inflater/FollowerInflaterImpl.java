package com.codimiracle.application.platform.huidu.inflater;

import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.web.middleware.content.inflation.FollowerInflatable;
import com.codimiracle.web.middleware.content.inflation.FollowerInflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FollowerInflaterImpl implements FollowerInflater {
    @Autowired
    private UserService userService;

    @Override
    public void inflate(FollowerInflatable inflatingPersistentObject) {
        inflatingPersistentObject.setFollower(userService.findByIdProtectly(inflatingPersistentObject.getFollowerId()));
    }
}
