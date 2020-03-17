package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Content;
import com.codimiracle.application.platform.huidu.entity.vo.ContentVO;

import java.util.List;


/**
 * @author Codimiracle
 */
public interface ContentService extends Service<String, Content> {

    int deleteByIdLogically(String id);

    ContentVO findByIdIntegrally(String id);

    void deleteByIdsLogically(List<String> ids);

    void likesIncrement(String contentId);

    void likesDecrement(String contentId);

    void commentsIncrement(String contentId);

    void commentsDecrement(String contentId);

    void rateIncrementBy(String contentId, Float rate);
    void rateDecrementBy(String contentId, Float rate);

    PageSlice<ContentVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);
}
