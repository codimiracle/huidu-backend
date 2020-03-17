package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.LogisticsInformationDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Table(name = "logistics_information")
public class LogisticsInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "express_number")
    private String expressNumber;

    @Column(name = "express_company")
    private String expressCompany;

    @Transient
    private List<PassingPoint> passingPoints;

    public static LogisticsInformation from(LogisticsInformationDTO logisticsInformationDTO) {
        if (Objects.isNull(logisticsInformationDTO)) {
            return null;
        }
        LogisticsInformation logisticsInformation = new LogisticsInformation();
        BeanUtils.copyProperties(logisticsInformationDTO, logisticsInformation);
        Optional.ofNullable(logisticsInformationDTO.getPassingPoints()).ifPresent((passingPoints -> {
            logisticsInformation.setPassingPoints(passingPoints.stream().map(PassingPoint::form).collect(Collectors.toList()));
        }));
        return logisticsInformation;
    }
}