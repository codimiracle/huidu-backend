package com.codimiracle.application.platform.huidu.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ExaminationVO {
    private String id;
    private String targetContentId;
    private String fromStatus;
    private String toStatus;
    private UserProtectedVO examiner;
    private Date examineTime;
    private String reason;
}
