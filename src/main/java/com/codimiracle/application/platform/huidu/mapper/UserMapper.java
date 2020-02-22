package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.User;

public interface UserMapper extends Mapper<User> {
    User selectByUsername(String username);
}