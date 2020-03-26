package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.po.UserToken;
import com.codimiracle.application.platform.huidu.entity.vo.UserTokenVO;
import com.codimiracle.application.platform.huidu.mapper.UserTokenMapper;
import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.application.platform.huidu.service.UserTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class UserTokenServiceImpl extends AbstractService<String, UserToken> implements UserTokenService {
    private final long WEEK = 604800000;
    private final long MONTH = 2592000000L;
    @Resource
    private UserTokenMapper userTokenMapper;
    @Resource
    private UserService userService‬;

    @Override
    public UserToken findByToken(String token) {
        UserToken userToken = findBy("token", token);
        if (Objects.nonNull(userToken)) {
            userToken.setUser(userService‬.loadUserById(userToken.getId()));
        }
        return userToken;
    }

    @Override
    public UserTokenVO authenticate(User user, boolean rememberMe) {
        UserToken userToken = findBy("userId", user.getId());
        long duration = rememberMe ? WEEK : MONTH;
        Date expired = new Date(System.currentTimeMillis() + duration);
        if (Objects.isNull(userToken)) {
            userToken = new UserToken();
            userToken.setExpireTime(expired);
            userToken.setUserId(user.getId());
            userToken.setToken(UUID.randomUUID().toString());
            save(userToken);
        } else {
            UserToken updatingToken = new UserToken();
            updatingToken.setId(userToken.getId());
            updatingToken.setExpireTime(expired);
            updatingToken.setToken(UUID.randomUUID().toString());
            update(updatingToken);
        }
        return findByIdIntegrally(userToken.getId());
    }

    @Override
    public UserTokenVO findByIdIntegrally(String id) {
        UserTokenVO userTokenVO = userTokenMapper.selectByIdIntegrally(id);
        userTokenVO.setUser(userService‬.findByIdIntegrally(userTokenVO.getUserId()));
        return userTokenVO;
    }

    @Override
    public UserToken findByUserId(String userId) {
        return findBy("userId", userId);
    }
}
