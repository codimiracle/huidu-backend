package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper extends Mapper<Book> {
    BookVO selectByIdIntegrally(@Param("type") BookType type, @Param("id") String id);

    List<BookVO> selectAllIntegrally(@Param("type") BookType type, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    List<BookVO> selectByCategoryIdIntegrally(@Param("categoryId") String categoryId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<String> selectPublishYearsByType(@Param("type") BookType bookType);

    void readsIncrement(String id);

    void playsIncrement(String id);

    BookVO selectByContentIdIntegrally(String contentId);

    List<BookVO> selectAllUsingUserFigureByUserIdIntegrally(@Param("userId") String userId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<BookVO> selectAllUsingUserFigureByAvgIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<BookVO> selectAllUsingUserFigureByTagId(@Param("tagId") String tagId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<BookVO> selectAllUsingUserFigureByCategoryId(@Param("categoryId") String categoryId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);
}