package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.Notification;
import com.codimiracle.application.platform.huidu.entity.vo.NotificationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationMapper extends Mapper<Notification> {
    List<NotificationVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdLogically(String id);

    void markAsRead(String id);

    NotificationVO selectByIdIntegrally(String id);
}