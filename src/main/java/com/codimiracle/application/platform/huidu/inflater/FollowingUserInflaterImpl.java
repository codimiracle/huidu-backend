package com.codimiracle.application.platform.huidu.inflater;

import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.web.middleware.content.inflation.FollowingUserInflatable;
import com.codimiracle.web.middleware.content.inflation.FollowingUserInflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FollowingUserInflaterImpl implements FollowingUserInflater {
    @Autowired
    private UserService userService;
    @Override
    public void inflate(FollowingUserInflatable inflatingPersistentObject) {
        inflatingPersistentObject.setFollowingUser(userService.findByIdProtectly(inflatingPersistentObject.getFollowingUserId()));
    }
}
