package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.Activity;
import com.codimiracle.application.platform.huidu.entity.vo.ActivityVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ActivityMapper extends Mapper<Activity, ActivityVO> {
    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    ActivityVO findByIdIntegrally(String id);

    List<ActivityVO> selectByStatusIntegrally(@Param("status") ActivityStatus status, @Param("number") Integer number);
}