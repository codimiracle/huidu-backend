package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.embedded.Dommark;
import com.codimiracle.application.platform.huidu.entity.po.BookNotes;
import com.codimiracle.application.platform.huidu.entity.vo.BookNotesVO;
import com.codimiracle.application.platform.huidu.entity.vt.BookNoteCollection;
import com.codimiracle.application.platform.huidu.mapper.BookNotesMapper;
import com.codimiracle.application.platform.huidu.service.BookNotesService;
import com.codimiracle.application.platform.huidu.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookNotesServiceImpl extends AbstractService<String, BookNotes> implements BookNotesService {
    @Resource
    private BookNotesMapper bookNotesMapper;

    @Resource
    private BookService bookService;

    @Override
    public PageSlice<BookNotesVO> findAllIntegrally(Filter filter, Sorter sorter, Page page) {
        return extractPageSlice(bookNotesMapper.selectAllIntegrally(filter, sorter, page));
    }

    @Override
    public PageSlice<BookNoteCollection> findAllCollectionIntegrally(Filter filter, Sorter sorter, Page page) {
        PageSlice<BookNoteCollection> slice = extractPageSlice(bookNotesMapper.selectAllCollectionIntegrally(filter, sorter, page));
        slice.getList().forEach(this::paddingAssociation);
        return slice;
    }

    private void paddingAssociation(BookNoteCollection bookNoteCollection) {
        bookNoteCollection.setBook(bookService.findByIdIntegrally(bookNoteCollection.getBookId()));
    }

    @Override
    public BookNotesVO findByIdIntegrally(String id) {
        return bookNotesMapper.selectByIdIntegrally(id);
    }

    @Override
    public void deleteByIdLogically(String id) {
        bookNotesMapper.deleteByIdLogically(id);
    }

    @Override
    public BookNoteCollection findBookNotesCollectionByBookIdAndUserId(String bookId, String userId) {
        return bookNotesMapper.selectBookNotesCollectionByBookIdAndUserId(bookId, userId);
    }

    @Override
    public boolean isDommarkExists(Dommark dommark) {
        int posOfStartTextNode = dommark.getStartDom().lastIndexOf("text()");
        String mainStartPath = dommark.getStartDom().substring(0, posOfStartTextNode);
        int posOfEndTextNode = dommark.getEndDom().lastIndexOf("text()");
        String mainEndPath = dommark.getEndDom().substring(0, posOfEndTextNode);
        return bookNotesMapper.existsWithMainDommarkPath(mainStartPath, mainEndPath);
    }

    public List<BookNoteCollection> findBookNotesCollectionByUserId(String id) {
        return bookNotesMapper.selectAllBookNotesCollectionByUserId(id);
    }
}
