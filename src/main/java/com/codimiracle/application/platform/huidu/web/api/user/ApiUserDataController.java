package com.codimiracle.application.platform.huidu.web.api.user;/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.entity.dto.ProfileDTO;
import com.codimiracle.application.platform.huidu.entity.dto.UserDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.ChangePasswordDTO;
import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(allowCredentials = "")
@RestController
@RequestMapping("/api/user")
public class ApiUserDataController {
    @Resource
    private UserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @GetMapping("/logged")
    public ApiResponse logged(@AuthenticationPrincipal User user) {
        return RestfulUtil.entity(user);
    }

    @PostMapping("/change-password")
    public ApiResponse changePassword(@AuthenticationPrincipal User user, @RequestBody ChangePasswordDTO changePasswordDTO) {
        if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            User updatingUser = new User();
            updatingUser.setId(user.getId());
            updatingUser.setPassword(changePasswordDTO.getNewPassowrd());
            userService.update(updatingUser);
            return RestfulUtil.success();
        }
        return RestfulUtil.fail("密码不匹配，修改失败！");
    }

    @PutMapping("/profile")
    public ApiResponse updateProfile(@AuthenticationPrincipal User user, @RequestBody ProfileDTO profileDTO) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(profileDTO, userDTO);
        User updatingData = User.from(userDTO);
        updatingData.setId(user.getId());
        userService.update(updatingData);
        return RestfulUtil.success();
    }
}
