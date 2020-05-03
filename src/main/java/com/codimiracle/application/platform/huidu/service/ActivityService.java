package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.Activity;
import com.codimiracle.application.platform.huidu.entity.vo.ActivityVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface ActivityService extends Service<String, Activity, ActivityVO> {

    void deleteByIdsLogically(List<String> ids);

    List<ActivityVO> findByStatusIntegrally(ActivityStatus status, Integer number);
}
