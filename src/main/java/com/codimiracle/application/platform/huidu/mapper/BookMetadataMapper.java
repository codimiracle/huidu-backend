package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.BookMetadata;
import com.codimiracle.application.platform.huidu.entity.vo.BookMetadataVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMetadataMapper extends Mapper<BookMetadata> {
    BookMetadataVO selectByIdIntegrally(String id);

    List<BookMetadataVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);
}