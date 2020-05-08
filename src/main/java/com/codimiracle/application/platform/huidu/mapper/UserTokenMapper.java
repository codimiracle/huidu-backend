package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.UserToken;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserTokenVO;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserTokenMapper extends Mapper<UserToken, UserTokenVO> {
    UserTokenVO selectByIdIntegrally(String id);

    List<UserProtectedVO> selectOnlineUserProtectively(@Param("page") Page page);

}