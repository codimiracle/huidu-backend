package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
public class Tag {
    /**
     * 标签名称
     */
    private final String name;
    /**
     * 标签id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
}