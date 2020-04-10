package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.service.UserFigureService;
import com.codimiracle.application.platform.huidu.web.api.base.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/api/user/burying-points")
public class ApiUserBuryingPointController {
    @Autowired
    private ApiHistoryController apiHistoryController;

    @Autowired
    private ApiBookShelfController apiBookShelfController;

    @Autowired
    private BookController bookController;

    @Resource
    private UserFigureService figureService;
}
