package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.BookEpisode;
import com.codimiracle.application.platform.huidu.mapper.BookEpisodeMapper;
import com.codimiracle.application.platform.huidu.service.BookEpisodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookEpisodeServiceImpl extends AbstractService<String, BookEpisode> implements BookEpisodeService {
    @Resource
    private BookEpisodeMapper bookEpisodeMapper;

}
