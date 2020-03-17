/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : huidu_online_reading

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-03-14 17:50:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `banner` varchar(255) DEFAULT NULL COMMENT '活动横幅',
  `status` varchar(255) DEFAULT NULL COMMENT '活动状态（activated: 已激活，deactivated: 未激活）',
  `url` varchar(255) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL COMMENT '图书id',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:32:00', '2020-03-03 00:32:00');
INSERT INTO `activity` VALUES ('2', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:40:53', '2020-03-03 00:40:53');
INSERT INTO `activity` VALUES ('3', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:40:59', '2020-03-03 00:40:59');
INSERT INTO `activity` VALUES ('4', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:40:59', '2020-03-03 00:40:59');
INSERT INTO `activity` VALUES ('5', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:40:59', '2020-03-03 00:40:59');
INSERT INTO `activity` VALUES ('6', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:00', '2020-03-03 00:41:00');
INSERT INTO `activity` VALUES ('7', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:00', '2020-03-03 00:41:00');
INSERT INTO `activity` VALUES ('8', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:00', '2020-03-03 00:41:00');
INSERT INTO `activity` VALUES ('9', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:01', '2020-03-03 00:41:01');
INSERT INTO `activity` VALUES ('10', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:01', '2020-03-03 00:41:01');
INSERT INTO `activity` VALUES ('11', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:01', '2020-03-03 00:41:01');
INSERT INTO `activity` VALUES ('12', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:02', '2020-03-03 00:41:02');
INSERT INTO `activity` VALUES ('13', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:02', '2020-03-03 00:41:02');
INSERT INTO `activity` VALUES ('14', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:03', '2020-03-03 00:41:03');
INSERT INTO `activity` VALUES ('15', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:03', '2020-03-03 00:41:03');
INSERT INTO `activity` VALUES ('16', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:03', '2020-03-03 00:41:03');
INSERT INTO `activity` VALUES ('17', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:04', '2020-03-03 00:41:04');
INSERT INTO `activity` VALUES ('18', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:04', '2020-03-03 00:41:04');
INSERT INTO `activity` VALUES ('19', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:05', '2020-03-03 00:41:05');
INSERT INTO `activity` VALUES ('20', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:05', '2020-03-03 00:41:05');
INSERT INTO `activity` VALUES ('21', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:05', '2020-03-03 00:41:05');
INSERT INTO `activity` VALUES ('22', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:06', '2020-03-03 00:41:06');
INSERT INTO `activity` VALUES ('23', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:06', '2020-03-03 00:41:06');
INSERT INTO `activity` VALUES ('24', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:06', '2020-03-03 00:41:06');
INSERT INTO `activity` VALUES ('25', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:06', '2020-03-03 00:41:06');
INSERT INTO `activity` VALUES ('26', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:07', '2020-03-03 00:41:07');
INSERT INTO `activity` VALUES ('27', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:07', '2020-03-03 00:41:07');
INSERT INTO `activity` VALUES ('28', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:07', '2020-03-03 00:41:07');
INSERT INTO `activity` VALUES ('29', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:08', '2020-03-03 00:41:08');
INSERT INTO `activity` VALUES ('30', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:08', '2020-03-03 00:41:08');
INSERT INTO `activity` VALUES ('31', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:09', '2020-03-03 00:41:09');
INSERT INTO `activity` VALUES ('32', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:09', '2020-03-03 00:41:09');
INSERT INTO `activity` VALUES ('33', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:09', '2020-03-03 00:41:09');
INSERT INTO `activity` VALUES ('34', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:10', '2020-03-03 00:41:10');
INSERT INTO `activity` VALUES ('35', 'http://192.168.43.178:4000/api/reference-data/source/RDS2235_4w-x4srgj_m', 'activated', null, null, '1', '2020-03-03 00:41:10', '2020-03-03 00:41:10');
INSERT INTO `activity` VALUES ('36', 'http://192.168.43.178:4000/api/reference-data/source/RD_xs-lotFU4NY0CAMZ', 'activated', null, null, '1', '2020-03-03 01:49:50', '2020-03-03 01:49:50');
INSERT INTO `activity` VALUES ('37', 'http://192.168.43.178:4000/api/reference-data/source/RDZzHmVKeCyw9-TLhO1', 'activated', 'http://www.baidu.com', '3', '0', '2020-03-05 13:19:06', '2020-03-05 13:19:06');

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region` varchar(255) DEFAULT NULL COMMENT '地区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `postcode` varchar(255) DEFAULT NULL COMMENT '邮政编码',
  `receiver_name` varchar(255) DEFAULT NULL COMMENT '收件人',
  `receiver_phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `user_id` int(11) DEFAULT NULL COMMENT '所属用户',
  `defaulted` tinyint(1) DEFAULT '0',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '广东省 珠海市 金湾区', '太平镇广从南路123号s', '510531', '黄林2', '15820239554', '1', '0', '0');
INSERT INTO `address` VALUES ('2', '北京市 市辖区 东城区', '太平镇广从南路348号', '525300', '黄林', '15820239553', '1', '1', '1');

-- ----------------------------
-- Table structure for arrived_history
-- ----------------------------
DROP TABLE IF EXISTS `arrived_history`;
CREATE TABLE `arrived_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `days` int(255) DEFAULT NULL COMMENT '连续签到天数',
  `motto` varchar(255) DEFAULT NULL COMMENT '今天名言',
  `sign_time` datetime DEFAULT NULL COMMENT '签到时间',
  `user_id` int(11) DEFAULT NULL COMMENT '签到用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of arrived_history
-- ----------------------------
INSERT INTO `arrived_history` VALUES ('1', '1', null, '2020-03-13 01:20:11', '1');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书id',
  `title` varchar(255) DEFAULT NULL COMMENT '有声书标题',
  `description` tinytext COMMENT '有声书描述',
  `cover` varchar(255) DEFAULT NULL COMMENT '有声书封面',
  `teller` varchar(255) DEFAULT NULL COMMENT '讲述人',
  `metadata_id` int(11) DEFAULT NULL COMMENT '书籍元数据id',
  `category_id` int(11) DEFAULT NULL COMMENT '类别id',
  `publish_year` char(4) DEFAULT NULL COMMENT '发布年份',
  `type` varchar(255) DEFAULT NULL COMMENT '图书类型（electronic-book: 电子书，audio-book: 有声书，paper-book = 纸质书）',
  `episodes` int(11) DEFAULT '0' COMMENT '章节数',
  `status` varchar(255) DEFAULT NULL COMMENT '图书状态（examining：审批, serializing: 连载中, paused: 停更, ended: 完结）',
  `money` bigint(20) DEFAULT NULL COMMENT '全本价格',
  `commodity_id` int(11) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标识',
  `content_id` int(11) DEFAULT NULL COMMENT '内容id',
  `plays` int(11) DEFAULT '0' COMMENT '播放数',
  `reads` int(11) DEFAULT '0' COMMENT '阅读数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', null, null, null, null, '3', '18', '1997', 'paper-book', '0', 'serializing', null, '7', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('2', null, null, null, null, '4', '19', '2089', 'paper-book', '0', 'rejected', null, '8', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('3', null, null, null, null, '5', '20', '1997', 'paper-book', '0', 'rejected', null, '9', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('4', null, null, null, null, '6', '21', '2012', 'paper-book', '0', 'rejected', null, '10', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('5', null, null, null, null, '7', '22', '2014', 'paper-book', '0', 'rejected', null, '11', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('6', null, null, null, null, '8', '23', '2017', 'paper-book', '0', 'serializing', null, '12', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('7', null, null, null, null, '9', '28', '2011', 'electronic-book', '0', 'serializing', null, null, '0', '1', '0', '0');
INSERT INTO `book` VALUES ('8', 'sdfds', 't3ter', 'http://192.168.43.178:4000/api/reference-data/source/RDZotU4AbBvyf0v1YCW', 'ewrq', '8', '21', '2019', 'audio-book', '0', 'paused', null, null, '0', '1', '0', '0');
INSERT INTO `book` VALUES ('9', 'sdfsd', 'ewfsd', 'http://192.168.43.178:4000/api/reference-data/source/RD9yp4fW3-_3eI6aO-C', 'sdgarg', '9', '22', '2019', 'audio-book', '0', 'paused', null, null, '0', '1', '0', '0');
INSERT INTO `book` VALUES ('10', 'sdfdssdfs', 'etrefds', 'http://192.168.43.178:4000/api/reference-data/source/RDuat0cfdl0x5DJDu86', 'dfsa', '9', '24', '2019', 'audio-book', '0', 'paused', null, null, '0', '1', '0', '0');
INSERT INTO `book` VALUES ('11', null, null, null, null, '2', '22', '2020', 'electronic-book', '0', 'serializing', null, null, '1', '1', '0', '0');
INSERT INTO `book` VALUES ('12', null, null, null, null, '2', '22', '2020', 'electronic-book', '0', 'serializing', null, null, '1', '1', '0', '0');
INSERT INTO `book` VALUES ('13', null, null, null, null, '1', '21', '2020', 'electronic-book', '0', 'serializing', null, null, '1', '1', '0', '0');
INSERT INTO `book` VALUES ('14', null, null, null, null, '13', '21', '2020', 'electronic-book', '0', 'serializing', null, null, '1', '1', '0', '0');
INSERT INTO `book` VALUES ('16', null, null, null, null, '15', '1', '2011', 'electronic-book', '0', 'serializing', null, null, '1', '1', '0', '0');
INSERT INTO `book` VALUES ('17', 'a', 'b', 'c', 'd', '2', '1', '3432', 'audio-book', '0', 'ended', null, null, '0', '1', '0', '0');
INSERT INTO `book` VALUES ('18', 'sdfdfsdfsd', 'sdfsdf', 'http://192.168.43.178:4000/api/reference-data/source/RDg-mj62LTX7eMwEEw0', '码迹', '11', '21', '3422', 'audio-book', '0', 'serializing', null, null, '0', '1', '20', '0');
INSERT INTO `book` VALUES ('19', '测试书籍', 'Hello this is a book', 'http://example.com/example.png', '测试者', '17', '23', '2010', 'electronic-book', '0', 'examining', null, '1', '1', '29', '0', '0');
INSERT INTO `book` VALUES ('20', '测试书籍', 'Hello this is a book', 'http://example.com/example.png', '测试者', '18', '1', '2010', 'electronic-book', '0', 'examining', null, '1', '1', '30', '0', '0');
INSERT INTO `book` VALUES ('21', '测试书籍', 'Hello this is a book', 'http://example.com/example.png', '测试者', '19', '1', '2010', 'electronic-book', '0', 'examining', null, '1', '1', '49', '0', '0');
INSERT INTO `book` VALUES ('22', '测试书籍', 'Hello this is a book', 'http://example.com/example.png', '测试者', '20', '1', '2010', 'electronic-book', '0', 'examining', null, '1', '1', '50', '0', '0');

-- ----------------------------
-- Table structure for book_audio_episode
-- ----------------------------
DROP TABLE IF EXISTS `book_audio_episode`;
CREATE TABLE `book_audio_episode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '章节标题',
  `episode_id` int(11) DEFAULT NULL COMMENT '文字章节id',
  `owner_id` int(11) DEFAULT NULL COMMENT '拥有者Id',
  `duration` int(11) DEFAULT NULL COMMENT '持续时间',
  `status` varchar(255) DEFAULT NULL COMMENT '章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）',
  `book_id` int(11) DEFAULT NULL,
  `commodity_id` int(11) DEFAULT NULL,
  `media_number` int(11) DEFAULT NULL,
  `stream_url` varchar(255) DEFAULT NULL COMMENT '音频流url',
  `next_episode_id` int(11) DEFAULT NULL COMMENT '下一章节id',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `plays` int(11) DEFAULT '0' COMMENT '播放数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_audio_episode
-- ----------------------------
INSERT INTO `book_audio_episode` VALUES ('1', 'sdfsdf', '1', '1', '97', 'draft', '8', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RDuqjlO0XFwJOpTThbo', null, '1', '2020-03-02 19:41:10', '2020-03-02 19:31:25', null);
INSERT INTO `book_audio_episode` VALUES ('2', '第一章 穷途末路', '2', '1', '102', 'draft', '8', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RDIJr5t98s5S5O85o6O', null, '1', '2020-03-02 19:50:57', '2020-03-02 19:50:57', null);
INSERT INTO `book_audio_episode` VALUES ('3', '第一章 穷途末路', '3', '1', '102', 'draft', '8', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RDIJr5t98s5S5O85o6O', null, '1', '2020-03-02 19:51:13', '2020-03-02 19:51:13', null);
INSERT INTO `book_audio_episode` VALUES ('4', 'sdfsda', '1', '1', '98', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RDSiFGhleQ9Gyht6sLH', null, '1', '2020-03-05 18:09:43', '2020-03-05 18:09:43', '0');
INSERT INTO `book_audio_episode` VALUES ('5', '32423', '1', '1', '100', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD7VKAlUievYkrK5Pu7', null, '1', '2020-03-05 18:15:53', '2020-03-05 18:15:53', '0');
INSERT INTO `book_audio_episode` VALUES ('6', '32423', '1', '1', '100', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD7VKAlUievYkrK5Pu7', null, '1', '2020-03-05 18:15:55', '2020-03-05 18:15:55', '0');
INSERT INTO `book_audio_episode` VALUES ('7', '32423', '1', '1', '100', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD7VKAlUievYkrK5Pu7', null, '1', '2020-03-05 18:15:56', '2020-03-05 18:15:56', '0');
INSERT INTO `book_audio_episode` VALUES ('8', '32423', '1', '1', '100', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD7VKAlUievYkrK5Pu7', null, '0', '2020-03-05 18:15:56', '2020-03-05 18:15:56', '0');
INSERT INTO `book_audio_episode` VALUES ('9', '32423', '1', '1', '100', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD7VKAlUievYkrK5Pu7', null, '0', '2020-03-05 18:15:56', '2020-03-05 18:15:56', '0');
INSERT INTO `book_audio_episode` VALUES ('10', '32423', '1', '1', '100', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD7VKAlUievYkrK5Pu7', null, '0', '2020-03-05 18:15:57', '2020-03-05 18:15:57', '0');
INSERT INTO `book_audio_episode` VALUES ('11', '32423', '1', '1', '100', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD7VKAlUievYkrK5Pu7', null, '1', '2020-03-05 18:16:22', '2020-03-05 18:16:22', '0');
INSERT INTO `book_audio_episode` VALUES ('12', '32423', '1', '1', '100', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD7VKAlUievYkrK5Pu7', null, '0', '2020-03-05 18:16:23', '2020-03-05 18:16:23', '0');
INSERT INTO `book_audio_episode` VALUES ('13', '32423', '1', '1', '100', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD7VKAlUievYkrK5Pu7', null, '0', '2020-03-05 18:16:23', '2020-03-05 18:16:23', '0');
INSERT INTO `book_audio_episode` VALUES ('14', '32423', '1', '1', '100', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD7VKAlUievYkrK5Pu7', null, '0', '2020-03-05 18:16:23', '2020-03-05 18:16:23', '0');
INSERT INTO `book_audio_episode` VALUES ('15', 'sdfsd', '14', '1', '110', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RDE372FdRPVPpxl0qMW', null, '1', '2020-03-05 18:20:21', '2020-03-05 18:20:21', '0');
INSERT INTO `book_audio_episode` VALUES ('16', '32423sdf', '1', '1', '5', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RDPOrYBsdzVVe8FT0lL', null, '1', '2020-03-05 18:31:40', '2020-03-05 18:31:40', '0');
INSERT INTO `book_audio_episode` VALUES ('17', 'retdafs', '2', '1', '97', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD_Kf0ksr75UzWKnrlm', null, '0', '2020-03-05 19:03:02', '2020-03-05 19:03:02', '0');
INSERT INTO `book_audio_episode` VALUES ('18', 'retdafs', '2', '1', '97', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD_Kf0ksr75UzWKnrlm', null, '0', '2020-03-05 19:03:07', '2020-03-05 19:03:07', '0');
INSERT INTO `book_audio_episode` VALUES ('19', 'retdafs', '2', '1', '97', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD_Kf0ksr75UzWKnrlm', null, '0', '2020-03-05 19:03:08', '2020-03-05 19:03:08', '0');
INSERT INTO `book_audio_episode` VALUES ('20', 'retdafs', '2', '1', '97', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RD_Kf0ksr75UzWKnrlm', null, '0', '2020-03-05 19:03:09', '2020-03-05 19:03:09', '0');
INSERT INTO `book_audio_episode` VALUES ('21', '3423', '1', '1', '96', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RDrNoJALVY8dfOloQCK', null, '0', '2020-03-05 19:15:16', '2020-03-05 19:15:16', '0');
INSERT INTO `book_audio_episode` VALUES ('22', 'rrgdfgdf', '1', '1', '97', 'draft', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RDVkB1WhagQuSxcBMAQ', null, '0', '2020-03-05 19:18:33', '2020-03-05 19:18:33', '0');
INSERT INTO `book_audio_episode` VALUES ('23', 'dfsdf', '1', '1', '102', 'examining', '18', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RDMGw-EmZAfiZpUzLJr', null, '0', '2020-03-05 19:23:06', '2020-03-05 19:25:58', '0');
INSERT INTO `book_audio_episode` VALUES ('24', '第一章 风起云涌', '16', '1', '92', 'draft', '8', null, null, 'http://192.168.43.178:4000/api/reference-data/source/RDy_E2g-63DeiaJ-qJ4', null, '0', '2020-03-13 18:10:11', '2020-03-13 18:11:09', '0');

-- ----------------------------
-- Table structure for book_episode
-- ----------------------------
DROP TABLE IF EXISTS `book_episode`;
CREATE TABLE `book_episode` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '章节id',
  `title` varchar(255) DEFAULT NULL COMMENT '章节标题',
  `owner_id` int(11) DEFAULT NULL,
  `commodity_id` int(11) DEFAULT NULL,
  `episode_number` int(11) DEFAULT NULL COMMENT '章节号',
  `content_type` varchar(255) DEFAULT NULL COMMENT '内容源类型（html: HTML代码）',
  `content_source` mediumtext COMMENT '内容源',
  `words` int(11) DEFAULT NULL COMMENT '字数',
  `status` varchar(255) DEFAULT NULL COMMENT '章节状态（draft: 草稿, examining: 审批, rejected: 驳回, publish: 发布）',
  `book_id` int(11) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_episode
-- ----------------------------
INSERT INTO `book_episode` VALUES ('17', '第 1 章　客户端/服务器网络编程简介', '1', null, '1', 'html', '<p>本书将带领读者使用Python语言探索网络编程。全书涵盖了网络编程的基本概念、模块以及第三方库，这些第三方库在使用流行互联网通信协议与远程机器进行通信时可能会用到。</p>\n\n<p>本书并不适用于未使用过Python语言甚至未编写过计算机程序的读者，因为书中并不教授如何使用Python语言进行编程。本书假设读者已经通过相关资料对Python编程有所了解。我希望书中的Python示例能够帮助读者了解如何组织并编写自己的代码，但我会不加解释地大量使用各种高级的Python特性。不过，在遇到一些我认为尤其有趣或巧妙的用法时，我也会指出并加以说明。</p>\n\n<p>另一方面，本书并不预设读者有任何网络知识！只要使用过浏览器或者发送过电子邮件，就完全可以从头开始阅读本书并循序渐进地学习计算机网络。本书会从应用开发者的角度来介绍网络：一是从实现一个网络连接服务的角度，例如网站、邮箱服务器或者网络游戏；二是从编写使用服务的客户端程序的角度。</p>\n\n<p>然而，需要注意的是，本书并不教授如何建立或配置网络。网络设计、服务器管理以及自动化配置都是内容丰富的主题，并且与本书涉及的计算机编程内容没有重复。随着在OpenStack、SaltStack和Ansible中的大量应用，Python确实已经在自动化配置领域得到了广泛的应用。尽管如此，如果读者想深入学习配置运维相关的技术，还是应该寻找专门介绍这方面技术的书籍和文档。</p>\n\n<h2>1.1　基础：协议栈与库</h2>\n\n<p>在学习Python网络编程的过程中，会不断遇到以下两个概念。</p>\n\n<ul>\n	<li><strong>协议栈</strong>（protocol stack）。复杂的网络服务建立在简单网络服务的基础之上。</li>\n	<li>经常需要使用之前编写过的Python<strong>库</strong>（library），包括Python内置的标准库以及下载安装的第三方库，来解析要使用的网络通信协议。</li>\n</ul>\n\n<p>很多情况下，网络编程就是选择并使用一个已经支持所需网络操作的库的过程。本书的主要目的就是向读者介绍Python的一些重要网络库，并讲解这些网络库所使用的底层网络服务。通过了解底层知识，除了可以理解网络库的运行原理外，还能够在底层部分出现错误时知道具体发生了什么，因此对读者是大有裨益的。</p>\n\n<p>让我们从一个简单的例子开始。下面是一个邮箱地址：</p>\n\n<pre>\n<code>207 N. Defiance St\nArchbold, OH</code></pre>\n\n<p>假设我们想知道该物理地址的纬度和经度。谷歌提供的地理编码API就能够完成这种从物理地址到经纬度的转化。那么如何通过Python来使用这个网络服务呢？</p>\n\n<p>想要使用一个全新的网络服务时，总是可以先查找一下是否有人已经实现了这个服务的通信协议。在本例中，所需要使用的是谷歌地理编码协议（Google Geocoding protocol）。因此可以先查阅Python的标准库文档，看看有没有与地理编码有关的内容。</p>\n\n<p><a href=\"http://docs.python.org/3/library/\">http://docs.python.org/3/library/</a></p>\n\n<p>读者查询到有关地理编码的库了吗？我也没有找到。尽管如此，经常查阅标准库目录对于Python程序员来说还是非常重要的，因为每次查询都可以加深对Python提供的服务的了解。Doug Hellmann的博客&ldquo;Python Module of the Week&rdquo;是另一个极佳的参考资料，读者也可以从中了解Python标准库提供的功能。</p>\n\n<p>由于标准库没有提供用于本例的包，因此我们可以查询Python包索引（Python Package Index）。这是一个相当优质的资源，可以从中找到全球各地的程序员和机构贡献的各种通用包。读者也同样可以通过所用服务提供商的网站查看其是否提供Python库的接口。除此之外，还可以直接用Python加上要使用的网络服务名称作为关键字，在谷歌上搜索，看看搜索结果中的前几个链接是否值得一试。</p>\n\n<p>在本例中，我搜索了Python Package Index，找到如下链接：</p>\n\n<p><a href=\"https://pypi.python.org/\">https://pypi.python.org/</a></p>\n\n<p>然后搜索geocoding，很快就找到了一个叫作pygeocoder的包，这个包提供了简洁的接口来获取谷歌的地理编码服务。（尽管从包名可以注意到，这个包<strong>并非</strong>由谷歌官方提供，而是第三方开发者开发的。）</p>\n\n<p><a href=\"http://pypi.python.org/pypi/pygeocoder/\">http://pypi.python.org/pypi/pygeocoder/</a></p>\n\n<p>正常情况下，开发者都能找到一个看上去完全满足需求的Python包并想试一试。我要在这里暂停一下，向读者介绍一个用来尝试新Python库的最棒的技术：virtualenv！</p>\n\n<p>以前，Python包的安装令人厌恶，并且不可逆。安装过程要求管理员权限，因此永久性地修改了系统的Python安装环境。经过了几个月的大规模Python开发，系统的Python包安装目录下就包含了大量的无用包，这些包均通过手动安装。更悲剧的是，一些新安装的包可能由于与已安装的包不兼容而无法使用。要知道，使用这些已安装包的项目在几个月前就已结束。</p>\n\n<p>而现在，谨慎的Python程序员再也不会遭这种罪了。通常来说，只要在系统层面上安装一个Python包就够了，那就是virtualenv！只要安装了virtualenv，就可以创建任意数量的小型、独立的&ldquo;虚拟Python环境&rdquo;。如果想要做实验的话，可以在这些&ldquo;虚拟Python环境&rdquo;中安装或卸载相应的包。这很好地保护了系统Python环境。当一个特定的项目或实验结束之后，只需删除对应的虚拟环境目录即可。过程简单，系统也能保持干净。</p>\n\n<p>在本例中，需要新建一个虚拟环境来测试pygeocoder包。如果读者之前从未在系统上安装过virtualenv，可访问下面的链接来下载并安装：</p>\n\n<p><a href=\"http://pypi.python.org/pypi/virtualenv\">http://pypi.python.org/pypi/virtualenv</a></p>\n\n<p>virtualenv安装完成后，便可使用如下命令来新建一个环境。（在Windows环境中，虚拟环境中包含Python二进制可执行文件的目录被命名为Scripts，而不是bin。）</p>\n\n<pre>\n<code>$ virtualenv &ndash;p python3 geo_env\n$ cd geo_env\n$ ls\nbin/ include/ lib/\n$ . bin/activate\n$ python -c &#39;import pygeocoder&#39;\nTraceback (most recent call last):\n  File &quot;&lt;string&gt;&quot;, line 1, in &lt;module&gt;\nImportError: No module named &#39;pygeocoder&#39;</code></pre>\n\n<p>由上可见，pygeocoder包尚未安装。由于运行了<code>activate</code>命令，新建的虚拟环境现在已经在系统路径中了，因此可在虚拟环境中使用<code>pip</code>命令安装pygeocoder包。</p>\n\n<pre>\n<code>$ pip install pygeocoder\nDownloading/unpacking pygeocoder\n  Downloading pygeocoder-1.2.1.1.tar.gz\n  Running setup.py egg_info for package pygeocoder\n\nDownloading/unpacking requests&gt;=1.0 (from pygeocoder)\n  Downloading requests-2.0.1.tar.gz (412kB): 412kB downloaded\n  Running setup.py egg_info for package requests\n\nInstalling collected packages: pygeocoder, requests\n  Running setup.py install for pygeocoder\n\n  Running setup.py install for requests\n\nSuccessfully installed pygeocoder requests\nCleaning up...</code></pre>\n\n<p>virtualenv中pygeocoder包的二进制可执行文件现在已经可以使用了。</p>\n\n<pre>\n<code>$ python -c &#39;import pygeocoder&#39;</code></pre>\n\n<p>安装了pygeocoder后，代码清单1-1所示的程序search1.py就可以运行了。</p>\n\n<blockquote>\n<p><strong>代码清单1-1</strong>　获取经度与纬度</p>\n</blockquote>\n\n<pre>\n<code>#!/usr/bin/env python3\n# Foundations of Python Network Programming, Third Edition\n# https://github.com/brandon-rhodes/fopnp/blob/m/py3/chapter01/search1.py\n\nfrom pygeocoder import Geocoder\n\nif __name__ == &#39;__main__&#39;:\n    address = &#39;207 N. Defiance St, Archbold, OH&#39;\n    print(Geocoder.geocode(address)[0].coordinates)</code></pre>\n\n<p>在命令行运行上述程序，可得到如下结果：</p>\n\n<pre>\n<code>$ python3 search1.py\n(41.521954, -84.306691)</code></pre>\n\n<p>看，屏幕上已经打印出了该地址的经纬度！这个结果是直接从谷歌的网络服务获取到的。我们的第一个示例程序成功了。</p>\n\n<p>通过上面的过程，你可能会觉得一本讲Python网络编程的书只是教你直接下载安装一个第三方包，然后把一个有趣的网络编程问题变成了3行无聊的Python脚本！你是不是觉得很不爽？淡定！你会发现90%的编程挑战就是这么解决的&mdash;&mdash;在Python社区中寻找其他已经解决你所面临问题的程序员，然后在他们的解决方案的基础上构建智能又简洁的方案。</p>\n\n<p>但是，本例还没彻底完成。我们已经展示了如何简单地获取复杂的网络服务，但是，优雅的pygeocoder接口背后的原理是怎样的呢？网络服务是如何工作的呢？下面，我们将详细学习如何在一个包含至少6层的网络协议栈的顶层构建这个复杂的服务。</p>\n\n<h2>1.2　应用层</h2>\n\n<p>第一个示例程序使用了一个从Python包索引下载的第三方Python库来解决问题。这个第三方库完整地包装处理了谷歌地理编码API。但如果没有这样的库怎么办呢？如果需要自己为谷歌地图API编写客户端又该怎么办呢？</p>\n\n<p>让我们通过代码清单1-2所示的search2.py来看看答案。这个程序没有使用直接提供地理编码功能的第三方库，而是使用了更底层的Requests库。Requests库相当流行，较pygeocoder更底层一些。从前面的<code>pip install</code>命令可以看出，Requests库也已经安装在虚拟环境中了。</p>\n\n<blockquote>\n<p><strong>代码清单1-2</strong>　从谷歌地理编码API获取一个JSON文档</p>\n</blockquote>\n\n<pre>\n<code>#!/usr/bin/env python3\n# Foundations of Python Network Programming, Third Edition\n# https://github.com/brandon-rhodes/fopnp/blob/m/py3/chapter01/search2.py\n\nimport requests\n\ndef geocode(address):\n    parameters = {&#39;address&#39;: address, &#39;sensor&#39;: &#39;false&#39;}\n    base = &#39;http://maps.googleapis.com/maps/api/geocode/json&#39;\n    response = requests.get(base, params=parameters)\n    answer = response.json()\n    print(answer[&#39;results&#39;][0][&#39;geometry&#39;][&#39;location&#39;])\n\nif __name__ == &#39;__main__&#39;:\n    geocode(&#39;207 N. Defiance St, Archbold, OH&#39;)</code></pre>\n\n<p>运行这段Python程序的返回结果与第一个脚本的结果颇为相似。</p>\n\n<pre>\n<code>$ python3 search2.py\n{&#39;lat&#39;: 41.521954, &#39;lng&#39;: -84.306691}</code></pre>\n\n<p>但可以看到，程序输出并非<strong>完全</strong>相同。例如，JSON数据将结果封装为一个&ldquo;对象&rdquo;，Requests库以Python字典的形式提供了该&ldquo;对象&rdquo;。但显而易见的是，这个脚本与第一个脚本基本完成了相同的功能。</p>\n\n<p>读者很容易注意到，本例中的代码并未像pygeocoder库一样提供更高层的地理编码语义。只有仔细分析这段代码才可能知道，其目的是查询一个邮寄地址对应的经纬度。在search1.py中，程序直接请求将地址转化为经纬度，而代码清单1-2则仔细地构造了一个基础URL以及一系列查询参数。程序编写者需要阅读过谷歌的文档才能清楚了解这些查询参数的意义。顺便提一下，如果想阅读该文档，可以从如下链接找到API描述：</p>\n\n<p><a href=\"http://code.google.com/apis/maps/documentation/geocoding/\">http://code.google.com/apis/maps/documentation/geocoding/</a></p>\n\n<p>仔细观察search2.py中的查询参数，可以发现，需要查询的邮寄地址是由名为<code>address</code>的参数标识的。另一个参数则向谷歌表明，该位置查询并非源于移动设备位置传感器提供的实时数据。</p>\n\n<p>接收到该URL查询的结果文档后，手动调用<code>response.json()</code>方法将结果转化为JSON格式，并通过Python字典的多层取值获取正确的经度和纬度。</p>\n\n<p>search2.py与search1.py完成的功能是相同的，不过区别在于，search2.py并没有通过地址和纬度这样的语义直接解决该问题，而是通过构造URL，获取查询响应，然后将结果转化为JSON，一步一步地解决了这个问题。这一区别在研究网络协议栈高层与底层协议时是相当常见的。高层的代码描述了查询的<strong>意义</strong>，而底层的代码则展示了查询的<strong>构造</strong>细节。</p>\n\n<h2>1.3　协议的使用</h2>\n\n<p>第二个例子的脚本构建了一个URL，并获取了该URL查询的响应文档。这一操作听上去相当简单。当然，为了使URL查询看起来像是一个基础操作，Web浏览器其实做了很多的工作。然而，URL之所以可以用来获取某个文档，真正原因显然是其描述了网络上该特定文档的位置以及获取方法。URL包含了协议的名称，后面跟着保存文档的主机名，最后是该主机上特定文档的路径。URL提供了更底层协议查询该文档所需的指令。这样一来，search2.py就能够解析URL并获取响应文档了。</p>\n\n<p>事实上，这个URL使用的底层协议就是著名的HTTP（Hypertext Transfer Protocol，超文本传输协议），HTTP协议几乎是所有现代网络通信的基础。在第9章、第10章和第11章中，读者将学到更多有关HTTP协议的知识。Requests库从谷歌获取结果的具体原理机制其实就是由HTTP提供的。如果不想使用Requests库提供的神奇功能，而是想直接使用HTTP来获取结果，又该怎么做呢？代码清单1-3所示的search3.py便是答案。</p>\n\n<blockquote>\n<p><strong>代码清单1-3</strong>　使用原始HTTP操作连接谷歌地图</p>\n</blockquote>\n\n<pre>\n<code>#!/usr/bin/env python3\n# Foundations of Python Network Programming, Third Edition\n# https://github.com/brandon-rhodes/fopnp/blob/m/py3/chapter01/search3.py\n\nimport http.client\nimport json\nfrom urllib.parse import quote_plus\n\nbase = &#39;/maps/api/geocode/json&#39;\n\ndef geocode(address):\n    path = &#39;{}?address={}&amp;sensor=false&#39;.format(base, quote_plus(address))\n    connection = http.client.HTTPConnection(&#39;maps.googleapis.com&#39;)\n    connection.request(&#39;GET&#39;, path)\n    rawreply = connection.getresponse().read()\n    reply = json.loads(rawreply.decode(&#39;utf-8&#39;))\n    print(reply[&#39;results&#39;][0][&#39;geometry&#39;][&#39;location&#39;])\n\nif __name__ == &#39;__main__&#39;:\n    geocode(&#39;207 N. Defiance St, Archbold, OH&#39;)</code></pre>\n\n<p>这个程序直接使用了HTTP协议。首先请求连接一台特定的主机，然后手动构造一个带<code>path</code>参数的<code>GET</code>查询，最后直接从HTTP连接读取响应结果。这种方法没有使用字典将查询参数方便地表示为独立的键值对，而是直接将它们手动嵌入到查询地址中。要通过这种方法完成查询，需要在问号（<code>?</code>）后跟上由<code>&amp;</code>分隔的参数。这些参数通过<code>name=value</code>的形式来表示。</p>\n\n<p>尽管方法不同，但程序运行的结果与前两个程序基本相同。</p>\n\n<pre>\n<code>$ python3 search3.py\n{&#39;lat&#39;: 41.521954, &#39;lng&#39;: -84.306691}</code></pre>\n\n<p>在阅读本书的过程中，读者会发现，HTTP只是Python标准库提供内置实现的众多协议之一。在search3.py中，毋需关心HTTP的实现细节，只需编写代码，简单地发送查询，然后查看相应结果即可。当然，这个程序需要处理的协议细节已经比search2.py更基础一些了，因为本例使用的协议比起前面的程序在协议栈中更低了一层。不过，至少仍然可以使用标准库来处理实际的网络数据，并保证其运行正确。</p>\n\n<h2>1.4　一个原始的网络会话</h2>\n\n<p>当然，HTTP是无法通过稀薄的空气在两台机器间传输数据的。HTTP协议必须使用一些更简单的抽象来完成操作。事实上，现代操作系统提供了使用TCP协议在IP网络的不同程序间进行纯文本网络会话的功能，而HTTP协议正是使用了这一功能。换句话说，HTTP协议精确描述了两台主机间通过TCP传输的信息格式，并以此提供HTTP的各项功能。</p>\n\n<p>如果继续深入观察HTTP的底层实现细节，那么你将到达通过Python可以方便操作的网络协议栈的最底层。仔细阅读代码清单1-4所示的search4.py，它向谷歌地图发起了与前三个程序相同的网络请求。不同的是，search4.py向网络发送了一个原始文本信息作为请求，并收到了很多原始文本作为响应。</p>\n\n<blockquote>\n<p><strong>代码清单1-4</strong>　直接使用套接字与谷歌地图通信</p>\n</blockquote>\n\n<pre>\n<code>#!/usr/bin/env python3\n# Foundations of Python Network Programming, Third Edition\n# https://github.com/brandon-rhodes/fopnp/blob/m/py3/chapter01/search4.py\n\nimport socket\nfrom urllib.parse import quote_plus\n\nrequest_text = &quot;&quot;&quot;\\\nGET /maps/api/geocode/json?address={}&amp;sensor=false HTTP/1.1\\r\\n\\\nHost: maps.google.com:80\\r\\n\\\nUser-Agent: search4.py (Foundations of Python Network Programming)\\r\\n\\\nConnection: close\\r\\n\\\n\\r\\n\\\n&quot;&quot;&quot;\n\ndef geocode(address):\n    sock = socket.socket()\n    sock.connect((&#39;maps.googleapis.com&#39;, 80))\n    request = request_text.format(quote_plus(address))\n    sock.sendall(request.encode(&#39;ascii&#39;))\n    raw_reply = b&#39;&#39;\n    while True:\n        more = sock.recv(4096)\n        if not more:\n            break\n        raw_reply += more\n    print(raw_reply.decode(&#39;utf-8&#39;))\n\nif __name__ == &#39;__main__&#39;:\n    geocode(&#39;207 N. Defiance St, Archbold, OH&#39;)</code></pre>\n\n<p>从search3.py到search4.py，有一个本质的不同。之前的3个程序各自都使用了一个Python库。Python的实现已经提供了这些库。我们可以使用这些库解析复杂的网络协议。而在本例中，我们已经深入到了最底层：使用主机操作系统提供的原始<code>socket()</code>函数来支持IP网络上的网络通信。换句话说，这种编写网络程序的方式和使用C语言的底层系统程序员所用的一样。</p>\n\n<p>在后面的几章中，读者将学习更多关于套接字的知识。目前可以从search4.py中观察到，原始网络通信的过程就是发送与接收字节串的过程。发送的查询是一个字节串，接收到的响应同样也是一个字节串。在本例中，我们将这个大型的响应字节串打印到屏幕。这样，读者就可以直观感受到底层操作的魅力了。（1.6节解释了为什么在打印前将字符串解码。）我们可以通过<code>sendall()</code>函数传入的参数了解到该HTTP查询的具体内容。能够看到，查询中包含了关键字<code>GET</code>（表示希望进行的操作的名称），<code>GET</code>后跟着待获取文档的路径以及支持的HTTP版本。</p>\n\n<pre>\n<code>GET /maps/api/geocode/json?address=207+N.+Defiance+St%2C+Archbold%2C+OH&amp;sensor=false HTTP/1.1</code></pre>\n\n<p><code>GET</code>信息后跟着一些请求头，每个请求头包含了名称、冒号、值。最后是表示请求结束的回车符和换行符。</p>\n\n<p>运行search4.py，查询的响应将会直接打印出来，如代码清单1-5所示。在本例中，我只是简单地将响应在屏幕上打印了出来，而没有编写复杂的文本处理代码来解析这一响应。这么做是因为，我认为阅读屏幕上打印出来的HTTP响应能够帮助读者更好地了解HTTP响应的形式。相比之下，要理解用于解析HTTP响应的代码就困难多了。</p>\n\n<blockquote>\n<p><strong>代码清单1-5</strong>　search4.py的运行输出</p>\n</blockquote>\n\n<pre>\n<code>HTTP/1.1 200 OK\nContent-Type: application/json; charset=UTF-8\nDate: Sat, 23 Nov 2013 18:34:30 GMT\nExpires: Sun, 24 Nov 2013 18:34:30 GMT\nCache-Control: public, max-age=86400\nVary: Accept-Language\nAccess-Control-Allow-Origin: *\nServer: mafe\nX-XSS-Protection: 1; mode=block\nX-Frame-Options: SAMEORIGIN\nAlternate-Protocol: 80:quic\nConnection: close\n\n{\n   &quot;results&quot; : [\n      {\n         ...\n         &quot;formatted_address&quot; : &quot;207 North Defiance Street, Archbold, OH 43502, USA&quot;,\n         &quot;geometry&quot; : {\n            &quot;location&quot; : {\n               &quot;lat&quot; : 41.521954,\n               &quot;lng&quot; : -84.306691\n            },\n            ...\n         },\n         &quot;types&quot; : [ &quot;street_address&quot; ]\n      }\n   ],\n   &quot;status&quot; : &quot;OK&quot;\n}</code></pre>\n\n<p>可以看到，HTTP响应的结构和HTTP请求相当类似。首先是状态行，然后跟着一些响应头。响应头后有一个空行，接着就是响应体。响应体是一个JavaScript数据结构，也就是JSON这一简单的格式。这个JSON就是之前查询的响应结果，描述了查询谷歌地理编码API返回的地理位置。</p>\n\n<p>当然，这里的所有状态和响应头事实上都是之前的程序中使用httplib库处理的底层细节。在本例中，可以看到，如果没有分层，那么网络通信的具体细节是什么样的。</p>\n\n<h2>1.5　层层深入</h2>\n\n<p>希望读者能够喜欢之前的几个Python网络编程的例子。这些例子展现了使用Python进行网络编程的一些要点。</p>\n\n<p>第一个要点，读者可能对<strong>协议栈</strong>这一术语有了更深的了解：先构建利用网络硬件在两台计算机之间传送文本字符串的原始对话功能，然后在此基础上创建更复杂、更高层、语义更丰富的对话（&ldquo;我想知道这个邮寄地址对应的地理位置&rdquo;）。</p>\n\n<p>在前面例子中分析过的协议栈包含4层。</p>\n\n<ul>\n	<li>最上层的谷歌地理编码API，对如何用URL表示地理信息查询和如何获取包含坐标信息的JSON数据进行了封装。</li>\n	<li>URL，标识了可通过HTTP获取的文档。</li>\n	<li>HTTP层，支持面向文档的命令（例如<code>GET</code>）。该层的操作使用了原始TCP/IP套接字。</li>\n	<li>TCP/IP套接字，只处理字节串的发送和接收。</li>\n</ul>\n\n<p>可以看到，协议栈的每一层都使用了其底层协议提供的功能，并同时向上层协议提供服务。这是第一点。</p>\n\n<p>通过这些例子说明的第二个要点是，Python对我们涉及的各网络层都提供了非常全面的支持。除非是要使用应用提供商定制的协议并定制请求的格式（如本例中使用pygeocoder连接谷歌服务），否则无需使用第三方库。我在第二个程序实例中选择使用Requests库，并非因为标准库缺少<code>urllib.request</code>模块，而是因为标准库提供的API太过时了。对于涉及的其他所有协议层，Python都内置了很强的支持。无论是要获取特定URL标识的文档，还是使用原始网络套接字来接收字符串，Python都提供了现成的函数和类来解决这些问题。</p>\n\n<p>第三点，注意到随着使用的通信协议越来越底层，程序的质量也明显随之下降。例如，search2.py和search3.py就对查询的表单结构和主机名等进行了硬编码。这样做使得程序的灵活性较差，日后较难维护。search4.py的代码就更差了，它包含了一个手写的、无参数的HTTP请求，这个请求的结构是Python完全无法解析的。当然，search4.py也不包含任何可用于解析HTTP响应，或分析可能的网络错误情况所需的实际逻辑。</p>\n\n<p>这说明了一个问题：正确实现网络协议并非易事。因此，应该尽可能地使用标准库或第三方库。在阅读本书剩余章节时，应将这点牢记于心。尤其是在编写网络客户端时，人们总是一不小心就将代码写得过于简单。人们总是倾向于忽略很多可能发生的错误情况，而只处理最可能出现的情况。适当的转义参数也经常被忽略，因为人们总是轻易相信查询字符串永远只会包含简单的字母字符。总而言之，人们经常会编写相当脆弱的代码。在技术上可行的情况下，对与之交互的服务考虑得少之又少。而如果使用一个已经精心实现了协议的第三方库，那么所有可能情况和麻烦的边界条件带来的问题都可迎刃而解。这是因为不同的Python开发者都要使用这个库来完成各种各样的工作，而库的实现者已经发现并知道如何恰当地解决这些问题。</p>\n\n<p>第四点，需要强调的是，高层的网络协议（如用来解析街道地址的谷歌地理编码API）通常都会将其底层网络细节<strong>隐藏</strong>。如果一个人只使用过pygeocoder库，可能永远不会知道URL和HTTP是pygeocoder用来解决这个问题的底层机制！</p>\n\n<p>有一个有趣的问题：Python库是否在底层正确地隐藏了错误？这个问题的答案取决于Python库实现得有多仔细。如果发生了一个网络错误，导致暂时无法访问谷歌，那么是否应该在查询街道地址坐标的代码中抛出一个原始的底层网络异常？是否要将所有的错误转换为包含地理编码语义的高层异常？在阅读本书的过程中，请仔细关注网络异常捕获这一话题，尤其是在强调底层网络通信的章节中。</p>\n\n<p>最后一点，也是在本章剩余部分将进行介绍的：search4.py使用的<code>socket()</code>接口其实并不是查询谷歌涉及的最底层的协议！正如本例中的网络协议构建于原始套接字之上一样，套接字这一抽象其实也基于更底层的协议，只不过这些协议由操作系统管理，而非Python。</p>\n\n<p>下面列出了<code>socket()</code> API层之下的几层。</p>\n\n<ul>\n	<li>传输控制协议（TCP），该层通过发送（可能重发）、接收以及重排称为数据包（packet）的小型网络信息，支持由字节流组成的双向网络会话。</li>\n	<li>网际协议（IP），该层处理不同计算机间数据包的发送。</li>\n	<li>最底层的&ldquo;链路层&rdquo;，该层负责在直接相连的计算机之间发送物理信息，由网络硬件设备组成，如以太网端口和无线网卡。</li>\n</ul>\n\n<p>在本章剩余部分以及第2章和第3章，读者将学习到这些底层的网络协议。在本章中，我们将先从了解IP层开始。之后的章节会介绍UDP和TCP这两个颇为不同的协议如何支持在两台联网的主机应用间进行的两种基本会话类型。</p>\n\n<p>不过在这之前，让我们先来谈谈字节与字符。</p>\n\n<h2>1.6　编码与解码</h2>\n\n<p>Python 3对字符串和底层字节序列做了明显的区分。字节（byte）是计算机网络通信过程中实际传输的二进制数。每个字节由8位二进制数构成，范围从00000000至11111111，转换为十进制整数就是0到255。Python中的字符（character）串则包含了Unicode字符，比如a（Unicode标准中称之为&ldquo;小写拉丁字母A&rdquo;），或者}（右花括号），或者&empty;（空集）。尽管每个Unicode字符均有一个叫作<strong>编码点</strong>（code point）的数字标识符与之对应，我们还是可以将其视为内部实现细节。Python 3对字符的处理相当谨慎，除非使用者主动请求Python对字符和外部可见的实际字节进行相互转化，否则对使用者可见的只有字符。</p>\n\n<p>两者间的相互转化操作有正式的名称。</p>\n\n<p><strong>解码</strong>（decoding）是在应用程序使用字节时发生的。此时需要理解这些字节的意义。试想，当应用程序从文件或网络接收到字节时，程序就像一个一流间谍一样，对通信信道间传输的原始字节进行解密。</p>\n\n<p><strong>编码</strong>（encoding）是程序将字符串对外输出时所实施的过程。此时，应用程序使用某一种数字计算机使用的编码方法将字符串转化为字节。当计算机需要传输或存储符号时，字节才是真正使用的格式。回想一下刚才提到的间谍，它需要将得到的消息传送回去，那么就需要将消息符号转化为某种能够在网络间传输的代码。</p>\n\n<p>使用Python 3操作这两个过程是相当简单的。使用<code>decode()</code>方法将读入的字节串转化为字符串，使用<code>encode()</code>方法对要输出的字符串进行编码。代码清单1-6展示了这一技术。</p>\n\n<blockquote>\n<p><strong>代码清单1-6</strong>　解码输入字节，编码输出字符</p>\n</blockquote>\n\n<pre>\n<code>#!/usr/bin/env python3\n# Foundations of Python Network Programming, Third Edition\n# https://github.com/brandon-rhodes/fopnp/blob/m/py3/chapter01/stringcodes.py\n\nif __name__ == &#39;__main__&#39;:\n    # Translating from the outside world of bytes to Unicode characters.\n    input_bytes = b&#39;\\xff\\xfe4\\x001\\x003\\x00 \\x00i\\x00s\\x00 \\x00i\\x00n\\x00.\\x00&#39;\n    input_characters = input_bytes.decode(&#39;utf-16&#39;)\n    print(repr(input_characters))\n\n    # Translating characters back into bytes before sending them.\n    output_characters = &#39;We copy you down, Eagle.\\n&#39;\n    output_bytes = output_characters.encode(&#39;utf-8&#39;)\n    with open(&#39;eagle.txt&#39;, &#39;wb&#39;) as f:\n        f.write(output_bytes)</code></pre>\n\n<p>本书的示例尝试将字节与字符仔细区分开来。注意在调用两者的<code>repr()</code>方法时的区别：字节串由字母b开始，如<code>b&#39;Hello&#39;</code>；而字符串则没有起始字母，如<code>&#39;world&#39;</code>。为了消除字节串与字符串带来的混淆，Python 3只对字符串类型提供了大量的字符串方法。</p>\n\n<h2>1.7　网际协议</h2>\n\n<p><strong>网络互联</strong>（networking）指的是通过物理链路将多台计算机连接，使之可以相互通信。而<strong>网际互联</strong>（internetworking）指的是将相邻的物理网络相连，使之形成更大的网络系统，比如互联网。但这两者本质上都是允许资源共享的精心设计的机制。</p>\n\n<p>当然，计算机中的各种各样的资源都需要被共享。磁盘、内存以及CPU都由操作系统进行精密的监控，这样一来，计算机中运行的独立程序就可以互不影响地访问这些资源。而网络则是操作系统需要保护的另一资源，这使得程序间进行通信时不会干涉同一网络中正在进行的其他通信会话。</p>\n\n<p>计算机使用诸如以太网卡、无线发送器以及USB端口等物理网络设备进行相互通信。这些设备都经过了精心设计。多个设备可以共享同一物理媒介，比如12块以太网卡可以插入同一个集线器，30块无线网卡可能共享同一无线信道，DSL调制解调器也使用了频域多路复用。这是电气工程中的一个基本概念。这一技术使得我们在打电话时，电子信号不会被线路中的其他模拟信号干扰。</p>\n\n<p>网络设备间进行共享的基本单元是<strong>数据包</strong>（packet）。数据包就像流通的货币一样，只要有需要，就可以交换。一个数据包是一串长度在几字节到几千字节间的字节串，它是网络设备间进行数据传输的基本单元。专用网络也确实存在，尤其是在电信这样的领域，单个字节在进行传输的时候，可能会被分别路由至不同的地方。尽管如此，用于构建现代计算机电子网络的更通用的技术，都建立在数据包这一基本单元之上。</p>\n\n<p>数据包在物理层通常只有两个属性：包含的字节串数据以及目标传输地址。物理数据包的地址一般是一个唯一的标识符，它标识了在计算机传输数据包的过程中，插入同一以太网段的其他网卡或无线信道。网卡负责发送并接收这样的数据包，使得计算机操作系统不用关心网络是如何处理网线、电压以及信号这些细节的。</p>\n\n<p>那么，什么是网际协议呢？</p>\n\n<p><strong>网际协议</strong>（IP）是为全世界通过互联网连接的计算机赋予统一地址系统的一种机制，它使得数据包能够从互联网的一端发送至另一端。理想情况下，网络浏览器无需了解具体使用哪种网络设备来传输数据包，就能够连接上任意一台主机。</p>\n\n<p>Python程序很少直接操作IP这么底层的协议，但是，至少知道它的具体原理还是对我们有所帮助的。</p>\n\n<h2>1.8　IP地址</h2>\n\n<p>IP的最初版本为连接到万维网的每台计算机分配了一个4字节的地址，通常写为4个由句点分隔的十进制数。每个十进制数表示地址的1字节。因此，每个数的范围是0到255。一个传统的4字节IP地址如下所示：</p>\n\n<pre>\n<code>130.207.244.244</code></pre>\n\n<p>由于纯数字表示的地址不便于记忆，人们使用互联网时常常用<strong>主机名</strong>（hostname）来代替IP地址。用户只要键入google.com就可访问谷歌了，不用去了解其背后的过程。其实，它是将主机名解析到了类似于<code>74.125.67.103</code>的地址，计算机实际上是通过互联网将数据包传输到了该地址。</p>\n\n<p>代码清单1-7所示的getname.py脚本展示了一个简单的Python程序。该程序用来向运行程序的操作系统请求解析主机名www.python.org，它支持Linux、Mac OS、Windows或其他任何系统。这个特定的网络服务叫作域名系统（DNS，Domain Name System）。DNS响应主机名查询的过程是相当复杂的，在第4章中我们将对这一内容作更详细的讨论。</p>\n\n<p><strong>代码清单1-7</strong>　将主机名转换为IP地址</p>\n\n<pre>\n<code>#!/usr/bin/env python3\n# Foundations of Python Network Programming, Third Edition\n# https://github.com/brandon-rhodes/fopnp/blob/m/py3/chapter01/getname.py\n\nimport socket\n\nif __name__ == &#39;__main__&#39;:\n    hostname = &#39;www.python.org&#39;\n    addr = socket.gethostbyname(hostname)\n    print(&#39;The IP address of {} is {}&#39;.format(hostname, addr))</code></pre>\n\n<p>到目前为止，读者只需记住以下两件事即可。</p>\n\n<ul>\n	<li>首先，无论一个互联网应用程序看起来多么新奇，实际上IP协议总是使用数字表示的IP地址来作为数据包传输的目标地址。</li>\n	<li>其次，将主机名解析为IP地址这一复杂的细节是由操作系统来处理的。</li>\n</ul>\n\n<p>操作系统倾向于自己处理IP的多数操作细节，而这些细节对用户及Python代码是不可见的。</p>\n\n<p>实际上，如今地址表示的问题比上述简单的4字节机制要复杂一些。因为全球的4字节IP地址已经开始不够用了，人们又部署了一种名为IPv6的扩展地址机制。它允许使用16字节的地址。这样的地址数量相当庞大，可以在很长时间内满足人们的需求。IPv6的写法与4字节IP地址不同，如下所示：</p>\n\n<pre>\n<code>fe80::fcfd:4aff:fecf:ea4e</code></pre>\n\n<p>然而，只要代码从用户处接收IP地址或主机名，然后将它们传递给网络库来处理，那么就可能永远不需要考虑IPv4与IPv6的区别。运行Python代码的操作系统会知道使用的IP版本，并作出相应的解析。</p>\n\n<p>通常情况下，传统的IP地址可以从左往右读：前1~2字节表示某个机构，接下来的字节通常表示目标机器所在的特定子网，最后一个字节将地址细化至该特定的机器或服务。另外，也有一些特殊的IP地址段，它们有着特殊的意义。</p>\n\n<ul>\n	<li><code>127.*.*.*</code>：以127开头的IP地址是特殊的预留地址段，这一地址段由机器上运行的本地应用程序使用。当Web浏览器、FTP客户端或者Python程序连接到这一地址段中的地址时，其实是在与同一机器上的一些其他服务或程序交互。大多数机器只使用这一地址段中的一个地址：<code>127.0.0.1</code>。这个IP地址被普遍使用，表示&ldquo;运行该程序的机器本身&rdquo;，通常可以通过主机名<code>localhost</code>来访问。</li>\n	<li><code>10.*.*.*</code>、<code>172.16-31.*.*</code>、<code>192.168.*.*</code>：这些IP地址段是为<strong>私有子网</strong>（private subnet）预留的。运营互联网的机构保证过：绝不会把这三个地址段中的任何地址分发给运行服务器或服务的实体公司。因此，在连接互联网时，可以确定，这些地址是没有意义的，它们并不对应可连接的任一主机。所以，如果要构建组织内部网络，可以随意使用这些地址来自由分配内部的IP地址，不需让外网访问这些主机。</li>\n</ul>\n\n<p>我们甚至可能会在自己家里看到这些私有地址。无线路由器或DSL调制解调器经常会把某个私有地址段中的IP地址分配给家用电脑和笔记本，这样就可以把所有的网络流量隐藏起来。而网络服务提供商分配给我们使用的则是另一个&ldquo;真正的&rdquo;IP地址。</p>\n\n<h2>1.9　路由</h2>\n\n<p>一旦应用程序请求操作系统向某一特定IP地址发送数据，操作系统就需要决定如何使用该机器连接的某一物理网络来传输数据。这一决定（根据目的IP地址选择将IP数据包发往何处）就叫作<strong>路由</strong>（routing）。</p>\n\n<p>我们在职业生涯中编写的大部分甚至全部Python代码，都将运行在网络边缘上，会有一个网络接口将程序与互联网相连。对于运行这些程序的机器来说，路由决定就变得相当简单。</p>\n\n<ul>\n	<li>如果IP地址形如<code>127.*.*.*</code>，那么操作系统会知道数据包的目的地址是本机上运行的另一个应用程序。这个数据包甚至不会被传送给物理网络设备，而是直接通过操作系统的内部数据复制转交给另一应用程序。</li>\n	<li>如果目的IP地址与本机处于同一子网，那么可以通过简单地检查本地以太网段、无线信道，或是其他任何网络信息来找到目标主机。然后就可以将数据包发送给本地连接的机器。</li>\n	<li>否则，计算机将数据包转发给一台<strong>网关机器</strong>（gateway machine）。这台机器将本地子网连接至互联网，然后再决定将该数据包发往何处。</li>\n</ul>\n\n<p>当然，路由只是在网络边缘的时候才这么容易，因为此时只需要决定是将数据包留在本地网络，还是将其发送到网络的其他部分。可以想象，对于组成互联网骨干网的专用网络设备来说，路由决定要复杂得多。这种情况下，需要使用交换机将整个网络的各子网相连。在交换机中构建、查询并不断更新详细的路由表，以此来获知发送给谷歌的数据包要走哪条线路，发送给亚马逊的要走哪条，发送给本机的又要走哪条。然而，Python应用程序很少运行在互联网骨干路由器上，所以在实际操作中遇到的情况几乎全是之前概括的简单路由情形。</p>\n\n<p>在前面的章节中，本书没有明确介绍计算机如何确定某个IP地址是属于本地子网，还是通过网关连接的外网。同一子网中的所有主机有着相同的IP地址前缀。为了阐释子网的概念，本书在前缀后加上星号来表示地址的可变部分。当然，实际上，操作系统中实现网络栈的具体逻辑并没有将ASCII星号插入到路由表中，而是通过结合IP地址和掩码来表示子网。掩码指出了某主机属于某子网所需的高位比特匹配数。如果读者记得IP地址的每字节表示8位二进制数，那么就能轻松读懂子网的数字表示。如下所示。</p>\n\n<ul>\n	<li><code>127.0.0.0/8</code>：此模式描述了前面所述的预留给本机的IP地址段，该模式指出地址的前8位（1字节）必须与127匹配，余下的24位（3字节）则可以是任意值。</li>\n	<li><code>192.168.0.0/16</code>：此模式匹配了属于<code>192.168</code>私有地址段的任何IP地址，因为它指出前16位必须完全匹配，而这个32位地址的后16位则可以是任意值。</li>\n	<li><code>192.168.5.0/24</code>：这里明确指定了一个特定的独立子网。这可能是整个互联网上最常见的子网掩码。地址的前3字节都被明确指定了，用来匹配属于该子网的IP地址。属于该子网的机器只有最后1字节（最后8位）不同。这就允许有256个不同的地址。通常来说，<code>.0</code>地址用来表示子网名，<code>.255</code>地址则用作&ldquo;广播数据包&rdquo;的目标地址，&ldquo;广播数据包&rdquo;会被发送到子网内的所有主机（下章中将作介绍）。这样，就有254个地址可以随意分配给计算机。<code>.1</code>地址通常用于连接外网的网关，但有些公司和学校也会选择其他地址。</li>\n</ul>\n\n<p>几乎在所有情况下，我们的Python代码都将直接使用主机操作系统提供的功能，去正确地选择数据包路由。这和之前依靠操作系统来将主机名解析至IP地址是一样的。</p>\n\n<h2>1.10　数据包分组</h2>\n\n<p>最后一个值得提及的IP概念是数据包分组。尽管我们认为操作系统的网络栈已经巧妙地对程序成功隐藏了这一复杂的细节，但是，在互联网的历史上，数据包分组还是引发了大量问题。因此，在这里还是要简单地提一下。</p>\n\n<p>IP支持的数据包极大，最大可至64KB，但是构建于IP网络之上的实际网络设备通常并不支持这么大的数据包，所以分组是相当必要的。例如，以太网只支持1500 B的数据包。因此，网络数据包中包含一个表示&ldquo;不分组&rdquo;（DF，Don&#39;t Fragment）的标记，在源计算机与目的计算机之间的某条物理网络无法容纳太大的数据包时，发送者可以通过这个标记选择是否进行分组。</p>\n\n<ul>\n	<li>如果没有设置DF标记，那么表示允许分组。当数据包的大小超过网络能够容纳的上限时，网关能够将其分为多个小数据包，并进行标记，以表示接收方在接受之后需要将这些小数据包重组为原始大数据包。</li>\n	<li>如果设置了DF标记，那么表示不允许分组。此时如果网络无法容纳数据包，将会丢弃该数据包，并发回一条错误信息。错误信息是由一种特殊信号数据包表示的，这种数据包叫作Internet控制报文协议（ICMP，Internet Control Message Protocol）数据包。发送方在收到错误信息后会尝试将信息分割为较小的数据包，然后重发。</li>\n</ul>\n\n<p>DF标记通常无法由Python程序控制，而是由操作系统来设置。粗略地说，系统通常使用的逻辑是这样的：如果正在进行一个由网络间传输的独立数据报组成的UDP会话（见第2章），那么操作系统不会设置DF标记。这样一来，无论需要传输多少数据，所有数据包都能够到达接收方。反之，如果正在进行的是一个TCP会话（见第3章），而TCP会话是由可能多达成百上千的数据包组成的长数据流，那么操作系统将设置DF标记，这样操作系统就可以选择正确的数据包大小，使得TCP会话顺畅进行。如果不这么做的话，数据包会<strong>在途中</strong>被不断分组，从而使得会话较为低效。</p>\n\n<p>一个互联网子网能够接收的最大数据包叫作<strong>最大传输单元</strong>（MTU，Maximum Transmission Unit）。关于MTU，曾经有个很大的问题，给很多互联网用户造成了麻烦。在20世纪90年代，互联网服务提供商（尤其是提供DSL链路的电话公司）开始使用PPPoE。PPPoE协议对IP数据包进行封装，封装后的大小只有1492B，而不是以太网允许的最大容量1500B。这使得很多网站措手不及，因为他们默认使用大小为1500B的数据包。他们还使用了一种错误的安全措施，阻塞了所有的ICMP数据包。结果，他们的服务器无法接收到任何ICMP错误信息，也就不知道那些未经分组的长达1500B的数据包到达客户的DSL链路时无法兼容。</p>\n\n<p>这个问题引起的症状令人崩溃，小文件和网页的访问都没有问题，Telnet和SSH等交互式协议也都正常，原因在于这两种操作发送的数据包都小于1492B。然而，一旦用户尝试下载一个大文件，或者某个Telnet或SSH命令一次性大量输出了好几个屏幕的信息，那么连接就会被冻结并无法响应。</p>\n\n<p>现在已经很少碰到这个问题了，但仍然可以从中看出底层IP特性是如何影响用户的。因此，在编写或调试网络程序时将IP特性牢记于心是大有裨益的。</p>\n\n<h2>1.11　进一步学习IP</h2>\n\n<p>在余下的章节中，我们将开始学习IP层之上的协议，了解Python的程序如何使用各种基于IP的服务来进行不同类型的网络会话。但如果读者对之前概述的IP工作原理兴趣盎然，并想进一步学习，该怎么办呢？</p>\n\n<p>描述IP的官方资源是由IETF发布的RFC（Requests For Comment）文档。RFC文档精确描述了协议的工作原理。RFC的撰写非常仔细。如果配着一杯浓浓的咖啡，花上几个小时的闲暇时间阅读，那么你就会了解到网际协议工作的每个细节。例如，这里给出了定义网际协议的RFC文档。网址为：</p>\n\n<p><a href=\"http://tools.ietf.org/html/rfc791\">http://tools.ietf.org/html/rfc791</a></p>\n\n<p>我们同样可以从维基百科等通用资源上找到对RFC文档的引用，而RFC文档也经常引用其他RFC文档，这些文档会对某个协议及其工作机制有更详尽的描述。</p>\n\n<p>如果想要系统学习IP及运行于IP之上的其他协议，推荐学习由Kevin R. Fall和W. Richard Stevens共同编著的《TCP/IP详解（卷1）：协议（第2版）》。这是一部经典之作，在篇幅允许的情况下，涵盖了协议操作的所有细节，且描述细致。另外，还有其他一些不错的书，有通识性的网络书籍，也有专门介绍IP网络和路由架设的网络配置（这些事情是在工作中甚至在家使用电脑联网时会遇到的）方面的书籍。</p>\n\n<h2>1.12　小结</h2>\n\n<p>除了最基本的网络服务之外，其他所有服务都构建于更基础的网络功能之上。</p>\n\n<p>在本章的开篇，我们介绍了一个栈。TCP/IP协议（将在第3章介绍）仅仅支持在客户端和服务器之间传输字节串。HTTP协议（见第9章）描述了客户端如何通过TCP/IP建立的连接来请求特定的文档，以及服务器如何响应并提供相应的结果。万维网（见第11章）将获取由HTTP托管的文档所需的指令编码为一个特殊的地址，这个地址称为URL。在服务器需要向客户端返回结构化数据时，标准JSON数据格式是最流行的表示返回文档的格式。在这整个复杂的体系之上，谷歌提供了一个地理编码服务。程序员可以构建一个URL用于请求，而谷歌会返回一个描述地理位置的JSON文档。</p>\n\n<p>每当需要在网络上传输文本信息，或将文本信息以字节的方式存储到磁盘等存储设备上时，都要将字符编码为字节。有多种广为使用的方法可以将字符表示为字节。现代互联网最常用的方法是简单而又有限的ASCII编码以及强大而通用的Unicode系统。其中，UTF-8是尤为常见的Unicode编码方法。可以使用Python的<code>decode()</code>将字节串转换为实际字符。<code>encode()</code>方法则可以用于反向的转换。Python 3做了一项尝试，永远不会自动将字节转换为字符串，原因在于要正确完成这一转换操作，就必须事先知道所使用的编码方法，否则只能靠猜。因此，比起Python 2，使用Python 3编写代码时，需要更多地调用<code>decode()</code>和<code>encode()</code>方法。</p>\n\n<p>由于IP网络帮助应用程序传输数据包，网络管理员、设备供应商和操作系统程序员一起协力为单独的机器分配了IP地址，在机器以及路由器上建立了路由表，并配置了域名系统（第4章）以将IP地址和用户可见的域名关联起来。Python程序员应该知道，每个IP数据包在发往目的地址时，都有自己的传输路径。另外，如果一个数据包超过了传输路径上路由器间一跳的大小限制，那么就可能会对这个数据包进行分组。</p>\n\n<p>在大多数应用程序中，有两种使用IP的基本方法。第一种是，将每个数据包视为独立的信息来使用；另一种则是，请求一个被自动分为多个数据包的数据流。这两种协议分别叫作UDP和TCP。它们分别是本书的第2章和第3章要讨论的主题。</p>\n', '19940', 'publish', '7', '0', '2020-03-13 19:38:22', '2020-03-14 12:06:14');

-- ----------------------------
-- Table structure for book_metadata
-- ----------------------------
DROP TABLE IF EXISTS `book_metadata`;
CREATE TABLE `book_metadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '图书名称',
  `cover` varchar(255) DEFAULT NULL COMMENT '图书封面',
  `description` text COMMENT '图书介绍',
  `words` bigint(20) DEFAULT '0' COMMENT '字数描述',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `deleted` int(11) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_metadata
-- ----------------------------
INSERT INTO `book_metadata` VALUES ('1', 'gfdgx', 'http://192.168.43.178:4000/api/reference-data/source/RDVcppgga69Xwpy-wWZ', 'sdfwefx', null, 'hjtyx', '0');
INSERT INTO `book_metadata` VALUES ('2', 'gfdg', 'http://192.168.43.178:4000/api/reference-data/sources/RDRzUxJw4RtfBhH0Wom.147.jpg', 'sdfwef', null, 'hjty', '0');
INSERT INTO `book_metadata` VALUES ('3', 'gfdg', 'http://192.168.43.178:4000/api/reference-data/sources/RDRzUxJw4RtfBhH0Wom.147.jpg', 'sdfwef', null, 'hjty', '0');
INSERT INTO `book_metadata` VALUES ('4', 'gfdg', 'http://192.168.43.178:4000/api/reference-data/sources/RDRzUxJw4RtfBhH0Wom.147.jpg', 'sdfwef', null, 'hjty', '0');
INSERT INTO `book_metadata` VALUES ('5', 'gfdg', 'http://192.168.43.178:4000/api/reference-data/sources/RDRzUxJw4RtfBhH0Wom.147.jpg', 'sdfwef', null, 'hjty', '0');
INSERT INTO `book_metadata` VALUES ('6', 'gfdg', 'http://192.168.43.178:4000/api/reference-data/sources/RDRzUxJw4RtfBhH0Wom.147.jpg', 'sdfwef', null, 'hjty', '0');
INSERT INTO `book_metadata` VALUES ('7', 'gfdg', 'http://192.168.43.178:4000/api/reference-data/sources/RDRzUxJw4RtfBhH0Wom.147.jpg', 'sdfwef', null, 'hjty', '0');
INSERT INTO `book_metadata` VALUES ('8', 'gfdg', 'http://192.168.43.178:4000/api/reference-data/sources/RDRzUxJw4RtfBhH0Wom.147.jpg', 'sdfwef', null, 'hjty', '0');
INSERT INTO `book_metadata` VALUES ('9', 'Python 网络编程', 'http://192.168.43.178:4000/api/reference-data/source/RDEiz5kuXXccP0vR6QO', '本书针对想要深入理解使用Python来解决网络相关问题或是构建网络应用程序的技术人员，结合实例讲解了网络协议、网络数据及错误、电子邮件、服务器架构和HTTP及Web应用程序等经典话题。具体内容包括：全面介绍Python3提供的SSL支持，异步I/O循环的编写，用Flask框架在Python代码中配置URL，跨站脚本以及跨站请求伪造攻击网站的原理', null, 'sdfsa', '0');
INSERT INTO `book_metadata` VALUES ('10', '垃圾回收', 'http://192.168.43.178:4000/api/reference-data/source/RDxAxKN0tgYnuw50QRB', '介绍 Java 垃圾回收', null, '某某', '0');
INSERT INTO `book_metadata` VALUES ('11', '垃圾回收s', 'http://192.168.43.178:4000/api/reference-data/source/RDxAxKN0tgYnuw50QRB', '介绍 Java 垃圾回收', null, '某某', '0');
INSERT INTO `book_metadata` VALUES ('12', '垃圾回收xxx', 'http://192.168.43.178:4000/api/reference-data/source/RDxAxKN0tgYnuw50QRB', '介绍 Java 垃圾回收', null, '某某', '0');
INSERT INTO `book_metadata` VALUES ('13', '垃圾回收ss', 'http://192.168.43.178:4000/api/reference-data/source/RDxAxKN0tgYnuw50QRB', '介绍 Java 垃圾回收\n\n全书分为几个有意思的部分\n', null, '某某d', '0');
INSERT INTO `book_metadata` VALUES ('15', 'ᅠ??鷘?嶾뻗?ᐔ?籇', 'http://example.com', '꩔?????賜툆?辰뼮?弇????儿?㘔챁??‮႒鈟裓譅???????㺏??⽠?샣?쮆???쐾???刈?졨꿗毤⍦?䖿挢픷륧Ⱳ戊???????紟?蜡?꿮⎁㔄?彆鲡????᷎???ཉ?菷?????ៀ翅連?迷?ᶞ????缡?????褓?碑岒ൌ?覍꜎ℳ젟???慬懗??????鮇?픸?厦?㫔?츿넣캨㓌??⃖极????䙯䑱???ﱨ࿘?눖', '0', '?꫶ᢡ䈫찮?', '0');
INSERT INTO `book_metadata` VALUES ('17', '书名', 'http://192.168.43.178:4000/api/reference-data/source/RDfvyvMygBlbFeVg1RP', '描述', '0', '作者', '0');
INSERT INTO `book_metadata` VALUES ('18', '书名', '封面', '描述', '0', '作者', '0');
INSERT INTO `book_metadata` VALUES ('19', '书名', '封面', '描述', '0', '作者', '0');
INSERT INTO `book_metadata` VALUES ('20', '书名', '封面', '描述', '0', '作者', '0');

-- ----------------------------
-- Table structure for book_notes
-- ----------------------------
DROP TABLE IF EXISTS `book_notes`;
CREATE TABLE `book_notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `episode_id` int(11) DEFAULT NULL,
  `ref` longtext,
  `content_type` varchar(255) DEFAULT NULL,
  `content_source` longtext,
  `dommark_start_dom` varchar(255) DEFAULT NULL,
  `dommark_start_offset` varchar(255) DEFAULT NULL,
  `dommark_end_dom` varchar(255) DEFAULT NULL,
  `dommark_end_offset` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int(1) DEFAULT '0' COMMENT '删除标识',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_notes
-- ----------------------------
INSERT INTO `book_notes` VALUES ('2', '7', '17', '内容没有重复。随着在OpenStack、SaltStack和Ansible中的大量应用，Python确实已经在自动化配置领域得到了广泛的应用。尽管如此，如果读者想深入学习配置运维相关的技术，还是应该寻找专门介绍这方面技术的书籍和文档。', 'plaintext', 'fsfsdaf', 'div[2]/p[4]/text()[1]', '66', 'div[2]/p[4]/text()[1]', '183', '2020-03-14 13:34:44', '2020-03-14 13:34:44', '0', '1');
INSERT INTO `book_notes` VALUES ('3', '7', '17', '因此我们可以查询Python包索引（Python Package Index）。这是一个相当优质的资源，可以从中找到全球各地的程序员和机构贡献的各种通用包。读者也同样可以通过所用服务提供商的网站查看其是否提供Python库的接口。除此', 'plaintext', 'Hello', 'div[2]/p[12]/text()[1]', '16', 'div[2]/p[12]/text()[1]', '133', '2020-03-14 13:56:10', '2020-03-14 13:56:10', '0', '1');
INSERT INTO `book_notes` VALUES ('4', '7', '17', '在第9章、第10章和第11章中，读者将学到更多有关HTTP协议的知识。Requests库从谷歌获取结果的具体原理机制其实就是由HTTP提供的。', 'plaintext', 'HTTP', 'div[2]/p[40]/text()[1]', '84', 'div[2]/p[40]/text()[1]', '155', '2020-03-14 13:57:11', '2020-03-14 13:57:11', '0', '1');
INSERT INTO `book_notes` VALUES ('5', '7', '17', 'virtualenv', 'plaintext', '这个是宝贝', 'div[2]/p[19]/text()[1]', '73', 'div[2]/p[19]/text()[1]', '83', '2020-03-14 14:31:00', '2020-03-14 14:31:00', '0', '1');
INSERT INTO `book_notes` VALUES ('6', '7', '17', '192.168.5.0/24：这里明确指定了一个特定的独立子网', 'plaintext', '独立子网', 'div[2]/ul[7]/li[3]/code[1]/text()[1]', '0', 'div[2]/ul[7]/li[3]/text()[1]', '17', '2020-03-14 14:33:48', '2020-03-14 14:33:48', '0', '1');
INSERT INTO `book_notes` VALUES ('7', '7', '17', 'Geocoding ', 'plaintext', 'hellossdfsfs', 'div[2]/p[9]/text()[1]', '71', 'div[2]/p[9]/text()[1]', '81', '2020-03-14 14:35:12', '2020-03-14 14:35:12', '0', '1');
INSERT INTO `book_notes` VALUES ('8', '7', '17', '包的二进制可执行文件现在已经可以使用了', 'plaintext', 'Test', 'div[2]/p[24]/text()[1]', '21', 'div[2]/p[24]/text()[1]', '40', '2020-03-14 14:36:29', '2020-03-14 14:36:29', '0', '1');
INSERT INTO `book_notes` VALUES ('9', '7', '17', 'API', 'plaintext', '应用程序接口', 'div[2]/p[8]/text()[1]', '28', 'div[2]/p[8]/text()[1]', '31', '2020-03-14 14:42:46', '2020-03-14 14:42:46', '0', '1');
INSERT INTO `book_notes` VALUES ('10', '7', '17', 'library', 'plaintext', 'marked', 'div[2]/ul[1]/li[2]/text()[1]', '1', 'div[2]/ul[1]/li[2]/text()[1]', '8', '2020-03-14 14:44:16', '2020-03-14 14:44:16', '0', '1');
INSERT INTO `book_notes` VALUES ('11', '7', '17', 'St', 'plaintext', 'test', 'div[2]/pre[5]/code[1]/text()[1]', '248', 'div[2]/pre[5]/code[1]/text()[1]', '250', '2020-03-14 14:46:15', '2020-03-14 14:46:15', '0', '1');
INSERT INTO `book_notes` VALUES ('12', '7', '17', 'requests', 'plaintext', 'ddd', 'div[2]/pre[3]/code[1]/text()[1]', '359', 'div[2]/pre[3]/code[1]/text()[1]', '367', '2020-03-14 14:50:09', '2020-03-14 14:50:09', '0', '1');
INSERT INTO `book_notes` VALUES ('13', '7', '17', '包尚未安装', 'plaintext', 'fsss', 'div[2]/p[23]/text()[1]', '15', 'div[2]/p[23]/text()[1]', '20', '2020-03-14 14:51:38', '2020-03-14 14:51:38', '0', '1');
INSERT INTO `book_notes` VALUES ('16', '7', '17', '库也已经安装在虚拟环境中了', 'plaintext', 'Hellosdfjldasjfldsjflasjfljaofpj;sdkfwe', 'div[2]/p[31]/text()[1]', '15', 'div[2]/p[31]/text()[1]', '28', '2020-03-14 15:07:47', '2020-03-14 15:07:47', '1', '1');
INSERT INTO `book_notes` VALUES ('17', '7', '17', '库也已经安装在虚拟环境中了', 'plaintext', 'Hello', 'div[2]/p[31]/text()[2]', '15', 'div[2]/p[31]/text()[2]', '28', '2020-03-14 15:29:22', '2020-03-14 15:29:22', '0', '1');

-- ----------------------------
-- Table structure for book_shelf
-- ----------------------------
DROP TABLE IF EXISTS `book_shelf`;
CREATE TABLE `book_shelf` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书架id',
  `name` varchar(255) DEFAULT NULL COMMENT '书架名称',
  `owner_id` int(11) DEFAULT NULL COMMENT '书架所有者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_shelf
-- ----------------------------
INSERT INTO `book_shelf` VALUES ('1', 'default', '1');
INSERT INTO `book_shelf` VALUES ('2', 'default', '3');

-- ----------------------------
-- Table structure for book_shelf_cell
-- ----------------------------
DROP TABLE IF EXISTS `book_shelf_cell`;
CREATE TABLE `book_shelf_cell` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书架书籍id',
  `book_id` int(11) DEFAULT NULL COMMENT '图书id',
  `shelf_id` int(11) DEFAULT NULL COMMENT '书架id',
  `history_id` int(11) DEFAULT NULL COMMENT '阅读历史Id',
  `finished` tinyint(4) DEFAULT '0' COMMENT '是否读完（0:未读完，1:已读完）',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标识（0：未删除,1：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_shelf_cell
-- ----------------------------
INSERT INTO `book_shelf_cell` VALUES ('1', '1', '1', '2', '0', '0');
INSERT INTO `book_shelf_cell` VALUES ('3', '19', '1', '3', '0', '0');
INSERT INTO `book_shelf_cell` VALUES ('4', '1', '2', '2', '0', '0');

-- ----------------------------
-- Table structure for book_tags
-- ----------------------------
DROP TABLE IF EXISTS `book_tags`;
CREATE TABLE `book_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL COMMENT '图书id',
  `tag_id` int(11) DEFAULT NULL COMMENT '标签id',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_tags
-- ----------------------------
INSERT INTO `book_tags` VALUES ('1', '1', '15', '0');
INSERT INTO `book_tags` VALUES ('2', '2', '15', '0');
INSERT INTO `book_tags` VALUES ('3', '3', '15', '0');
INSERT INTO `book_tags` VALUES ('4', '4', '15', '0');
INSERT INTO `book_tags` VALUES ('5', '5', '15', '0');
INSERT INTO `book_tags` VALUES ('6', '6', '15', '0');
INSERT INTO `book_tags` VALUES ('7', '7', '23', '0');
INSERT INTO `book_tags` VALUES ('8', '8', '24', '0');
INSERT INTO `book_tags` VALUES ('9', '9', '25', '0');
INSERT INTO `book_tags` VALUES ('10', '10', '26', '0');
INSERT INTO `book_tags` VALUES ('11', '13', '20', '0');
INSERT INTO `book_tags` VALUES ('12', '13', '15', '0');
INSERT INTO `book_tags` VALUES ('13', '13', '21', '0');
INSERT INTO `book_tags` VALUES ('14', '14', '17', '0');
INSERT INTO `book_tags` VALUES ('15', '14', '24', '0');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `name` varchar(255) DEFAULT NULL COMMENT '类别名称',
  `description` tinytext COMMENT '类别描述',
  `extra_url` varchar(255) DEFAULT NULL COMMENT '专辑封面',
  `extra_aside_title` varchar(255) DEFAULT NULL COMMENT '侧栏标题',
  `type` varchar(255) DEFAULT 'category' COMMENT '类别类型（category: 类别，collection: 榜单）',
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Test category', 'Test Category', '/assets/huid.png', 'Test Aside Title', 'category', '1');
INSERT INTO `category` VALUES ('2', 'Test category', 'Test Category', null, null, 'category', '1');
INSERT INTO `category` VALUES ('3', 'Test category', 'Test Category', null, null, 'category', '1');
INSERT INTO `category` VALUES ('5', 'Test category', 'Test Category', null, null, 'category', '1');
INSERT INTO `category` VALUES ('6', 'Test category', 'Test Category', null, null, 'category', '1');
INSERT INTO `category` VALUES ('18', '电子书:言情小说', '言情小说是中国旧体小说的一种，又称狭邪小说或才子佳人小说。以讲述异性相爱为中心，通过完整的故事情节和具体的环境描写来反映爱情的心理、状态、事物等社会生活的一种文学体裁。', null, null, 'collection', '0');
INSERT INTO `category` VALUES ('19', 'dssd', 'fsfds', null, null, 'category', '0');
INSERT INTO `category` VALUES ('20', 'dssd', 'fsfds', null, null, 'category', '0');
INSERT INTO `category` VALUES ('21', 'dssd', 'fsfds', null, null, 'category', '0');
INSERT INTO `category` VALUES ('22', 'dssd', 'fsfds', null, null, 'category', '0');
INSERT INTO `category` VALUES ('23', 'dssd', 'fsfds', null, null, 'category', '0');
INSERT INTO `category` VALUES ('24', 'fdgfdg', 's', null, null, 'category', '0');
INSERT INTO `category` VALUES ('25', 'sdfa', 'efsdaf', null, null, 'category', '0');
INSERT INTO `category` VALUES ('28', '编程开发', 'IT 互联网开发相关的书籍', null, null, 'category', '0');

-- ----------------------------
-- Table structure for category_tags
-- ----------------------------
DROP TABLE IF EXISTS `category_tags`;
CREATE TABLE `category_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category_tags
-- ----------------------------
INSERT INTO `category_tags` VALUES ('5', '5', '4', '0');
INSERT INTO `category_tags` VALUES ('6', '5', '3', '0');
INSERT INTO `category_tags` VALUES ('7', '6', '4', '0');
INSERT INTO `category_tags` VALUES ('8', '6', '3', '0');
INSERT INTO `category_tags` VALUES ('12', '5', '10', '1');
INSERT INTO `category_tags` VALUES ('16', '18', '14', '0');
INSERT INTO `category_tags` VALUES ('17', '19', '14', '0');
INSERT INTO `category_tags` VALUES ('18', '20', '14', '0');
INSERT INTO `category_tags` VALUES ('19', '21', '14', '0');
INSERT INTO `category_tags` VALUES ('20', '22', '14', '0');
INSERT INTO `category_tags` VALUES ('21', '23', '14', '0');
INSERT INTO `category_tags` VALUES ('22', '24', '16', '0');
INSERT INTO `category_tags` VALUES ('23', '18', '17', '0');
INSERT INTO `category_tags` VALUES ('24', '18', '18', '0');
INSERT INTO `category_tags` VALUES ('25', '18', '19', '0');
INSERT INTO `category_tags` VALUES ('26', '18', '20', '0');
INSERT INTO `category_tags` VALUES ('27', '18', '21', '0');
INSERT INTO `category_tags` VALUES ('28', '25', '22', '0');

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购买项id',
  `name` varchar(255) DEFAULT NULL COMMENT '购买项名称',
  `type` varchar(255) DEFAULT NULL COMMENT '购买项类型',
  `picture` varchar(255) DEFAULT NULL COMMENT '购买项图片',
  `introduction` varchar(255) DEFAULT NULL COMMENT '购买项介绍',
  `rate` float DEFAULT '0' COMMENT '评分',
  `weight` double DEFAULT NULL COMMENT '重量(g)',
  `stock` int(11) DEFAULT NULL COMMENT '初始库存',
  `available_stock` int(11) DEFAULT NULL COMMENT '可用库存',
  `sales` bigint(20) DEFAULT '0' COMMENT '销售数量',
  `shipment` decimal(20,2) DEFAULT NULL COMMENT '运费',
  `extra` varchar(255) DEFAULT NULL COMMENT '额外数据',
  `prices` decimal(20,2) DEFAULT NULL COMMENT '购买项价格',
  `status` varchar(255) DEFAULT NULL COMMENT '购买项状态',
  `deleted` int(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', '购买项', 'material-object', '/assets/empty.png', '这是一个购买项', '3.5', null, '100', '20', '323', '100.00', null, '200.00', 'put-on-sale', '1');
INSERT INTO `commodity` VALUES ('2', '购买项2', 'virtual-product', '/assets/empty.png', '这是一个购买项2', '5.5', null, '100', '4', '123', '10.00', null, '233.00', 'in-stock', '1');
INSERT INTO `commodity` VALUES ('3', '购买项', 'material-object', '/assets/empty.png', '这是一个购买项', '3.5', null, '100', '20', '323', '100.00', null, '200.00', 'put-on-sale', '1');
INSERT INTO `commodity` VALUES ('4', 'Python', 'material-object', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'Python', '0', '500', '100', '76', '0', '23.00', null, '100.00', 'put-on-sale', '0');
INSERT INTO `commodity` VALUES ('5', 'gfdg', 'material-object', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'sdfwef', '0', '500', '100', null, '0', '0.00', null, '0.00', 'put-on-sale', '0');
INSERT INTO `commodity` VALUES ('6', 'gfdg', 'material-object', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'sdfwef', '0', '500', '100', null, '0', '0.00', null, '0.00', 'put-on-sale', '0');
INSERT INTO `commodity` VALUES ('7', 'gfdg', 'material-object', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'sdfwef', '0', '500', '100', null, '0', '0.00', null, '0.00', 'put-on-sale', '0');
INSERT INTO `commodity` VALUES ('8', 'gfdg', 'material-object', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'sdfwef', '0', '500', '100', null, '0', '0.00', null, '0.00', 'in-stock', '0');
INSERT INTO `commodity` VALUES ('9', 'gfdg', 'material-object', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'sdfwef', '0', '500', '100', null, '0', '0.00', null, '0.00', 'in-stock', '0');
INSERT INTO `commodity` VALUES ('10', 'gfdg', 'material-object', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'sdfwef', '0', '500', '100', null, '0', '0.00', null, '0.00', 'in-stock', '0');
INSERT INTO `commodity` VALUES ('11', 'gfdg', 'material-object', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'sdfwef', '0', '500', '100', null, '0', '0.00', null, '0.00', 'in-stock', '0');
INSERT INTO `commodity` VALUES ('12', 'gfdg', 'material-object', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'sdfwef', '0', '500', '100', null, '0', '0.00', null, '0.00', 'put-on-sale', '0');

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '内容id',
  `type` varchar(255) DEFAULT NULL COMMENT '内容类型（topic: 话题, comment: 评论, review: 点评, book: 图书）',
  `owner_id` int(11) DEFAULT NULL COMMENT '拥有者id',
  `comments` int(11) DEFAULT '0' COMMENT '评论数',
  `rate` float DEFAULT '0' COMMENT '评分',
  `likes` bigint(11) DEFAULT '0' COMMENT '点赞数',
  `reposts` bigint(11) DEFAULT '0' COMMENT '转发数',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('1', 'topic', '1', '3', '3.75', '0', '0', '1', '2020-02-23 17:47:25', '2020-02-23 17:47:29');
INSERT INTO `content` VALUES ('4', 'topic', '1', '0', '0', '0', '0', '1', '2020-02-23 21:26:23', '2020-02-23 21:26:23');
INSERT INTO `content` VALUES ('5', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:32:43', '2020-02-23 21:32:43');
INSERT INTO `content` VALUES ('6', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:32:51', '2020-02-23 21:32:51');
INSERT INTO `content` VALUES ('7', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:32:52', '2020-03-13 20:28:02');
INSERT INTO `content` VALUES ('8', 'topic', '1', '0', '0', '0', '0', '1', '2020-02-23 21:32:52', '2020-02-23 21:32:52');
INSERT INTO `content` VALUES ('9', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:32:56', '2020-02-23 21:32:56');
INSERT INTO `content` VALUES ('10', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:32:56', '2020-02-23 21:32:56');
INSERT INTO `content` VALUES ('11', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:34:29', '2020-02-23 21:34:29');
INSERT INTO `content` VALUES ('12', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:35:59', '2020-02-23 21:35:59');
INSERT INTO `content` VALUES ('13', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:35:59', '2020-02-23 21:35:59');
INSERT INTO `content` VALUES ('14', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:00', '2020-02-23 21:36:00');
INSERT INTO `content` VALUES ('15', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:00', '2020-02-23 21:36:00');
INSERT INTO `content` VALUES ('16', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:01', '2020-02-23 21:36:01');
INSERT INTO `content` VALUES ('17', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:01', '2020-02-23 21:36:01');
INSERT INTO `content` VALUES ('18', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:02', '2020-02-23 21:36:02');
INSERT INTO `content` VALUES ('19', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:02', '2020-03-06 19:10:49');
INSERT INTO `content` VALUES ('20', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:03', '2020-02-23 21:36:03');
INSERT INTO `content` VALUES ('21', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:04', '2020-02-23 21:36:04');
INSERT INTO `content` VALUES ('22', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:04', '2020-02-23 21:36:04');
INSERT INTO `content` VALUES ('23', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:05', '2020-02-23 21:36:05');
INSERT INTO `content` VALUES ('24', 'topic', '1', '0', '0', '0', '0', '0', '2020-02-23 21:36:05', '2020-02-23 21:36:05');
INSERT INTO `content` VALUES ('26', 'comment', '1', '0', '0', '0', '0', '0', null, null);
INSERT INTO `content` VALUES ('27', 'comment', '1', '0', '0', '0', '0', '0', null, null);
INSERT INTO `content` VALUES ('28', 'comment', '1', '0', '0', '0', '0', '0', null, null);
INSERT INTO `content` VALUES ('29', 'book', '1', '3', '2', '0', '0', '0', '2020-03-06 16:54:41', '2020-03-06 16:54:41');
INSERT INTO `content` VALUES ('30', 'book', '1', '0', '0', '0', '0', '0', '2020-03-06 16:57:48', '2020-03-06 16:57:48');
INSERT INTO `content` VALUES ('44', 'comment', '1', '1', '5', '0', '0', '0', '2020-03-07 12:56:40', '2020-03-07 12:56:40');
INSERT INTO `content` VALUES ('45', 'comment', '1', '0', '1', '1', '0', '0', '2020-03-07 12:58:35', '2020-03-07 12:58:35');
INSERT INTO `content` VALUES ('46', 'comment', '1', '0', '1', '0', '0', '0', '2020-03-07 12:59:08', '2020-03-07 12:59:08');
INSERT INTO `content` VALUES ('47', 'comment', '1', '0', '0', '1', '0', '0', '2020-03-07 12:59:27', '2020-03-07 12:59:27');
INSERT INTO `content` VALUES ('48', 'comment', '1', '0', '4.5', '0', '0', '0', '2020-03-07 17:02:16', '2020-03-07 17:02:16');
INSERT INTO `content` VALUES ('49', 'book', '1', '0', '0', '0', '0', '0', '2020-03-07 18:16:21', '2020-03-07 18:16:21');
INSERT INTO `content` VALUES ('50', 'book', '1', '0', '0', '0', '0', '0', '2020-03-08 17:35:37', '2020-03-08 17:35:37');
INSERT INTO `content` VALUES ('51', 'comment', '1', '0', '3.5', '0', '0', '0', '2020-03-08 23:42:45', '2020-03-08 23:42:45');
INSERT INTO `content` VALUES ('52', 'comment', '1', '0', '3.5', '0', '0', '0', '2020-03-08 23:43:36', '2020-03-08 23:43:36');

-- ----------------------------
-- Table structure for content_article
-- ----------------------------
DROP TABLE IF EXISTS `content_article`;
CREATE TABLE `content_article` (
  `content_id` int(11) NOT NULL COMMENT '内容id',
  `target_content_id` int(11) DEFAULT NULL COMMENT '内容对内容id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `words` bigint(20) DEFAULT NULL COMMENT '字数',
  `content_type` varchar(255) DEFAULT NULL COMMENT '内容源类型（html: HTML代码, plaintext: 纯文本）',
  `content_source` mediumtext COMMENT '内容源',
  `status` varchar(255) DEFAULT NULL COMMENT '内容状态',
  `reads` bigint(20) DEFAULT '0' COMMENT '阅读数',
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of content_article
-- ----------------------------
INSERT INTO `content_article` VALUES ('1', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'publish', '0');
INSERT INTO `content_article` VALUES ('4', null, null, '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('5', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('6', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'examining', '0');
INSERT INTO `content_article` VALUES ('7', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'examining', '0');
INSERT INTO `content_article` VALUES ('8', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'examining', '0');
INSERT INTO `content_article` VALUES ('9', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'examining', '0');
INSERT INTO `content_article` VALUES ('10', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'examining', '0');
INSERT INTO `content_article` VALUES ('11', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('12', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('13', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('14', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('15', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'examining', '0');
INSERT INTO `content_article` VALUES ('16', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('17', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('18', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('19', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('20', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('21', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('22', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('23', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('24', null, '大家可以推荐一些关于成长的书吗？', '100', 'html', '啊啊啊啊，想看关于成长的书', 'draft', '0');
INSERT INTO `content_article` VALUES ('26', '312', null, '100', 'plaintext', 'hello this is a comments', 'examining', '0');
INSERT INTO `content_article` VALUES ('27', null, null, null, 'plaintext', 'hello this is a comments', 'examining', '0');
INSERT INTO `content_article` VALUES ('28', null, null, null, 'plaintext', 'hello this is a comments', 'examining', '0');
INSERT INTO `content_article` VALUES ('42', '29', null, null, 'html', '差评', 'publish', '0');
INSERT INTO `content_article` VALUES ('43', '29', null, null, 'html', 'sfds', 'publish', '0');
INSERT INTO `content_article` VALUES ('44', '29', null, null, 'html', 'Hello', 'publish', '0');
INSERT INTO `content_article` VALUES ('45', '29', null, null, 'html', '差评', 'publish', '0');
INSERT INTO `content_article` VALUES ('46', '29', null, null, 'html', '啦啦啦啦', 'publish', '0');
INSERT INTO `content_article` VALUES ('47', '44', null, null, 'html', 'bilbil', 'publish', '0');
INSERT INTO `content_article` VALUES ('48', '1', null, null, 'html', '好评', 'publish', '0');
INSERT INTO `content_article` VALUES ('51', '1', null, null, 'plaintext', 'Hello?', 'publish', '0');
INSERT INTO `content_article` VALUES ('52', '1', null, null, 'plaintext', 'Hello?', 'publish', '0');

-- ----------------------------
-- Table structure for content_examination
-- ----------------------------
DROP TABLE IF EXISTS `content_examination`;
CREATE TABLE `content_examination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `target_content_id` int(11) DEFAULT NULL,
  `from_status` varchar(255) DEFAULT NULL,
  `to_status` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of content_examination
-- ----------------------------

-- ----------------------------
-- Table structure for content_likes
-- ----------------------------
DROP TABLE IF EXISTS `content_likes`;
CREATE TABLE `content_likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '点赞用户',
  `content_id` int(11) DEFAULT NULL COMMENT '点赞内容',
  `liked` tinyint(1) DEFAULT NULL COMMENT '点赞（1：已点赞，0：取消点赞）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of content_likes
-- ----------------------------
INSERT INTO `content_likes` VALUES ('1', '1', '31', '0');
INSERT INTO `content_likes` VALUES ('2', '1', '32', '0');
INSERT INTO `content_likes` VALUES ('3', '1', '33', '0');
INSERT INTO `content_likes` VALUES ('4', '1', '39', '1');
INSERT INTO `content_likes` VALUES ('5', '1', '34', '0');
INSERT INTO `content_likes` VALUES ('6', '1', '36', '0');
INSERT INTO `content_likes` VALUES ('7', '1', '47', '1');
INSERT INTO `content_likes` VALUES ('8', '1', '45', '1');
INSERT INTO `content_likes` VALUES ('9', '1', '46', '0');
INSERT INTO `content_likes` VALUES ('10', '1', '989579', '0');
INSERT INTO `content_likes` VALUES ('11', '1', '877266', '1');
INSERT INTO `content_likes` VALUES ('12', '1', '714151', '0');
INSERT INTO `content_likes` VALUES ('13', '1', '52', '0');

-- ----------------------------
-- Table structure for content_mention
-- ----------------------------
DROP TABLE IF EXISTS `content_mention`;
CREATE TABLE `content_mention` (
  `content_id` int(11) NOT NULL,
  `mention_user_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of content_mention
-- ----------------------------

-- ----------------------------
-- Table structure for content_reference
-- ----------------------------
DROP TABLE IF EXISTS `content_reference`;
CREATE TABLE `content_reference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL COMMENT '引用类型（content: 内容, book: 图书）',
  `ref_id` varchar(255) DEFAULT NULL COMMENT '图书id 或者 内容id',
  `content_id` int(11) DEFAULT NULL COMMENT '内容id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of content_reference
-- ----------------------------
INSERT INTO `content_reference` VALUES ('1', 'book', '1', '11');
INSERT INTO `content_reference` VALUES ('2', 'book', '2', '11');
INSERT INTO `content_reference` VALUES ('3', 'book', '1', '12');
INSERT INTO `content_reference` VALUES ('4', 'book', '2', '12');
INSERT INTO `content_reference` VALUES ('5', 'book', '1', '13');
INSERT INTO `content_reference` VALUES ('6', 'book', '2', '13');
INSERT INTO `content_reference` VALUES ('9', 'book', '1', '15');
INSERT INTO `content_reference` VALUES ('10', 'book', '2', '15');
INSERT INTO `content_reference` VALUES ('11', 'book', '1', '16');
INSERT INTO `content_reference` VALUES ('12', 'book', '2', '16');
INSERT INTO `content_reference` VALUES ('13', 'book', '1', '17');
INSERT INTO `content_reference` VALUES ('14', 'book', '2', '17');
INSERT INTO `content_reference` VALUES ('15', 'book', '1', '18');
INSERT INTO `content_reference` VALUES ('16', 'book', '2', '18');
INSERT INTO `content_reference` VALUES ('17', 'book', '1', '19');
INSERT INTO `content_reference` VALUES ('18', 'book', '2', '19');
INSERT INTO `content_reference` VALUES ('19', 'book', '1', '20');
INSERT INTO `content_reference` VALUES ('20', 'book', '2', '20');
INSERT INTO `content_reference` VALUES ('21', 'book', '1', '21');
INSERT INTO `content_reference` VALUES ('22', 'book', '2', '21');
INSERT INTO `content_reference` VALUES ('23', 'book', '1', '22');
INSERT INTO `content_reference` VALUES ('24', 'book', '2', '22');
INSERT INTO `content_reference` VALUES ('25', 'book', '1', '23');
INSERT INTO `content_reference` VALUES ('26', 'book', '2', '23');
INSERT INTO `content_reference` VALUES ('27', 'book', '1', '24');
INSERT INTO `content_reference` VALUES ('28', 'book', '2', '24');
INSERT INTO `content_reference` VALUES ('29', 'book', '1', '14');
INSERT INTO `content_reference` VALUES ('30', 'book', '2', '14');
INSERT INTO `content_reference` VALUES ('31', 'book', '3', '13');
INSERT INTO `content_reference` VALUES ('39', 'book', '3', '14');

-- ----------------------------
-- Table structure for content_tags
-- ----------------------------
DROP TABLE IF EXISTS `content_tags`;
CREATE TABLE `content_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `deleted` int(1) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of content_tags
-- ----------------------------

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '阅读用户',
  `book_id` int(11) DEFAULT NULL COMMENT '图书id',
  `audio_episode_id` int(11) DEFAULT NULL COMMENT '有声书章节id',
  `episode_id` int(11) DEFAULT NULL COMMENT '章节id',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `progress` int(11) DEFAULT NULL COMMENT '阅读进度',
  `deleted` int(11) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES ('1', '1', '18', null, '1', '2020-03-06 13:06:08', null, '0');
INSERT INTO `history` VALUES ('2', '3', '1', null, null, null, '0', '0');
INSERT INTO `history` VALUES ('3', '1', '19', null, null, null, '0', '0');

-- ----------------------------
-- Table structure for logistics_information
-- ----------------------------
DROP TABLE IF EXISTS `logistics_information`;
CREATE TABLE `logistics_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `express_number` varchar(255) DEFAULT NULL COMMENT '快递单号',
  `express_company` varchar(255) DEFAULT NULL COMMENT '快递公司',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of logistics_information
-- ----------------------------
INSERT INTO `logistics_information` VALUES ('1', '4324452423', '圆通');

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `sender_id` int(11) DEFAULT NULL COMMENT '发送者',
  `receiver_id` int(11) DEFAULT NULL COMMENT '接收者',
  `type` varchar(255) DEFAULT NULL COMMENT '通知类型（subscribe: 订阅通知, message: 消息）',
  `message` tinytext COMMENT '通知内容',
  `link` varchar(255) DEFAULT NULL COMMENT '通知交互链接',
  `read` tinyint(1) DEFAULT '0' COMMENT '已读标识（0: 未读, 1:已读）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES ('1', '1', '1', 'message', 'Hello world', null, '1', '2020-03-11 13:02:46', '2020-03-11 13:02:49', '0');

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL COMMENT '操作类型（modify: 修改, create: 创建, delete: 删除）',
  `describe` varchar(255) DEFAULT NULL COMMENT '描述文字',
  `target_type` varchar(255) DEFAULT NULL COMMENT '操作目标类型',
  `target_id` int(11) DEFAULT NULL COMMENT '操作目标id',
  `level` varchar(255) DEFAULT NULL COMMENT '操作等级（admin: 管理员, user: 用户, anonymous: 匿名用户）',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作者id',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of operation
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_number` char(255) NOT NULL,
  `owner_id` int(11) DEFAULT NULL COMMENT '订单所有者id',
  `type` varchar(255) DEFAULT NULL COMMENT '订单类型（electronic-book: 购买电子书, audio-book: 有声书, paper-book: 纸质书, recharge: 充值）',
  `pay_type` varchar(255) DEFAULT NULL COMMENT '支付类型（wechat: 微信, alipay: 支付宝）',
  `address_id` int(11) DEFAULT NULL COMMENT '收货地址id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `deliver_time` datetime DEFAULT NULL COMMENT '发货时间',
  `closing_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `total_money` bigint(20) DEFAULT NULL COMMENT '总金额',
  `shipment_money` bigint(20) DEFAULT NULL COMMENT '运费',
  `status` varchar(255) DEFAULT NULL COMMENT '订单状态（awaiting-payment: 待支付, awaiting-shipment: 待发货, awaiting-delivery: 待收货, awaiting-evaluation: 待评价, canceled: 订单取消, completed: 交易完成）',
  `logistics_information_id` varchar(255) DEFAULT NULL COMMENT '物流信息id',
  `address_region` varchar(255) DEFAULT NULL,
  `address_address` varchar(255) DEFAULT NULL,
  `address_receiver_name` varchar(255) DEFAULT NULL,
  `address_receiver_phone` varchar(255) DEFAULT NULL,
  `address_postcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('821000012020022620722938', '1', 'electronic-book', null, '1', '2020-02-26 20:38:43', null, null, null, '400', '0', 'awaiting-payment', null, '广东省 广州市 从化区', '太平镇广从南路123号', '黄林', '15820239553', '510530');
INSERT INTO `order` VALUES ('821000012020022624461150', '1', 'electronic-book', null, '1', '2020-02-26 21:41:01', null, null, null, '800', '400', 'awaiting-payment', null, '广东省 广州市 从化区', '太平镇广从南路123号', '黄林', '15820239553', '510530');
INSERT INTO `order` VALUES ('821000012020022797762146', '1', 'electronic-book', 'huidu', '1', '2020-02-27 18:02:42', '2020-02-27 20:21:04', null, null, '800', '400', 'awaiting-delivery', '1', '广东省 广州市 从化区', '太平镇广从南路123号', '黄林', '15820239553', '510530');
INSERT INTO `order` VALUES ('821000012020030776179257', '1', 'electronic-book', null, '1', '2020-03-07 18:16:19', null, null, null, '800', '400', 'awaiting-payment', null, '广东省 广州市 从化区', '太平镇广从南路123号', '黄林', '15820239553', '510530');
INSERT INTO `order` VALUES ('821000012020030776391387', '1', 'electronic-book', null, '1', '2020-03-07 18:19:51', null, null, null, '800', '400', 'awaiting-payment', null, '广东省 广州市 从化区', '太平镇广从南路123号', '黄林', '15820239553', '510530');
INSERT INTO `order` VALUES ('821000012020030776701536', '1', 'electronic-book', null, '1', '2020-03-07 18:25:01', null, null, null, '800', '400', 'awaiting-payment', null, '广东省 广州市 从化区', '太平镇广从南路123号', '黄林', '15820239553', '510530');
INSERT INTO `order` VALUES ('821000012020030776920693', '1', 'electronic-book', null, '1', '2020-03-07 18:28:40', null, null, null, '800', '400', 'awaiting-payment', null, '广东省 广州市 从化区', '太平镇广从南路123号', '黄林', '15820239553', '510530');
INSERT INTO `order` VALUES ('821000012020030860136104', '1', 'electronic-book', null, '1', '2020-03-08 17:35:36', null, null, null, '800', '400', 'awaiting-payment', null, '广东省 广州市 从化区', '太平镇广从南路123号', '黄林', '15820239553', '510530');
INSERT INTO `order` VALUES ('823010012020031200088004', '1', 'recharge', 'wechat', null, '2020-03-12 16:01:28', '2020-03-12 16:01:30', null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031200266410', '1', 'recharge', 'wechat', null, '2020-03-12 16:04:26', '2020-03-12 16:04:28', null, null, '100', '0', 'awaiting-shipment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031200506901', '1', 'recharge', 'wechat', null, '2020-03-12 16:08:27', '2020-03-12 16:08:28', null, null, '100', '0', 'completed', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031201093484', '1', 'recharge', 'wechat', null, '2020-03-12 16:18:13', '2020-03-12 16:18:15', null, null, '100', '0', 'completed', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031201220760', '1', 'recharge', 'wechat', null, '2020-03-12 16:20:21', '2020-03-12 16:20:22', null, null, '100', '0', 'completed', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031292842359', '1', 'recharge', null, null, '2020-03-12 14:00:42', null, null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031293235226', '1', 'recharge', null, null, '2020-03-12 14:07:15', null, null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031293743895', '1', 'recharge', null, null, '2020-03-12 14:15:44', null, null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031295636891', '1', 'recharge', null, null, '2020-03-12 14:47:17', null, null, null, '300', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031295832175', '1', 'recharge', 'wechat', null, '2020-03-12 14:50:32', '2020-03-12 14:51:39', null, null, '3000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031295963804', '1', 'recharge', 'wechat', null, '2020-03-12 14:52:44', '2020-03-12 14:54:47', null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031296199926', '1', 'recharge', null, null, '2020-03-12 14:56:40', null, null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031296286043', '1', 'recharge', null, null, '2020-03-12 14:58:06', null, null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031296344765', '1', 'recharge', null, null, '2020-03-12 14:59:05', null, null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031296581273', '1', 'recharge', 'wechat', null, '2020-03-12 15:03:01', '2020-03-12 15:03:21', null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031296834648', '1', 'recharge', 'wechat', null, '2020-03-12 15:07:15', '2020-03-12 15:07:20', null, null, '50000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297080024', '1', 'recharge', 'wechat', null, '2020-03-12 15:11:20', '2020-03-12 15:12:51', null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297171089', '1', 'recharge', null, null, '2020-03-12 15:12:51', null, null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297171109', '1', 'recharge', 'wechat', null, '2020-03-12 15:12:51', '2020-03-12 15:12:57', null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297341922', '1', 'recharge', null, null, '2020-03-12 15:15:42', null, null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297357178', '1', 'recharge', null, null, '2020-03-12 15:15:57', null, null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297676878', '1', 'recharge', null, null, '2020-03-12 15:21:17', null, null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297684582', '1', 'recharge', null, null, '2020-03-12 15:21:25', null, null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297766025', '1', 'recharge', 'wechat', null, '2020-03-12 15:22:46', '2020-03-12 15:22:48', null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297800280', '1', 'recharge', 'wechat', null, '2020-03-12 15:23:20', '2020-03-12 15:23:22', null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297874111', '1', 'recharge', null, null, '2020-03-12 15:24:34', null, null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031297880904', '1', 'recharge', 'wechat', null, '2020-03-12 15:24:41', '2020-03-12 15:24:43', null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031298043869', '1', 'recharge', 'wechat', null, '2020-03-12 15:27:24', '2020-03-12 15:29:09', null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031298379571', '1', 'recharge', 'wechat', null, '2020-03-12 15:32:59', '2020-03-12 15:33:02', null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031298397889', '1', 'recharge', null, null, '2020-03-12 15:33:18', null, null, null, '30000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031298715678', '1', 'recharge', null, null, '2020-03-12 15:38:36', null, null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031299056582', '1', 'recharge', 'wechat', null, '2020-03-12 15:44:17', '2020-03-12 15:44:18', null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031299064017', '1', 'recharge', 'wechat', null, '2020-03-12 15:44:24', '2020-03-12 15:44:25', null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031299074510', '1', 'recharge', 'wechat', null, '2020-03-12 15:44:35', '2020-03-12 15:44:36', null, null, '30000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031299322986', '1', 'recharge', 'wechat', null, '2020-03-12 15:48:43', '2020-03-12 15:48:45', null, null, '50000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031299384759', '1', 'recharge', 'wechat', null, '2020-03-12 15:49:45', '2020-03-12 15:49:46', null, null, '300', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031299390144', '1', 'recharge', null, null, '2020-03-12 15:49:50', null, null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031299396238', '1', 'recharge', 'wechat', null, '2020-03-12 15:49:56', '2020-03-12 15:49:57', null, null, '10000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031299401245', '1', 'recharge', 'wechat', null, '2020-03-12 15:50:01', '2020-03-12 15:50:04', null, null, '3000', '0', 'awaiting-payment', null, null, null, null, null, null);
INSERT INTO `order` VALUES ('823010012020031299406398', '1', 'recharge', 'wechat', null, '2020-03-12 15:50:06', '2020-03-12 15:50:08', null, null, '100', '0', 'awaiting-payment', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品id',
  `quantity` int(255) DEFAULT NULL COMMENT '数量',
  `prices` decimal(20,2) DEFAULT NULL COMMENT '小计',
  `order_number` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `commodity_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `commodity_picture` varchar(255) DEFAULT NULL COMMENT '商品封面',
  `commodity_introduction` varchar(255) DEFAULT NULL COMMENT '商品简介',
  `commodity_weight` double DEFAULT NULL,
  `commodity_type` varchar(255) DEFAULT NULL,
  `commodity_prices` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES ('1', '4', '4', '400.00', '821000012020022615381546', 'Python', 'http://192.168.43.178:4000/api/reference-data/sources/RDA4b2gNuagsCIscF_l.jpg', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('2', '4', '4', '400.00', '821000012020022619638722', 'Python', 'http://192.168.43.178:4000/api/reference-data/sources/RDA4b2gNuagsCIscF_l.jpg', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('3', '4', '4', '400.00', '821000012020022619773075', 'Python', 'http://192.168.43.178:4000/api/reference-data/sources/RDA4b2gNuagsCIscF_l.jpg', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('4', '4', '4', '400.00', '821000012020022619971183', 'Python', 'http://192.168.43.178:4000/api/reference-data/sources/RDA4b2gNuagsCIscF_l.jpg', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('6', '4', '4', '400.00', '821000012020022620722938', 'Python', 'http://192.168.43.178:4000/api/reference-data/sources/RDA4b2gNuagsCIscF_l.jpg', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('7', '4', '4', '400.00', '821000012020022624461150', 'Python', 'http://192.168.43.178:4000/api/reference-data/sources/RDA4b2gNuagsCIscF_l.jpg', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('8', '4', '4', '400.00', '821000012020022797762146', 'Python', 'http://192.168.43.178:4000/api/reference-data/sources/RDA4b2gNuagsCIscF_l.jpg', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('9', '4', '4', '400.00', '821000012020030776179257', 'Python', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('10', '4', '4', '400.00', '821000012020030776391387', 'Python', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('11', '4', '4', '400.00', '821000012020030776701536', 'Python', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('12', '4', '4', '400.00', '821000012020030776920693', 'Python', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'Python', '500', 'material-object', '100.00');
INSERT INTO `order_details` VALUES ('13', '4', '4', '400.00', '821000012020030860136104', 'Python', 'http://192.168.43.178:4000/api/reference-data/source/RDTYQ9deoOb7ujHP7hL', 'Python', '500', 'material-object', '100.00');

-- ----------------------------
-- Table structure for passing_point
-- ----------------------------
DROP TABLE IF EXISTS `passing_point`;
CREATE TABLE `passing_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '途经内容',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(255) DEFAULT NULL COMMENT '途经状态（doing: 正在进行, done: 完成）',
  `logistics_infomation_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of passing_point
-- ----------------------------
INSERT INTO `passing_point` VALUES ('1', '快件准备出发', '2020-02-28 16:14:36', 'done', '1', '2020-02-28 15:54:23');
INSERT INTO `passing_point` VALUES ('17', 'Test2', '2020-02-28 17:29:49', 'done', '1', '2020-02-28 17:22:52');
INSERT INTO `passing_point` VALUES ('18', 'Test3', '2020-02-28 17:44:17', 'done', '1', '2020-02-28 17:29:24');
INSERT INTO `passing_point` VALUES ('19', 'dfgfdgsd', '2020-02-28 17:44:22', 'done', '1', '2020-02-28 17:42:20');
INSERT INTO `passing_point` VALUES ('20', '234234324534', '2020-02-28 19:30:00', 'done', '1', '2020-02-28 19:30:00');
INSERT INTO `passing_point` VALUES ('21', 'fasfegart32qref', '2020-02-28 19:30:00', 'done', '1', '2020-02-28 19:30:00');
INSERT INTO `passing_point` VALUES ('22', 'afsaf3rtaewfadsf', '2020-02-28 19:30:00', 'done', '1', '2020-02-28 19:30:00');
INSERT INTO `passing_point` VALUES ('23', 'ferq235resfsa345q234df', '2020-02-28 19:30:00', 'done', '1', '2020-02-28 19:30:00');
INSERT INTO `passing_point` VALUES ('24', 'sa5435redgsrty4e5t34', '2020-02-28 19:30:00', 'done', '1', '2020-02-28 19:30:00');
INSERT INTO `passing_point` VALUES ('25', 'sa3q23trfawrdsaf', '2020-02-28 19:30:00', 'doing', '1', '2020-02-28 19:30:00');

-- ----------------------------
-- Table structure for reference_data
-- ----------------------------
DROP TABLE IF EXISTS `reference_data`;
CREATE TABLE `reference_data` (
  `reference_id` char(20) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_original_name` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `uploaded_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `file_path` text,
  `status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`reference_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reference_data
-- ----------------------------
INSERT INTO `reference_data` VALUES ('RD-k94QVEusnuIBYmr-', 'RD-k94QVEusnuIBYmr-..mp3', '『クローバー』Instrumental ver..mp3', '3870254', 'audio/mp3', '2020-03-02 17:28:36', '.\\upload\\RD-k94QVEusnuIBYmr-..mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RD-yDjPXDSA-Sujxqky', 'RD-yDjPXDSA-Sujxqky.mp3', 'Evan Call - Sneaky Little Creatures.mp3', '4406998', 'audio/mp3', '2020-03-02 19:12:15', '.\\upload\\RD-yDjPXDSA-Sujxqky.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RD3pjbQBsbHJeInkusM', 'RD3pjbQBsbHJeInkusM.jpg', '3_zf.jpg', '14501', 'image/jpeg', '2020-03-01 20:45:08', '.\\upload\\RD3pjbQBsbHJeInkusM.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RD4x_5kqBi41zTKq_1N', 'RD4x_5kqBi41zTKq_1N.147.jpg', '926.147.jpg', '18004', 'image/jpeg', '2020-03-01 17:08:03', '.\\upload\\RD4x_5kqBi41zTKq_1N.147.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RD6xVtE0r8aztN31PRk', 'RD6xVtE0r8aztN31PRk.png', '5.png', '26181', 'image/png', '2020-03-04 17:05:01', '.\\upload\\RD6xVtE0r8aztN31PRk.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RD7abwCVIKBNX_uKZNC', 'RD7abwCVIKBNX_uKZNC.mp3', 'Evan Call - Fancy Beetle.mp3', '3951389', 'audio/mp3', '2020-03-02 16:53:06', '.\\upload\\RD7abwCVIKBNX_uKZNC.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RD7VKAlUievYkrK5Pu7', 'RD7VKAlUievYkrK5Pu7.mp3', 'Evan Call - Back in The Day.mp3', '4111268', 'audio/mp3', '2020-03-05 18:15:50', '.\\upload\\RD7VKAlUievYkrK5Pu7.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RD8jk_j-qoTPFXu-mM6', 'RD8jk_j-qoTPFXu-mM6.mp3', 'Evan Call - No Shoes, No Problem.mp3', '4514616', 'audio/mp3', '2020-03-02 18:58:21', '.\\upload\\RD8jk_j-qoTPFXu-mM6.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RD9yp4fW3-_3eI6aO-C', 'RD9yp4fW3-_3eI6aO-C.147.jpg', '926.147.jpg', '18004', 'image/jpeg', '2020-03-01 22:34:21', '.\\upload\\RD9yp4fW3-_3eI6aO-C.147.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDAJf0WRj6QZSJTM2PS', 'RDAJf0WRj6QZSJTM2PS.mp3', 'Evan Call - Always At Your Side.mp3', '4740347', 'audio/mp3', '2020-03-02 19:20:56', '.\\upload\\RDAJf0WRj6QZSJTM2PS.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDanZtgQ-AL_Yindfdv', 'RDanZtgQ-AL_Yindfdv.mp3', 'Evan Call - The Sunset Kite.mp3', '4351594', 'audio/mp3', '2020-03-02 19:21:03', '.\\upload\\RDanZtgQ-AL_Yindfdv.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDbwInogcYtmyEJ70FK', 'RDbwInogcYtmyEJ70FK.mp3', 'Evan Call - What Secrets Lie Within.mp3', '4018299', 'audio/mpeg', '2020-03-13 16:09:21', '.\\upload\\RDbwInogcYtmyEJ70FK.mp3', '1', null);
INSERT INTO `reference_data` VALUES ('RDCcI-cHpxU7kp4ZGgA', 'RDCcI-cHpxU7kp4ZGgA.147.jpg', '926.147.jpg', '18004', 'image/jpeg', '2020-03-01 20:22:40', '.\\upload\\RDCcI-cHpxU7kp4ZGgA.147.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDCEiu8WnpjYckxrA7i', 'RDCEiu8WnpjYckxrA7i.mp3', '40㍍P - Initial song.mp3', '9667456', 'audio/mp3', '2020-03-02 16:19:45', '.\\upload\\RDCEiu8WnpjYckxrA7i.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDcriFwf02SvzgwIoqb', 'RDcriFwf02SvzgwIoqb..mp3', '『クローバー』Instrumental ver..mp3', '3870254', 'audio/mp3', '2020-03-02 17:38:21', '.\\upload\\RDcriFwf02SvzgwIoqb..mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDcVMnDgE2yULljv15J', 'RDcVMnDgE2yULljv15J.png', '5.png', '26181', 'image/png', '2020-03-03 16:00:11', '.\\upload\\RDcVMnDgE2yULljv15J.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDd2aiW4EwkkVybSdBK', 'RDd2aiW4EwkkVybSdBK..mp3', '『クローバー』Instrumental ver..mp3', '3870254', 'audio/mp3', '2020-03-02 18:35:13', '.\\upload\\RDd2aiW4EwkkVybSdBK..mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDDmawcRAznuV7VnMFg', 'RDDmawcRAznuV7VnMFg.mp3', 'Evan Call - Lurking in the Night.mp3', '5183351', 'audio/mpeg', '2020-03-13 18:00:54', '.\\upload\\RDDmawcRAznuV7VnMFg.mp3', '1', null);
INSERT INTO `reference_data` VALUES ('RDE372FdRPVPpxl0qMW', 'RDE372FdRPVPpxl0qMW.mp3', 'Evan Call - La Cueca del Mar.mp3', '4532368', 'audio/mp3', '2020-03-05 18:20:17', '.\\upload\\RDE372FdRPVPpxl0qMW.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDEiz5kuXXccP0vR6QO', 'RDEiz5kuXXccP0vR6QO.jpg', '3.jpg', '19433', 'image/jpeg', '2020-03-01 17:23:44', '.\\upload\\RDEiz5kuXXccP0vR6QO.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDeMIBPWj1WVX3OW-y4', 'RDeMIBPWj1WVX3OW-y4.147.jpg', '926.147.jpg', '18004', 'image/jpeg', '2020-03-05 12:23:38', '.\\upload\\RDeMIBPWj1WVX3OW-y4.147.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDEOIcnqVFqJDxIvvvJ', 'RDEOIcnqVFqJDxIvvvJ.mp3', 'Evan Call - Takin\' It Easy.mp3', '4123807', 'audio/mp3', '2020-03-02 16:53:13', '.\\upload\\RDEOIcnqVFqJDxIvvvJ.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDfvyvMygBlbFeVg1RP', 'RDfvyvMygBlbFeVg1RP.jpg', '3_zf.jpg', '14501', 'image/jpeg', '2020-03-06 19:10:41', '.\\upload\\RDfvyvMygBlbFeVg1RP.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDg-mj62LTX7eMwEEw0', 'RDg-mj62LTX7eMwEEw0.147.jpg', '926.147.jpg', '18004', 'image/jpeg', '2020-03-05 18:07:31', '.\\upload\\RDg-mj62LTX7eMwEEw0.147.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDg72r1XIzD1NuGDVuB', 'RDg72r1XIzD1NuGDVuB.png', '5.png', '26181', 'image/png', '2020-03-05 18:04:13', '.\\upload\\RDg72r1XIzD1NuGDVuB.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDgGH1onSR0l-mGXGvf', 'RDgGH1onSR0l-mGXGvf.mp3', 'Evan Call - Stargazing.mp3', '4596088', 'audio/mpeg', '2020-03-13 18:06:53', '.\\upload\\RDgGH1onSR0l-mGXGvf.mp3', '1', null);
INSERT INTO `reference_data` VALUES ('RDH0GfC_2-mMY6ok-ab', 'RDH0GfC_2-mMY6ok-ab.mp3', 'Evan Call - The Bonds That Define Us.mp3', '4202201', 'audio/mp3', '2020-03-02 18:56:54', '.\\upload\\RDH0GfC_2-mMY6ok-ab.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDHjVNx5zjwmRzjrv-I', 'RDHjVNx5zjwmRzjrv-I..mp3', '『クローバー』Instrumental ver..mp3', '3870254', 'audio/mp3', '2020-03-02 16:16:47', '.\\upload\\RDHjVNx5zjwmRzjrv-I..mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDHx9M0XNofAxYy5ZEy', 'RDHx9M0XNofAxYy5ZEy.147.jpg', '926.147.jpg', '18004', 'image/jpeg', '2020-03-05 17:18:43', '.\\upload\\RDHx9M0XNofAxYy5ZEy.147.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDIJr5t98s5S5O85o6O', 'RDIJr5t98s5S5O85o6O.mp3', 'Evan Call - The Bonds That Define Us.mp3', '4202201', 'audio/mp3', '2020-03-02 19:50:51', '.\\upload\\RDIJr5t98s5S5O85o6O.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDiNVkUpsdcXskL9eQJ', 'RDiNVkUpsdcXskL9eQJ.png', '5.png', '26181', 'image/png', '2020-03-05 16:51:35', '.\\upload\\RDiNVkUpsdcXskL9eQJ.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDjxRx2e3DVm2wbn-aZ', 'RDjxRx2e3DVm2wbn-aZ.jpg', '2.jpg', '13312', 'image/jpeg', '2020-03-01 21:11:06', '.\\upload\\RDjxRx2e3DVm2wbn-aZ.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDKNvLyNMnrpelLNKGb', 'RDKNvLyNMnrpelLNKGb.mp3', 'Evan Call - The Evening Sea Shanty.mp3', '3112369', 'audio/mp3', '2020-03-05 16:37:56', '.\\upload\\RDKNvLyNMnrpelLNKGb.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDl5uCA0FfZwNpp-KGz', 'RDl5uCA0FfZwNpp-KGz.147.jpg', '926.147.jpg', '18004', 'image/jpeg', '2020-03-05 17:28:56', '.\\upload\\RDl5uCA0FfZwNpp-KGz.147.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDLrnIJu4oayR3iXlfT', 'RDLrnIJu4oayR3iXlfT.png', '2019_11_12_5d9a3de6d8144ef0bda095604e283b1f.png', '109812', 'image/png', '2020-03-03 00:19:59', '.\\upload\\RDLrnIJu4oayR3iXlfT.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDm2eRzj3-dWIgc8aKe', 'RDm2eRzj3-dWIgc8aKe.jpg', '4.jpg', '24798', 'image/jpeg', '2020-03-01 19:14:48', '.\\upload\\RDm2eRzj3-dWIgc8aKe.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDM7yTnngzmedG7aRzr', 'RDM7yTnngzmedG7aRzr.png', '5.png', '26181', 'image/png', '2020-03-05 13:23:46', '.\\upload\\RDM7yTnngzmedG7aRzr.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDMGw-EmZAfiZpUzLJr', 'RDMGw-EmZAfiZpUzLJr.mp3', 'Evan Call - A True Master.mp3', '4189632', 'audio/mp3', '2020-03-05 19:23:03', '.\\upload\\RDMGw-EmZAfiZpUzLJr.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDN-Hbh9qGOysy_JufI', 'RDN-Hbh9qGOysy_JufI.png', 'huidu.png', '6271', 'image/png', '2020-03-07 13:57:14', '.\\upload\\RDN-Hbh9qGOysy_JufI.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDnqEy-8LQgSf3BsXQZ', 'RDnqEy-8LQgSf3BsXQZ.png', '5.png', '26181', 'image/png', '2020-03-01 19:24:54', '.\\upload\\RDnqEy-8LQgSf3BsXQZ.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDOwGvzI0zpncQdsMVF', 'RDOwGvzI0zpncQdsMVF..mp3', '『クローバー』Instrumental ver..mp3', '3870254', 'audio/mp3', '2020-03-02 17:57:38', '.\\upload\\RDOwGvzI0zpncQdsMVF..mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDPIgrrn2QDO6PiwIiv', 'RDPIgrrn2QDO6PiwIiv.mp3', '40㍍P - Initial song.mp3', '9667456', 'audio/mp3', '2020-03-02 16:19:31', '.\\upload\\RDPIgrrn2QDO6PiwIiv.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDPmCrdaaXunxZUp5AA', 'RDPmCrdaaXunxZUp5AA..mp3', '『クローバー』Instrumental ver..mp3', '3870254', 'audio/mp3', '2020-03-02 16:18:18', '.\\upload\\RDPmCrdaaXunxZUp5AA..mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDPOrYBsdzVVe8FT0lL', 'RDPOrYBsdzVVe8FT0lL.mp3', 'Evan Call - EyecatchC.mp3', '325587', 'audio/mp3', '2020-03-05 18:31:26', '.\\upload\\RDPOrYBsdzVVe8FT0lL.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDRiJ_IbIJkN4kwan57', 'RDRiJ_IbIJkN4kwan57.147.jpg', '926.147.jpg', '18004', 'image/jpeg', '2020-03-03 15:55:04', '.\\upload\\RDRiJ_IbIJkN4kwan57.147.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDrNoJALVY8dfOloQCK', 'RDrNoJALVY8dfOloQCK.mp3', 'Evan Call - Fancy Beetle.mp3', '3951389', 'audio/mp3', '2020-03-05 19:15:12', '.\\upload\\RDrNoJALVY8dfOloQCK.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDrz4O4aqqY2Mi209AA', 'RDrz4O4aqqY2Mi209AA.jpg', '2.jpg', '13312', 'image/jpeg', '2020-03-01 17:15:56', '.\\upload\\RDrz4O4aqqY2Mi209AA.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDS2235_4w-x4srgj_m', 'RDS2235_4w-x4srgj_m.png', 'act1.png', '109812', 'image/png', '2020-03-03 00:31:50', '.\\upload\\RDS2235_4w-x4srgj_m.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDSiFGhleQ9Gyht6sLH', 'RDSiFGhleQ9Gyht6sLH.mp3', 'Evan Call - What Secrets Lie Within.mp3', '4018299', 'audio/mp3', '2020-03-05 18:09:36', '.\\upload\\RDSiFGhleQ9Gyht6sLH.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDtd-qW60x51FSLH-S5', 'RDtd-qW60x51FSLH-S5.png', '2019_11_12_bfd0169352cc4671a98b7b25dc3cfb79.png', '964874', 'image/png', '2020-03-03 00:30:37', '.\\upload\\RDtd-qW60x51FSLH-S5.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDTYQ9deoOb7ujHP7hL', 'RDTYQ9deoOb7ujHP7hL.jpg', '4.jpg', '24798', 'image/jpeg', '2020-03-01 11:04:45', '.\\upload\\RDTYQ9deoOb7ujHP7hL.jpg', '1', null);
INSERT INTO `reference_data` VALUES ('RDU5urvYI6Ju4tx1YGL', 'RDU5urvYI6Ju4tx1YGL.png', 'act1.png', '109812', 'image/png', '2020-03-03 15:29:01', '.\\upload\\RDU5urvYI6Ju4tx1YGL.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDuat0cfdl0x5DJDu86', 'RDuat0cfdl0x5DJDu86.jpg', '2.jpg', '13312', 'image/jpeg', '2020-03-01 22:46:16', '.\\upload\\RDuat0cfdl0x5DJDu86.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDuooBW8U0VpNJVfXx1', 'RDuooBW8U0VpNJVfXx1.mp3', 'Evan Call - Friends Forever.mp3', '5490536', 'audio/mp3', '2020-03-02 19:13:31', '.\\upload\\RDuooBW8U0VpNJVfXx1.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDuqjlO0XFwJOpTThbo', 'RDuqjlO0XFwJOpTThbo.mp3', 'Evan Call - Trouble Ahead!.mp3', '3991104', 'audio/mp3', '2020-03-02 19:31:10', '.\\upload\\RDuqjlO0XFwJOpTThbo.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDv0OyemgZhuveybikB', 'RDv0OyemgZhuveybikB..mp3', '『クローバー』Instrumental ver..mp3', '3870254', 'audio/mp3', '2020-03-02 18:56:41', '.\\upload\\RDv0OyemgZhuveybikB..mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDVcppgga69Xwpy-wWZ', 'RDVcppgga69Xwpy-wWZ.jpg', '3.jpg', '19433', 'image/jpeg', '2020-03-05 12:26:01', '.\\upload\\RDVcppgga69Xwpy-wWZ.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDVkB1WhagQuSxcBMAQ', 'RDVkB1WhagQuSxcBMAQ.mp3', 'Evan Call - High Society.mp3', '4008862', 'audio/mp3', '2020-03-05 19:18:27', '.\\upload\\RDVkB1WhagQuSxcBMAQ.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDVTfXu5XRx1CBGkTTj', 'RDVTfXu5XRx1CBGkTTj.png', '1.png', '16029', 'image/png', '2020-03-01 21:00:13', '.\\upload\\RDVTfXu5XRx1CBGkTTj.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RDw73Qewgvd1lWR02hO', 'RDw73Qewgvd1lWR02hO.mp3', 'Evan Call - Takin\' It Easy.mp3', '4123807', 'audio/mp3', '2020-03-02 19:15:18', '.\\upload\\RDw73Qewgvd1lWR02hO.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDxAxKN0tgYnuw50QRB', 'RDxAxKN0tgYnuw50QRB.jpg', '2.jpg', '13312', 'image/jpeg', '2020-03-04 14:23:06', '.\\upload\\RDxAxKN0tgYnuw50QRB.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDyLpb6rWF6C--SLrbO', 'RDyLpb6rWF6C--SLrbO.mp3', 'Evan Call - La Cueca del Mar.mp3', '4532368', 'audio/mp3', '2020-03-02 18:53:45', '.\\upload\\RDyLpb6rWF6C--SLrbO.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDyWrFGoQhq6O-5XIv1', 'RDyWrFGoQhq6O-5XIv1.mp3', 'Evan Call - The Evening Sea Shanty.mp3', '3112369', 'audio/mp3', '2020-03-02 19:09:07', '.\\upload\\RDyWrFGoQhq6O-5XIv1.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RDy_E2g-63DeiaJ-qJ4', 'RDy_E2g-63DeiaJ-qJ4.mp3', 'Evan Call - The Eccentric Barber.mp3', '3814535', 'audio/mpeg', '2020-03-13 18:10:07', '.\\upload\\RDy_E2g-63DeiaJ-qJ4.mp3', '1', null);
INSERT INTO `reference_data` VALUES ('RDZotU4AbBvyf0v1YCW', 'RDZotU4AbBvyf0v1YCW.jpg', '3_zf.jpg', '14501', 'image/jpeg', '2020-03-01 22:04:45', '.\\upload\\RDZotU4AbBvyf0v1YCW.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDzzBC0ioKVOT4u_lFX', 'RDzzBC0ioKVOT4u_lFX.147.jpg', '926.147.jpg', '18004', 'image/jpeg', '2020-03-05 17:23:46', '.\\upload\\RDzzBC0ioKVOT4u_lFX.147.jpg', '1', '1');
INSERT INTO `reference_data` VALUES ('RDZzHmVKeCyw9-TLhO1', 'RDZzHmVKeCyw9-TLhO1.png', 'act1.png', '109812', 'image/png', '2020-03-03 02:00:05', '.\\upload\\RDZzHmVKeCyw9-TLhO1.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RD_Kf0ksr75UzWKnrlm', 'RD_Kf0ksr75UzWKnrlm.mp3', 'Evan Call - Rumble in the Streets.mp3', '3977542', 'audio/mp3', '2020-03-05 19:02:39', '.\\upload\\RD_Kf0ksr75UzWKnrlm.mp3', '1', '1');
INSERT INTO `reference_data` VALUES ('RD_xs-lotFU4NY0CAMZ', 'RD_xs-lotFU4NY0CAMZ.png', '2019_11_12_bfd0169352cc4671a98b7b25dc3cfb79.png', '964874', 'image/png', '2020-03-03 01:49:36', '.\\upload\\RD_xs-lotFU4NY0CAMZ.png', '1', '1');
INSERT INTO `reference_data` VALUES ('RD_zf57_f812qMJaAzr', 'RD_zf57_f812qMJaAzr.png', '1.png', '16029', 'image/png', '2020-03-01 19:22:42', '.\\upload\\RD_zf57_f812qMJaAzr.png', '1', '1');

-- ----------------------------
-- Table structure for subscribe
-- ----------------------------
DROP TABLE IF EXISTS `subscribe`;
CREATE TABLE `subscribe` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订阅id',
  `type` varchar(255) DEFAULT NULL COMMENT '订阅类型（electronic-book: 电子书更新, audio-book: 有声书更新）',
  `content_id` int(11) DEFAULT NULL COMMENT '内容Id',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品Id',
  `book_id` int(11) DEFAULT NULL COMMENT '图书Id',
  `subscriber_id` int(11) DEFAULT NULL COMMENT '订阅者',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of subscribe
-- ----------------------------

-- ----------------------------
-- Table structure for system_settings
-- ----------------------------
DROP TABLE IF EXISTS `system_settings`;
CREATE TABLE `system_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_settings
-- ----------------------------
INSERT INTO `system_settings` VALUES ('1', 'topic.examination', 'false');
INSERT INTO `system_settings` VALUES ('2', 'comment.examination', 'auto');
INSERT INTO `system_settings` VALUES ('3', 'review.examination', 'false');
INSERT INTO `system_settings` VALUES ('4', 'comprehensive-page.categories', '21,23,22');
INSERT INTO `system_settings` VALUES ('5', 'comprehensive-page.collections', '18,18');
INSERT INTO `system_settings` VALUES ('6', 'activity.number', '6');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(255) DEFAULT NULL COMMENT '标签名称',
  `deleted` int(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'Test Tag', '0');
INSERT INTO `tag` VALUES ('2', 'Test Tag2', '0');
INSERT INTO `tag` VALUES ('3', 'Hello', '0');
INSERT INTO `tag` VALUES ('4', 'HI', '0');
INSERT INTO `tag` VALUES ('10', 'Interesting', '0');
INSERT INTO `tag` VALUES ('11', '武侠x', '0');
INSERT INTO `tag` VALUES ('14', 'dsfs', '0');
INSERT INTO `tag` VALUES ('15', 'sdfa', '0');
INSERT INTO `tag` VALUES ('16', 'sdfawe', '0');
INSERT INTO `tag` VALUES ('17', 'rtdsafsdafsdafewtsafsdaf', '0');
INSERT INTO `tag` VALUES ('18', 'bre', '0');
INSERT INTO `tag` VALUES ('19', 'dage', '0');
INSERT INTO `tag` VALUES ('20', 'fadsfsdfasd', '0');
INSERT INTO `tag` VALUES ('21', 'dafsdfas', '0');
INSERT INTO `tag` VALUES ('22', 'asfs', '0');
INSERT INTO `tag` VALUES ('23', 'gersdf', '0');
INSERT INTO `tag` VALUES ('24', 'dsaf', '0');
INSERT INTO `tag` VALUES ('25', '21', '0');
INSERT INTO `tag` VALUES ('26', 'sfas', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` char(13) DEFAULT NULL COMMENT '用户名',
  `password` char(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(16) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像url',
  `account_expired` timestamp NULL DEFAULT NULL COMMENT '账号过期时间',
  `credentials_expired` timestamp NULL DEFAULT NULL COMMENT '登录凭据过期时间',
  `account_locked` int(1) DEFAULT NULL COMMENT '账号是否锁定(1: 锁定, 0: 未锁定)',
  `enabled` int(1) DEFAULT '1' COMMENT '账号是否禁用(1: 可用, 0: 不可用)',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `deleted` int(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'codimiracle', '$2a$10$iB2X4S5ApwcsC1psTEWC5e2ckXH8x5U9XZXKz2q/XhhVyyMVVjwYO', '码迹', 'http://192.168.43.178:4000/api/user/avatar/RDN-Hbh9qGOysy_JufI', null, null, '0', '1', '1', '0');
INSERT INTO `user` VALUES ('3', 'tester', '$2a$10$iB2X4S5ApwcsC1psTEWC5e2ckXH8x5U9XZXKz2q/XhhVyyMVVjwYO', 'tester', 'http://192.168.43.178:4000/api/user/avatar/RDN-Hbh9qGOysy_JufI', null, null, '0', '1', '1', '0');

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` bigint(20) DEFAULT '0' COMMENT '荟币余额',
  `version` int(11) DEFAULT '0' COMMENT '修改版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES ('1', '1', '30100', '0');
INSERT INTO `user_account` VALUES ('2', '1', '1000', '0');
INSERT INTO `user_account` VALUES ('3', '1', '300', '0');
INSERT INTO `user_account` VALUES ('4', '1', '3400', '0');
INSERT INTO `user_account` VALUES ('5', '1', '60600', '0');

-- ----------------------------
-- Table structure for user_cart
-- ----------------------------
DROP TABLE IF EXISTS `user_cart`;
CREATE TABLE `user_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `join_time` datetime DEFAULT NULL,
  `settled` tinyint(1) DEFAULT NULL COMMENT '已结账',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_cart
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL,
  `gender` varchar(255) DEFAULT NULL COMMENT '用户性别（boy: 男, girl: 女）',
  `age` int(11) DEFAULT NULL COMMENT '用户年龄',
  `slogan` varchar(255) DEFAULT NULL COMMENT '个人签名',
  `introduction` varchar(255) DEFAULT NULL COMMENT '个人介绍',
  `birthdate` datetime DEFAULT NULL COMMENT '生日',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户电话',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮件',
  `region` varchar(255) DEFAULT NULL COMMENT '用户地区',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'boy', null, '你才是?!', '你看看，那个是啥？', '1997-10-11 08:00:00', '15800000001', 'codimiracle@outlook.com', '广东省 广州市 从化区');
INSERT INTO `user_info` VALUES ('2', 'girl', null, '213', '2423', '1997-10-11 08:00:00', '15800000000', 'codimiracle@outlook.com', '广东省 广州市 从化区');
INSERT INTO `user_info` VALUES ('3', 'boy', null, 'Software testing', '阳光帅气的测试人员或可爱元气萌妹子', '2020-03-07 08:00:00', '15800000000', 'tester@huidu.com', '广东省 广州市 从化区');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `authorities` text COMMENT '权限标识',
  `deleted` int(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '管理员', 'frontend-services,content-reviews,content,content-topics,content-comments,content-electronic-books,content-audio-books,recommendation,user-roles,system,user-users,classification-collection,classification,classification-categories,classification-tags,shopping-orders,shopping,shopping-commodities,shopping-paper-books,dashboard,author-data-services,comprehensive-page,author-electronic-books-service,author-audio-books-service,creator', '0');

-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_token
-- ----------------------------
INSERT INTO `user_token` VALUES ('1', '1', '2020-04-13 16:12:04', 'eab4b03f-01bc-41fe-b24a-795af43ce7ca');
