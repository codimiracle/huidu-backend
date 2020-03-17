package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.BookShelfCell;
import com.codimiracle.application.platform.huidu.entity.vo.BookShelfCellVO;


/**
 * @author Codimiracle
 */
public interface BookShelfCellService extends Service<String, BookShelfCell> {

    PageSlice<BookShelfCellVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    BookShelfCell findByShelfIdAndBookId(String id, String bookId);
}
