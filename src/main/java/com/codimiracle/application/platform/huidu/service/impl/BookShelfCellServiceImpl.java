package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.BookShelfCell;
import com.codimiracle.application.platform.huidu.entity.vo.BookShelfCellVO;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;
import com.codimiracle.application.platform.huidu.mapper.BookShelfCellMapper;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.BookShelfCellService;
import com.codimiracle.application.platform.huidu.service.HistoryService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookShelfCellServiceImpl extends AbstractService<String, BookShelfCell, BookShelfCellVO> implements BookShelfCellService {
    @Resource
    private BookShelfCellMapper bookShelfCellMapper;

    @Resource
    private BookService bookService;

    @Resource
    private HistoryService historyService;

    protected BookShelfCellVO mutate(BookShelfCellVO bookShelfCellVO) {
        if (Objects.nonNull(bookShelfCellVO)) {
            bookShelfCellVO.setBook(bookService.findByIdIntegrally(null, bookShelfCellVO.getBookId()));
            String ownerId = bookShelfCellVO.getShelf().getOwnerId();
            HistoryVO historyVO = historyService.findLastReadByUserIdAndBookIdIntegrallyOrFirstEpisode(ownerId, bookShelfCellVO.getBookId());
            bookShelfCellVO.setProgress(historyService.findReadingProgressByUserIdAndBookId(bookShelfCellVO.getShelf().getOwnerId(), bookShelfCellVO.getBookId()));
            bookShelfCellVO.setHistoryId(historyVO.getId());
            bookShelfCellVO.setHistory(historyVO);
        }
        return bookShelfCellVO;
    }

    @Override
    public BookShelfCell findByShelfIdAndBookId(String id, String bookId) {
        BookShelfCell bookShelfCell = new BookShelfCell();
        bookShelfCell.setShelfId(id);
        bookShelfCell.setBookId(bookId);
        return bookShelfCellMapper.selectOne(bookShelfCell);
    }
}
