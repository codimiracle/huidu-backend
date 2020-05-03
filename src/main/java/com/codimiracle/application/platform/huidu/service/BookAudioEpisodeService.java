package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.BookAudioEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.AudioCatalogs;
import com.codimiracle.application.platform.huidu.enumeration.BookAudioEpisodeStatus;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface BookAudioEpisodeService extends Service<String, BookAudioEpisode, BookAudioEpisodeVO> {

    PageSlice<BookAudioEpisodeVO> findAllIntegrally(String bookId, Filter filter, Sorter sorter, Page page);

    BookAudioEpisodeVO findLastUpdateEpisodeByBookIdIntegrally(String bookId);

    BookAudioEpisodeVO findByMediaNumberIntegrally(String bookId, Integer mediaNumber);

    List<AudioCatalogs> findCatalogsByBookIdAndStatus(String id, BookAudioEpisodeStatus status);

    BookAudioEpisodeVO findLastPublishedEpisodeByBookId(String bookId);

    void passExamination(String id, String reason, String userId);

    void rejectExamination(String id, String reason, String userId);

    Integer findLastMediaNumberByBookId(String bookId);

    Integer findLastPublishedMediaNumberByBookId(String bookId);
}
