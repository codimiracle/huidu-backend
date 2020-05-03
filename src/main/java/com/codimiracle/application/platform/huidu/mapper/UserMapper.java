package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User, UserVO> {
    User selectByUsername(String username);

    void deleteByIdsLogically(List<String> ids);

    UserVO selectByIdIntegrally(String id);

    UserProtectedVO selectByIdProtectly(String userId);

    User selectById(String id);

    Boolean existsUsername(String username);

    Boolean existsNickname(String nickname);
}