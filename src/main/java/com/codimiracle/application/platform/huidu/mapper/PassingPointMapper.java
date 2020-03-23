package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.PassingPoint;
import com.codimiracle.application.platform.huidu.entity.vo.PassingPointVO;

import java.util.List;

public interface PassingPointMapper extends Mapper<PassingPoint> {
    List<PassingPointVO> selectByLogisticsInformationId(String id);
}