package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Subscribe;
import com.codimiracle.application.platform.huidu.entity.vo.SubscribeVO;
import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;


/**
 * @author Codimiracle
 */
public interface SubscribeService extends Service<String, Subscribe> {

    PageSlice<SubscribeVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    /**
     * 根据订阅类型对内容进行订阅
     *
     * @param subscriberId 订阅者Id
     * @param targetId 订阅对应的内容Id
     * @param type 订阅的类型
     */
    void subscribe(String subscriberId, String targetId, SubscribeType type);

    void deleteByIdLogically(String subscribeId);
}
