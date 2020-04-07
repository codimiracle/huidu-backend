package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.ContentExamination;
import com.codimiracle.application.platform.huidu.entity.vo.ExaminationVO;
import com.codimiracle.application.platform.huidu.mapper.ContentExaminationMapper;
import com.codimiracle.application.platform.huidu.service.ContentExaminationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class ContentExaminationServiceImpl extends AbstractService<String, ContentExamination> implements ContentExaminationService {
    @Resource
    private ContentExaminationMapper contentExaminationMapper;

    @Override
    public ExaminationVO findLastExaminationByContentId(String contentId) {
        return contentExaminationMapper.selectLastExaminationByContentId(contentId);
    }
}
