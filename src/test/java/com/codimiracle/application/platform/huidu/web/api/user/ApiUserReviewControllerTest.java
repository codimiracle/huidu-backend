package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.entity.dto.ReviewDTO;
import com.codimiracle.application.platform.huidu.entity.embedded.ContentSource;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiUserReviewControllerTest {
    @Autowired
    private ApiUserReviewController apiUserReviewController;
    @Resource
    private UserService userService;

    @Test
    void create() {
        User user = userService.findById("1");
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setTitle("这是我最喜欢的书");
        reviewDTO.setWords(10000);
        ContentSource source = new ContentSource();
        source.setSource("<p>这是一条点评</p>");
        source.setType("html");
        reviewDTO.setRate(3.0f);
        reviewDTO.setContent(source);
        reviewDTO.setReferences(new String[]{"23", "9", "8"});
        reviewDTO.setStatus(ContentStatus.Draft.toString());
        apiUserReviewController.create(user, reviewDTO);
    }

    @Test
    void update() {
        User user = userService.findById("1");
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setTitle("这是我最喜欢的书x");
        reviewDTO.setWords(10002);
        ContentSource source = new ContentSource();
        source.setSource("<p>这是一条点评x</p>");
        source.setType("html");
        reviewDTO.setRate(3.0f);
        reviewDTO.setContent(source);
        reviewDTO.setReferences(new String[]{"9"});
        reviewDTO.setStatus(ContentStatus.Draft.toString());
        apiUserReviewController.update(user, "60", reviewDTO);
    }

    @Test
    void delete() {
        User user = userService.findById("1");
        apiUserReviewController.delete(user, "60");
    }
}