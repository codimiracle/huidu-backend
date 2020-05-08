package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.UserRole;
import com.codimiracle.application.platform.huidu.entity.vo.UserRoleVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserRoleMapper extends Mapper<UserRole, UserRoleVO> {
    void deleteByIdsLogically(List<String> ids);

    UserRoleVO selectByIdIntegrally(String id);
}