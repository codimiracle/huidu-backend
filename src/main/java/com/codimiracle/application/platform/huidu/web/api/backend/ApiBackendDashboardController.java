package com.codimiracle.application.platform.huidu.web.api.backend;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.vo.BookCategoryCountVO;
import com.codimiracle.application.platform.huidu.entity.vo.PaperBookSalesVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserBookPreferenceVO;
import com.codimiracle.application.platform.huidu.entity.vo.UserProtectedVO;
import com.codimiracle.application.platform.huidu.service.StatisticsService;
import com.codimiracle.application.platform.huidu.service.UserTokenService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/backend/dashboard")
public class ApiBackendDashboardController {
    @Resource
    private StatisticsService statisticsService;

    @Resource
    private UserTokenService userTokenService;

    @GetMapping("/user-book-preference")
    public ApiResponse statisticsUserBookPreference() {
        List<UserBookPreferenceVO> list = statisticsService.statisticsUserBookPreference();
        return RestfulUtil.success(list);
    }

    @GetMapping("/platform-statistics")
    public ApiResponse platformStatistics() {
        return RestfulUtil.success(statisticsService.statisticsPlatformData());
    }

    @GetMapping("/book-category-count")
    public ApiResponse statisticsBookCategoryCount() {
        List<BookCategoryCountVO> list = statisticsService.statisticsBookCategoryCount();
        return RestfulUtil.success(list);
    }

    @GetMapping("/paper-book-sales")
    public ApiResponse statisticsPaperBookSales() {
        List<PaperBookSalesVO> list = statisticsService.statisticsPaperBookSales();
        return RestfulUtil.success(list);
    }

    @GetMapping("/online-users")
    public ApiResponse onlineUsers() {
        PageSlice<UserProtectedVO> slice = userTokenService.findOnlineUserProtectively(new Page(1, 10));
        return RestfulUtil.list(slice);
    }
}
