package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.PassingPoint;
import com.codimiracle.application.platform.huidu.entity.vo.PassingPointVO;
import com.codimiracle.application.platform.huidu.mapper.PassingPointMapper;
import com.codimiracle.application.platform.huidu.service.PassingPointService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class PassingPointServiceImpl extends AbstractService<String, PassingPoint, PassingPointVO> implements PassingPointService {
    @Resource
    private PassingPointMapper passingPointMapper;

    @Override
    public List<PassingPointVO> findByLogisticsInformationId(String id) {
        return passingPointMapper.selectByLogisticsInformationId(id);
    }
}
