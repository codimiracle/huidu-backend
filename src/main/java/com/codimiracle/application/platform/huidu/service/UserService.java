package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.User;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * @author Codimiracle
 */
public interface UserService extends Service<String, User>, UserDetailsService {

}
