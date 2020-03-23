package com.codimiracle.application.platform.huidu.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShipmentPredictionDTO {
    private String addressId;
    private List<OrderDetailsDTO> items;
}
