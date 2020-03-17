package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.vo.ContentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ContentMapper extends Mapper<Content> {
    int deleteByIdLogically(String id);

    ContentVO findByIdIntegrally(String id);

    void deleteByIdsLogically(List<String> ids);

    void likesIncrement(String contentId);

    void likesDecrement(String contentId);

    void commentsIncrement(String contentId);

    void commentsDecrement(String contentId);

    void rateIncrementBy(@Param("contentId") String contentId, @Param("rate") Float rate);

    void rateDecrementBy(@Param("contentId") String contentId, @Param("rate") Float rate);

    List<ContentVO> selectAllIntegrally(@RequestParam("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @RequestParam("page") Page page);
}