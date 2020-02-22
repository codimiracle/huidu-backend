package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.UserInfo;
import com.codimiracle.application.platform.huidu.mapper.UserInfoMapper;
import com.codimiracle.application.platform.huidu.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class UserInfoServiceImpl extends AbstractService<String, UserInfo> implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

}
