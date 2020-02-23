package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "content_mention")
public class ContentMention {
    @Id
    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "mention_user_id")
    private Integer mentionUserId;

    /**
     * @return content_id
     */
    /**
     * @param contentId
     */
    /**
     * @return mention_user_id
     */
    /**
     * @param mentionUserId
     */
}