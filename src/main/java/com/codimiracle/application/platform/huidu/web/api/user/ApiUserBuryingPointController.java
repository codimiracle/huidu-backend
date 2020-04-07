package com.codimiracle.application.platform.huidu.web.api.user;

import com.codimiracle.application.platform.huidu.contract.ApiResponse;
import com.codimiracle.application.platform.huidu.entity.dto.ReadingHistoryDTO;
import com.codimiracle.application.platform.huidu.entity.po.User;
import com.codimiracle.application.platform.huidu.service.UserFigureService;
import com.codimiracle.application.platform.huidu.web.api.base.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/history")
    public ApiResponse history(@AuthenticationPrincipal User user, ReadingHistoryDTO readingHistoryDTO) {
        return apiHistoryController.reading(user, readingHistoryDTO);
    }

    @GetMapping("/join-book-shelf")
    public void joinBookShelf(@AuthenticationPrincipal User user, String bookId) {
        apiBookShelfController.join(user, bookId);
    }

    @GetMapping("/visit-book-details")
    public void visitBookDetails(String bookId) {

    }
}
