package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "category_tags")
public class CategoryTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "tag_id")
    private String tagId;

    private boolean deleted;

    public static CategoryTags valueOf(Category category, Tag tag) {
        CategoryTags categoryTags = new CategoryTags();
        categoryTags.setCategoryId(category.getId());
        categoryTags.setTagId(tag.getId());
        return categoryTags;
    }
}