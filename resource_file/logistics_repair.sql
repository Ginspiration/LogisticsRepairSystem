/*
 Navicat MySQL Data Transfer

 Source Server         : 腾讯云docker
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 82.157.63.226:3306
 Source Schema         : logistics_repair

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 30/01/2022 22:21:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系手机',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '管理员001', '123456', '15270899309');

-- ----------------------------
-- Table structure for announce
-- ----------------------------
DROP TABLE IF EXISTS `announce`;
CREATE TABLE `announce`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_user` int(11) NOT NULL COMMENT '发布人',
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告发布方',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容',
  `date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announce
-- ----------------------------

-- ----------------------------
-- Table structure for maintainer
-- ----------------------------
DROP TABLE IF EXISTS `maintainer`;
CREATE TABLE `maintainer`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '维修人姓名',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '维修人手机号',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '维修状态,1正在,0空闲',
  `work_count` int(11) NOT NULL DEFAULT 0 COMMENT '出工次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintainer
-- ----------------------------

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上报人联系手机',
  `repair_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修人联系手机',
  `status` int(2) NULL DEFAULT 0 COMMENT '维修状态,0未开始,1正在,2已结束,3异常',
  `feedback` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '上报人反馈',
  `repaired_date` datetime(0) NULL DEFAULT NULL COMMENT '修复日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2072043523 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (-2105483262, '222', NULL, 0, NULL, NULL);
INSERT INTO `repair` VALUES (-1690247166, '222', NULL, 1, NULL, NULL);
INSERT INTO `repair` VALUES (-926932991, '123', NULL, 0, NULL, NULL);
INSERT INTO `repair` VALUES (-780083199, '555', NULL, 2, NULL, NULL);
INSERT INTO `repair` VALUES (167829505, '222', NULL, 3, NULL, NULL);
INSERT INTO `repair` VALUES (474013698, '52', NULL, 1, NULL, NULL);
INSERT INTO `repair` VALUES (1312919554, '15270899309', NULL, 0, NULL, NULL);
INSERT INTO `repair` VALUES (1363206146, '535', NULL, 0, NULL, NULL);
INSERT INTO `repair` VALUES (1619050497, '111', NULL, 0, NULL, NULL);
INSERT INTO `repair` VALUES (2072043522, '555', NULL, 0, NULL, NULL);

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `id` int(11) NOT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上报人微信appId',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上报人姓名',
  `number` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号/教工号',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系手机',
  `faculty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院系',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上报的地址',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上报日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES (-1627381758, 'oFkTJ5yUHowEOngV3FZW6Krtt04M', '邱', '3123', '123', '13', NULL, '123', NULL, '123123', '2022-01-22 19:23:11');
INSERT INTO `report` VALUES (-1505697790, 'oFkTJ50r4ZP5D-7Rakt9XYSEIti8', '哈哈哈', '222', '555', '哈哈哈', NULL, '哈哈哈', 'D:/GraduationDesign/save_files_path/59f7c257-db3f-46bf-b891-60b969c6eb80.jpg', '滚滚滚', '2022-01-20 21:37:28');
INSERT INTO `report` VALUES (-553590782, 'oFkTJ50r4ZP5D-7Rakt9XYSEIti8', '快快快', '222', '222', '哈哈哈', NULL, '滚滚滚', 'D:/GraduationDesign/save_files_path/f4adb992-2e47-45f4-9644-9ba4c5d1cf8a.jpg', '滚滚滚', '2022-01-20 21:33:16');
INSERT INTO `report` VALUES (427868161, 'oFkTJ50r4ZP5D-7Rakt9XYSEIti8', '滴滴滴', '111', '111', '等等', NULL, '等等', 'D:/GraduationDesign/save_files_path/c35162d1-2aff-47cf-aeb1-8a035f045ef1.jpg', '滴滴滴', '2022-01-30 21:31:50');
INSERT INTO `report` VALUES (713089025, 'oFkTJ50r4ZP5D-7Rakt9XYSEIti8', '嘎嘎嘎', '522', '535', '哈哈哈', NULL, '共和国', 'D:/GraduationDesign/save_files_path/ad885f2d-70d8-43ac-9e85-180f0e792f0c.jpg', '刚刚给', '2022-01-20 21:36:49');
INSERT INTO `report` VALUES (1023467522, 'oFkTJ50r4ZP5D-7Rakt9XYSEIti8', '刚刚', '55', '52', '很好', NULL, '方法', NULL, '天堂', '2022-01-20 21:26:21');
INSERT INTO `report` VALUES (1346428930, 'oFkTJ50r4ZP5D-7Rakt9XYSEIti8', '哈哈哈', '222', '555', '哈哈哈', NULL, '哈哈哈', 'D:/GraduationDesign/save_files_path/e72b00da-9653-40e5-8518-990e6e8e5f1b.jpg', '滚滚滚', '2022-01-20 21:37:45');
INSERT INTO `report` VALUES (1526784002, 'oFkTJ50r4ZP5D-7Rakt9XYSEIti8', '滚滚滚', '555', '222', '比较好', NULL, '英语', 'D:/GraduationDesign/save_files_path/5a15d1b7-b6a1-4f91-a1a8-464b4af009b8.jpg', '那你就', '2022-01-20 21:39:02');
INSERT INTO `report` VALUES (1841356801, 'oFkTJ50r4ZP5D-7Rakt9XYSEIti8', '快快快', '222', '222', '哈哈哈', NULL, '滚滚滚', 'D:/GraduationDesign/save_files_path/97b83001-5f81-48c0-b247-4cb95b158858.jpg', '滚滚滚', '2022-01-20 21:36:18');
INSERT INTO `report` VALUES (1958842369, 'oFkTJ50r4ZP5D-7Rakt9XYSEIti8', '高云', '20180210440416', '15270899309', '电信', NULL, '9栋1059栋1059栋1059栋1059栋1059栋1059栋1059栋1059栋1059栋1059栋105', 'D:/GraduationDesign/save_files_path/9603f8ed-7f20-4194-988c-b2cd1589e96d.jpg', '天花板漏水天花板漏水天花板漏水天花板漏水天花板漏水天花板漏水天花板漏水天花板漏水天花板漏水天花板漏水天花板漏水天花板漏水天花板漏水', '2022-01-22 11:28:38');

-- ----------------------------
-- Table structure for wwrt
-- ----------------------------
DROP TABLE IF EXISTS `wwrt`;
CREATE TABLE `wwrt`  (
  `
列
column
列
web_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`
列
column
列
web_token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wwrt
-- ----------------------------
INSERT INTO `wwrt` VALUES ('东西');
INSERT INTO `wwrt` VALUES ('东西3');
INSERT INTO `wwrt` VALUES ('东西4');

SET FOREIGN_KEY_CHECKS = 1;
