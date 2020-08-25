/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : localhost:3306
 Source Schema         : cblog

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 25/08/2020 14:44:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆名称',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员显示昵称',
  `locked` tinyint(4) NULL DEFAULT 0 COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'yuanfu', 'a66abb5684c45962d887564f08346e8d', 'yuanfu', 0);
INSERT INTO `admin_user` VALUES (2, 'cyf', 'e10adc3949ba59abbe56e057f20f883e', 'chenyf', 0);

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `blog_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客表主键id',
  `blog_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客标题',
  `blog_sub_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客自定义路径url',
  `blog_cover_image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客封面图',
  `blog_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客内容',
  `blog_category_id` int(11) NOT NULL COMMENT '博客分类id',
  `blog_category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客分类(冗余字段)',
  `blog_tags` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客标签',
  `blog_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-草稿 1-发布',
  `blog_views` bigint(20) NOT NULL DEFAULT 0 COMMENT '阅读量',
  `enable_comment` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-允许评论 1-不允许评论',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`blog_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, '测试博客', 'about', 'http://localhost:8080/upload/20200807_13240032.png', 'testtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt', 0, '默认分类', '高并发', 1, 15, 0, 0, '2020-07-26 13:41:53', '2020-07-26 13:41:56');
INSERT INTO `blog` VALUES (7, '用户测试', 'aaa', 'http://localhost:8080', 'sdgdfwehofhdslkghldkfjsdhglsdjflashgfhweodjasgjhlkdsjfhlhglfwajsdlghaldsjglgahsdlfgoweoinvoasdnosndsf', 0, '默认分类', '测试', 1, 1, 0, 0, '2020-08-10 10:25:01', '2020-08-10 10:25:03');
INSERT INTO `blog` VALUES (8, '大威天龙', '', 'http://localhost:8080/upload/20200807_13195689.png', '# 测试2\ndfjsslghkjsdflkahglajsglghljflda和公司感觉哈啰的发货啦是腹肌阿什拉夫 阿萨德狠辣的后果法拉赫拉世纪东方朗ad发了个花了多少飞机哎懒得搞打了个喝辣的解放啦大V甲氨蝶呤共和噢IG女神泪的房间里很给力撒酒疯了问你了感受到了跟你说收到了受到估计是代理方式联合国老师讲的付临时工是否登录时代光华了十多家发来的山沟沟龙卷风老大哥打了附近水利电力十多号来分了数据打了个降温哦哦拿到手了开发商来个少了等号分离式多功能论文就是来说的两个抗世纪东方联合国冷恋时代佛教', 0, '默认分类', '词语,文章,搞笑', 1, 1, 0, 0, '2020-08-07 09:27:51', '2020-08-07 09:27:51');
INSERT INTO `blog` VALUES (9, '罗汉番天印', '', 'http://localhost:8080/upload/20200807_13275173.jpg', '导航京东方懒得搞火蓝刀锋个登录和发来的数据发收到两个华为欧三等奖老牛舐犊公司的发送到了更合适李开复男的发涉猎但是来得及发牢骚灯火阑珊的房间里沙发所发生的了实例好的斐林试剂的分类还是单工商量定价发傻掉了啥登录还是点了份蓝色大海拉三等奖flash的昂拉分类大公会阿里飞回来都是阿里手拉手横拦竖挡拉水电费拉三等奖是读后感商量定价发领到水果海量数据的分类十来个傻掉了啥的公司的连接是对方商量定价发蓝色大海了和我if加了我IE烦死你代理费十来个江苏大连福利费都是 欢乐谷 数据多了结了婚公司你干啥借我安过啦伽古拉刚看到ins绯闻给老师发了发顺丰身份横老师傅了数据范围就饿哦更好额饭都额送安慰你是佛教为欧冠男生肯定粉嫩佛斯蒂芬侯文峰公司大佛师傅好发发火发发好好放松卡佛为防溺水OA非农暗黑风\n![测试](http://localhost:8080/upload/20200807_14123657.jpg \"测试\")', 1, 'java', '武功,佛教,神仙', 1, 0, 0, 0, '2020-08-07 13:29:08', '2020-08-07 13:29:08');

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类表主键',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类的名称',
  `category_icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类的图标',
  `category_rank` int(11) NOT NULL DEFAULT 1 COMMENT '分类的排序值 被使用的越多数值越大',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_category
-- ----------------------------
INSERT INTO `blog_category` VALUES (1, 'java', '/admin/dist/img/category/02.png', 5, 0, '2020-07-26 13:43:11');
INSERT INTO `blog_category` VALUES (2, 'ios', '/admin/dist/img/category/01.png', 1, 0, '2020-08-07 07:23:25');
INSERT INTO `blog_category` VALUES (3, '默认分类', '/admin/dist/img/category/00.png', 1, 0, '2020-08-07 07:23:51');
INSERT INTO `blog_category` VALUES (4, 'android', '/admin/dist/img/category/03.png', 1, 0, '2020-08-07 07:24:18');
INSERT INTO `blog_category` VALUES (5, 'mysql', '/admin/dist/img/category/04.png', 1, 0, '2020-08-07 07:24:29');
INSERT INTO `blog_category` VALUES (6, '前端', '/admin/dist/img/category/08.png', 1, 0, '2020-08-07 07:24:50');
INSERT INTO `blog_category` VALUES (7, 'linux', '/admin/dist/img/category/13.png', 1, 0, '2020-08-07 07:25:00');
INSERT INTO `blog_category` VALUES (8, 'docker', '/admin/dist/img/category/11.png', 1, 0, '2020-08-07 07:25:15');

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `blog_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联的blog主键',
  `commentator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论者名称',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论人的邮箱',
  `website_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '网址',
  `comment_body` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论内容',
  `comment_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '评论提交时间',
  `commentator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论时的ip地址',
  `reply_body` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '回复内容',
  `reply_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '回复时间',
  `comment_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否审核通过 0-未审核 1-审核通过',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客评论' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `blog_comment` VALUES (1, 1, 'yuanfu', '3125659849@qq.com', '', '欢迎在下方评论', '2020-07-26 17:53:26', '127.0.0.1', '无', '2020-07-26 17:54:10', 1, 1);
