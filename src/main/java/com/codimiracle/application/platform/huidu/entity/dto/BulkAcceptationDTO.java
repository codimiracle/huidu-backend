package com.codimiracle.application.platform.huidu.entity.dto;

import lombok.Data;

@Data
public class BulkAcceptationDTO {
    private String[] ids;
    private String reason;
}
