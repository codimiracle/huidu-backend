package com.codimiracle.application.platform.huidu.entity.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Book {
    /**
     * 图书id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 有声书标题
     */
    private String title;

    /**
     * 有声书描述
     */
    private String description;

    /**
     * 有声书封面
     */
    private String cover;

    /**
     * 讲述人
     */
    private String teller;

    /**
     * 书籍元数据id
     */
    @Column(name = "metadata_id")
    private Integer metadataId;

    /**
     * 类别id
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 发布年份
     */
    @Column(name = "publish_year")
    private String publishYear;

    /**
     * 图书类型（electronic-book: 电子书，audio-book: 有声书，paper-book = 纸质书）
     */
    private String type;

    /**
     * 章节数
     */
    private Integer episodes;

    /**
     * 图书状态（examining：审批, serializing: 连载中, paused: 停更, ended: 完结）
     */
    private String status;

    /**
     * 全本价格
     */
    private Integer money;

    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 获取图书id
     *
     * @return id - 图书id
     */
    /**
     * 设置图书id
     *
     * @param id 图书id
     */
    /**
     * 获取有声书标题
     *
     * @return title - 有声书标题
     */
    /**
     * 设置有声书标题
     *
     * @param title 有声书标题
     */
    /**
     * 获取有声书描述
     *
     * @return description - 有声书描述
     */
    /**
     * 设置有声书描述
     *
     * @param description 有声书描述
     */
    /**
     * 获取有声书封面
     *
     * @return cover - 有声书封面
     */
    /**
     * 设置有声书封面
     *
     * @param cover 有声书封面
     */
    /**
     * 获取讲述人
     *
     * @return teller - 讲述人
     */
    /**
     * 设置讲述人
     *
     * @param teller 讲述人
     */
    /**
     * 获取书籍元数据id
     *
     * @return metadata_id - 书籍元数据id
     */
    /**
     * 设置书籍元数据id
     *
     * @param metadataId 书籍元数据id
     */
    /**
     * 获取类别id
     *
     * @return category_id - 类别id
     */
    /**
     * 设置类别id
     *
     * @param categoryId 类别id
     */
    /**
     * 获取发布年份
     *
     * @return publish_year - 发布年份
     */
    /**
     * 设置发布年份
     *
     * @param publishYear 发布年份
     */
    /**
     * 获取图书类型（electronic-book: 电子书，audio-book: 有声书，paper-book = 纸质书）
     *
     * @return type - 图书类型（electronic-book: 电子书，audio-book: 有声书，paper-book = 纸质书）
     */
    /**
     * 设置图书类型（electronic-book: 电子书，audio-book: 有声书，paper-book = 纸质书）
     *
     * @param type 图书类型（electronic-book: 电子书，audio-book: 有声书，paper-book = 纸质书）
     */
    /**
     * 获取章节数
     *
     * @return episodes - 章节数
     */
    /**
     * 设置章节数
     *
     * @param episodes 章节数
     */
    /**
     * 获取图书状态（examining：审批, serializing: 连载中, paused: 停更, ended: 完结）
     *
     * @return status - 图书状态（examining：审批, serializing: 连载中, paused: 停更, ended: 完结）
     */
    /**
     * 设置图书状态（examining：审批, serializing: 连载中, paused: 停更, ended: 完结）
     *
     * @param status 图书状态（examining：审批, serializing: 连载中, paused: 停更, ended: 完结）
     */
    /**
     * 获取全本价格
     *
     * @return money - 全本价格
     */
    /**
     * 设置全本价格
     *
     * @param money 全本价格
     */
    /**
     * @return commodity_id
     */
    /**
     * @param commodityId
     */
    /**
     * 获取删除标识
     *
     * @return deleted - 删除标识
     */
    /**
     * 设置删除标识
     *
     * @param deleted 删除标识
     */
}