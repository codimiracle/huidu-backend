package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.entity.dto.ArrivingDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class ApiArrivedHistoryControllerTest {

    @Autowired
    private ApiArrivedHistoryController apiArrivedHistoryController;
    @Resource
    private UserService userService;

    @Test
    void signin() {
        User user = userService.findById("1");
        ArrivingDTO arrivingDTO = new ArrivingDTO();
        arrivingDTO.setMotto("Hello world");
        // previous
        arrivingDTO.setDate(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
        System.out.println(apiArrivedHistoryController.signin(user, arrivingDTO));
        // today
        arrivingDTO.setDate(new Date());
        System.out.println(apiArrivedHistoryController.signin(user, arrivingDTO));
        // future
        arrivingDTO.setDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
        System.out.println(apiArrivedHistoryController.signin(user, arrivingDTO));
    }

    @Test
    void today() {
        User user = userService.findById("1");
        System.out.println(apiArrivedHistoryController.today(user));
    }
}