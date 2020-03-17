package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.LogisticsInformation;
import com.codimiracle.application.platform.huidu.entity.vo.LogisticsInformationVO;

import java.util.List;

public interface LogisticsInformationMapper extends Mapper<LogisticsInformation> {
    LogisticsInformationVO selectByIdIntegrally(String id);
}