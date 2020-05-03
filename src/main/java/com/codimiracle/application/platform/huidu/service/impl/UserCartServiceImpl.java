package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.Conditioner;
import com.codimiracle.application.platform.huidu.entity.po.CartItem;
import com.codimiracle.application.platform.huidu.entity.vo.CartItemVO;
import com.codimiracle.application.platform.huidu.mapper.UserCartMapper;
import com.codimiracle.application.platform.huidu.service.CommodityService;
import com.codimiracle.application.platform.huidu.service.UserCartService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
public class UserCartServiceImpl extends AbstractService<String, CartItem, CartItemVO> implements UserCartService {
    @Resource
    private UserCartMapper userCartMapper;

    @Resource
    private CommodityService commodityService;

    @Override
    public void settleByIdsLogically(List<String> ids) {
        Optional.ofNullable(ids).ifPresent((e) -> e.forEach(userCartMapper::settleByIdLogically));
    }

    protected CartItemVO mutate(CartItemVO cartItemVO) {
        cartItemVO.setCommodity(commodityService.findByIdIntegrally(cartItemVO.getCommodityId()));
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

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        userCartMapper.deleteByIdsLogically(ids);
    }

    @Override
    public boolean isJoined(String userId, String commodityId) {
        Boolean result = userCartMapper.existsByUserIdAndCommodityId(userId, commodityId);
        return Objects.nonNull(result) && result;
    }
}
