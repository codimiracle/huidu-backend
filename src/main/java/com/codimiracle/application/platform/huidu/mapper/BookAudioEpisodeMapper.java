package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.BookAudioEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.AudioCatalogs;
import com.codimiracle.application.platform.huidu.enumeration.BookAudioEpisodeStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookAudioEpisodeMapper extends Mapper<BookAudioEpisode> {
    List<BookAudioEpisodeVO> selectAllIntegrally(@Param("bookId") String bookId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    BookAudioEpisodeVO selectByIdIntegrally(String id);

    void deleteByIdLogically(String id);

    List<AudioCatalogs> selectCatalogsByBookIdAndStatus(@Param("bookId") String bookId, @Param("status") BookAudioEpisodeStatus status);

    BookAudioEpisodeVO selectLastUpdateEpisodeByBookId(String bookId);

    BookAudioEpisodeVO selectByMediaNumberIntegrally(@Param("bookId") String bookId, @Param("mediaNumber") Integer mediaNumber);

    BookAudioEpisodeVO selectLastPublishedEpisodeByBookId(String bookId);
}