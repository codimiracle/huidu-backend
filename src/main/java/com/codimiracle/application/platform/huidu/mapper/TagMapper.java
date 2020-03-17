package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.Filter;
import com.codimiracle.application.platform.huidu.contract.Mapper;
import com.codimiracle.application.platform.huidu.contract.Page;
import com.codimiracle.application.platform.huidu.contract.Sorter;
import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper extends Mapper<Tag> {
    TagVO selectByIdIntegrally(String id);

    List<TagVO> selectByCategoryIdIntegrally(String categoryId);

    List<TagVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<Tag> selectByTagNames(List<String> tagNames);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);
}