package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.BookAudioEpisode;
import com.codimiracle.application.platform.huidu.entity.vo.BookAudioEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vt.AudioCatalogs;
import com.codimiracle.application.platform.huidu.enumeration.BookAudioEpisodeStatus;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface BookAudioEpisodeService extends Service<String, BookAudioEpisode> {

    PageSlice<BookAudioEpisodeVO> findAllIntegrally(String bookId, Filter filter, Sorter sorter, Page page);

    BookAudioEpisodeVO findByIdIntegrally(String id);

    void deleteByIdLogically(String id);

    BookAudioEpisodeVO findLastUpdateEpisodeByBookIdIntegrally(String bookId);

    BookAudioEpisodeVO findByMediaNumberIntegrally(String bookId, Integer mediaNumber);

    List<AudioCatalogs> findCatalogsByBookIdAndStatus(String id, BookAudioEpisodeStatus status);
}
