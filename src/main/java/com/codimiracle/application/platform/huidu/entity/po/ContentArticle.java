package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.ContentArticleDTO;
import com.codimiracle.application.platform.huidu.enumeration.ContentStatus;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Table(name = "content_article")
public class ContentArticle {
    /**
     * 内容id
     */
    @Id
    @Column(name = "content_id")
    private String contentId;

    /**
     * 内容对内容id
     */
    @Column(name = "target_content_id")
    private String targetContentId;

    /**
     * 标题
     */
    private String title;

    /**
     * 字数
     */
    private Integer words;

    /**
     * 内容源类型（html: HTML代码, plaintext: 纯文本）
     */
    @Column(name = "content_type")
    private String contentType;

    /**
     * 内容源
     */
    @Column(name = "content_source")
    private String contentSource;

    /**
     * 内容状态
     */
    private ContentStatus status;

    /**
     * 阅读数
     */
    @Column(name = "`reads`")
    private Integer reads;

    public static ContentArticle from(ContentArticleDTO contentArticleDTO) {
        if (Objects.isNull(contentArticleDTO)) {
            return null;
        }
        ContentArticle article = new ContentArticle();
        BeanUtils.copyProperties(contentArticleDTO, article);
        article.setContentType(contentArticleDTO.getContent().getType());
        article.setContentSource(contentArticleDTO.getContent().getSource());
        article.setStatus(ContentStatus.valueOfCode(contentArticleDTO.getStatus()));
        return article;
    }
}