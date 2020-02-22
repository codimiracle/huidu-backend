package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.BookShelfCell;
import com.codimiracle.application.platform.huidu.mapper.BookShelfCellMapper;
import com.codimiracle.application.platform.huidu.service.BookShelfCellService;
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

}
