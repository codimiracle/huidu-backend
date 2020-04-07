package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.ContentExamination;
import com.codimiracle.application.platform.huidu.entity.vo.ExaminationVO;

public interface ContentExaminationService extends Service<String, ContentExamination> {
    public ExaminationVO findLastExaminationByContentId(String contentId);
}
