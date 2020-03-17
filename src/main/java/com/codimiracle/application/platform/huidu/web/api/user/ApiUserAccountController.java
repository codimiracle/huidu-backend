package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.entity.dto.OrderringDTO;
import com.codimiracle.application.platform.huidu.entity.dto.PayingDTO;
import com.codimiracle.application.platform.huidu.entity.dto.RechargeDTO;
import com.codimiracle.application.platform.huidu.entity.po.Order;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.entity.vo.UserAccountVO;
import com.codimiracle.application.platform.huidu.enumeration.OrderType;
import com.codimiracle.application.platform.huidu.enumeration.PaymentType;
import com.codimiracle.application.platform.huidu.service.OrderService;
import com.codimiracle.application.platform.huidu.service.UserAccountService;
import com.codimiracle.application.platform.huidu.util.RestfulUtil;
import com.codimiracle.application.platform.huidu.web.api.base.OrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Codimiracle
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user/account")
public class ApiUserAccountController {
    @Resource
    private UserAccountService userAccountService;

    @Resource
    private OrderService orderService;

    @Autowired
    private OrderController orderController;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping("/pay")
    public ApiResponse pay(@AuthenticationPrincipal User user, @RequestBody PayingDTO payingDTO) {
        Order order = orderService.findByOrderNumber(payingDTO.getOrderNumber());
        if (Objects.isNull(order) || !Objects.equals(order.getOwnerId(), user.getId())) {
            return RestfulUtil.fail("订单没有找到！");
        }
        PaymentType paymentType = PaymentType.valueOfCode(payingDTO.getType());
        if (paymentType == PaymentType.Huidu) {
            if (!passwordEncoder.matches(payingDTO.getPassword(), user.getPassword())) {
                return RestfulUtil.fail("支付失败，密码不匹配！");
            }
        }
        userAccountService.pay(order.getOrderNumber(), paymentType);
        return RestfulUtil.success();
    }

    @PostMapping("/recharge")
    public ApiResponse recharge(@AuthenticationPrincipal User user, @RequestBody RechargeDTO rechargeDTO) {
        OrderringDTO orderringDTO = new OrderringDTO();
        orderringDTO.setType(OrderType.Recharge.getType());
        orderringDTO.setCharge(rechargeDTO.getCharge());
        return orderController.orderring(user, orderringDTO);
    }

    @GetMapping
    public ApiResponse account(@AuthenticationPrincipal User user) {
        UserAccountVO userAccountVO = userAccountService.findByUserIdIntegrally(user.getId());
        return RestfulUtil.entity(userAccountVO);
    }
}
