package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.BookShelfCellDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Objects;

@Data
@Table(name = "book_shelf_cell")
public class BookShelfCell {
    /**
     * 书架书籍id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private String bookId;

    /**
     * 书架id
     */
    @Column(name = "shelf_id")
    private String shelfId;

    private boolean finished;

    public static BookShelfCell from(BookShelfCellDTO bookShelfCellDTO) {
        if (Objects.isNull(bookShelfCellDTO)) {
            return null;
        }
        BookShelfCell bookShelfCell = new BookShelfCell();
        BeanUtils.copyProperties(bookShelfCellDTO, bookShelfCell);
        return bookShelfCell;
    }
}