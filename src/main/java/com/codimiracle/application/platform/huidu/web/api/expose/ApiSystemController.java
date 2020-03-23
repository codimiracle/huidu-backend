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

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.entity.dto.SignInDTO;
import com.codimiracle.application.platform.huidu.entity.embedded.HotCommunity;
import com.codimiracle.application.platform.huidu.entity.embedded.PersonalRecommendation;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.po.UserToken;
import com.codimiracle.application.platform.huidu.entity.vo.CategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.RealtimeVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserTokenVO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import com.codimiracle.application.platform.huidu.service.*;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.util.StringifizationUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.codimiracle.application.platform.huidu.enumeration.Settings.*;

@CrossOrigin
@RestController
@RequestMapping("/api/system")
public class ApiSystemController {
    @Resource
    private SettingsService settingsService;

    @Resource
    private UserService userService;

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
    private ContentReferenceService contentReferenceService;

    @GetMapping("/version")
    public ApiResponse version() {
        return RestfulUtil.success("huidu-platform-build-20200323");
    }

    @PostMapping("/sign-in")
    public ApiResponse signIn(@RequestBody SignInDTO signInDTO) {
        User user = userService.loadUserByUsername(signInDTO.getUsername());
        if (passwordEncoder.matches(signInDTO.getPassword(), user.getPassword())) {
            UserTokenVO userTokenVO = userTokenService.authenticate(user, signInDTO.isRememberMe());
            return RestfulUtil.success(userTokenVO);
        }
        return RestfulUtil.fail("登录失败，密码错误或用户名不存在！");
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

    @GetMapping("realtime")
    public ApiResponse realtime(@AuthenticationPrincipal User user) {
        RealtimeVO realtimeVO = new RealtimeVO();
        int number = Integer.valueOf(settingsService.retrive(ACTIVITY_NUMBER));
        realtimeVO.setActivities(activityService.findByStatusIntegrally(ActivityStatus.Activated, number));
        List<String> categoryIds = StringifizationUtil.toList(settingsService.retrive(COMPREHENSIVE_PAGE_CATEGORIES));
        realtimeVO.setCategories(categoryService.findByIdsIntegrally(categoryIds));
        List<String> collectionIds = StringifizationUtil.toList(settingsService.retrive(COMPREHENSIVE_PAGE_COLLECTIONS));
        realtimeVO.setSections(categoryService.findByIdsIntegrally(collectionIds));
        HotCommunity hotCommunity = new HotCommunity();
        realtimeVO.setCommunity(hotCommunity);
        hotCommunity.setHotTopics(topicService.findHotIntegrally(null, null, new Page()).getList());
        hotCommunity.setHotReviews(reviewService.findHotReviewIntegrally(null, null, new Page()).getList());
        hotCommunity.setFocus(contentReferenceService.findCommunityFocusIntegrally(null, null, new Page()).getList());
        PersonalRecommendation personalRecommendation = new PersonalRecommendation();
        realtimeVO.setRecommendations(personalRecommendation);
        if (Objects.isNull(user)) {
            List<CategoryVO> similarCategories = userFigureService.findSimilarCategoryByAvgIntegrally();
            List<CategoryVO> sametasteCategory = userFigureService.findSametasteCategoryByAvgIntegrally();
            personalRecommendation.setGuessing(similarCategories.get(0));
            personalRecommendation.setSametaste(sametasteCategory.get(0));
        } else {
            List<CategoryVO> similarCategories = userFigureService.findSimilarCategoryByUserIdIntegrally(user.getId());
            List<CategoryVO> sametasteCategory = userFigureService.findSametasteCategoryByUserIdIntegrally(user.getId());
            if (similarCategories.size() > 0 && sametasteCategory.size() > 0) {
                personalRecommendation.setGuessing(similarCategories.get(0));
                personalRecommendation.setSametaste(sametasteCategory.get(0));
            }
        }
        return RestfulUtil.success(realtimeVO);
    }
}
