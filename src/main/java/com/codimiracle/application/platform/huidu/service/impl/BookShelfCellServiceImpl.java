package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.BookShelfCell;
import com.codimiracle.application.platform.huidu.entity.vo.BookShelfCellVO;
import com.codimiracle.application.platform.huidu.entity.vo.HistoryVO;
import com.codimiracle.application.platform.huidu.mapper.BookShelfCellMapper;
import com.codimiracle.application.platform.huidu.service.BookService;
import com.codimiracle.application.platform.huidu.service.BookShelfCellService;
import com.codimiracle.application.platform.huidu.service.HistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookShelfCellServiceImpl extends AbstractService<String, BookShelfCell> implements BookShelfCellService {
    @Resource
    private BookShelfCellMapper bookShelfCellMapper;

    @Resource
    private BookService bookService;

    @Resource
    private HistoryService historyService;

    private void paddingAssociation(BookShelfCellVO bookShelfCellVO) {
        bookShelfCellVO.setBook(bookService.findByIdIntegrally(null, bookShelfCellVO.getBookId()));
        String ownerId = bookShelfCellVO.getShelf().getOwnerId();
        HistoryVO historyVO = historyService.findByUserIdAndBookIdIntegrallyOrFirstEpisode(ownerId, bookShelfCellVO.getBookId());
        bookShelfCellVO.setHistoryId(historyVO.getId());
        bookShelfCellVO.setHistory(historyVO);
    }

    @Override
    public PageSlice<BookShelfCellVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<BookShelfCellVO> slice = extractPageSlice(bookShelfCellMapper.findAllIntegrally(filter, sorter, page));
        slice.getList().forEach(this::paddingAssociation);
        return slice;
    }

    @Override
    public BookShelfCell findByShelfIdAndBookId(String id, String bookId) {
        BookShelfCell bookShelfCell = new BookShelfCell();
        bookShelfCell.setShelfId(id);
        bookShelfCell.setBookId(bookId);
        return bookShelfCellMapper.selectOne(bookShelfCell);
    }
}
