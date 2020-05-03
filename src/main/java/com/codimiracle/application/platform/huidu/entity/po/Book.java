package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.BookDTO;
import com.codimiracle.application.platform.huidu.enumeration.BookStatus;
import com.codimiracle.application.platform.huidu.enumeration.BookType;
import com.codimiracle.web.middleware.content.pojo.po.Content;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
public class Book {
    /**
     * 图书id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 有声书标题
     */
    private String title;

    /**
     * 有声书描述
     */
    private String description;

    /**
     * 有声书封面
     */
    private String cover;

    /**
     * 讲述人
     */
    private String teller;

    @Column(name = "content_id")
    private String contentId;

    @Transient
    private Content content;
    /**
     * 书籍元数据id
     */
    @Column(name = "metadata_id")
    private String metadataId;

    @Transient
    private BookMetadata metadata;

    /**
     * 类别id
     */
    @Column(name = "category_id")
    private String categoryId;

    @Transient
    private Category category;

    private String money;

    /**
     * 发布年份
     */
    @Column(name = "publish_year")
    private String publishYear;

    /**
     * 图书类型（electronic-book: 电子书，audio-book: 有声书，paper-book = 纸质书）
     */
    private BookType type;

    /**
     * 章节数
     */
    private Integer episodes;

    /**
     * 图书状态（examining：审批, serializing: 连载中, paused: 停更, ended: 完结）
     */
    private BookStatus status;

    @Column(name = "commodity_id")
    private String commodityId;

    @Transient
    private Commodity commodity;

    @Transient
    private List<Tag> tags;

    /**
     * 删除标识
     */
    private Boolean deleted;

    public static Book from(BookDTO bookDTO) {
        if (Objects.isNull(bookDTO)) {
            return null;
        }
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        //新建类别
        if (Objects.isNull(bookDTO.getCategoryId())) {
            book.setCategory(Category.from(bookDTO.getCategory()));
        }
        book.setMetadata(BookMetadata.from(bookDTO.getMetadata()));
        book.setCommodity(Commodity.from(bookDTO.getCommodity()));
        book.setStatus(BookStatus.valueOfCode(bookDTO.getStatus()));
        book.setType(BookType.valueOfCode(bookDTO.getType()));
        return book;
    }
}