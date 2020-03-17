package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.UserToken;
import com.codimiracle.application.platform.huidu.entity.vo.UserTokenVO;

public interface UserTokenMapper extends Mapper<UserToken> {
    UserTokenVO selectByIdIntegrally(String id);
}