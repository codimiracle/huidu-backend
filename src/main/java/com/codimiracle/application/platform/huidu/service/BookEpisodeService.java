package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.Catalogs;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface BookEpisodeService extends Service<String, BookEpisode> {

    BookEpisodeVO findByIdIntegrally(String id);

    PageSlice<BookEpisodeVO> findAllIntegrally(String bookId, Filter filter, Sorter sorter, Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    BookEpisodeVO findLastUpdateEpisodeByBookIdIntegrally(String id);

    BookEpisodeVO findLastEpisodeByBookId(String id);

    Integer findLastEpisodeNumberByBookId(String id);

    BookEpisodeVO findByEpisodeNumberIntegrally(String bookId, Integer episodeNumber);

    List<Catalogs> findCatalogsByBookIdAndStatus(String id, ContentStatus status);

    BookEpisodeVO findLastPublishedEpisodeByBookId(String bookId);
}
