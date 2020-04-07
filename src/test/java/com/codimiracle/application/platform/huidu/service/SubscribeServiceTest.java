package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubscribeServiceTest {

    @Autowired
    private SubscribeService subscribeService;

    @Test
    void subscribe() {
        subscribeService.subscribe("1", "1", SubscribeType.BookUpdated);
    }

    @Test
    void deleteByIdLogically() {
        subscribeService.deleteByIdLogically("2");
    }
}