package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.BookShelf;
import com.codimiracle.application.platform.huidu.entity.vo.BookShelfVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface BookShelfMapper extends Mapper<BookShelf, BookShelfVO> {
    List<BookShelf> selectByUserId(String userId);

    Boolean existsByUserIdAndBookId(String userId, String bookId);
}