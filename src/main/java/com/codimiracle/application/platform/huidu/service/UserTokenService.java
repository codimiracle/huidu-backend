package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.po.UserToken;
import com.codimiracle.application.platform.huidu.entity.vo.UserTokenVO;

public interface UserTokenService extends Service<String, UserToken> {

    UserToken findByToken(String token);

    UserTokenVO authenticate(User user, boolean rememberMe);

    UserTokenVO findByIdIntegrally(String id);

    UserToken findByUserId(String id);
}
