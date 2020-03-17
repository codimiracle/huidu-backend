package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.BookShelfDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Objects;

@Data
@Table(name = "book_shelf")
public class BookShelf {
    /**
     * 书架id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 书架名称
     */
    private String name;

    /**
     * 书架所有者id
     */
    @Column(name = "owner_id")
    private String ownerId;


    public static BookShelf from(BookShelfDTO bookShelfDTO) {
        if (Objects.isNull(bookShelfDTO)) {
            return null;
        }
        BookShelf shelf = new BookShelf();
        BeanUtils.copyProperties(bookShelfDTO, shelf);
        return shelf;
    }
}