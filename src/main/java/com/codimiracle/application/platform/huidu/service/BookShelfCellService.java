package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.BookShelfCell;
import com.codimiracle.application.platform.huidu.entity.vo.BookShelfCellVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;


/**
 * @author Codimiracle
 */
public interface BookShelfCellService extends Service<String, BookShelfCell, BookShelfCellVO> {
    BookShelfCell findByShelfIdAndBookId(String id, String bookId);
}
