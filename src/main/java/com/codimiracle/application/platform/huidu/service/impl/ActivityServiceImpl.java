package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Activity;
import com.codimiracle.application.platform.huidu.entity.vo.ActivityVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import com.codimiracle.application.platform.huidu.mapper.ActivityMapper;
import com.codimiracle.application.platform.huidu.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ActivityServiceImpl extends AbstractService<String, Activity> implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;


    @Override
    public void deleteByIdLogically(String id) {
        activityMapper.deleteByIdLogically(id);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        activityMapper.deleteByIdsLogically(ids);
    }

    @Override
    public ActivityVO findByIdIntegrally(String id) {
        return activityMapper.findByIdIntegrally(id);
    }

    @Override
    public PageSlice<ActivityVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(activityMapper.findAllIntegrally(filter, sorter, page));
    }

    @Override
    public List<ActivityVO> findByStatusIntegrally(ActivityStatus status, Integer number) {
        return activityMapper.selectByStatusIntegrally(status, number);
    }
}
