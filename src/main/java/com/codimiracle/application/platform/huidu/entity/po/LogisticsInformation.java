package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.*;

@Table(name = "logistics_information")
public class LogisticsInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "express_number")
    private Integer expressNumber;

    @Column(name = "express_company")
    private String expressCompany;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return express_number
     */
    public Integer getExpressNumber() {
        return expressNumber;
    }

    /**
     * @param expressNumber
     */
    public void setExpressNumber(Integer expressNumber) {
        this.expressNumber = expressNumber;
    }

    /**
     * @return express_company
     */
    public String getExpressCompany() {
        return expressCompany;
    }

    /**
     * @param expressCompany
     */
    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }
}