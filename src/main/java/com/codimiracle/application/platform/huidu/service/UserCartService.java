package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.CartItem;
import com.codimiracle.application.platform.huidu.entity.vo.CartItemVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;

public interface UserCartService extends Service<String, CartItem, CartItemVO> {

    void settleByIdsLogically(List<String> ids);

    CartItemVO findByIdIntegrally(String id);

    Integer countByUserId(String id);

    List<CartItemVO> findByIdsIntegrally(List<String> ids);

    void settleByIdLogically(String cartItemId);

    void deleteByIdsLogically(List<String> ids);

    boolean isJoined(String userId, String bookId);
}
