package com.codimiracle.application.platform.huidu.converter;

import com.codimiracle.application.platform.huidu.entity.dto.CommentDTO;
import com.codimiracle.web.middleware.content.pojo.po.Comment;
import com.codimiracle.web.mybatis.contract.support.vo.converter.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter extends AbstractConverter<CommentDTO, Comment> {
}
