package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "reference_data")
public class ReferenceData {
    @Id
    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_original_name")
    private String fileOriginalName;

    @Column(name = "file_size")
    private Long fileSize;

    private String type;

    @Column(name = "uploaded_date")
    private Date uploadedDate;

    private Integer status;

    @Column(name = "file_path")
    private String filePath;
}