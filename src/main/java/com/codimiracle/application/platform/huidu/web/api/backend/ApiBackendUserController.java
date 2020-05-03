package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.entity.dto.BulkDeletionDTO;
import com.codimiracle.application.platform.huidu.entity.dto.UserDTO;
import com.codimiracle.application.platform.huidu.entity.dto.UserResetPasswordDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.UserVO;
import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.web.basic.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/backend/system/users")
public class ApiBackendUserController {
    @Resource
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/{id}/reset-password")
    public ApiResponse resetPassword(@PathVariable("id") String userId, @Valid @RequestBody UserResetPasswordDTO userResetPasswordDTO) {
        User user = userService.findById(userId);
        if (Objects.isNull(user)) {
            return RestfulUtil.fail("用户数据没有找到！");
        }
        User updatedUser = new User();
        updatedUser.setEnabled(user.isEnabled());
        updatedUser.setPassword(userResetPasswordDTO.getPassword());
        updatedUser.setId(userId);
        userService.update(updatedUser);
        return RestfulUtil.success();
    }

    @PostMapping
    public ApiResponse create(@Valid @RequestBody UserDTO userDTO) {
        User user = User.from(userDTO);
        user.setEnabled(true);
        userService.save(user);
        return RestfulUtil.entity(userService.findByIdIntegrally(user.getId()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        userService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse deleteBulk(@Valid @RequestBody BulkDeletionDTO bulkDeletionDTO) {
        userService.deleteByIdsLogically(Arrays.asList(bulkDeletionDTO.getIds()));
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @Valid @RequestBody UserDTO userDTO) {
        User user = userService.findById(id);
        if (Objects.isNull(user)) {
            return RestfulUtil.fail("找不到用户数据！");
        }
        User updatingUser = User.from(userDTO);
        Objects.requireNonNull(updatingUser);
        updatingUser.setId(id);
        userService.update(updatingUser);
        return RestfulUtil.entity(userService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        UserVO user = userService.findByIdIntegrally(id);
        return RestfulUtil.entity(user);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<UserVO> slice = userService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
