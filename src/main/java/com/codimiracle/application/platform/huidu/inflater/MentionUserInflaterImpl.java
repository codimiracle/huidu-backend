package com.codimiracle.application.platform.huidu.inflater;

import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.web.middleware.content.inflation.MentionUserInflatable;
import com.codimiracle.web.middleware.content.inflation.MentionUserInflater;
import org.springframework.stereotype.Component;

@Component
public class MentionUserInflaterImpl implements MentionUserInflater {
    private UserService userService;
    @Override
    public void inflate(MentionUserInflatable inflatingPersistentObject) {
        UserProtectedVO user = userService.findByIdProtectly(inflatingPersistentObject.getMentionUserId());
        inflatingPersistentObject.setMentionUser(user);
    }
}