INSERT INTO `blog_comment` VALUES (2, 1, '大威天龙', '1233252523@168.com', '', '写的好，奥利给', '2020-08-04 15:59:35', '', '谢谢', '2020-08-07 07:52:05', 1, 0);

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签表主键id',
  `tag_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `use_num` bigint(20) NOT NULL COMMENT '标签使用次数',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES (1, '高并发', 11, 0, '2020-07-26 17:55:17');
INSERT INTO `blog_tag` VALUES (6, 'java', 3, 0, '2020-08-07 01:23:43');
INSERT INTO `blog_tag` VALUES (14, 'mysql', 1, 0, '2020-08-07 08:41:46');
INSERT INTO `blog_tag` VALUES (15, '测试', 1, 0, '2020-08-07 08:42:03');
INSERT INTO `blog_tag` VALUES (16, '随笔', 1, 0, '2020-08-07 08:42:24');
INSERT INTO `blog_tag` VALUES (17, '杂谈', 1, 0, '2020-08-07 08:42:33');

-- ----------------------------
-- Table structure for blog_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag_relation`;
CREATE TABLE `blog_tag_relation`  (
  `relation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关系表id',
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  PRIMARY KEY (`relation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客标签关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_tag_relation
-- ----------------------------
INSERT INTO `blog_tag_relation` VALUES (15, 0, 8, '2020-08-07 05:29:16');
INSERT INTO `blog_tag_relation` VALUES (16, 0, 9, '2020-08-07 05:29:16');
INSERT INTO `blog_tag_relation` VALUES (17, 0, 10, '2020-08-07 05:29:16');
INSERT INTO `blog_tag_relation` VALUES (21, 9, 8, '2020-08-07 05:39:20');
INSERT INTO `blog_tag_relation` VALUES (22, 9, 9, '2020-08-07 05:39:20');
INSERT INTO `blog_tag_relation` VALUES (23, 9, 10, '2020-08-07 05:39:20');

-- ----------------------------
-- Table structure for friend_link
-- ----------------------------
DROP TABLE IF EXISTS `friend_link`;
CREATE TABLE `friend_link`  (
  `link_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '友链表主键id',
  `link_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '友链类别 0-友链 1-推荐 2-个人网站',
  `link_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站名称',
  `link_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站链接',
  `link_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站描述',
  `link_rank` int(11) NOT NULL DEFAULT 0 COMMENT '用于列表排序',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '友链表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of friend_link
-- ----------------------------
INSERT INTO `friend_link` VALUES (2, 0, 'csdn', 'https://me.csdn.net/qq_36929638', 'chenyf的csdn主页', 3, 0, '2020-08-07 09:39:04');
INSERT INTO `friend_link` VALUES (5, 2, 'cblog', 'http://localhost:8080', '个人博客网站', 1, 0, '2020-08-07 09:43:20');
INSERT INTO `friend_link` VALUES (6, 0, 'github', 'https://github.com/yuanfu999', 'github个人主页', 4, 0, '2020-08-07 09:43:04');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配置项的名称',
  `config_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配置项的值',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`config_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('footerAbout', 'CBlog. have fun.', '2018-11-11 20:33:23', '2020-08-10 01:31:10');
INSERT INTO `sys_config` VALUES ('footerCopyRight', '2020 yuanfu', '2018-11-11 20:33:31', '2020-08-10 01:31:10');
INSERT INTO `sys_config` VALUES ('footerICP', '沪ICP备17081265号-3', '2018-11-11 20:33:27', '2020-08-10 01:31:10');
INSERT INTO `sys_config` VALUES ('footerPoweredBy', 'https://github.com/yuanfu999', '2018-11-11 20:33:36', '2020-08-10 01:31:10');
INSERT INTO `sys_config` VALUES ('footerPoweredByURL', 'https://github.com/yuanfu999', '2018-11-11 20:33:39', '2020-08-10 01:31:10');
INSERT INTO `sys_config` VALUES ('websiteDescription', 'CBlog是SpringBoot2+Thymeleaf+Mybatis建造的个人博客网站.', '2018-11-11 20:33:04', '2018-11-11 22:05:14');
INSERT INTO `sys_config` VALUES ('websiteIcon', '/admin/dist/img/favicon.png', '2018-11-11 20:33:11', '2018-11-11 22:05:14');
INSERT INTO `sys_config` VALUES ('websiteLogo', '/admin/dist/img/user2-160x160.jpg', '2018-11-11 20:33:08', '2018-11-11 22:05:14');
INSERT INTO `sys_config` VALUES ('websiteName', 'personal blog', '2018-11-11 20:33:01', '2018-11-11 22:05:14');
INSERT INTO `sys_config` VALUES ('yourAvatar', '/admin/dist/img/user2-160x160.jpg', '2018-11-11 20:33:14', '2019-05-07 21:56:23');
INSERT INTO `sys_config` VALUES ('yourEmail', '3125659849@qq.com', '2018-11-11 20:33:17', '2019-05-07 21:56:23');
INSERT INTO `sys_config` VALUES ('yourName', 'yuanfu', '2018-11-11 20:33:20', '2019-05-07 21:56:23');

-- ----------------------------
-- Table structure for user_blog_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_blog_relation`;
CREATE TABLE `user_blog_relation`  (
  `id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `blog_id` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_blog_relation
-- ----------------------------
INSERT INTO `user_blog_relation` VALUES (1, 1, 1, '2020-08-10 09:55:46');
INSERT INTO `user_blog_relation` VALUES (2, 1, 8, '2020-08-10 09:56:08');
INSERT INTO `user_blog_relation` VALUES (3, 1, 9, '2020-08-10 09:56:26');

SET FOREIGN_KEY_CHECKS = 1;
