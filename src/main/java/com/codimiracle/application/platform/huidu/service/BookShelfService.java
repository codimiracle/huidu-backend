package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.BookShelf;
import com.codimiracle.web.mybatis.contract.Service;


/**
 * @author Codimiracle
 */
public interface BookShelfService extends Service<String, BookShelf> {

    void join(String userId, String bookId);

    boolean isJoined(String userId, String bookId);
}
