package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.contract.ServiceException;
import com.codimiracle.application.platform.huidu.entity.po.Order;
import com.codimiracle.application.platform.huidu.entity.po.UserAccount;
import com.codimiracle.application.platform.huidu.entity.vo.UserAccountVO;
import com.codimiracle.application.platform.huidu.enumeration.OrderStatus;
import com.codimiracle.application.platform.huidu.enumeration.OrderType;
import com.codimiracle.application.platform.huidu.enumeration.PaymentType;
import com.codimiracle.application.platform.huidu.mapper.UserAccountMapper;
import com.codimiracle.application.platform.huidu.service.CommodityService;
import com.codimiracle.application.platform.huidu.service.OrderDetailsService;
import com.codimiracle.application.platform.huidu.service.OrderService;
import com.codimiracle.application.platform.huidu.service.UserAccountService;
import com.codimiracle.application.platform.huidu.util.HuiduMoneyUtil;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import org.apache.commons.lang3.RandomUtils;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class UserAccountServiceImpl extends AbstractService<String, UserAccount> implements UserAccountService {

    Interner<String> orderPaymentLock = Interners.newWeakInterner();
    @Value("${huidu.user-account.portion-number}")
    private Integer portionNumber;
    @Resource
    private OrderService orderService;
    @Resource
    private OrderDetailsService orderDetailsService;
    @Resource
    private CommodityService commodityService;
    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    public List<UserAccount> findByUserId(String userId) {
        return userAccountMapper.selectByUserId(userId);
    }

    private UserAccount randomUserAccount(List<UserAccount> accounts) {
        int randomIndex = RandomUtils.nextInt(0, accounts.size());
        return accounts.get(randomIndex);
    }

    @Override
    public void save(UserAccount model) {
        super.save(model);
        for (int i = 1; i < portionNumber; i++) {
            UserAccount userAccount = new UserAccount();
            userAccount.setUserId(model.getUserId());
            userAccountMapper.insertSelective(userAccount);
        }
    }

    @Override
    public void pay(Money charge, String userId) {
        List<UserAccount> userAccountList = userAccountMapper.selectByUserId(userId);
        Money totalAccount = userAccountList.stream().map(UserAccount::getBalance).reduce(Money::total).orElse(HuiduMoneyUtil.huicoinMoney(BigDecimal.ZERO));
        if (charge.isGreaterThan(totalAccount)) {
            throw new ServiceException("余额不足！");
        }
        UserAccount userAccount = randomUserAccount(userAccountList);
        userAccount.setBalance(userAccount.getBalance().minus(charge));
        update(userAccount);
    }

    @Override
    public void refund(Money charge, String userId) {
        List<UserAccount> userAccountList = userAccountMapper.selectByUserId(userId);
        UserAccount userAccount = randomUserAccount(userAccountList);
        userAccount.setBalance(userAccount.getBalance().plus(charge));
        update(userAccount);
    }

    @Override
    public void pay(String orderNumber, PaymentType type) {
        String lock = orderPaymentLock.intern(String.format("order-%s", orderNumber));
        // 对订单进行加锁
        synchronized (lock) {
            Order oringinal = orderService.findByOrderNumber(orderNumber);
            if (type == PaymentType.Huidu) {
                // 用户账户付款
                pay(oringinal.getTotalMoney(), oringinal.getOwnerId());
            }
            Order updatingOrder = new Order();
            updatingOrder.setOrderNumber(orderNumber);
            updatingOrder.setPayTime(new Date());
            updatingOrder.setPayType(type);
            updatingOrder.setStatus(OrderStatus.AwaitingShipment);
            //充值订单
            if (oringinal.getType() == OrderType.Recharge) {
                //完成订单
                updatingOrder.setDeliverTime(new Date());
                updatingOrder.setClosingTime(new Date());
                updatingOrder.setStatus(OrderStatus.Completed);
                //给用户加帐户
                refund(oringinal.getTotalMoney(), oringinal.getOwnerId());
            }
            orderService.update(updatingOrder);
        }
    }

    @Override
    public UserAccountVO findByUserIdIntegrally(String userId) {
        List<UserAccount> userAccountList = userAccountMapper.selectByUserId(userId);
        UserAccountVO userAccountVO = new UserAccountVO();
        userAccountVO.setUserId(userId);
        userAccountVO.setBalance(userAccountList.stream()
                .map(UserAccount::getBalance)
                .reduce(Money::total).orElse(HuiduMoneyUtil.huicoinMoney(BigDecimal.ZERO))
        );
        return userAccountVO;
    }

}
