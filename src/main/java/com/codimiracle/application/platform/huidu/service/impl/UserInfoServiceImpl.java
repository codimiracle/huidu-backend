package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.UserInfo;
import com.codimiracle.application.platform.huidu.entity.vo.UserInfoVO;
import com.codimiracle.application.platform.huidu.mapper.UserInfoMapper;
import com.codimiracle.application.platform.huidu.service.UserInfoService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class UserInfoServiceImpl extends AbstractService<String, UserInfo, UserInfoVO> implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

}
