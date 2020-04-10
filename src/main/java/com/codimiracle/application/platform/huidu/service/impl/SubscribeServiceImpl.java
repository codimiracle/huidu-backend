package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Notification;
import com.codimiracle.application.platform.huidu.entity.po.Subscribe;
import com.codimiracle.application.platform.huidu.entity.vo.SubscribeVO;
import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;
import com.codimiracle.application.platform.huidu.helper.NotificationTemplate;
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
    private NotificationService notificationService;

    @Resource
    private BookService bookService;

    @Resource
    private CommentService commentService;

    @Resource
    private NotificationTemplate notificationTemplate;

    private void mutate(SubscribeVO subscribeVO) {
        if (Objects.equals(subscribeVO.getType(), SubscribeType.BookUpdated.getType())) {
            subscribeVO.setBook(bookService.findByIdIntegrally(subscribeVO.getBookId()));
        }
    }

    @Override
    public PageSlice<SubscribeVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<SubscribeVO> slice = extractPageSlice(subscribeMapper.selectAllIntegrally(filter, sorter, page));
        slice.getList().forEach(this::mutate);
        return slice;
    }

    private Subscribe ensureNotNull(Subscribe subscribe) {
        if (Objects.isNull(subscribe)) {
            return new Subscribe();
        }
        return subscribe;
    }

    @Override
    public void subscribe(String subscriberId, String targetId, SubscribeType type) {
        Subscribe subscribe = null;
        // 处理订阅
        if (SubscribeType.BookUpdated == type) {
            subscribe = ensureNotNull(findBySubscriberIdAndBookId(subscriberId, targetId));
            subscribe.setBookId(targetId);
        } else if (SubscribeType.CommodityPutOnSale == type) {
            subscribe = ensureNotNull(findBySubscriberIdAndCommodityId(subscriberId, targetId));
            subscribe.setCommodityId(targetId);
        } else if (SubscribeType.ContentCommented == type) {
            subscribe = ensureNotNull(findBySubscriberIdAndContentId(subscriberId, targetId));
            subscribe.setContentId(targetId);
        } else if (SubscribeType.ContentReplay == type) {
            subscribe = ensureNotNull(findBySubscriberIdAndContentId(subscriberId, targetId));
            subscribe.setContentId(targetId);
        } else {
            throw new ServiceException("未知订阅！");
        }

        if (Objects.isNull(subscribe.getSubscriberId())) {
            subscribe.setType(type);
            subscribe.setSubscriberId(subscriberId);
            subscribe.setCreateTime(new Date());
            subscribe.setUpdateTime(subscribe.getCreateTime());
            save(subscribe);
        } else {
            subscribe.setUpdateTime(new Date());
            subscribe.setDeleted(false);
            update(subscribe);
        }
        // 发出通知
        Notification notification = notificationTemplate.generateSubscribeBy(type, subscriberId, targetId);
        notificationService.notify(notification);
    }

    public List<Subscribe> findBySubscribeTypeAndBookId(SubscribeType subscribeType, String bookId) {
        return subscribeMapper.selectBySubscribeTypeAndBookId(subscribeType, bookId);
    }

    @Override
    public void deleteByIdLogically(String subscribeId) {
        Subscribe subscribe = subscribeMapper.selectByPrimaryKey(subscribeId);
        subscribeMapper.deleteByIdLogically(subscribeId);
        Notification notification = notificationTemplate.generateCancelBy(subscribe);
        notificationService.notify(notification);
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
