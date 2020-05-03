package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.entity.dto.ActivityDTO;
import com.codimiracle.application.platform.huidu.entity.dto.BulkDeletionDTO;
import com.codimiracle.application.platform.huidu.entity.po.Activity;
import com.codimiracle.application.platform.huidu.entity.vo.ActivityVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import com.codimiracle.application.platform.huidu.service.ActivityService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/activities")
public class ApiBackendActivityController {
    @Resource
    private ActivityService activityService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody ActivityDTO activityDTO) {
        Activity activity = Activity.from(activityDTO);
        activity.setCreateTime(new Date());
        activity.setUpdateTime(activity.getCreateTime());
        activityService.save(activity);
        return RestfulUtil.entity(activityService.findByIdIntegrally(activity.getId()));
    }

    @DeleteMapping
    public ApiResponse deleteBulk(@Valid @RequestBody BulkDeletionDTO bulkDeletionDTO) {
        activityService.deleteByIdsLogically(Arrays.asList(bulkDeletionDTO.getIds()));
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        activityService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @Valid @RequestBody ActivityDTO activityDTO) {
        Activity activity = Activity.from(activityDTO);
        activity.setId(id);
        activity.setCreateTime(new Date());
        activity.setUpdateTime(activity.getCreateTime());
        activity.setStatus(ActivityStatus.valueOfCode(activityDTO.getStatus()));
        activityService.update(activity);
        return RestfulUtil.entity(activityService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        ActivityVO activityVO = activityService.findByIdIntegrally(id);
        return RestfulUtil.success(activityVO);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<ActivityVO> slice = activityService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
