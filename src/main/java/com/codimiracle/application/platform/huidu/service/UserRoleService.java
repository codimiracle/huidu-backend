package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.UserRole;
import com.codimiracle.application.platform.huidu.entity.vo.UserRoleVO;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface UserRoleService extends Service<String, UserRole> {

    void deleteByIdsLogically(List<String> ids);

    void deleteByIdLogically(String id);

    UserRoleVO findByIdIntegrally(String id);

    PageSlice<UserRoleVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);
}
