package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.History;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface HistoryMapper extends Mapper<History, HistoryVO> {
    HistoryVO selectLastReadByUserIdAndBookIdIntegrally(@Param("userId") String userId, @Param("bookId") String bookId);

    History selectLastReadByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId);

    HistoryVO selectByIdIntegrally(@Param("historyId") String historyId);

    List<HistoryVO> selectThatDayByUserIdIntegrally(@Param("userId") String userId, @Param("thatDay") Date thatDay);

    List<HistoryVO> selectAllIntegrally(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @RequestParam("page") Page page);

    List<History> selectByUserIdAndBookIdIntegrally(@Param("userId") String userId, @Param("bookId") String bookId);

    HistoryVO selectLastReadByUserIdAndBookIdAndEpisodeIdIntegrally(@Param("userId") String userId, @Param("bookId") String bookId, @Param("episodeId") String episodeId);

    HistoryVO selectLastReadByUserIdAndBookIdAndAudioEpisodeIdIntegrally(@Param("userId") String userId, @Param("bookId") String bookId, @Param("audioEpisodeId") String audioEpisodeId);

    Float sumProgressByUserIdAndBookId(String userId, String bookId);
}