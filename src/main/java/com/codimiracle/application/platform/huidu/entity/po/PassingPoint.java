package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.PassingPointDTO;
import com.codimiracle.application.platform.huidu.enumeration.PassingPointStatus;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Table(name = "passing_point")
public class PassingPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 途经内容
     */
    private String name;

    @Column(name = "create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 途经状态（doing: 正在进行, done: 完成）
     */
    private PassingPointStatus status;

    @Column(name = "logistics_infomation_id")
    private String logisticsInfomationId;

    public static PassingPoint form(PassingPointDTO passingPointDTO) {
        if (Objects.isNull(passingPointDTO)) {
            return null;
        }
        PassingPoint passingPoint = new PassingPoint();
        BeanUtils.copyProperties(passingPointDTO, passingPoint);
        passingPoint.setStatus(PassingPointStatus.valueOfCode(passingPointDTO.getStatus()));
        return passingPoint;
    }
}