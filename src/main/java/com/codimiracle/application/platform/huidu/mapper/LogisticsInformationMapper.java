package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.LogisticsInformation;
import com.codimiracle.application.platform.huidu.entity.vo.LogisticsInformationVO;
import org.apache.ibatis.annotations.Param;

public interface LogisticsInformationMapper extends Mapper<LogisticsInformation> {
    LogisticsInformationVO selectByIdIntegrally(@Param("id") String id);

    LogisticsInformationVO selectByOrderNumberIntegrally(@Param("orderNumber") String orderNumber);
}