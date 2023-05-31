/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : mysource

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 04/05/2023 18:23:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `Cname` char(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `information` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Price` decimal(10, 2) NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('小玩具', '法外狂徒', 'hhcat.png', 123.00, 'vff', 'wu');
INSERT INTO `commodity` VALUES ('大玩具', '知名人物', '1.jpg', 688.00, 'err', 'wu');
INSERT INTO `commodity` VALUES ('笔记本', '知名销售', '2.jpg', 488.00, 'er', 'wu');
INSERT INTO `commodity` VALUES ('高达', '小黑子', '3.jpg', 900.00, 'fer', 'wu');

-- ----------------------------
-- Table structure for lots
-- ----------------------------
DROP TABLE IF EXISTS `lots`;
CREATE TABLE `lots`  (
  `Lname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `wraparound` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Price` decimal(10, 2) NOT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `LPrice` decimal(10, 2) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT ''
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lots
-- ----------------------------
INSERT INTO `lots` VALUES ('猫猫', '一只猫猫', 2001.00, '一只可爱的猫猫', 1234579.00, 'hhcat.png', '玩偶图片', '111000');
INSERT INTO `lots` VALUES ('山水图', '国画经典', 100.00, '  山水画', 2357.00, '1.jpg', ' 山水国画', '');
INSERT INTO `lots` VALUES ('古董', '青花瓷', 400.00, '景德镇', 5007.00, '2.jpg', '古玩', '');
INSERT INTO `lots` VALUES ('游戏', '暂无', 566.00, NULL, 666.00, '4.jpg', '未知', '');

-- ----------------------------
-- Table structure for shopping cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping cart`;
CREATE TABLE `shopping cart`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ugoods` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping cart
-- ----------------------------
INSERT INTO `shopping cart` VALUES ('王五', '古董');
INSERT INTO `shopping cart` VALUES ('张三', '游戏');

-- ----------------------------
-- Table structure for temporarily
-- ----------------------------
DROP TABLE IF EXISTS `temporarily`;
CREATE TABLE `temporarily`  (
  `Uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Ugoods` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `bid` decimal(10, 2) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of temporarily
-- ----------------------------
INSERT INTO `temporarily` VALUES ('我', '泄欲垃圾', 2.00);
INSERT INTO `temporarily` VALUES ('张三', '我的玩具', 9009.00);
INSERT INTO `temporarily` VALUES ('fyx', '我的玩具', 1001.00);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `adminstrator` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', 'admin', 'true');
INSERT INTO `user` VALUES ('fyx', '456', 'true');
INSERT INTO `user` VALUES ('张三', '123', 'false');
INSERT INTO `user` VALUES ('李四', '123', 'false');

SET FOREIGN_KEY_CHECKS = 1;
