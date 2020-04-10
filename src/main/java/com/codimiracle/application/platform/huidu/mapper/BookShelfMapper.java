package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.entity.po.BookShelf;

import java.util.List;

public interface BookShelfMapper extends Mapper<BookShelf> {
    List<BookShelf> selectByUserId(String userId);

    Boolean existsByUserIdAndBookId(String userId, String bookId);
}