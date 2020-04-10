package com.codimiracle.application.platform.huidu.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserBookPreferenceVO {
    String tagName;
    BigDecimal score;
    Integer count;
}
