package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.BookShelfCell;
import com.codimiracle.application.platform.huidu.entity.vo.BookShelfCellVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookShelfCellMapper extends Mapper<BookShelfCell> {
    List<BookShelfCellVO> findAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);
}