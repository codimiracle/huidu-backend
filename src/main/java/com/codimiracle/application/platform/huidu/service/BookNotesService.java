package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.embedded.Dommark;
import com.codimiracle.application.platform.huidu.entity.po.BookNotes;
import com.codimiracle.application.platform.huidu.entity.vo.BookNotesVO;
import com.codimiracle.application.platform.huidu.entity.vt.BookNoteCollection;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Service;


/**
 * @author Codimiracle
 */
public interface BookNotesService extends Service<String, BookNotes, BookNotesVO> {

    PageSlice<BookNotesVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<BookNoteCollection> findAllCollectionIntegrally(Filter filter, Sorter sorter, Page page);

    BookNotesVO findByIdIntegrally(String id);

    void deleteByIdLogically(String id);

    BookNoteCollection findBookNotesCollectionByBookIdAndUserId(String bookId, String id);

    boolean isDommarkExists(Dommark dommark);
}
