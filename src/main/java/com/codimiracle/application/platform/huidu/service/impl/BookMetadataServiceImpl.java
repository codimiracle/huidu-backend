package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.BookMetadata;
import com.codimiracle.application.platform.huidu.entity.vo.BookMetadataVO;
import com.codimiracle.application.platform.huidu.mapper.BookMetadataMapper;
import com.codimiracle.application.platform.huidu.service.BookMetadataService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class BookMetadataServiceImpl extends AbstractService<String, BookMetadata, BookMetadataVO> implements BookMetadataService {
    @Resource
    private BookMetadataMapper bookMetadataMapper;

    @Override
    public BookMetadataVO findByIdIntegrally(String id) {
        return bookMetadataMapper.selectByIdIntegrally(id);
    }

    @Override
    public void deleteByIdLogically(String id) {
        bookMetadataMapper.deleteByIdLogically(id);
    }

    @Override
    public void deleteByIdsLogically(List<String> ids) {
        bookMetadataMapper.deleteByIdsLogically(ids);
    }

    @Override
    public void incrementWordsBy(String bookId, Integer words) {
        bookMetadataMapper.incrementWordsBy(bookId, words);
    }

    @Override
    public void decrementWordsBy(String metadataId, Integer words) {
        bookMetadataMapper.decrementWordsBy(metadataId, words);
    }
}
