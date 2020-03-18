package com.codimiracle.application.platform.huidu.entity.vo;

import lombok.Data;

@Data
public class FigureTagVO {
    private String id;
    private String userId;
    private Double score;
    private TagVO tag;
}
