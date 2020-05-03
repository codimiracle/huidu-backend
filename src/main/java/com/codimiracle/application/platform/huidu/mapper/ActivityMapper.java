package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.Activity;
import com.codimiracle.application.platform.huidu.entity.vo.ActivityVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper extends Mapper<Activity> {
    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    ActivityVO findByIdIntegrally(String id);

    List<ActivityVO> findAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<ActivityVO> selectByStatusIntegrally(@Param("status") ActivityStatus status, @Param("number") Integer number);
}