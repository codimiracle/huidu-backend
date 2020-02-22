package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.UserAccount;
import com.codimiracle.application.platform.huidu.mapper.UserAccountMapper;
import com.codimiracle.application.platform.huidu.service.UserAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class UserAccountServiceImpl extends AbstractService<String, UserAccount> implements UserAccountService {
    @Resource
    private UserAccountMapper userAccountMapper;

}
