package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.BookMetadataDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Table(name = "book_metadata")
public class BookMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 图书名称
     */
    private String name;

    /**
     * 图书封面
     */
    private String cover;

    /**
     * 图书介绍
     */
    private String description;

    /**
     * 字数描述
     */
    private String words;

    /**
     * 作者
     */
    private String author;

    public static BookMetadata from(BookMetadataDTO bookMetadataDTO) {
        if (Objects.isNull(bookMetadataDTO)) {
            return null;
        }
        BookMetadata bookMetadata = new BookMetadata();
        BeanUtils.copyProperties(bookMetadataDTO, bookMetadata);
        return bookMetadata;
    }
}