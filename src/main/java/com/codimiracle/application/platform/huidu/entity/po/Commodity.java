package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.CommodityDTO;
import com.codimiracle.application.platform.huidu.enumeration.CommodityStatus;
import com.codimiracle.application.platform.huidu.enumeration.CommodityType;
import lombok.Data;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Data
public class Commodity {
    /**
     * 购买项id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 购买项名称
     */
    private String name;

    /**
     * 购买项类型
     */
    private CommodityType type;

    /**
     * 购买项图片
     */
    private String picture;

    /**
     * 购买项介绍
     */
    private String introduction;

    /**
     * 评分
     */
    private Float rate;

    /**
     * 重量(g)
     */
    private Double weight;

    /**
     * 初始库存
     */
    private Integer stock;

    /**
     * 可用库存
     */
    @Column(name = "available_stock")
    private Integer availableStock;

    /**
     * 销售数量
     */
    private Long sales;

    /**
     * 运费
     */
    private Money shipment;

    /**
     * 额外数据
     */
    private String extra;

    private Money prices;

    private CommodityStatus status;

    private boolean deleted;

    public static Commodity from(CommodityDTO commodityDTO) {
        if (Objects.isNull(commodityDTO)) {
            return null;
        }
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityDTO, commodity);
        commodity.setShipment(Money.of(CurrencyUnit.of("CNY"), commodityDTO.getShipment()));
        commodity.setPrices(Money.of(CurrencyUnit.of("CNY"), commodityDTO.getPrices()));
        commodity.setType(CommodityType.valueOfCode(commodityDTO.getType()));
        commodity.setStatus(CommodityStatus.valueOfCode(commodityDTO.getStatus()));
        commodity.setExtra(commodityDTO.getSpecification());
        return commodity;
    }
}