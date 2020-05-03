package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.Catalogs;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface BookEpisodeService extends Service<String, BookEpisode, BookEpisodeVO> {

    BookEpisodeVO findByIdIntegrally(String id);

    PageSlice<BookEpisodeVO> findAllIntegrally(String bookId, Filter filter, Sorter sorter, Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    BookEpisodeVO findLastUpdateEpisodeByBookIdIntegrally(String id);

    BookEpisodeVO findLastEpisodeByBookId(String id);

    Integer findLastEpisodeNumberByBookId(String id);

    Integer findLastPublishedEpisodeNumberByBookId(String bookId);

    BookEpisodeVO findByEpisodeNumberIntegrally(String bookId, Integer episodeNumber);

    List<Catalogs> findCatalogsByBookIdAndStatus(String id, ContentStatus status);

    BookEpisodeVO findLastPublishedEpisodeByBookId(String bookId);

    void passExamination(String id, String reason, String userId);

    void rejectExamination(String id, String reason, String userId);
}
