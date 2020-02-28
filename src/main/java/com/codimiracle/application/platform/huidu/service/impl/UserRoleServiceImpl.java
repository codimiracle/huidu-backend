package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.UserRole;
import com.codimiracle.application.platform.huidu.entity.vo.UserRoleVO;
import com.codimiracle.application.platform.huidu.mapper.UserRoleMapper;
import com.codimiracle.application.platform.huidu.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class UserRoleServiceImpl extends AbstractService<String, UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        userRoleMapper.deleteByIdsLogically(ids);
    }

    @Override
    public void deleteByIdLogically(String id) {
        userRoleMapper.deleteByIdLogically(id);
    }

    @Override
    public UserRoleVO findByIdIntegrally(String id) {
        return userRoleMapper.selectByIdIntegrally(id);
    }

    @Override
    public PageSlice<UserRoleVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(userRoleMapper.selectAllIntegrally(filter, sorter, page));
    }
}
