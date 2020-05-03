package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.History;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.Date;
import java.util.List;


/**
 * @author Codimiracle
 */
public interface HistoryService extends Service<String, History, HistoryVO> {

    HistoryVO findLastReadByUserIdAndBookIdIntegrally(String userId, String bookId);
    HistoryVO findLastReadByUserIdAndBookIdIntegrallyOrFirstEpisode(String userId, String bookId);

    List<History> findByUserIdAndBookId(String userId, String bookId);

    List<History> findByUserIdAndBookIdIntegrally(String userId, String bookId);

    Integer countByUserIdAndBookId(String userId, String bookId);

    List<HistoryVO> findByUserIdIntegrally(String userId);

    List<HistoryVO> findByThatDayAndUserIdIntegrally(String userId, Date thaDay);

    void deleteByIdLogically(String id);

    History findByUserIdAndBookIdAndEpisodeId(String userId, String bookId, String id);

    History findByUserIdAndBookIdAndAudioEpisodeId(String userId, String bookId, String audioEpisodeId);

    void accessEpisode(String userId, String bookId, String episdoeId, Float progress);

    void accessAudioEpisode(String userId, String bookId, String audioEpisdoeId, Float progress);

    HistoryVO findLastReadByUserIdAndBookIdAndEpisodeIdIntegrally(String userId, String bookId, String episodeId);

    HistoryVO findByUserIdAndBookIdAndEpisodeIdOrNewIntegrally(String id, String bookId, String episodeId);

    HistoryVO findByUserIdAndBookIdAndAudioEpisodeIdOrNewIntegrally(String id, String bookId, String episodeId);

    Float findReadingProgressByUserIdAndBookId(String ownerId, String bookId);
}
