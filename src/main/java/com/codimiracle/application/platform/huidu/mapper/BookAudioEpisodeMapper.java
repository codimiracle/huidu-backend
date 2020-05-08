package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.BookAudioEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.AudioCatalogs;
import com.codimiracle.application.platform.huidu.enumeration.BookAudioEpisodeStatus;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface BookAudioEpisodeMapper extends Mapper<BookAudioEpisode, BookAudioEpisodeVO> {
    List<BookAudioEpisodeVO> selectAllIntegrally(@Param("bookId") String bookId, @Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    BookAudioEpisodeVO selectByIdIntegrally(String id);

    void deleteByIdLogically(String id);

    List<AudioCatalogs> selectCatalogsByBookIdAndStatus(@Param("bookId") String bookId, @Param("status") BookAudioEpisodeStatus status);

    BookAudioEpisodeVO selectLastUpdateEpisodeByBookId(String bookId);

    BookAudioEpisodeVO selectByMediaNumberIntegrally(@Param("bookId") String bookId, @Param("mediaNumber") Integer mediaNumber);

    BookAudioEpisodeVO selectLastPublishedEpisodeByBookId(String bookId);

    String selectAudioEpisodeIdByMediaNumber(String bookId, int mediaNumber);

    BookAudioEpisodeVO selectLastEpisodeByBookIdIntegrally(String bookId);

    String selectPublishedAudioEpisodeIdByMediaNumber(String bookId, int mediaNumber);
}