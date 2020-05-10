package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.Book;
import com.codimiracle.application.platform.huidu.entity.vo.BookVO;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface BookService extends Service<String, Book, BookVO> {

    BookVO findPublishByIdIntegrally(BookType type, String id);

    BookVO findByIdWithTypeIntegrally(BookType type, String id);

    PageSlice<BookVO> findByTypeIntegrally(BookType type, Filter filter, Sorter sorter, Page page);

    void deleteByIdsLogically(List<String> ids);

    PageSlice<BookVO> findByCategoryIdIntegrally(String categoryId, Filter filter, Sorter sorter, Page page);

    PageSlice<BookVO> findByCollectionIdIntegrally(String categoryId, Filter filter, Sorter sorter, Page page);

    List<String> retrivePublishYearsByType(BookType bookType);

    void readsIncrement(String id);

    void playsIncrement(String id);

    BookVO findByContentIdIntegrally(String contentId);

    PageSlice<BookVO> findAllUsingUserFigureByUserIdIntegrally(String id, Filter filter, Sorter sorter, Page page);

    PageSlice<BookVO> findAllUsingUserFigureByAvgIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<BookVO> findAllUsingUserFigureByTagId(String tagId, String userId, Filter filter, Sorter sorter, Page page);

    PageSlice<BookVO> findAllUsingUserFigureByCategoryId(String categoryId, String userId, Filter filter, Sorter sorter, Page page);

    PageSlice<BookVO> findAllUsingUserFigureByBookId(String bookId, String userId, Filter filter, Sorter sorter, Page page);

    PageSlice<BookVO> findAllUsingUserFigureByHistoryToday(String userId, Filter filter, Sorter sorter, Page page);

    Book findByCommodityId(String commodityId);

    PageSlice<BookVO> findAllUsingUserFigureByBookType(BookType type, String userId, Filter filter, Sorter sorter, Page page);

    Float avgReviewStars(String bookId);

    String findMetadataIdByBookId(String bookId);

    void incrementEpisodes(String bookId);

    void decrementEpisodes(String bookId);

    String findContentIdByBookId(String bookId);
}
