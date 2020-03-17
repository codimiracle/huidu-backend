package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.UserAccount;
import com.codimiracle.application.platform.huidu.entity.vo.UserAccountVO;
import com.codimiracle.application.platform.huidu.enumeration.PaymentType;
import org.joda.money.Money;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface UserAccountService extends Service<String, UserAccount> {

    List<UserAccount> findByUserId(String userId);

    void pay(Money charge, String userId);

    void refund(Money charge, String userId);

    void pay(String orderNumber, PaymentType type);

    UserAccountVO findByUserIdIntegrally(String id);

}
