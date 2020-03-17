package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Subscribe;
import com.codimiracle.application.platform.huidu.entity.vo.SubscribeVO;
import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;
import com.codimiracle.application.platform.huidu.mapper.SubscribeMapper;
import com.codimiracle.application.platform.huidu.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class SubscribeServiceImpl extends AbstractService<String, Subscribe> implements SubscribeService {
    @Resource
    private SubscribeMapper subscribeMapper;

    @Resource
    private ContentService contentService;

    @Resource
    private CommodityService commodityService;

    @Resource
    private BookService bookService;

    @Resource
    private CommentService commentService;

    private void paddingAssociation(SubscribeVO subscribeVO) {
        if (Objects.equals(subscribeVO.getType(), SubscribeType.BookUpdated.getType())) {
            subscribeVO.setBook(bookService.findByIdIntegrally(subscribeVO.getBookId()));
        }
    }

    @Override
    public PageSlice<SubscribeVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<SubscribeVO> slice = extractPageSlice(subscribeMapper.selectAllIntegrally(filter, sorter, page));
        slice.getList().forEach(this::paddingAssociation);
        return slice;
    }

    @Override
    public void subscribe(String subscriberId, String targetId, SubscribeType type) {
        Subscribe subscribe = null;
        if (SubscribeType.BookUpdated == type) {
            subscribe.setBookId(targetId);
            subscribe = findBySubscriberIdAndBookId(subscriberId, targetId);
        } else if (SubscribeType.CommodityPutOnSale == type) {
            subscribe.setCommodityId(targetId);
            subscribe = findBySubscriberIdAndCommodityId(subscriberId, targetId);
        } else if (SubscribeType.ContentCommented == type) {
            subscribe.setContentId(targetId);
            subscribe = findBySubscriberIdAndContentId(subscriberId, targetId);
        } else if (SubscribeType.ContentReplay == type) {
            subscribe.setContentId(targetId);
            subscribe = findBySubscriberIdAndContentId(subscriberId, targetId);
        }
        if (Objects.isNull(subscribe)) {
            subscribe = new Subscribe();
            subscribe.setSubscriberId(subscriberId);
            subscribe.setCreateTime(new Date());
            subscribe.setUpdateTime(subscribe.getCreateTime());
            save(subscribe);
        } else {
            subscribe.setUpdateTime(new Date());
            update(subscribe);
        }
    }

    @Override
    public void deleteByIdLogically(String subscribeId) {
        subscribeMapper.deleteByIdLogically(subscribeId);
    }

    private List<Subscribe> findBySubscriberIdAndType(String subscriberId, SubscribeType type) {
        return subscribeMapper.selectBySubscriberIdAndType(subscriberId, type);
    }

    private Subscribe findBySubscriberIdAndBookId(String subscriberId, String bookId) {
        return subscribeMapper.selectBySubscriberIdAndBookId(subscriberId, bookId);
    }

    private Subscribe findBySubscriberIdAndCommodityId(String subscriberId, String commodityId) {
        return subscribeMapper.selectBySubscriberIdAndCommodityId(subscriberId, commodityId);
    }

    private Subscribe findBySubscriberIdAndContentId(String subscriberId, String contentId) {
        return subscribeMapper.selectBySubscriberIdAndContentId(subscriberId, contentId);
    }
}
