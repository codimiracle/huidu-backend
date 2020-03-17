package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.CategoryDTO;
import com.codimiracle.application.platform.huidu.entity.dto.CollectionDTO;
import com.codimiracle.application.platform.huidu.enumeration.CategoryType;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
import java.util.Objects;

@Data
public class Category {
    /**
     * 类别id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别描述
     */
    private String description;

    private CategoryType type;

    /**
     * 榜单封面
     */
    private String extraUrl;

    /**
     * 侧栏标题
     */
    private String extraAsideTitle;

    @Transient
    private List<Tag> tags;

    private Boolean deleted;

    public static Category from(CollectionDTO collectionDTO) {
        if (Objects.isNull(collectionDTO)) {
            return null;
        }
        Category category = new Category();
        BeanUtils.copyProperties(collectionDTO, category);
        if (Objects.nonNull(collectionDTO.getExtra())) {
            category.setExtraAsideTitle(collectionDTO.getExtra().getAsideTitle());
            category.setExtraUrl(collectionDTO.getExtra().getUrl());
        }
        return category;
    }

    public static Category from(CategoryDTO categoryDTO) {
        if (Objects.isNull(categoryDTO)) {
            return null;
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        return category;
    }
}