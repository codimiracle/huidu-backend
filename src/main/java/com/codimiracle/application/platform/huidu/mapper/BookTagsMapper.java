package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.BookTags;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;

import java.util.List;

public interface BookTagsMapper extends Mapper<BookTags> {
    void deleteByLogically(String id);

    List<TagVO> selectByBookIdIntegrally(String id);
}