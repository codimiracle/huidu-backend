package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.embedded.Dommark;
import com.codimiracle.application.platform.huidu.entity.po.BookNotes;
import com.codimiracle.application.platform.huidu.entity.vo.BookNotesVO;
import com.codimiracle.application.platform.huidu.entity.vt.BookNoteCollection;


/**
 * @author Codimiracle
 */
public interface BookNotesService extends Service<String, BookNotes> {

    PageSlice<BookNotesVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    PageSlice<BookNoteCollection> findAllCollectionIntegrally(Filter filter, Sorter sorter, Page page);

    BookNotesVO findByIdIntegrally(String id);

    void deleteByIdLogically(String id);

    BookNoteCollection findBookNotesCollectionByBookIdAndUserId(String bookId, String id);

    boolean isDommarkExists(Dommark dommark);
}
