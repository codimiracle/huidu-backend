package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.UserRole;
import com.codimiracle.application.platform.huidu.entity.vo.UserRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper extends Mapper<UserRole> {
    void deleteByIdsLogically(List<String> ids);

    void deleteByIdLogically(String id);

    UserRoleVO selectByIdIntegrally(String id);

    List<UserRoleVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);
}