package com.codimiracle.application.platform.huidu.web.api.creator;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.BookReadingStatisticsVO;
import com.codimiracle.application.platform.huidu.entity.vo.CreativeCategoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.CreativeDataVO;
import com.codimiracle.application.platform.huidu.service.StatisticsService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/creator/dashboard")
public class ApiCreatorDashboardController {

    @Resource
    private StatisticsService statisticsService;

    @GetMapping("/creative-statistics")
    public ApiResponse statisticsCreative(@AuthenticationPrincipal User user) {
        CreativeDataVO creativeDataVO = statisticsService.statisticsCreativeStatistics(user.getId());
        if (Objects.isNull(creativeDataVO)) {
            creativeDataVO = new CreativeDataVO();
            creativeDataVO.setAudioBookCount(0);
            creativeDataVO.setElectronicBookCount(0);
            creativeDataVO.setEpisodeCount(0);
            creativeDataVO.setEpisodeTotalTimes(0L);
            creativeDataVO.setEpisodeTotalWords(0L);
        }
        return RestfulUtil.success(creativeDataVO);
    }

    @GetMapping("/book-reading-statistics")
    public ApiResponse statisticsBookReadingStatistics(@AuthenticationPrincipal User user) {
        List<BookReadingStatisticsVO> list = statisticsService.statisticsBookReadingStatistics(user.getId());
        return RestfulUtil.success(list);
    }

    @GetMapping("/creative-category-statistics")
    public ApiResponse statisticsCreativeCategory(@AuthenticationPrincipal User user) {
        List<CreativeCategoryVO> list = statisticsService.statisticsCreativeCategory(user.getId());
        return RestfulUtil.success(list);
    }
}
