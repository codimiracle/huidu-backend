package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface UserService extends Service<String, User>, UserDetailsService {

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    UserVO findByIdIntegrally(String id);

    PageSlice<UserVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    UserProtectedVO findByIdProtectly(String userId);

    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    User loadUserById(String id);

    boolean existsUsername(String username);
    boolean existsNickname(String username);
}
