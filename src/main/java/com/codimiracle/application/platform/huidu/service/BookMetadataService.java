package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.BookMetadata;
import com.codimiracle.application.platform.huidu.entity.vo.BookMetadataVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;

public interface BookMetadataService extends Service<String, BookMetadata, BookMetadataVO> {
    BookMetadataVO findByIdIntegrally(String id);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    PageSlice<BookMetadataVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    void incrementWordsBy(String metadataId, Integer words);

    void decrementWordsBy(String metadataId, Integer words);
}
