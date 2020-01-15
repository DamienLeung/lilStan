SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `content` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章内容',
  `browse_count` int(11) NULL DEFAULT NULL COMMENT '浏览次数',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `publish_username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人名称',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发布人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '如何做一名合格的Java工程师？', '如何做一名合格的Java工程师', 1011, '2019-07-11 16:30:21', 'admin', 1);
INSERT INTO `article` VALUES (2, '学会自立', '学会自立', 246, '2019-01-11 21:30:29', 'admin', 1);
INSERT INTO `article` VALUES (3, '在逆境中生长', '在逆境中生长', 748, '2018-01-11 16:14:11', 'dfbz', 13);
INSERT INTO `article` VALUES (4, '阳光总在风雨后', '阳光总在风雨后', 147, '2019-07-11 16:30:21', 'xiaobiao', 11);
INSERT INTO `article` VALUES (5, '我真的很不错', '我真的很不错', 257, '2019-07-11 16:30:21', 'xiaodong', 2);
INSERT INTO `article` VALUES (6, '你若安好便是晴天', '你若安好便是晴天', 159, '2019-06-08 16:12:21', 'xiaozhun', 12);
INSERT INTO `article` VALUES (8, '入门到精通', '删库跑路(rm /rf /*)', 2589545, '2019-07-24 10:52:31', 'xiaozhun', 12);
INSERT INTO `article` VALUES (9, 'Java入门到精通', 'Java入门到精通', 112, '2019-07-14 10:52:31', 'xiaozhun', 12);
INSERT INTO `article` VALUES (10, 'Spring入门到精通', 'Java入门到精通', 543, '2019-04-24 10:52:31', 'xiaozhun', 12);
INSERT INTO `article` VALUES (11, 'SpringMVC入门到精通', 'Java入门到精通', 23, '2019-02-24 10:52:31', 'xiaozhun', 12);
INSERT INTO `article` VALUES (12, 'SpringBoot入门到精通', 'Java入门到精通', 47, '2019-01-24 10:52:31', 'xiaozhun', 12);
INSERT INTO `article` VALUES (13, 'MyBatis入门到精通', 'Java入门到精通', 232, '2019-07-24 10:52:31', 'xiaozhun', 12);
INSERT INTO `article` VALUES (14, '23423424', '23423', 21, '2019-07-25 10:47:54', 'admin', 1);
INSERT INTO `article` VALUES (15, '东方标准', '东方标准真好', 21, '2019-07-25 10:55:06', 'admin', 1);
INSERT INTO `article` VALUES (16, '今天天气好晴朗', '今天天气好晴朗，处处好风光，好风光', 0, '2019-07-25 12:48:34', 'admin', 1);

-- ----------------------------
-- Table structure for con_join
-- ----------------------------
DROP TABLE IF EXISTS `con_join`;
CREATE TABLE `con_join`  (
  `id` int(11) NOT NULL,
  `u_id` int(11) NULL DEFAULT NULL COMMENT '需参加人id',
  `c_id` int(11) NULL DEFAULT NULL COMMENT '会议id',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态: 0:未参加 1:已参加',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id_con_join_rk`(`u_id`) USING BTREE,
  INDEX `c_id_con_join_rk`(`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for conference
-- ----------------------------
DROP TABLE IF EXISTS `conference`;
CREATE TABLE `conference`  (
  `id` int(11) NOT NULL,
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议内容',
  `publish_date` date NULL DEFAULT NULL COMMENT '发布日期',
  `start_time` time(0) NULL DEFAULT NULL COMMENT '开始时间',
  `status` int(1) NULL DEFAULT NULL COMMENT '会议状态 0:未开始 1:进行中 2:已结束',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dept_id_conference_rk`(`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of conference
-- ----------------------------
INSERT INTO `conference` VALUES (1, '研发部', 1, '关于小标交友部门功能升级', '小标交友有如下问题：1、会议模块需要升级，2、文章模块需要升级，3、用户模块需要升级', '2019-07-27', '18:19:18', 1);

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '研发部');
INSERT INTO `dept` VALUES (2, '推广部');
INSERT INTO `dept` VALUES (3, '行政部');
INSERT INTO `dept` VALUES (4, '财务部');
INSERT INTO `dept` VALUES (5, '总裁办公室');
INSERT INTO `dept` VALUES (7, '秘书部');

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NULL DEFAULT NULL COMMENT '收藏用户id',
  `a_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id_favorite_rk`(`u_id`) USING BTREE,
  INDEX `a_id_favorite_rk`(`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES (1, 1, 1);
INSERT INTO `favorite` VALUES (2, 1, 15);
INSERT INTO `favorite` VALUES (3, 3, 1);
INSERT INTO `favorite` VALUES (4, 1, 9);
INSERT INTO `favorite` VALUES (5, 2, 9);


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `is_secret` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0：公开(默认值) 1：私密',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门Id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dept_id`(`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '0', 'zijun1024@aliyun.com', 5);
INSERT INTO `user` VALUES (2, 'xiaodong', 'admin', '0', 'xiaobiao@dfbz.com', 2);
INSERT INTO `user` VALUES (3, 'xiaofang', 'admin', '0', 'xiaofang@dfbz.com', 1);
INSERT INTO `user` VALUES (4, 'xiaobiao', 'admin', '0', 'xiaobiao@dfbz.com', 3);
INSERT INTO `user` VALUES (5, 'xiaozhun', 'admin', '0', 'xiaozhun@dfbz.com', 4);
INSERT INTO `user` VALUES (6, 'dfbz', 'admin', '0', 'dfbz@dfbz.com', 5);
INSERT INTO `user` VALUES (7, 'xiaoming', 'admin', '0', 'xiaoming@dfbz.com', NULL);

-- ----------------------------
-- Table structure for user_focus
-- ----------------------------
DROP TABLE IF EXISTS `user_focus`;
CREATE TABLE `user_focus`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `user_focus_id` int(10) NOT NULL COMMENT '用户关注的朋友id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_userFocus_user_1`(`user_id`) USING BTREE,
  INDEX `fk_userFocus_user_2`(`user_focus_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户id',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别: ‘0’-男   ‘1’-女',
  `desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '自我介绍',
  `register_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '上一次登录时间',
  `pic` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `look` int(10) NULL DEFAULT NULL COMMENT '查看数',
  INDEX `uid`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '总裁', 20, '1101', '0', '你很帅', '2019-05-22 23:27:56', '2019-07-26 15:04:49', 'http://localhost:8080/update/d7e680f1-3e77-4eac-945f-499be2d9e5a31.png', 10);
INSERT INTO `user_info` VALUES (2, '小东', 30, '110', '1', '我很帅', '2019-05-08 23:28:00', '2019-07-24 14:32:21', 'http://img06.jiuxian.com/2019/0620/5134b2ac721c402496066045b7a8dbb52.jpg', 4);
INSERT INTO `user_info` VALUES (3, '小方', 10, '110', '1', '我很帅', '2019-05-26 11:32:16', '2019-05-26 23:09:40', 'http://img06.jiuxian.com/2019/0620/5134b2ac721c402496066045b7a8dbb52.jpg', 0);
INSERT INTO `user_info` VALUES (4, '小标', 10, '110', '1', '我很帅', '2019-05-26 11:38:00', '2019-05-26 23:09:40', 'http://img06.jiuxian.com/2019/0620/5134b2ac721c402496066045b7a8dbb52.jpg', 0);
INSERT INTO `user_info` VALUES (5, '小准', 20, '110', '1', '我很帅', '2019-05-26 11:38:06', '2019-05-26 23:09:40', 'http://img06.jiuxian.com/2019/0620/5134b2ac721c402496066045b7a8dbb52.jpg', 0);
INSERT INTO `user_info` VALUES (6, '东方标准总裁', 20, '110', '0', '我很帅', '2019-05-26 11:38:13', '2019-05-26 23:09:40', 'http://img06.jiuxian.com/2019/0620/5134b2ac721c402496066045b7a8dbb52.jpg', 14);
INSERT INTO `user_info` VALUES (7, 'xiaoming', NULL, NULL, NULL, NULL, '2019-07-25 16:53:35', '2019-07-25 16:53:35', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
