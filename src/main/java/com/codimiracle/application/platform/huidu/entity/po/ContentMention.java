package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "content_mention")
public class ContentMention {
    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "mention_user_id")
    private Integer mentionUserId;

    /**
     * @return content_id
     */
    public Integer getContentId() {
        return contentId;
    }

    /**
     * @param contentId
     */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /**
     * @return mention_user_id
     */
    public Integer getMentionUserId() {
        return mentionUserId;
    }

    /**
     * @param mentionUserId
     */
    public void setMentionUserId(Integer mentionUserId) {
        this.mentionUserId = mentionUserId;
    }
}