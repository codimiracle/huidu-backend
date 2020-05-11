package com.codimiracle.application.platform.huidu.entity.po;

import com.codimiracle.application.platform.huidu.entity.dto.ActivityDTO;
import com.codimiracle.application.platform.huidu.enumeration.ActivityStatus;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Activity {
    /**
     * 活动id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 活动横幅
     */
    private String banner;

    private String url;

    /**
     * 活动状态（activated: 已激活，deactivated: 未激活）
     */
    private ActivityStatus status;

    /**
     * 图书id
     */
    @Column(name = "book_id")
    private String bookId;

    /**
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public static Activity from(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);
        activity.setStatus(ActivityStatus.valueOfCode(activityDTO.getStatus()));
        return activity;
    }
}