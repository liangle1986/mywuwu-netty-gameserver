# 基于netty 与AKKA 的棋牌游戏服务器解决方案

# 数据存储

> 游戏缓存:redis
>
> 物理存储:mongodb

# 模块说明

> cardlib 棋牌算法库
>
> core 框架核心，处理网络请求模块轮转请求，房间请求等
>
> data 公用数据模型
>
> hallserver 大厅服务器

> webserver web服务器

> yingssanzhangserver 炸金花游戏逻辑服务器
> 数据脚步 
/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : mywuwu

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-01-05 22:11:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mywuwu_order
-- ----------------------------
DROP TABLE IF EXISTS `mywuwu_order`;
CREATE TABLE `mywuwu_order` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `product_id` varchar(50) NOT NULL,
  `room_card_num` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `wx_pay_price` int(11) NOT NULL,
  `transaction_id` varchar(200) DEFAULT NULL,
  `pay_status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);
-- ----------------------------
-- Records of mywuwu_order
-- ----------------------------

-- ----------------------------
-- Table structure for mywuwu_proxy
-- ----------------------------
DROP TABLE IF EXISTS `mywuwu_proxy`;
CREATE TABLE `mywuwu_proxy` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `nick_name` varchar(100) NOT NULL,
  `mobile_phone` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `wechat_num` varchar(100) NOT NULL,
  `real_name` varchar(100) NOT NULL,
  `id_card_no` varchar(25) DEFAULT NULL,
  `extract_amount` bigint(20) DEFAULT '0',
  `remainder_amount` bigint(20) DEFAULT '0',
  `total_income` bigint(20) DEFAULT '0',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `mobile_phone` (`mobile_phone`) USING BTREE
);

-- ----------------------------
-- Records of mywuwu_proxy
-- ----------------------------

-- ----------------------------
-- Table structure for mywuwu_proxy_user
-- ----------------------------
DROP TABLE IF EXISTS `mywuwu_proxy_user`;
CREATE TABLE `mywuwu_proxy_user` (
  `id` varchar(50) NOT NULL,
  `proxy_id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `nick_name` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE
);

-- ----------------------------
-- Records of mywuwu_proxy_user
-- ----------------------------

-- ----------------------------
-- Table structure for mywuwu_user
-- ----------------------------
DROP TABLE IF EXISTS `mywuwu_user`;
CREATE TABLE `mywuwu_user` (
  `id` varchar(50) NOT NULL,
  `nick_name` varchar(100) NOT NULL,
  `head_img_url` varchar(500) DEFAULT NULL,
  `wx_open_id` varchar(100) NOT NULL,
  `room_card_num` int(11) NOT NULL DEFAULT '0',
  `user_level` tinyint(1) NOT NULL DEFAULT '1',
  `win_probability` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wx_open_id` (`wx_open_id`)
);

-- ----------------------------
-- Records of mywuwu_user
-- ----------------------------

-- ----------------------------
-- Table structure for mywuwu_user_feedback
-- ----------------------------
DROP TABLE IF EXISTS `mywuwu_user_feedback`;
CREATE TABLE `mywuwu_user_feedback` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `mobile_phone` varchar(20) NOT NULL,
  `feed_back` varchar(500) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of mywuwu_user_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for mywuwu_user_record
-- ----------------------------
DROP TABLE IF EXISTS `mywuwu_user_record`;
CREATE TABLE `mywuwu_user_record` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `game_type` tinyint(1) NOT NULL,
  `room_id` int(20) NOT NULL,
  `pay_type` tinyint(1) NOT NULL,
  `total_games` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `record_info` varchar(1200) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of mywuwu_user_record
-- ----------------------------

-- ----------------------------
-- Table structure for mywuwu_user_room_card_log
-- ----------------------------
DROP TABLE IF EXISTS `mywuwu_user_room_card_log`;
CREATE TABLE `mywuwu_user_room_card_log` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `pre_room_card_num` int(11) NOT NULL DEFAULT '0',
  `cur_room_card_num` int(11) NOT NULL DEFAULT '0',
  `diff_room_card_num` int(11) NOT NULL DEFAULT '0',
  `amount` int(11) NOT NULL DEFAULT '0',
  `game_type` tinyint(1) DEFAULT NULL,
  `operator_id` varchar(50) NOT NULL,
  `operator_type` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of mywuwu_user_room_card_log
-- ----------------------------

-- ----------------------------
-- Table structure for wuwu_statistics
-- ----------------------------
DROP TABLE IF EXISTS `wuwu_statistics`;
CREATE TABLE `wuwu_statistics` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `access_ip` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `access_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `access_number` int(8) NOT NULL,
  `access_type` int(8) NOT NULL,
  `login_user` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_wuwu_statistics` (`access_ip`),
  KEY `pk_wuwu_statistics_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of wuwu_statistics
-- ----------------------------
INSERT INTO `wuwu_statistics` VALUES ('1', '0:0:0:0:0:0:0:1', '2017-05-21 13:35:18', '4', '1', '');

