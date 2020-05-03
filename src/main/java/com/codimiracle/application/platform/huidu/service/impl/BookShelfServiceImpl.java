package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.BookShelf;
import com.codimiracle.application.platform.huidu.entity.po.BookShelfCell;
import com.codimiracle.application.platform.huidu.entity.vo.BookShelfVO;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import com.codimiracle.application.platform.huidu.enumeration.SubscribeType;
import com.codimiracle.application.platform.huidu.mapper.BookShelfMapper;
import com.codimiracle.application.platform.huidu.service.BookShelfCellService;
import com.codimiracle.application.platform.huidu.service.BookShelfService;
import com.codimiracle.application.platform.huidu.service.HistoryService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import com.codimiracle.web.notification.middleware.template.SubscriptionTemplate;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookShelfServiceImpl extends AbstractService<String, BookShelf, BookShelfVO> implements BookShelfService {
    Interner<String> bookJoinLock = Interners.newWeakInterner();
    @Resource
    private BookShelfMapper bookShelfMapper;
    @Resource
    private HistoryService historyService;
    @Resource
    private SubscriptionTemplate subscriptionTemplate;
    @Resource
    private BookShelfCellService bookShelfCellService;

    @Override
    public void join(String userId, String bookId) {
        String lock = bookJoinLock.intern(String.format("user-%s-shelf-join-%s", userId, bookId));
        synchronized (lock) {
            List<BookShelf> bookShelves = bookShelfMapper.selectByUserId(userId);
            BookShelf defaultShelf;
            if (bookShelves.isEmpty()) {
                defaultShelf = new BookShelf();
                defaultShelf.setName("default");
                defaultShelf.setOwnerId(userId);
                save(defaultShelf);
            } else {
                defaultShelf = bookShelves.get(0);
            }
            BookShelfCell cell = bookShelfCellService.findByShelfIdAndBookId(defaultShelf.getId(), bookId);
            // 未放到书架
            if (Objects.isNull(cell)) {
                cell = new BookShelfCell();
                cell.setShelfId(defaultShelf.getId());
                cell.setBookId(bookId);
                bookShelfCellService.save(cell);

                //订阅图书更新
                subscriptionTemplate.subscribe(userId, SubscribeType.BookUpdated.toString(), bookId, ContentType.Book.getType());
            }
            //已经放到书架
        }
    }

    @Override
    public boolean isJoined(String userId, String bookId) {
        Boolean result = bookShelfMapper.existsByUserIdAndBookId(userId, bookId);
        return Objects.nonNull(result) && result;
    }
}
