package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.BookMetadata;
import com.codimiracle.application.platform.huidu.entity.vo.BookMetadataVO;

import java.util.List;

public interface BookMetadataService extends Service<String, BookMetadata> {
    BookMetadataVO findByIdIntegrally(String id);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    PageSlice<BookMetadataVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    void incrementWordsBy(String metadataId, Integer words);

    void decrementWordsBy(String metadataId, Integer words);
}
