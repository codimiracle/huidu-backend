package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.BookNotes;
import com.codimiracle.application.platform.huidu.mapper.BookNotesMapper;
import com.codimiracle.application.platform.huidu.service.BookNotesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookNotesServiceImpl extends AbstractService<String, BookNotes> implements BookNotesService {
    @Resource
    private BookNotesMapper bookNotesMapper;

}
