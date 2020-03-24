package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.History;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface HistoryMapper extends Mapper<History> {
    HistoryVO selectByUserIdAndBookIdIntegrally(@Param("userId") String userId, @Param("bookId") String bookId);

    History selectByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId);

    HistoryVO selectByIdIntegrally(@Param("historyId") String historyId);

    List<HistoryVO> selectThatDayByUserIdIntegrally(@Param("userId") String userId, @Param("thatDay") Date thatDay);

    void deleteByIdLogically(String id);

    List<HistoryVO> selectAllIntegrally(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @RequestParam("page") Page page);
}