package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    User selectByUsername(String username);

    void deleteByIdsLogically(List<String> ids);

    void deleteByIdLogically(String id);

    UserVO selectByIdIntegrally(String id);

    List<UserVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    UserProtectedVO selectByIdProtectly(String userId);

    User selectById(String id);
}