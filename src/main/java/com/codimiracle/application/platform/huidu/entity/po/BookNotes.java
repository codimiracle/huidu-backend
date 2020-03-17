package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.BookNotesDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Table(name = "book_notes")
public class BookNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "episode_id")
    private String episodeId;

    private String ref;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "content_source")
    private String contentSource;

    @Column(name = "dommark_start_dom")
    private String dommarkStartDom;

    @Column(name = "dommark_start_offset")
    private String dommarkStartOffset;

    @Column(name = "dommark_end_dom")
    private String dommarkEndDom;

    @Column(name = "dommark_end_offset")
    private String dommarkEndOffset;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public static BookNotes from(BookNotesDTO bookNotesDTO) {
        if (Objects.isNull(bookNotesDTO)) {
            return null;
        }
        BookNotes bookNotes = new BookNotes();
        BeanUtils.copyProperties(bookNotesDTO, bookNotes);
        bookNotes.setBookId(bookNotesDTO.getBookId().toString());
        bookNotes.setEpisodeId(bookNotesDTO.getEpisodeId().toString());
        bookNotes.setContentType(bookNotesDTO.getContent().getType());
        bookNotes.setContentSource(bookNotesDTO.getContent().getSource());
        bookNotes.setDommarkStartDom(bookNotesDTO.getDommark().getStartDom());
        bookNotes.setDommarkStartOffset(Objects.toString(bookNotesDTO.getDommark().getStartOffset(), null));
        bookNotes.setDommarkEndDom(bookNotesDTO.getDommark().getEndDom());
        bookNotes.setDommarkEndOffset(Objects.toString(bookNotesDTO.getDommark().getEndOffset(), null));
        return bookNotes;
    }
}