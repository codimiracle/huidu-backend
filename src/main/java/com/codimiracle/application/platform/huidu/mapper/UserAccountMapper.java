package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.UserAccount;
import com.codimiracle.web.mybatis.contract.Mapper;

import java.util.List;

public interface UserAccountMapper extends Mapper<UserAccount> {
    List<UserAccount> selectByUserId(String userId);
}