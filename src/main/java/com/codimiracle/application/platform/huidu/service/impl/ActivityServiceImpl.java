package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Activity;
import com.codimiracle.application.platform.huidu.entity.vo.ActivityVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import com.codimiracle.application.platform.huidu.mapper.ActivityMapper;
import com.codimiracle.application.platform.huidu.service.ActivityService;
import com.codimiracle.application.platform.huidu.service.BookService;
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
public class ActivityServiceImpl extends AbstractService<String, Activity> implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private BookService bookService;

    private ActivityVO mutate(ActivityVO activity) {
        if (Objects.nonNull(activity)) {
            activity.setBook(bookService.findByIdIntegrally(activity.getBookId()));
        }
        return activity;
    }
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
        return mutate(activityMapper.findByIdIntegrally(id));
    }

    @Override
    public PageSlice<ActivityVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<ActivityVO> slice = extractPageSlice(activityMapper.findAllIntegrally(filter, sorter, page));
        slice.getList().forEach(this::mutate);
        return slice;
    }

    @Override
    public List<ActivityVO> findByStatusIntegrally(ActivityStatus status, Integer number) {
        List<ActivityVO> list = activityMapper.selectByStatusIntegrally(status, number);
        list.forEach(this::mutate);
        return list;
    }
}
