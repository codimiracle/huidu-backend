package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "content_examination")
public class ContentExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "target_content_id")
    private String targetContentId;

    @Column(name = "from_status")
    private String fromStatus;

    @Column(name = "to_status")
    private String toStatus;

    private String reason;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "examine_time")
    private Date examineTime;
}