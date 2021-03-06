package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.Activity;
import com.codimiracle.application.platform.huidu.entity.vo.ActivityVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper extends Mapper<Activity> {
    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    ActivityVO findByIdIntegrally(String id);

    List<ActivityVO> findAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<ActivityVO> selectByStatusIntegrally(@Param("status") ActivityStatus status, @Param("number") Integer number);
}