package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Tag {
    /**
     * 标签名称
     */
    private String name;
    /**
     * 标签id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private boolean deleted;

    public Tag(String name) {
        this.name = name;
    }
}