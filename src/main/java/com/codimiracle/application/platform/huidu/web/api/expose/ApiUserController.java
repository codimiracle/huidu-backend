package com.codimiracle.application.platform.huidu.web.api.expose;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.dto.UserDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.UserVO;
import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    @Resource
    private UserService userService;

    @PostMapping
    public ApiResponse create(@RequestBody UserDTO userDTO) {
        User user = User.from(userDTO);
        userService.save(user);
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
        return RestfulUtil.success();
    }

    @GetMapping("/{id}")
    public ApiResponse entity(@PathVariable String id) {
        UserVO user = userService.findByIdIntegrally(id);
        return RestfulUtil.success(user);
    }

    @GetMapping
    public ApiResponse collection(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @ModelAttribute Page page) {
        PageSlice<UserVO> slice = userService.findAllIntegrally(filter, sorter, page);
        return RestfulUtil.list(slice);
    }
}
