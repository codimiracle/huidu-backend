package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.*;
import com.codimiracle.application.platform.huidu.entity.vo.OrderVO;
import com.codimiracle.application.platform.huidu.entity.vt.Comment;
import com.codimiracle.application.platform.huidu.enumeration.CommodityStatus;
import com.codimiracle.application.platform.huidu.enumeration.OrderStatus;
import com.codimiracle.application.platform.huidu.enumeration.OrderType;
import com.codimiracle.application.platform.huidu.helper.ShipmentCalculator;
import com.codimiracle.application.platform.huidu.mapper.OrderMapper;
import com.codimiracle.application.platform.huidu.service.*;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class OrderServiceImpl extends AbstractService<String, Order> implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailsService orderDetailsService;

    @Resource
    private LogisticsInformationService logisticsInformationService;

    @Resource
    private UserCartService userCartService;

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private CommodityService commodityService;

    @Resource
    private AddressService addressService;

    @Resource
    private BookService bookService;

    @Resource
    private CommentService commentService;

    @Autowired
    private ShipmentCalculator shipmentCalculator;

    private void settleCartItem(String cartItemId) {
        userCartService.settleByIdLogically(cartItemId);
    }

    private void obtainingAndSetOrderDetails(Order order, OrderDetails orderDetails) {
        //商品处理
        Commodity commodity = commodityService.findById(orderDetails.getCommodityId());
        if (Objects.isNull(commodity)) {
            throw new ServiceException("商品没有找到！");
        }
        //没有库存
        if (commodity.getStatus() == CommodityStatus.SoldOut) {
            throw new ServiceException("商品销售告罄!");
        }
        //没有可用库存从新设定库存
        commodity.setAvailableStock(Optional.ofNullable(commodity.getAvailableStock()).orElse(commodity.getStock()));
        //库存不足
        if (commodity.getAvailableStock() < orderDetails.getQuantity()) {
            throw new ServiceException("库存不足!");
        }
        //删除购物车
        if (Objects.nonNull(orderDetails.getCartItemId())) {
            settleCartItem(orderDetails.getCartItemId());
        }
        //冗余商品数据
        orderDetails.setCommodityName(commodity.getName());
        orderDetails.setCommodityIntroduction(commodity.getIntroduction());
        orderDetails.setCommodityPicture(commodity.getPicture());
        orderDetails.setCommodityWeight(commodity.getWeight());
        orderDetails.setCommodityType(commodity.getType());
        orderDetails.setCommodityPrices(commodity.getPrices());
        //计算出小计
        orderDetails.setPrices(commodity.getPrices().multipliedBy(orderDetails.getQuantity()));
        orderDetails.setOrderNumber(order.getOrderNumber());
        orderDetailsService.save(orderDetails);
        Commodity subtractStockCommodity = new Commodity();
        subtractStockCommodity.setId(commodity.getId());
        subtractStockCommodity.setAvailableStock(commodity.getAvailableStock() - orderDetails.getQuantity());
        if (subtractStockCommodity.getAvailableStock() == 0) {
            subtractStockCommodity.setStatus(CommodityStatus.SoldOut);
        }
        commodityService.update(subtractStockCommodity);
    }

    private void obtainingAndSetOrderAddress(Order order) {
        Address address = addressService.findById(order.getAddressId());
        if (Objects.isNull(address)) {
            throw new ServiceException("收货地址为空！");
        }
        //冗余地址数据
        order.setAddressRegion(address.getRegion());
        order.setAddressAddress(address.getAddress());
        order.setAddressPostcode(address.getPostcode());
        order.setAddressReceiverName(address.getReceiverName());
        order.setAddressReceiverPhone(address.getReceiverPhone());
    }

    @Override
    public void save(Order model) {
        if (model.getType() != OrderType.Recharge) {
            //商品订单
            final Money[] money = {Money.of(CurrencyUnit.of("CNY"), 0)};
            final BigDecimal[] weight = {new BigDecimal(0)};
            model.getDetailsList().forEach((orderDetails -> {
                obtainingAndSetOrderDetails(model, orderDetails);
                //计算总和
                money[0] = money[0].plus(orderDetails.getPrices());
                //计算总净重
                weight[0] = weight[0].add(BigDecimal.valueOf(orderDetails.getCommodityWeight()).multiply(BigDecimal.valueOf(orderDetails.getQuantity())));
            }));
            obtainingAndSetOrderAddress(model);
            Money shipment = shipmentCalculator.calculate(weight[0], model.getAddressRegion(), model.getAddressAddress());
            model.setTotalMoney(money[0].plus(shipment));
            model.setShipmentMoney(shipment);
        }
        super.save(model);
    }

    @Override
    public synchronized void chargebackByOrderNumber(String orderNumber) {
        OrderVO orderVO = orderMapper.selectByOrderNumberIntegrally(orderNumber);
        if (Objects.isNull(orderVO)) {
            throw new ServiceException("订单不存在");
        }
        if (OrderStatus.valueOfCode(orderVO.getStatus()) != OrderStatus.AwaitingShipment) {
            throw new ServiceException("订单或已经处理完毕，或状态不正确！");
        }
        //退还库存
        orderVO.getDetailsList().forEach(orderDetailsVO -> {
            Commodity commodity = commodityService.findById(orderDetailsVO.getCommodity().getId());
            Commodity updatedCommodity = new Commodity();
            updatedCommodity.setId(commodity.getId());
            updatedCommodity.setAvailableStock(commodity.getAvailableStock() + orderDetailsVO.getQuantity());
            commodityService.update(updatedCommodity);
        });
        //金额处理
        userAccountService.refund(orderVO.getTotalMoney(), orderVO.getOwner().getId());
        //变更订单状态
        Order order = new Order();
        order.setOrderNumber(orderVO.getOrderNumber());
        order.setStatus(OrderStatus.Canceled);
        update(order);
    }

    @Override
    public Order findByOrderNumber(String orderNumber) {
        return findBy("orderNumber", orderNumber);
    }

    @Override
    public Money shipmentPrediction(String addressId, List<OrderDetails> orderDetails) {
        Address address = addressService.findById(addressId);
        BigDecimal[] weight = new BigDecimal[]{BigDecimal.ZERO};
        orderDetails.forEach((orderDetail) -> {
            Commodity commodity = commodityService.findById(orderDetail.getCommodityId());
            if (Objects.nonNull(commodity)) {
                weight[0] = weight[0].add(BigDecimal.valueOf(commodity.getWeight()).multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
            }
        });
        return shipmentCalculator.calculate(weight[0], address.getRegion(), address.getAddress());
    }

    @Override
    public void cancel(String userId, String orderNumber, OrderStatus from) {
        changeStatus(userId, orderNumber, from, OrderStatus.Canceled);
    }

    @Override
    public void evaluate(String userId, String orderNumber, Comment comment) {
        Order order = orderMapper.selectByPrimaryKey(orderNumber);
        List<OrderDetails> list = orderDetailsService.findByOrderNumber(orderNumber);
        list.forEach((e) -> {
            Book book = bookService.findByCommodityId(e.getCommodityId());
            if (Objects.nonNull(book)) {
                comment.setOwnerId(userId);
                comment.setTargetContentId(book.getContentId());
                commentService.save(comment);
                // 复用同一条评论
                comment.setId(null);
            }
        });
        //完成交易
        changeStatus(userId, orderNumber, OrderStatus.AwaitingEvaluation, OrderStatus.Completed);
    }

    @Override
    public void changeStatus(String userId, String orderNumber, OrderStatus from, OrderStatus to) {
        Order order = orderMapper.selectByPrimaryKey(orderNumber);
        if (Objects.isNull(order) || !Objects.equals(order.getOrderNumber(), orderNumber)) {
            throw new ServiceException("找不到订单！");
        }
        if (to == OrderStatus.Completed && order.getType() != OrderType.Recharge) {
            //商品订单
            List<OrderDetails> orderDetails = orderDetailsService.findByOrderNumber(orderNumber);
            orderDetails.forEach((od -> {
                commodityService.incrementSalesById(od.getCommodityId(), od.getQuantity());
            }));
        }
        orderMapper.changeStatus(orderNumber, from, to);
    }

    @Override
    public OrderVO findByOrderNumberIntegrally(String orderNumber) {
        OrderVO orderVO = orderMapper.selectByOrderNumberIntegrally(orderNumber);
        mutate(orderVO);
        return orderVO;
    }

    private void mutate(OrderVO orderVO) {
        if (Objects.nonNull(orderVO.getLogisticsInformationId())) {
            orderVO.setLogisticsInformation(logisticsInformationService.findByIdIntegrally(orderVO.getLogisticsInformationId()));
        }
        orderVO.setDetailsList(orderDetailsService.findByOrderNumberIntegrally(orderVO.getOrderNumber()));
    }

    @Override
    public PageSlice<OrderVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<OrderVO> slice = extractPageSlice(orderMapper.selectAllIntegrally(filter, sorter, page));
        slice.getList().forEach(this::mutate);
        return slice;
    }

}
