package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.UserFigure;
import com.codimiracle.application.platform.huidu.mapper.UserFigureMapper;
import com.codimiracle.application.platform.huidu.service.UserFigureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Codimiracle on 2020/03/17.
 */
@Service
@Transactional
public class UserFigureServiceImpl extends AbstractService<String, UserFigure> implements UserFigureService {
    @Resource
    private UserFigureMapper userFigureMapper;

}
