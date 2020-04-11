package com.codimiracle.application.platform.huidu.entity.vo;

import lombok.Data;

import java.util.Objects;

@Data
public class BookReadingStatisticsVO {
    private String ebookName;
    private String audioBookName;
    private Integer bookReads;
    private Integer bookEpisodes;

    public String getBookName() {
        if (Objects.nonNull(audioBookName)) {
            return audioBookName;
        }
        return ebookName;
    }
}
