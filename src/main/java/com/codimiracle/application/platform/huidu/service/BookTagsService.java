package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.BookTags;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;

import java.util.List;

public interface BookTagsService extends Service<String, BookTags> {
    void deleteByIdLogically(String id);

    List<TagVO> findByBookIdItegrally(String id);

    List<BookTags> findByBookId(String bookId);
}
