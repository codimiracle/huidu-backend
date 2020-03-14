package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Notification;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.NotificationVO;
import com.codimiracle.application.platform.huidu.service.NotificationService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user/notifications")
public class ApiUserNotificationController {
    @Resource
    private NotificationService notificationService;

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        notificationService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @PostMapping("/{id}/read")
    public ApiResponse markAsRead(@AuthenticationPrincipal User user, @PathVariable String id) {
        Notification notification = notificationService.findById(id);
        if (Objects.isNull(notification) || !Objects.equals(notification.getReceiverId(), user.getId())) {
            return RestfulUtil.fail("没有找到对应的通知！");
        }
        notificationService.markAsRead(id);
        return RestfulUtil.success();
    }

    @PostMapping("/mark-as-read-bulk")
    public ApiResponse markAsReadBulk(@AuthenticationPrincipal User user, @ModelAttribute String[] ids) {
        notificationService.markAsReadBulk(user.getId(), Arrays.asList(ids));
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        NotificationVO notificationVO = notificationService.findByIdIntegrally(id);
        return RestfulUtil.entity(notificationVO);
    }

    @GetMapping("/unreads")
    public ApiResponse unreadCollection(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.nonNull(filter) ? filter : new Filter();
        filter.put("read", new String[]{"0"});
        return collection(user, filter, null, page);
    }

    @GetMapping("/reads")
    public ApiResponse readCollection(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.nonNull(filter) ? filter : new Filter();
        filter.put("read", new String[]{"1"});
        return collection(user, filter, null, page);
    }

    @GetMapping
    public ApiResponse collection(@AuthenticationPrincipal User user, @RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        filter = Objects.isNull(filter) ? new Filter() : filter;
        filter.put("receiverId", new String[]{user.getId()});
        PageSlice<NotificationVO> slice = notificationService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
