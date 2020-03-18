package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface BookService extends Service<String, Book> {

    BookVO findByIdIntegrally(BookType type, String id);
    BookVO findByIdIntegrally(String id);

    PageSlice<BookVO> findAllIntegrally(BookType type, Filter filter, Sorter sorter, Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    PageSlice<BookVO> findByCategoryIdIntegrally(String categoryId, Filter filter, Sorter sorter, Page page);

    List<String> retrivePublishYearsByType(BookType bookType);

    void readsIncrement(String id);

    void playsIncrement(String id);

    BookVO findByContentIdIntegrally(String contentId);

    PageSlice<BookVO> findAllUsingUserFigureByUserIdIntegrally(String id, Filter filter, Sorter sorter, Page page);

    PageSlice<BookVO> findAllUsingUserFigureByAvgIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<BookVO> findAllUsingUserFigureByTagId(String tagId, Filter filter, Sorter sorter, Page page);

    PageSlice<BookVO> findAllUsingUserFigureByCategoryId(String categoryId, Filter filter, Sorter sorter, Page page);
}
