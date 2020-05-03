package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.po.UserToken;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserTokenVO;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

public interface UserTokenService extends Service<String, UserToken, UserTokenVO> {

    UserToken findByToken(String token);

    UserTokenVO authenticate(User user, boolean rememberMe);

    UserTokenVO findByIdIntegrally(String id);

    UserToken findByUserId(String id);

    PageSlice<UserProtectedVO> findOnlineUserProtectively(Page page);

}
