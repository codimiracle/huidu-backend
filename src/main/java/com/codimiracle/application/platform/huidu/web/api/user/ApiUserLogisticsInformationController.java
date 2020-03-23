package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.PageSlice;
import com.codimiracle.application.platform.huidu.entity.dto.LogisticsInformationDTO;
import com.codimiracle.application.platform.huidu.entity.po.LogisticsInformation;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.LogisticsInformationVO;
import com.codimiracle.application.platform.huidu.service.LogisticsInformationService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user/orders/{order_number}/logistics-information")
public class ApiUserLogisticsInformationController {
    @Resource
    private LogisticsInformationService logisticsInformationService;

    @GetMapping
    public ApiResponse entity(@AuthenticationPrincipal User user, @PathVariable("order_number") String orderNumber) {
        LogisticsInformationVO logisticsInformationVO = logisticsInformationService.findByOrderNumberIntegrally(orderNumber);
        if (Objects.isNull(logisticsInformationVO) || !Objects.equals(user.getId(), logisticsInformationVO.getUserId())) {
            return RestfulUtil.fail("没有找到快递信息！");
        }
        return RestfulUtil.entity(logisticsInformationVO);
    }

}
