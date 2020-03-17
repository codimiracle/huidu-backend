package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.ArrivedHistory;
import com.codimiracle.application.platform.huidu.entity.vo.ArrivedHistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ArrivedHistoryMapper extends Mapper<ArrivedHistory> {
    ArrivedHistory selectLastArrivedHistory(@Param("signerId") String signerId, @Param("signingDate") Date signingDate);

    ArrivedHistoryVO selectByThatDayIntegrally(@Param("signerId") String signerId, @Param("thatDay") Date date);

    ArrivedHistory selectByThatDay(@Param("signerId") String signerId, @Param("thatDay") Date date);

    List<Map<String, Object>> retriveSignedMapByUserId(String userId);

}