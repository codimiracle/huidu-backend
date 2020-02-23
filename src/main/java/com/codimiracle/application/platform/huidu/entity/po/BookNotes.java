package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "book_notes")
public class BookNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "episode_id")
    private Integer episodeId;

    private String ref;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "content_source")
    private String contentSource;

    @Column(name = "dommark_startdom")
    private String dommarkStartdom;

    @Column(name = "dommark_startoffset")
    private String dommarkStartoffset;

    @Column(name = "dommark_enddom")
    private String dommarkEnddom;

    @Column(name = "dommark_endoffset")
    private String dommarkEndoffset;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    /**
     * @param id
     */
    /**
     * @return book_id
     */
    /**
     * @param bookId
     */
    /**
     * @return episode_id
     */
    /**
     * @param episodeId
     */
    /**
     * @return ref
     */
    /**
     * @param ref
     */
    /**
     * @return content_type
     */
    /**
     * @param contentType
     */
    /**
     * @return content_source
     */
    /**
     * @param contentSource
     */
    /**
     * @return dommark_startdom
     */
    /**
     * @param dommarkStartdom
     */
    /**
     * @return dommark_startoffset
     */
    /**
     * @param dommarkStartoffset
     */
    /**
     * @return dommark_enddom
     */
    /**
     * @param dommarkEnddom
     */
    /**
     * @return dommark_endoffset
     */
    /**
     * @param dommarkEndoffset
     */
    /**
     * @return create_time
     */
    /**
     * @param createTime
     */
    /**
     * @return update_time
     */
    /**
     * @param updateTime
     */
}