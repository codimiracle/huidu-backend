package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.UserRole;
import com.codimiracle.application.platform.huidu.entity.vo.UserRoleVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface UserRoleService extends Service<String, UserRole, UserRoleVO> {

    void deleteByIdsLogically(List<String> ids);

    UserRole findByRoleName(String name);
}
