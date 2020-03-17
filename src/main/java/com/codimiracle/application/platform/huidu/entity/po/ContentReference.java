package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.ContentReferenceDTO;
import com.codimiracle.application.platform.huidu.enumeration.ContentType;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Table(name = "content_reference")
public class ContentReference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 引用类型
     *
     * @see ContentType
     */
    private ContentType type;

    /**
     * 图书id 或者 内容id
     */
    @Column(name = "ref_id")
    private String refId;

    @Column(name = "content_id")
    private String contentId;

    public static ContentReference from(ContentReferenceDTO contentReferenceDTO) {
        if (Objects.isNull(contentReferenceDTO)) {
            return null;
        }
        ContentReference reference = new ContentReference();
        BeanUtils.copyProperties(contentReferenceDTO, contentReferenceDTO);
        return reference;
    }

    public static List<ContentReference> from(List<ContentReferenceDTO> contentReferenceDTOList) {
        return contentReferenceDTOList.stream().map(ContentReference::from).collect(Collectors.toList());
    }
}