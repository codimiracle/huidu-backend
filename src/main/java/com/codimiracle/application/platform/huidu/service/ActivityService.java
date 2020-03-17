package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Activity;
import com.codimiracle.application.platform.huidu.entity.vo.ActivityVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface ActivityService extends Service<String, Activity> {

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    ActivityVO findByIdIntegrally(String id);

    PageSlice<ActivityVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    List<ActivityVO> findByStatusIntegrally(ActivityStatus status, Integer number);
}
