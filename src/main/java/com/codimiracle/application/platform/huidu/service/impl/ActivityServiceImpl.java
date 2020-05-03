package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.Activity;
import com.codimiracle.application.platform.huidu.entity.vo.ActivityVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import com.codimiracle.application.platform.huidu.mapper.ActivityMapper;
import com.codimiracle.application.platform.huidu.service.ActivityService;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class ActivityServiceImpl extends AbstractService<String, Activity, ActivityVO> implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private BookService bookService;

    protected ActivityVO mutate(ActivityVO activity) {
        if (Objects.nonNull(activity)) {
            activity.setBook(bookService.findByIdIntegrally(activity.getBookId()));
        }
        return activity;
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        activityMapper.deleteByIdsLogically(ids);
    }

    @Override
    public List<ActivityVO> findByStatusIntegrally(ActivityStatus status, Integer number) {
        List<ActivityVO> list = activityMapper.selectByStatusIntegrally(status, number);
        list.forEach(this::mutate);
        return list;
    }
}
