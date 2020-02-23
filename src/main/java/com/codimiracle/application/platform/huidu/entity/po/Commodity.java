package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Commodity {
    /**
     * 购买项id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 购买项名称
     */
    private String name;

    /**
     * 购买项类型
     */
    private String type;

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
    private String rate;

    /**
     * 重量(g)
     */
    private Double weight;

    /**
     * 初始库存
     */
    private String stock;

    /**
     * 可用库存
     */
    @Column(name = "available_stock")
    private String availableStock;

    /**
     * 销售数量
     */
    private String sales;

    /**
     * 运费
     */
    private String shipment;

    /**
     * 额外数据
     */
    private String extra;

    private String prices;

    private String status;
}