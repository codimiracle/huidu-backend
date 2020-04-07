package com.codimiracle.application.platform.huidu.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FigureTagVO {
    private String id;
    private String userId;
    private BigDecimal score;
    private TagVO tag;
}
