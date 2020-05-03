package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.UserRole;
import com.codimiracle.application.platform.huidu.entity.vo.UserRoleVO;
import com.codimiracle.application.platform.huidu.mapper.UserRoleMapper;
import com.codimiracle.application.platform.huidu.service.UserRoleService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class UserRoleServiceImpl extends AbstractService<String, UserRole, UserRoleVO> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        userRoleMapper.deleteByIdsLogically(ids);
    }

    @Override
    public UserRole findByRoleName(String name) {
        return findBy("name", name);
    }
}
