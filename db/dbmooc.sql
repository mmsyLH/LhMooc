/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : dbmooc

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2024-03-11 17:52:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment_video
-- ----------------------------
DROP TABLE IF EXISTS `comment_video`;
CREATE TABLE `comment_video` (
  `commentid` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频评论id',
  `userid` int(11) NOT NULL COMMENT '评论用户的id',
  `videoid` int(11) DEFAULT NULL COMMENT '评论的视频id',
  `content` varchar(255) NOT NULL COMMENT '评论的内容',
  `isdelete` int(11) DEFAULT '0' COMMENT '评论是否删除 0表示未删除 1表示删除',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  PRIMARY KEY (`commentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频评论表';

-- ----------------------------
-- Records of comment_video
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseid` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程表Id',
  `coursename` varchar(255) NOT NULL COMMENT '课程名',
  `categoryid` int(11) NOT NULL COMMENT '课程分类id 是课程分类表的主键',
  `profile` varchar(255) DEFAULT NULL COMMENT '课程介绍',
  `price` int(11) DEFAULT NULL COMMENT '课程价格',
  `imgurl` varchar(255) DEFAULT NULL COMMENT '课程封面的图片',
  PRIMARY KEY (`courseid`),
  UNIQUE KEY `course_courseid_uindex` (`courseid`),
  UNIQUE KEY `course_coursename_uindex` (`coursename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

-- ----------------------------
-- Records of course
-- ----------------------------

-- ----------------------------
-- Table structure for course_category
-- ----------------------------
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category` (
  `categoryid` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程分类表的id',
  `categoryname` varchar(255) NOT NULL COMMENT '课程分类名',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY `course_category_category_name_uindex` (`categoryname`),
  UNIQUE KEY `course_category_category_id_uindex` (`categoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程分类表';

-- ----------------------------
-- Records of course_category
-- ----------------------------

-- ----------------------------
-- Table structure for course_chapter
-- ----------------------------
DROP TABLE IF EXISTS `course_chapter`;
CREATE TABLE `course_chapter` (
  `chapterid` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程章节表的id',
  `chaptername` varchar(255) NOT NULL COMMENT '章节名称 ',
  `couseid` int(11) DEFAULT NULL COMMENT '课程id',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`chapterid`),
  UNIQUE KEY `course_chapter_chapterid_uindex` (`chapterid`),
  UNIQUE KEY `course_chapter_chaptername_uindex` (`chaptername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程章节表';

-- ----------------------------
-- Records of course_chapter
-- ----------------------------

-- ----------------------------
-- Table structure for course_video
-- ----------------------------
DROP TABLE IF EXISTS `course_video`;
CREATE TABLE `course_video` (
  `videoid` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频表的id',
  `videoname` varchar(255) DEFAULT NULL COMMENT '视频名称，存储课程视频的名称',
  `chapterid` int(11) DEFAULT NULL COMMENT '章节表的id',
  `videotime` datetime NOT NULL COMMENT '视频时长',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `userid` int(11) DEFAULT NULL COMMENT '上传用户的id',
  `videourl` varchar(255) DEFAULT NULL COMMENT '视频链接',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`videoid`),
  UNIQUE KEY `course_video_videoid_uindex` (`videoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of course_video
-- ----------------------------

-- ----------------------------
-- Table structure for follow_video
-- ----------------------------
DROP TABLE IF EXISTS `follow_video`;
CREATE TABLE `follow_video` (
  `followid` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频收藏的id',
  `userid` int(11) NOT NULL COMMENT '用户id',
  `videoid` int(11) NOT NULL COMMENT '视频id',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isdelete` int(11) NOT NULL DEFAULT '0' COMMENT '0是未收藏 1是已经收藏',
  PRIMARY KEY (`followid`),
  UNIQUE KEY `follow_video_followid_uindex` (`followid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频收藏表';

-- ----------------------------
-- Records of follow_video
-- ----------------------------

-- ----------------------------
-- Table structure for like_video
-- ----------------------------
DROP TABLE IF EXISTS `like_video`;
CREATE TABLE `like_video` (
  `likeid` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞表的id',
  `userid` int(11) NOT NULL COMMENT '用户id',
  `videoid` int(11) NOT NULL COMMENT '视频id',
  `isdelete` int(11) DEFAULT '0' COMMENT '0是未点赞 1是点赞',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`likeid`),
  UNIQUE KEY `like_video_likeid_uindex` (`likeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

-- ----------------------------
-- Records of like_video
-- ----------------------------

-- ----------------------------
-- Table structure for mooc_user
-- ----------------------------
DROP TABLE IF EXISTS `mooc_user`;
CREATE TABLE `mooc_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动增长的id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT '测试名',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像链接,相对路径(相对于项目的路径)',
  `isdelete` int(11) DEFAULT '0' COMMENT '0表示还没删除 1表示删除',
  `wallet` double DEFAULT '100' COMMENT '钱包余额 单位元,用户的账户余额。初始注册赠送100',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `userrole` int(11) DEFAULT '0' COMMENT '0 表示普通人员 1表示管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='慕课用户信息表';

-- ----------------------------
-- Records of mooc_user
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` varchar(255) DEFAULT NULL COMMENT '订单id',
  `orderstatus` int(11) DEFAULT '0' COMMENT '订单状态 0待付款 1付款',
  `orderamount` int(11) DEFAULT NULL COMMENT '订单金额',
  `courseid` int(11) DEFAULT NULL COMMENT '订单的课程id',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单时间',
  `paytime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '支付时间',
  `paychannel` int(11) DEFAULT NULL COMMENT '支付渠道 1是支付宝 2是微信',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orders_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for play_history
-- ----------------------------
DROP TABLE IF EXISTS `play_history`;
CREATE TABLE `play_history` (
  `playid` int(11) NOT NULL AUTO_INCREMENT COMMENT '播放表的id',
  `userid` int(11) NOT NULL COMMENT '用户id',
  `videoid` int(11) NOT NULL COMMENT '视频id',
  `playprogress` datetime DEFAULT NULL COMMENT '播放进度表',
  PRIMARY KEY (`playid`),
  UNIQUE KEY `play_history_id_uindex` (`playid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of play_history
-- ----------------------------

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '交易流水表的id',
  `transactionid` varchar(255) DEFAULT NULL COMMENT '流水号',
  `userid` int(11) DEFAULT NULL COMMENT '流水的用户id',
  `transactiontype` int(11) DEFAULT NULL COMMENT '流水类型 1充值 2消费',
  `amount` int(11) DEFAULT NULL COMMENT '交易金额',
  `transactiontime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间',
  `transactionstatus` int(11) DEFAULT NULL COMMENT '交易状态 1 成功 2失败 3进行中',
  `paychannel` int(11) DEFAULT NULL COMMENT '交易方式 1支付宝 2微信',
  `info` varchar(255) DEFAULT NULL COMMENT '附加信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `transaction_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交易流水表';

-- ----------------------------
-- Records of transaction
-- ----------------------------
