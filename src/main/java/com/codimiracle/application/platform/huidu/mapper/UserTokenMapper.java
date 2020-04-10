package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.entity.po.UserToken;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserTokenVO;

import java.util.List;

public interface UserTokenMapper extends Mapper<UserToken> {
    UserTokenVO selectByIdIntegrally(String id);

    List<UserProtectedVO> selectOnlineUserProtectively(Page page);

}