package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.PassingPoint;
import com.codimiracle.application.platform.huidu.entity.vo.PassingPointVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;

import java.util.List;

public interface PassingPointMapper extends Mapper<PassingPoint, PassingPointVO> {
    List<PassingPointVO> selectByLogisticsInformationId(String id);
}