package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.Subscribe;
import com.codimiracle.application.platform.huidu.entity.vo.SubscribeVO;
import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubscribeMapper extends Mapper<Subscribe> {
    List<SubscribeVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    Subscribe selectBySubscriberIdAndBookId(@Param("subscriberId") String subscriberId, @Param("bookId") String bookId);

    Subscribe selectBySubscriberIdAndCommodityId(@Param("subscriberId") String subscriberId, @Param("commodityId") String commodityId);

    Subscribe selectBySubscriberIdAndContentId(@Param("subscriberId") String subscriberId, @Param("contentId") String contentId);

    List<Subscribe> selectBySubscriberIdAndType(@Param("subscriberId") String subscriberId, @Param("type") SubscribeType type);

    void deleteByIdLogically(@Param("subscribeId") String subscribeId);
}