package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.Catalogs;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookEpisodeMapper extends Mapper<BookEpisode> {
    BookEpisodeVO selectByIdIntegrally(String id);

    List<BookEpisodeVO> selectAllIntegrally(@Param("bookId") String bookId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    BookEpisodeVO selectLastUpdateEpisodeIntegrally(String bookId);

    BookEpisodeVO selectLastEpisodeIntegrally(String bookId);

    BookEpisodeVO selectByEpisodeNumberIntegrally(String bookId, Integer episodeNumber);

    List<Catalogs> selectCatalogsByBookId(@Param("bookId") String bookId, @Param("status") ContentStatus status);

    BookEpisodeVO selectLastPublishedEpisodeByBookId(String bookId);
}