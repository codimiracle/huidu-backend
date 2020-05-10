package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface BookMapper extends Mapper<Book,BookVO> {
    BookVO selectByIdWithTypeIntegrally(@Param("type") BookType type, @Param("id") String id);
    List<BookVO> selectByTypeIntegrally(@Param("type") BookType type, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdsLogically(List<String> ids);

    List<BookVO> selectByCategoryIdIntegrally(@Param("categoryId") String categoryId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);
    List<BookVO> selectByCollectionIdIntegrally(@Param("categoryId") String categoryId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<String> selectPublishYearsByType(@Param("type") BookType bookType);

    void readsIncrement(String id);

    void playsIncrement(String id);

    BookVO selectByContentIdIntegrally(String contentId);

    List<BookVO> selectAllUsingUserFigureByUserIdIntegrally(@Param("userId") String userId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<BookVO> selectAllUsingUserFigureByAvgIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<BookVO> selectAllUsingUserFigureByTagId(@Param("tagId") String tagId, @Param("userId") String userId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<BookVO> selectAllUsingUserFigureByCategoryId(@Param("categoryId") String categoryId, @Param("userId") String userId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<BookVO> selectAllUsingUserFigureByBookId(@Param("bookId") String bookId, @Param("userId") String userId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<BookVO> selectAllUsingUserFigureByBookType(@Param("type") BookType type, @Param("userId") String userId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<BookVO> selectAllUsingUserFigureByHistoryToday(@Param("userId") String userId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    Float avgReviewRate(String bookId);

    String selectMetadataIdByBookId(String bookId);

    void incrementEpisodes(String bookId);

    void decrementEpisodes(String bookId);

    String findContentIdByBookId(String bookId);
}