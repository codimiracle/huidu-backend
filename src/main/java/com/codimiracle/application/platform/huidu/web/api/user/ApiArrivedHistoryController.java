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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/arrive")
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
        //补签
        LocalDate nowDate = LocalDate.now();
        LocalDate signedDate = LocalDate.ofEpochDay(arrivingDTO.getDate().getTime());
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
        ArrivedHistoryVO arrivedHistoryVO = arrivedHistoryService.findByThatDayIntegrally(now);
        if (Objects.isNull(arrivedHistoryVO)) {
            arrivedHistoryVO = new ArrivedHistoryVO();
            arrivedHistoryVO.setMotto(mottoService.randomMotto());
            List<HistoryVO> reads = historyService.findThatDayByUserIdIntegrally(user.getId(), now);
            arrivedHistoryVO.setReads(reads);
            Map<String, Boolean> historyMap = arrivedHistoryService.retriveHistoryMap(user.getId());
            arrivedHistoryVO.setHistory(historyMap);
            arrivedHistoryVO.setToday(now);
            arrivedHistoryVO.setUser(userService.findByIdProtectly(user.getId()));
        }
        return RestfulUtil.entity(arrivedHistoryVO);
    }
}
