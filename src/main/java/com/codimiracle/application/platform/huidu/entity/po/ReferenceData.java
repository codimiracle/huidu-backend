package com.codimiracle.application.platform.huidu.entity.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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

    /**
     * @return reference_id
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * @param referenceId
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * @return file_name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return file_original_name
     */
    public String getFileOriginalName() {
        return fileOriginalName;
    }

    /**
     * @param fileOriginalName
     */
    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
    }

    /**
     * @return file_size
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return uploaded_date
     */
    public Date getUploadedDate() {
        return uploadedDate;
    }

    /**
     * @param uploadedDate
     */
    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return file_path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}