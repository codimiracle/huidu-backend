package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.ContentExamination;
import com.codimiracle.application.platform.huidu.entity.vo.ExaminationVO;
import org.apache.ibatis.annotations.Param;

public interface ContentExaminationMapper extends Mapper<ContentExamination> {
    ExaminationVO selectLastExaminationByContentId(@Param("contentId") String contentId);
}