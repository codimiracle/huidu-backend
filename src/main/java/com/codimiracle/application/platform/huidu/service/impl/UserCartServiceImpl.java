package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.CartItem;
import com.codimiracle.application.platform.huidu.entity.vo.CartItemVO;
import com.codimiracle.application.platform.huidu.mapper.UserCartMapper;
import com.codimiracle.application.platform.huidu.service.CommodityService;
import com.codimiracle.application.platform.huidu.service.UserCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserCartServiceImpl extends AbstractService<String, CartItem> implements UserCartService {
    @Resource
    private UserCartMapper userCartMapper;

    @Resource
    private CommodityService commodityService;

    @Override
    public void deleteByIdLogically(String id) {
        userCartMapper.deleteByIdLogically(id);
    }

    @Override
    public void settleByIdsLogically(List<String> ids) {
        Optional.ofNullable(ids).ifPresent((e) -> e.forEach(userCartMapper::settleByIdLogically));
    }


    @Override
    public PageSlice<CartItemVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<CartItemVO> slice = extractPageSlice(userCartMapper.selectAllIntegrally(filter, sorter, page));
        slice.getList().forEach(this::paddingAssociation);
        return slice;
    }
    private void paddingAssociation(CartItemVO cartItemVO) {
        cartItemVO.setCommodity(commodityService.findByIdIntegrally(cartItemVO.getCommodityId()));
    }

    @Override
    public CartItemVO findByIdIntegrally(String id) {
        CartItemVO cartItemVO = userCartMapper.selectByIdIntegrally(id);
        paddingAssociation(cartItemVO);
        return cartItemVO;
    }

    @Override
    public Integer countByUserId(String userId) {
        Condition condition = Conditioner.conditionOf(CartItem.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("deleted", false);
        criteria.andEqualTo("userId", userId);
        return userCartMapper.selectCountByCondition(condition);
    }

    @Override
    public List<CartItemVO> findByIdsIntegrally(List<String> ids) {
        return userCartMapper.selectByIdsIntegrally(ids);
    }

    @Override
    public void settleByIdLogically(String cartItemId) {
        userCartMapper.settleByIdLogically(cartItemId);
    }
}
