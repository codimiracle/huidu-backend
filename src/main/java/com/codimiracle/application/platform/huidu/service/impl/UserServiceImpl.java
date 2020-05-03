package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.po.UserAccount;
import com.codimiracle.application.platform.huidu.entity.po.UserInfo;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserVO;
import com.codimiracle.application.platform.huidu.mapper.UserMapper;
import com.codimiracle.application.platform.huidu.service.UserAccountService;
import com.codimiracle.application.platform.huidu.service.UserInfoService;
import com.codimiracle.application.platform.huidu.service.UserRoleService;
import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<String, User, UserVO> implements UserService {
    private static final String DEFAULT_USER_PASSWORD = "12345678";
    @Resource
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserAccountService userAccountService;
    @Resource
    private UserRoleService userRoleService;

    @Override
    public void save(User model) {
        if (Objects.isNull(model.getPassword())) {
            model.setPassword(DEFAULT_USER_PASSWORD);
        }
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        super.save(model);
        UserInfo userInfo = model.getExtra();
        userInfo.setUserId(model.getId());
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(model.getId());
        userAccountService.save(userAccount);
        userInfoService.save(model.getExtra());
    }

    @Override
    public void update(User model) {
        if (Objects.nonNull(model.getPassword())) {
            model.setPassword(passwordEncoder.encode(model.getPassword()));
        }
        super.update(model);
        if (Objects.nonNull(model.getExtra())) {
            UserInfo userInfo = model.getExtra();
            userInfo.setUserId(model.getId());
            userInfoService.update(userInfo);
        }
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("username %s not found", username));
        }
        return user;
    }

    @Override
    public User loadUserById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean existsUsername(String username) {
        Boolean result = userMapper.existsUsername(username);
        return Objects.nonNull(result) && result;
    }

    @Override
    public boolean existsNickname(String nickname) {
        Boolean result = userMapper.existsNickname(nickname);
        return Objects.nonNull(result) && result;
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        userMapper.deleteByIdsLogically(ids);
    }

    @Override
    public UserProtectedVO findByIdProtectly(String userId) {
        return userMapper.selectByIdProtectly(userId);
    }
}
