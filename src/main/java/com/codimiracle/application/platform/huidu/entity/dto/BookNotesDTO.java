package com.codimiracle.application.platform.huidu.entity.dto;

import com.codimiracle.application.platform.huidu.entity.embedded.ContentSource;
import com.codimiracle.application.platform.huidu.entity.embedded.Dommark;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class BookNotesDTO {
    private Integer bookId;

    private Integer episodeId;

    private String ref;

    private ContentSource content;

    private Dommark dommark;
}