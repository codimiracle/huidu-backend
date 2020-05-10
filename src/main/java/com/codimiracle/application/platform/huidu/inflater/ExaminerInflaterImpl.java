package com.codimiracle.application.platform.huidu.inflater;

import com.codimiracle.application.platform.huidu.service.UserService;
import com.codimiracle.web.middleware.content.inflation.ExaminerInflatable;
import com.codimiracle.web.middleware.content.inflation.ExaminerInflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExaminerInflaterImpl implements ExaminerInflater {
    @Autowired
    private UserService userService;
    @Override
    public void inflate(ExaminerInflatable inflatingPersistentObject) {
        inflatingPersistentObject.setExaminer(userService.findByIdProtectly(inflatingPersistentObject.getExaminerId()));
    }
}
