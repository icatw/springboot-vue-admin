/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : icatw

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 29/04/2022 15:18:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小(kb)',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下载链接',
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件md5',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (23, 'QQ图片20201108110118.jpg', 'jpg', 49, 'http://localhost:9090/file/219004018cc2414e83dd5aafd6c163d9.jpg', 'dde3c71e642f112140ce6ec3797bbf27', 1, 1);
INSERT INTO `sys_file` VALUES (24, 'QQ图片20201108110118.jpg', 'jpg', 49, 'http://localhost:9090/file/219004018cc2414e83dd5aafd6c163d9.jpg', 'dde3c71e642f112140ce6ec3797bbf27', 1, 1);
INSERT INTO `sys_file` VALUES (25, 'QQ图片20201108110118.jpg', 'jpg', 49, 'http://localhost:9090/file/219004018cc2414e83dd5aafd6c163d9.jpg', 'dde3c71e642f112140ce6ec3797bbf27', 0, 1);
INSERT INTO `sys_file` VALUES (26, 'QQ图片20201108110118.jpg', 'jpg', 49, 'http://localhost:9090/file/219004018cc2414e83dd5aafd6c163d9.jpg', 'dde3c71e642f112140ce6ec3797bbf27', 0, 1);
INSERT INTO `sys_file` VALUES (27, '1606576891910.jpeg', 'jpeg', 87, 'http://localhost:9090/file/9f1bf1547bf64b2d85174b5db05ef4cf.jpeg', 'b3e7b531324f6cf4a990f7bc577c5814', 0, 1);
INSERT INTO `sys_file` VALUES (28, '1606576891910.jpeg', 'jpeg', 87, 'http://localhost:9090/file/9f1bf1547bf64b2d85174b5db05ef4cf.jpeg', 'b3e7b531324f6cf4a990f7bc577c5814', 0, 1);
INSERT INTO `sys_file` VALUES (29, 'gif_02-28-02.02.26.gif', 'gif', 718, 'http://localhost:9090/file/00381478cc4b40fda481df81a996afd8.gif', 'b125e7fecb354fcea834727fe03ab6d7', 0, 1);
INSERT INTO `sys_file` VALUES (30, 'gif_02-28-02.02.26.gif', 'gif', 718, 'http://localhost:9090/file/00381478cc4b40fda481df81a996afd8.gif', 'b125e7fecb354fcea834727fe03ab6d7', 0, 0);
INSERT INTO `sys_file` VALUES (31, '1606576891910.jpeg', 'jpeg', 87, 'http://localhost:9090/file/9f1bf1547bf64b2d85174b5db05ef4cf.jpeg', 'b3e7b531324f6cf4a990f7bc577c5814', 1, 1);
INSERT INTO `sys_file` VALUES (32, 'QQ图片20201108110118.jpg', 'jpg', 49, 'http://localhost:9090/file/219004018cc2414e83dd5aafd6c163d9.jpg', 'dde3c71e642f112140ce6ec3797bbf27', 0, 0);
INSERT INTO `sys_file` VALUES (33, '1606576891910.jpeg', 'jpeg', 87, 'http://localhost:9090/file/9f1bf1547bf64b2d85174b5db05ef4cf.jpeg', 'b3e7b531324f6cf4a990f7bc577c5814', 0, 0);
INSERT INTO `sys_file` VALUES (34, 'QQ图片20201108110118.jpg', 'jpg', 49, 'http://localhost:9090/file/219004018cc2414e83dd5aafd6c163d9.jpg', 'dde3c71e642f112140ce6ec3797bbf27', 0, 0);
INSERT INTO `sys_file` VALUES (35, 'QQ图片20201108110118.jpg', 'jpg', 49, 'http://localhost:9090/file/219004018cc2414e83dd5aafd6c163d9.jpg', 'dde3c71e642f112140ce6ec3797bbf27', 0, 0);
INSERT INTO `sys_file` VALUES (36, 'QQ图片20201108110118.jpg', 'jpg', 49, 'http://localhost:9090/file/219004018cc2414e83dd5aafd6c163d9.jpg', 'dde3c71e642f112140ce6ec3797bbf27', 0, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码\r\n',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '123456', 'icatw', '762188827@qq.com', '173****0398', '湖南', '2022-04-26 00:00:00', 'https://img-blog.csdnimg.cn/20200706094157708.jpg');
INSERT INTO `sys_user` VALUES (2, 'icatw', '123456', 'icatw', '762188827@qq.com', '173****0398', '湖南', '2022-04-26 00:00:00', 'http://localhost:9090/file/219004018cc2414e83dd5aafd6c163d9.jpg');

SET FOREIGN_KEY_CHECKS = 1;
