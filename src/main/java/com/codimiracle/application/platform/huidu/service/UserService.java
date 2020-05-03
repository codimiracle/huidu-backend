package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface UserService extends Service<String, User, UserVO>, UserDetailsService {

    void deleteByIdsLogically(List<String> ids);

    UserProtectedVO findByIdProtectly(String userId);

    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    User loadUserById(String id);

    boolean existsUsername(String username);
    boolean existsNickname(String username);
}
