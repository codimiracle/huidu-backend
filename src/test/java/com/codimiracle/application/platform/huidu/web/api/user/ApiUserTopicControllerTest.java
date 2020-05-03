package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.entity.dto.TopicDTO;
import com.codimiracle.application.platform.huidu.entity.embedded.ContentSource;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.application.platform.huidu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiUserTopicControllerTest {
    @Autowired
    private ApiUserTopicController apiUserTopicController;

    @Resource
    private UserService userService;

    @Test
    void create() {
        User user = userService.findById("1");
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setTitle("要不要交流一下推理小说？");
        ContentSource contentSource = new ContentSource();
        contentSource.setType("html");
        contentSource.setSource("<p>这是一个话题</p>");
        topicDTO.setContent(contentSource);
        topicDTO.setReferences(new String[] {"12", "10", "9"});
        topicDTO.setStatus(ContentStatus.Draft.toString());
        topicDTO.setWords(23432);
        apiUserTopicController.create(user, topicDTO);
    }

    @Test
    void update() {
        User user = userService.findById("1");
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setTitle("要不要交流一下推理小说x？");
        ContentSource contentSource = new ContentSource();
        contentSource.setType("html");
        contentSource.setSource("<p>这是一个话题x</p>");
        topicDTO.setContent(contentSource);
        topicDTO.setReferences(new String[] {"12", "9"});
        topicDTO.setStatus(ContentStatus.Draft.toString());
        topicDTO.setWords(23432);
        apiUserTopicController.update(user, "61", topicDTO);
    }

    @Test
    void delete() {
        User user = userService.findById("1");
        apiUserTopicController.delete(user, "61");
    }
}