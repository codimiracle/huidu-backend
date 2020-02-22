package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.UserRole;
import com.codimiracle.application.platform.huidu.mapper.UserRoleMapper;
import com.codimiracle.application.platform.huidu.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class UserRoleServiceImpl extends AbstractService<String, UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

}
