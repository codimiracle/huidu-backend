package com.codimiracle.application.platform.huidu.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MottoServiceTest {

    @Resource
    private MottoService mottoService;
    @Test
    void randomMotto() {
        String s = mottoService.randomMotto();
        System.out.println(s);
        assertNotNull(s);
    }
}