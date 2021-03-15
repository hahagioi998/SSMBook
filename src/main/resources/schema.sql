/*
 Navicat Premium Data Transfer

 Source Server         : LocalMySQL
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : book_management

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 22/11/2020 18:06:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '书籍编号',
  `isbn` char(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '国际标准书号（13位数字）',
  `name` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '书籍名称',
  `author` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '书籍作者',
  `press` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '书籍出版社',
  `price` double UNSIGNED NULL DEFAULT 0 COMMENT '书籍定价',
  `count` int(11) UNSIGNED NULL DEFAULT 1 COMMENT '书籍数量',
  `introduction` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '书籍简介',
  `category_id` int(11) NULL DEFAULT 0 COMMENT '书籍分类编号',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '书籍表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '1698756354986', 'MySQL', 'author01', 'press01', 23.36, 10, '从删库到跑路', 4, '2020-11-07 21:37:14', '2020-11-06 10:02:34');
INSERT INTO `book` VALUES (2, '1658945632156', 'Linux', 'author02', 'press02', 45.96, 5, '从进门到进牢', 1, '2020-11-07 21:35:45', '2020-11-06 10:02:34');
INSERT INTO `book` VALUES (3, '1598765415963', 'Python3', 'author03', 'press03', 66.35, 6, '人生苦短我学Python', 3, '2020-11-07 21:35:50', '2020-11-06 10:02:34');
INSERT INTO `book` VALUES (4, '1364985643215', '测试驱动开发', 'author04', 'press04', 53.65, 3, '测试驱动开发', 4, '2020-11-07 21:35:55', '2020-11-06 10:02:34');
INSERT INTO `book` VALUES (5, '1536987564125', 'Head First设计模式', 'author06', 'press06', 44.35, 2, 'Head First系列可以将各种枯燥的设计生起起来，这是一本非常适合入门设计模式的书籍没有之一。', 1, '2020-11-07 21:36:07', '2020-11-06 10:02:34');
INSERT INTO `book` VALUES (6, '1598765412368', '设计模式解析', 'author07', 'press07', 75.36, 1, '比Head First更深入，但是比DP简单。作者选用了一些常用或者说用到的模式，讲述了为什么在这里适合用它。', 2, '2020-11-07 21:36:13', '2020-11-06 10:02:34');
INSERT INTO `book` VALUES (7, '1479856452156', '代码大全', 'author08', 'press08', 66.93, 4, '这本书可以说是一本百科全书，其中对编程的各方面都有讲解，并且适用于各种语言，描述也不晦涩，非常通俗。', 2, '2020-11-07 21:36:19', '2020-11-06 10:02:34');
INSERT INTO `book` VALUES (8, '5987469851236', '程序员修炼之道:从小工到专家', 'author09', 'press09', 75.63, 3, '《程序员修炼之道》由一系列独立的部分组成， 涵盖的主题从个人责任、职业发展，知道用于使代码保持灵活、并且易于改编和复用的各种架构技术， 利用许多富有娱乐性的奇闻轶事、有思想性的例子及有趣的类比， 全面阐释了软件开发的许多不同方面的最佳实践和重大陷阱。', 3, '2020-11-07 21:36:26', '2020-11-06 10:02:34');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '分类描述',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '书籍类别表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '自然科学类', '自然科学类描述', '2020-11-22 17:37:44', '2020-11-06 10:12:05');
INSERT INTO `category` VALUES (2, '计算机类', '计算机类描述', '2020-11-08 09:33:39', '2020-11-06 15:21:30');
INSERT INTO `category` VALUES (3, '软件类', '软件类描述', '2020-11-08 09:33:51', '2020-11-06 15:21:40');
INSERT INTO `category` VALUES (4, '工具类', '工具类描述', '2020-11-08 09:34:01', '2020-11-06 15:21:48');
INSERT INTO `category` VALUES (5, '未定义类', '未定义类描述', '2020-11-08 12:44:09', '2020-11-08 12:44:09');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `nickname` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户昵称',
  `description` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户描述',
  `role` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '角色',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'aaa42296669b958c3cee6c0475c8093e', 'Myles', '种一棵树最好的时间是十年，其次是现在，从现在就着手你想做但总觉得为时已晚的事吧！', 'admin', '2020-11-22 17:29:33', '2020-11-20 16:58:21');

SET FOREIGN_KEY_CHECKS = 1;
