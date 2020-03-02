package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.UserDTO;
import com.codimiracle.application.platform.huidu.entity.dto.UserResetPasswordDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.UserVO;
import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @PostMapping
    public ApiResponse create(@RequestBody UserDTO userDTO) {
        User user = User.from(userDTO);
        userService.save(user);
        return RestfulUtil.entity(userService.findByIdIntegrally(user.getId()));
    }

    @PostMapping("/{id}/reset-password")
    public ApiResponse resetPassword(@PathVariable String userId, @RequestBody UserResetPasswordDTO userResetPasswordDTO) {
        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setId(userId);
        userService.update(updatedUser);
        return RestfulUtil.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        userService.deleteByIdLogically(id);
        return RestfulUtil.success();
    }

    @DeleteMapping
    public ApiResponse deleteBulk(String[] ids) {
        userService.deleteByIdsLogically(Arrays.asList(ids));
        return RestfulUtil.success();
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = User.from(userDTO);
        Objects.requireNonNull(user);
        user.setId(id);
        userService.update(user);
        return RestfulUtil.entity(userService.findByIdIntegrally(id));
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        UserVO user = userService.findByIdIntegrally(id);
        return RestfulUtil.entity(user);
    }

    @PutMapping("{id}/reset-password")
    public ApiResponse resetPassword(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = new User();
        user.setId(id);
        user.setPassword(userDTO.getPassword());
        userService.save(user);
        return RestfulUtil.success();
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<UserVO> slice = userService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
