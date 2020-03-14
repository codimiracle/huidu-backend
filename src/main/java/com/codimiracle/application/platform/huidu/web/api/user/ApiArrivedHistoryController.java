package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.entity.dto.ArrivingDTO;
import com.codimiracle.application.platform.huidu.entity.po.ArrivedHistory;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.ArrivedHistoryVO;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;
import com.codimiracle.application.platform.huidu.service.ArrivedHistoryService;
import com.codimiracle.application.platform.huidu.service.HistoryService;
import com.codimiracle.application.platform.huidu.service.MottoService;
import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user/arrive")
public class ApiArrivedHistoryController {
    @Resource
    private ArrivedHistoryService arrivedHistoryService;

    @Resource
    private HistoryService historyService;

    @Resource
    private MottoService mottoService;

    @Resource
    private UserService userService;

    @PostMapping("/signin")
    public ApiResponse signin(@AuthenticationPrincipal User user, @RequestBody ArrivingDTO arrivingDTO) {
        LocalDate nowDate = LocalDate.now();
        LocalDate signedDate = LocalDate.parse(DateFormatUtils.format(arrivingDTO.getDate(), "yyyy-MM-dd"));
        if (nowDate.compareTo(signedDate) == 0) {
            //当天签到
            ArrivedHistory signed = arrivedHistoryService.signin(user.getId(), arrivingDTO.getDate());
            return RestfulUtil.entity(signed);
        } else if (nowDate.compareTo(signedDate) < 0) {
            return RestfulUtil.fail("不能进行未来签到！");
        }
        return RestfulUtil.fail("不能补签!");
    }


    @GetMapping("/today")
    public ApiResponse today(@AuthenticationPrincipal User user) {
        Date now = new Date();
        ArrivedHistoryVO arrivedHistoryVO = arrivedHistoryService.findByThatDayIntegrally(user.getId(), now);
        if (Objects.isNull(arrivedHistoryVO)) {
            arrivedHistoryVO = new ArrivedHistoryVO();
            arrivedHistoryVO.setDays(0);
            arrivedHistoryVO.setMotto(mottoService.randomMotto());
            arrivedHistoryVO.setToday(now);
            arrivedHistoryVO.setUser(userService.findByIdProtectly(user.getId()));
        }
        List<HistoryVO> reads = historyService.findByThatDayAndUserIdIntegrally(user.getId(), now);
        arrivedHistoryVO.setReads(reads);
        Map<String, Boolean> historyMap = arrivedHistoryService.retriveHistoryMap(user.getId());
        if (Objects.isNull(historyMap)) {
            historyMap = Collections.emptyMap();
        }
        arrivedHistoryVO.setHistory(historyMap);
        return RestfulUtil.entity(arrivedHistoryVO);
    }
}
