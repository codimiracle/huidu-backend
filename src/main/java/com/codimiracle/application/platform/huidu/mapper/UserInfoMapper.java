package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.UserInfo;
import com.codimiracle.application.platform.huidu.entity.vo.UserInfoVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserInfoMapper extends Mapper<UserInfo, UserInfoVO> {
}