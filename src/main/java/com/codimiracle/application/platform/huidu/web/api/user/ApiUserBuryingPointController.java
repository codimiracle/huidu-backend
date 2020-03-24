package com.codimiracle.application.platform.huidu.web.api.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/user/burying-points")
public class ApiUserBuryingPointController {
    @GetMapping("/history")
    public void history() {

    }

    @GetMapping("/join-book-shelf")
    public void joinBookShelf() {

    }

    @GetMapping("/visit-book-details")
    public void visitBookDetails() {

    }
}
