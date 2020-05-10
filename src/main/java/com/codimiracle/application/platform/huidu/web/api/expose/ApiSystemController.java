package com.codimiracle.application.platform.huidu.web.api.expose;/*
 * MIT License
 *
 * Copyright (c) 2020 Codimiracle
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, Publish, distribute, sublicense, and/or sell
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

import com.codimiracle.application.platform.huidu.entity.dto.SiginUpDTO;
import com.codimiracle.application.platform.huidu.entity.dto.SignInDTO;
import com.codimiracle.application.platform.huidu.entity.embedded.HotCommunity;
import com.codimiracle.application.platform.huidu.entity.embedded.PersonalRecommendation;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.po.UserRole;
import com.codimiracle.application.platform.huidu.entity.po.UserToken;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.RealtimeVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserTokenVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import com.codimiracle.application.platform.huidu.service.*;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.util.StringifizationUtil;
import com.codimiracle.web.basic.contract.ApiResponse;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.middleware.content.service.ReferenceService;
import com.codimiracle.web.notification.middleware.template.NotificationTemplate;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.codimiracle.application.platform.huidu.enumeration.Settings.*;

@CrossOrigin
@RestController
@RequestMapping("/api/system")
public class ApiSystemController {
    @Resource
    private PopularService popularService;

    @Resource
    private SettingsService settingsService;

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private UserTokenService userTokenService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private ActivityService activityService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private TopicService topicService;

    @Resource
    private ReviewService reviewService;

    @Resource
    private UserFigureService userFigureService;

    @Resource
    private ReferenceService referenceService;
    @Resource
    private NotificationTemplate notificationTemplate;

    @GetMapping("/version")
    public ApiResponse version() {
        if (Files.exists(Paths.get("./version"))) {
            try {
                return RestfulUtil.success(FileUtils.readFileToString(new File("./version"), StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
                return RestfulUtil.fail("读取 version 文件失败！");
            }
        }
        return RestfulUtil.fail("没有找到 version 文件！");
    }

    @PostMapping("/sign-in")
    public ApiResponse signIn(@Valid @RequestBody SignInDTO signInDTO) {
        User user = userService.loadUserByUsername(signInDTO.getUsername());
        if (passwordEncoder.matches(signInDTO.getPassword(), user.getPassword())) {
            UserTokenVO userTokenVO = userTokenService.authenticate(user, signInDTO.isRememberMe());
            return RestfulUtil.success(userTokenVO);
        }
        return RestfulUtil.fail("密码错误或用户名不存在！");
    }

    @PostMapping("/sign-up")
    public ApiResponse signUp(@Valid @RequestBody SiginUpDTO siginUpDTO) {
        User user = User.from(siginUpDTO.getUserdata());
        Objects.requireNonNull(user);
        user.setEnabled(true);
        user.setPassword(siginUpDTO.getPassword());
        String roleName = null;
        if (Objects.equals(siginUpDTO.getAccountType(), "user")) {
            roleName = "用户";
        } else if (Objects.equals(siginUpDTO.getAccountType(), "author")) {
            roleName = "作者";
        } else {
            return RestfulUtil.fail("无法识别相应的权限设定！");
        }
        UserRole role = userRoleService.findByRoleName(roleName);
        if (Objects.isNull(role)) {
            return RestfulUtil.fail("抱歉系统无法完成权限设定！");
        }
        user.setRoleId(role.getId());
        userService.save(user);
        notificationTemplate.sendPlaintext(notificationTemplate.getDefaultSenderId(), user.getId(), "欢迎注册荟读账号");
        return RestfulUtil.success();
    }

    @GetMapping("/sign-out")
    public ApiResponse signOut(@AuthenticationPrincipal User user) {
        UserToken original = userTokenService.findByUserId(user.getId());
        UserToken updatingUserToken = new UserToken();
        updatingUserToken.setExpireTime(new Date());
        updatingUserToken.setId(original.getId());
        userTokenService.update(updatingUserToken);
        return RestfulUtil.success();
    }

    @GetMapping("/username-exists")
    public ApiResponse usernameExists(@RequestParam("username") String username) {
        if (StringUtils.isEmpty(username)) {
            return RestfulUtil.fail("用户名无效!");
        }
        if (userService.existsUsername(username)) {
            return RestfulUtil.success();
        } else {
            return RestfulUtil.response(404, "用户名不存在!");
        }
    }

    @GetMapping("/nickname-exists")
    public ApiResponse nicknameExists(@RequestParam("nickname") String nickname) {
        if (StringUtils.isEmpty(nickname)) {
            return RestfulUtil.fail("昵称无效!");
        }
        if (userService.existsNickname(nickname)) {
            return RestfulUtil.success();
        } else {
            return RestfulUtil.response(404, "用户名不存在!");
        }
    }

    @GetMapping("realtime")
    public ApiResponse realtime(@AuthenticationPrincipal User user) {
        RealtimeVO realtimeVO = new RealtimeVO();
        int number = Integer.valueOf(settingsService.retrieve(ACTIVITY_NUMBER));
        realtimeVO.setActivities(activityService.findByStatusIntegrally(ActivityStatus.Activated, number));
        List<String> categoryIds = StringifizationUtil.toList(settingsService.retrieve(COMPREHENSIVE_PAGE_CATEGORIES));
        realtimeVO.setCategories(categoryService.findByIdsIntegrally(categoryIds));
        List<String> collectionIds = StringifizationUtil.toList(settingsService.retrieve(COMPREHENSIVE_PAGE_COLLECTIONS));
        realtimeVO.setSections(categoryService.findByIdsIntegrally(collectionIds));
        HotCommunity hotCommunity = new HotCommunity();
        realtimeVO.setCommunity(hotCommunity);
        hotCommunity.setHotTopics(popularService.findPopularTopicIntegrally(null, null, new Page()).getList());
        hotCommunity.setHotReviews(popularService.findPopularReviewIntegrally( null, null, new Page()).getList());
        //hotCommunity.setFocus(referenceService.findCommunityFocusIntegrally(null, null, new Page()).getList());
        PersonalRecommendation personalRecommendation = new PersonalRecommendation();
        realtimeVO.setRecommendations(personalRecommendation);
        List<CategoryVO> similarCategories;
        List<CategoryVO> sametasteCategories;
        if (Objects.isNull(user)) {
            similarCategories = userFigureService.findSimilarCategoryByAvgIntegrally();
            sametasteCategories = userFigureService.findSametasteCategoryByAvgIntegrally();
        } else {
            similarCategories = userFigureService.findSimilarCategoryByUserIdIntegrally(user.getId());
            sametasteCategories = userFigureService.findSametasteCategoryByUserIdIntegrally(user.getId());
        }
        if (similarCategories.size() > 0) {
            personalRecommendation.setGuessing(similarCategories.get(0));
        }
        if (sametasteCategories.size() > 0) {
            personalRecommendation.setSametaste(sametasteCategories.get(0));
        }
        return RestfulUtil.success(realtimeVO);
    }
}
