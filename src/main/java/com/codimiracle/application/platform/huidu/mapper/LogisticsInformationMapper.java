package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.LogisticsInformation;
import com.codimiracle.application.platform.huidu.entity.vo.LogisticsInformationVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

@org.apache.ibatis.annotations.Mapper
public interface LogisticsInformationMapper extends Mapper<LogisticsInformation, LogisticsInformationVO> {
    LogisticsInformationVO selectByIdIntegrally(@Param("id") String id);

    LogisticsInformationVO selectByOrderNumberIntegrally(@Param("orderNumber") String orderNumber);
}