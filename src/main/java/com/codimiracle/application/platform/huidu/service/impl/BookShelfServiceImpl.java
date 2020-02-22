package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.BookShelf;
import com.codimiracle.application.platform.huidu.mapper.BookShelfMapper;
import com.codimiracle.application.platform.huidu.service.BookShelfService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookShelfServiceImpl extends AbstractService<String, BookShelf> implements BookShelfService {
    @Resource
    private BookShelfMapper bookShelfMapper;

}
