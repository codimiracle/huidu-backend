package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.Catalogs;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookEpisodeMapper extends Mapper<BookEpisode, BookEpisodeVO> {
    BookEpisodeVO selectByIdIntegrally(String id);

    List<BookEpisodeVO> selectAllIntegrally(@Param("bookId") String bookId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    BookEpisodeVO selectLastUpdateEpisodeIntegrally(String bookId);

    BookEpisodeVO selectLastEpisodeByBookIdIntegrally(String bookId);

    BookEpisodeVO selectByEpisodeNumberIntegrally(String bookId, Integer episodeNumber);

    List<Catalogs> selectCatalogsByBookId(@Param("bookId") String bookId, @Param("status") ContentStatus status);

    BookEpisodeVO selectLastPublishedEpisodeByBookId(String bookId);

    String selectEpisodeIdByEpisodeNumber(@Param("bookId") String bookId, @Param("episodeNumber") int episodeNumber);
}