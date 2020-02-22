package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.BookAudioEpisode;
import com.codimiracle.application.platform.huidu.mapper.BookAudioEpisodeMapper;
import com.codimiracle.application.platform.huidu.service.BookAudioEpisodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class BookAudioEpisodeServiceImpl extends AbstractService<String, BookAudioEpisode> implements BookAudioEpisodeService {
    @Resource
    private BookAudioEpisodeMapper bookAudioEpisodeMapper;

}
