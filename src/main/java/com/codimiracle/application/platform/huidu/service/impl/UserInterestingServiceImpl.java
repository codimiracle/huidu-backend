package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.UserInteresting;
import com.codimiracle.application.platform.huidu.mapper.UserInterestingMapper;
import com.codimiracle.application.platform.huidu.service.UserInterestingService;
import com.codimiracle.web.mybatis.contract.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Codimiracle on 2020/03/17.
 */
@Service
@Transactional
public class UserInterestingServiceImpl extends AbstractService<String, UserInteresting> implements UserInterestingService {
    @Resource
    private UserInterestingMapper userInterestingMapper;

}
