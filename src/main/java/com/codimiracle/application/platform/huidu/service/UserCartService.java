package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.CartItem;
import com.codimiracle.application.platform.huidu.entity.vo.CartItemVO;

import java.util.List;

public interface UserCartService extends Service<String, CartItem> {

    void deleteByIdLogically(String id);

    void settleByIdsLogically(List<String> ids);

    PageSlice<CartItemVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    CartItemVO findByIdIntegrally(String id);

    Integer countByUserId(String id);

    List<CartItemVO> findByIdsIntegrally(List<String> ids);

    void settleByIdLogically(String cartItemId);

    void deleteByIdsLogically(List<String> ids);
}
