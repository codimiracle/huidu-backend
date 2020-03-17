package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.History;
import com.codimiracle.application.platform.huidu.entity.vo.BookEpisodeVO;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;

import java.util.Date;
import java.util.List;


/**
 * @author Codimiracle
 */
public interface HistoryService extends Service<String, History> {

    HistoryVO findByUserIdAndBookIdIntegrally(String userId, String bookId);
    HistoryVO findByUserIdAndBookIdIntegrallyOrFirstEpisode(String userId, String bookId);

    History findByUserIdAndBookId(String userId, String bookId);

    HistoryVO findByIdIntegrally(String historyId);

    List<HistoryVO> findByUserIdIntegrally(String userId);

    List<HistoryVO> findByThatDayAndUserIdIntegrally(String userId, Date thaDay);

    void deleteByIdLogically(String id);

    PageSlice<HistoryVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);
}
