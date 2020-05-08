package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.BookMetadata;
import com.codimiracle.application.platform.huidu.entity.vo.BookMetadataVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface BookMetadataMapper extends Mapper<BookMetadata, BookMetadataVO> {
    BookMetadataVO selectByIdIntegrally(String id);

    List<BookMetadataVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);

    void incrementWordsBy(@Param("metadataId") String metadataId, @Param("words") Integer words);

    void decrementWordsBy(@Param("metadataId") String metadataId, @Param("words") Integer words);
}