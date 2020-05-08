package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.Tag;
import com.codimiracle.application.platform.huidu.entity.vo.TagVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface TagMapper extends Mapper<Tag, TagVO> {
    TagVO selectByIdIntegrally(String id);

    List<TagVO> selectByCategoryIdIntegrally(String categoryId);

    List<TagVO> selectAllIntegrally(@Param("filter") Filter filter, @Param("sorter") Sorter sorter, @Param("page") Page page);

    List<Tag> selectByTagNames(List<String> tagNames);

    void deleteByIdLogically(String id);

    void deleteByIdsLogically(List<String> ids);
}