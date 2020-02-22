/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : huidu

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-02-11 20:55:02
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`
(
  `id`      int(11) NOT NULL COMMENT '活动id',
  `banner`  varchar(255) DEFAULT NULL COMMENT '封面图片',
  `url`     varchar(255) DEFAULT NULL COMMENT '活动链接',
  `book_id` int(11)      DEFAULT NULL COMMENT '图书id',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`
(
  `id`             int(11) NOT NULL,
  `region`         varchar(255) DEFAULT NULL,
  `address`        varchar(255) DEFAULT NULL,
  `postcode`       varchar(8)   DEFAULT NULL,
  `receiver_name`  varchar(30)  DEFAULT NULL,
  `receiver_phone` varchar(16)  DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for arrived_data
-- ----------------------------
DROP TABLE IF EXISTS `arrived_data`;
CREATE TABLE `arrived_data`
(
  `id`      int(11) NOT NULL,
  `days`    int(11)    DEFAULT NULL,
  `motto`   tinytext,
  `today`   datetime   DEFAULT NULL,
  `signed`  tinyint(1) DEFAULT NULL,
  `user_id` int(11)    DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`
(
  `id`                 int(11) NOT NULL COMMENT '图书id',
  `type`               varchar(255)   DEFAULT NULL COMMENT '图书类型(电子书: electronic-book,有声书: audio-book, 纸质书: paper-book)',
  `title`              varchar(255)   DEFAULT NULL COMMENT '标题(有声书)',
  `description`        varchar(255)   DEFAULT NULL COMMENT '描述(有声书)',
  `cover`              varchar(255)   DEFAULT NULL COMMENT '封面(有声书)',
  `teller`             varchar(255)   DEFAULT NULL COMMENT '讲述人(有声书)',
  `all_episodes_money` decimal(10, 0) DEFAULT NULL COMMENT '章节总价格',
  `content_id`         int(11)        DEFAULT NULL,
  `metadata_id`        int(11)        DEFAULT NULL COMMENT '图书元数据id',
  `category_id`        int(11)        DEFAULT NULL COMMENT '类别id',
  `status`             char(20)       DEFAULT NULL COMMENT '书籍状态(examine:审核,public:发布)',
  `episodes`           int(255)       DEFAULT NULL COMMENT '章节数',
  `deleted`            tinyint(1)     DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for book_audio_episode
-- ----------------------------
DROP TABLE IF EXISTS `book_audio_episode`;
CREATE TABLE `book_audio_episode`
(
  `id`         int(11) NOT NULL,
  `book_id`    int(11)      DEFAULT NULL,
  `title`      varchar(255) DEFAULT NULL,
  `episode_id` int(11)      DEFAULT NULL,
  `stream_url` varchar(255) DEFAULT NULL,
  `next`       varchar(255) DEFAULT NULL COMMENT '下一章媒体章节id',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for book_catalogs
-- ----------------------------
DROP TABLE IF EXISTS `book_catalogs`;
CREATE TABLE `book_catalogs`
(
  `id`         int(11) NOT NULL,
  `book_id`    int(11)      DEFAULT NULL,
  `title`      varchar(255) DEFAULT NULL,
  `episode_id` int(11)      DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for book_episode
-- ----------------------------
DROP TABLE IF EXISTS `book_episode`;
CREATE TABLE `book_episode`
(
  `id`             int(11) NOT NULL,
  `title`          varchar(255) DEFAULT NULL,
  `commodity_id`   int(11)      DEFAULT NULL,
  `content_type`   varchar(255) DEFAULT NULL,
  `content_source` varchar(255) DEFAULT NULL,
  `next`           varchar(255) DEFAULT NULL,
  `book_id`        int(11)      DEFAULT NULL,
  `create_time`    datetime     DEFAULT NULL,
  `update_time`    datetime     DEFAULT NULL,
  `deleted`        tinyint(1)   DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for book_metadata
-- ----------------------------
DROP TABLE IF EXISTS `book_metadata`;
CREATE TABLE `book_metadata`
(
  `id`          int(11) NOT NULL,
  `name`        varchar(255) DEFAULT NULL COMMENT '图书名称',
  `cover`       varchar(255) DEFAULT NULL COMMENT '图书封面',
  `description` tinytext COMMENT '图书介绍',
  `words`       varchar(255) DEFAULT NULL COMMENT '字数描述',
  `author`      varchar(255) DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for book_notes
-- ----------------------------
DROP TABLE IF EXISTS `book_notes`;
CREATE TABLE `book_notes`
(
  `id`          int(11) NOT NULL,
  `book_id`     int(11)  DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for book_shelf
-- ----------------------------
DROP TABLE IF EXISTS `book_shelf`;
CREATE TABLE `book_shelf`
(
  `id`      int(11) NOT NULL,
  `name`    varchar(255) DEFAULT NULL,
  `user_id` int(11)      DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for book_shelf_cell
-- ----------------------------
DROP TABLE IF EXISTS `book_shelf_cell`;
CREATE TABLE `book_shelf_cell`
(
  `id`                   int(11) NOT NULL,
  `shelf_id`             int(11) DEFAULT NULL,
  `book_id`              int(11) DEFAULT NULL,
  `last_read_episode_id` int(11) DEFAULT NULL,
  `progress`             float   DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
  `id`          int(11) NOT NULL COMMENT '类别id',
  `name`        varchar(255) DEFAULT NULL COMMENT '类别名称',
  `description` varchar(255) DEFAULT NULL COMMENT '类别描述',
  `extra`       tinytext,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`
(
  `id`           bigint(20) NOT NULL,
  `name`         varchar(128) DEFAULT NULL,
  `type`         varchar(128) DEFAULT NULL,
  `introduction` tinytext,
  `picture`      varchar(255) DEFAULT NULL,
  `rate`         float        DEFAULT NULL,
  `stock`        int(255)     DEFAULT NULL,
  `prices`       varchar(255) DEFAULT NULL,
  `status`       char(64)     DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for consumed_info
-- ----------------------------
DROP TABLE IF EXISTS `consumed_info`;
CREATE TABLE `consumed_info`
(
  `id`             int(11) NOT NULL,
  `from_balance`   varchar(255) DEFAULT NULL,
  `to_balance`     varchar(255) DEFAULT NULL,
  `version`        varchar(255) DEFAULT NULL,
  `operating_time` datetime     DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`
(
  `content_id`  int(11) NOT NULL COMMENT '内容id',
  `type`        varchar(255) DEFAULT NULL COMMENT '内容类型',
  `comments`    int(11)      DEFAULT NULL COMMENT '评论数',
  `likes`       int(11)      DEFAULT NULL COMMENT '点赞数',
  `reposts`     int(11)      DEFAULT NULL COMMENT '转发数',
  `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`content_id`)
);

-- ----------------------------
-- Table structure for content_article
-- ----------------------------
DROP TABLE IF EXISTS `content_article`;
CREATE TABLE `content_article`
(
  `content_id`     int(11) NOT NULL COMMENT '内容唯一标识',
  `title`          varchar(128) DEFAULT NULL COMMENT '文章标题',
  `content_type`   varchar(64)  DEFAULT NULL COMMENT '文章内容类型，值为：html,plaintext,markdown',
  `content_source` text COMMENT '内容解析源',
  `type`           varchar(255) DEFAULT NULL COMMENT '内容类型',
  `owner_id`       int(11)      DEFAULT NULL COMMENT '拥有者id',
  `reads`          bigint(255)  DEFAULT NULL COMMENT '阅读数',
  PRIMARY KEY (`content_id`)
);

-- ----------------------------
-- Table structure for content_comment
-- ----------------------------
DROP TABLE IF EXISTS `content_comment`;
CREATE TABLE `content_comment`
(
  `content_id` int(11) NOT NULL COMMENT '内容id',
  `rate`       varchar(255) DEFAULT NULL,
  `target_id`  int(11)      DEFAULT NULL,
  PRIMARY KEY (`content_id`)
);

-- ----------------------------
-- Table structure for content_reference
-- ----------------------------
DROP TABLE IF EXISTS `content_reference`;
CREATE TABLE `content_reference`
(
  `content_id`   int(11) NOT NULL,
  `reference_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`content_id`)
);

-- ----------------------------
-- Table structure for episode_notes
-- ----------------------------
DROP TABLE IF EXISTS `episode_notes`;
CREATE TABLE `episode_notes`
(
  `id`                   int(11) NOT NULL,
  `book_id`              int(11)      DEFAULT NULL,
  `episode_id`           int(11)      DEFAULT NULL,
  `ref`                  varchar(255) DEFAULT NULL,
  `content_type`         varchar(255) DEFAULT NULL,
  `content_source`       varchar(255) DEFAULT NULL,
  `dommark_start_dom`    varchar(255) DEFAULT NULL,
  `dommark_start_offset` varchar(255) DEFAULT NULL,
  `dommark_end_dom`      varchar(255) DEFAULT NULL,
  `dommark_end_offset`   varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for logistics_infomation
-- ----------------------------
DROP TABLE IF EXISTS `logistics_infomation`;
CREATE TABLE `logistics_infomation`
(
  `id`              int(11) NOT NULL,
  `express_number`  int(11)      DEFAULT NULL,
  `express_company` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation`
(
  `id`                int(11) NOT NULL,
  `type`              char(10)  DEFAULT NULL COMMENT '操作类型(create: 创建, modify: 修改, delete: 删除)',
  `target_type`       char(255) DEFAULT NULL COMMENT '操作目标类型(comment: 评论, topic: 话题, review: 点评)',
  `target_content_id` int(11)   DEFAULT NULL,
  `level`             char(10)  DEFAULT NULL,
  `operator_id`       int(11)   DEFAULT NULL COMMENT '操作者id',
  `ipaddr`            char(18)  DEFAULT NULL COMMENT 'ip 地址',
  `create_time`       datetime  DEFAULT NULL COMMENT '操作时间'
);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
  `order_number`            int(11) NOT NULL COMMENT '订单编号',
  `type`                    varchar(255)   DEFAULT NULL COMMENT '订单类型(electronic-book: 电子书, audio-book: 有声书, paper-book: 纸质书, recharge: 充值)',
  `pay_type`                varchar(255)   DEFAULT NULL COMMENT '支付类型',
  `address_id`              int(11)        DEFAULT NULL COMMENT '地址id',
  `create_time`             datetime       DEFAULT NULL COMMENT '创建时间',
  `pay_time`                datetime       DEFAULT NULL COMMENT '支付时间',
  `deliver_time`            datetime       DEFAULT NULL COMMENT '发货时间',
  `closing_time`            datetime       DEFAULT NULL COMMENT '交易时间',
  `total_money`             decimal(10, 0) DEFAULT NULL COMMENT '总金额',
  `shipment_money`          varchar(255)   DEFAULT NULL COMMENT '运费',
  `status`                  varchar(255)   DEFAULT NULL COMMENT '订单状态(awaiting-payment: 等待支付, awaiting-shipment: 等待发货, awaiting-evaluation: 等待评价, cancel: 订单取消, finish: 订单完成)',
  `logistics_infomation_id` int(11)        DEFAULT NULL COMMENT '物流信息id',
  PRIMARY KEY (`order_number`)
);

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`
(
  `id`           int(11) NOT NULL,
  `commodity_id` int(11)        DEFAULT NULL,
  `quantity`     int(255)       DEFAULT NULL,
  `prices`       decimal(10, 0) DEFAULT NULL,
  `order_number` int(11)        DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for passing_point
-- ----------------------------
DROP TABLE IF EXISTS `passing_point`;
CREATE TABLE `passing_point`
(
  `id`                       int(11) NOT NULL,
  `name`                     varchar(255) DEFAULT NULL,
  `update_time`              datetime     DEFAULT NULL,
  `status`                   varchar(255) DEFAULT NULL COMMENT '中转状态(doing: 中转中, done: 发出)',
  `logistics_information_id` int(11)      DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for reference_data
-- ----------------------------
DROP TABLE IF EXISTS `reference_data`;
CREATE TABLE `reference_data`
(
  `reference_id`       char(20)  NOT NULL,
  `file_name`          varchar(255)   DEFAULT NULL,
  `file_original_name` varchar(255)   DEFAULT NULL,
  `file_size`          bigint(20)     DEFAULT NULL,
  `type`               varchar(255)   DEFAULT NULL,
  `uploaded_date`      timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `file_path`          text,
  `status`             int(11)        DEFAULT NULL,
  PRIMARY KEY (`reference_id`)
);

-- ----------------------------
-- Table structure for subscribe
-- ----------------------------
DROP TABLE IF EXISTS `subscribe`;
CREATE TABLE `subscribe`
(
  `id`                     int(11) NOT NULL AUTO_INCREMENT,
  `type`                   varchar(255) DEFAULT NULL COMMENT '订阅类型(update: 更新通知)',
  `book_id`                int(11)      DEFAULT NULL,
  `last_update_episode_id` int(11)      DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`
(
  `id`          int(11) NOT NULL,
  `name`        varchar(255) DEFAULT NULL COMMENT '标签名称',
  `category_id` int(11)      DEFAULT NULL COMMENT '类别id',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
  `id`                  int(11)   NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username`            char(13)       DEFAULT NULL COMMENT '用户名',
  `password`            char(255)      DEFAULT NULL COMMENT '密码',
  `nickname`            varchar(16)    DEFAULT NULL COMMENT '昵称',
  `avatar`              varchar(255)   DEFAULT NULL COMMENT '头像url',
  `account_expired`     timestamp NULL DEFAULT NULL COMMENT '账号过期时间',
  `credentials_expired` timestamp NULL DEFAULT NULL COMMENT '登录凭据过期时间',
  `account_locked`      int(1)         DEFAULT NULL COMMENT '账号是否锁定(1: 锁定, 0: 未锁定)',
  `enabled`             int(1)         DEFAULT NULL COMMENT '账号是否禁用(1: 可用, 0: 不可用)',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account`
(
  `id`      int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11)    DEFAULT NULL,
  `balance` bigint(20) DEFAULT '0' COMMENT '荟币余额',
  `version` int(11)    DEFAULT '0',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`
(
  `id`           int(11) NOT NULL,
  `user_id`      int(11)      DEFAULT NULL,
  `gender`       varchar(255) DEFAULT NULL,
  `age`          int(11)      DEFAULT NULL,
  `slogan`       varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `birthdate`    datetime     DEFAULT NULL,
  `phone`        varchar(255) DEFAULT NULL,
  `email`        varchar(255) DEFAULT NULL,
  `region`       varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
