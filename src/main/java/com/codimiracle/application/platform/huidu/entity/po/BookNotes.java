package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "book_notes")
public class BookNotes {
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
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return book_id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * @param bookId
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * @return episode_id
     */
    public Integer getEpisodeId() {
        return episodeId;
    }

    /**
     * @param episodeId
     */
    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    /**
     * @return ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * @param ref
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * @return content_type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return content_source
     */
    public String getContentSource() {
        return contentSource;
    }

    /**
     * @param contentSource
     */
    public void setContentSource(String contentSource) {
        this.contentSource = contentSource;
    }

    /**
     * @return dommark_startdom
     */
    public String getDommarkStartdom() {
        return dommarkStartdom;
    }

    /**
     * @param dommarkStartdom
     */
    public void setDommarkStartdom(String dommarkStartdom) {
        this.dommarkStartdom = dommarkStartdom;
    }

    /**
     * @return dommark_startoffset
     */
    public String getDommarkStartoffset() {
        return dommarkStartoffset;
    }

    /**
     * @param dommarkStartoffset
     */
    public void setDommarkStartoffset(String dommarkStartoffset) {
        this.dommarkStartoffset = dommarkStartoffset;
    }

    /**
     * @return dommark_enddom
     */
    public String getDommarkEnddom() {
        return dommarkEnddom;
    }

    /**
     * @param dommarkEnddom
     */
    public void setDommarkEnddom(String dommarkEnddom) {
        this.dommarkEnddom = dommarkEnddom;
    }

    /**
     * @return dommark_endoffset
     */
    public String getDommarkEndoffset() {
        return dommarkEndoffset;
    }

    /**
     * @param dommarkEndoffset
     */
    public void setDommarkEndoffset(String dommarkEndoffset) {
        this.dommarkEndoffset = dommarkEndoffset;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}