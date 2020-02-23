package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "logistics_information")
public class LogisticsInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "express_number")
    private Integer expressNumber;

    @Column(name = "express_company")
    private String expressCompany;
}