/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50517
Source Host           : localhost:3306
Source Database       : xiaoming

Target Server Type    : MYSQL
Target Server Version : 50517
File Encoding         : 65001

Date: 2016-09-21 14:51:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for absence
-- ----------------------------
DROP TABLE IF EXISTS `absence`;
CREATE TABLE `absence` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`CONTENT`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MEBMER`  bigint(20) NULL DEFAULT NULL ,
`ABSENCETIME`  datetime NULL DEFAULT NULL ,
`CREATETIME`  datetime NULL DEFAULT NULL ,
`MEMBER`  bigint(20) NULL DEFAULT NULL ,
`UPDATETIME`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`MEBMER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`MEMBER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_9omiobjt4ausm8ipt58xfrijs` (`MEBMER`) USING BTREE ,
INDEX `FK_aetwo5bn0jrcwybw2lldbkeib` (`MEMBER`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=5

;

-- ----------------------------
-- Records of absence
-- ----------------------------
BEGIN;
INSERT INTO `absence` VALUES ('1', '这个请假内容', '1', '2016-08-01 17:01:13', '2016-08-01 17:01:13', '1', null), ('2', '这个请假内容', '1', '2016-08-01 17:01:46', '2016-08-01 17:01:46', '1', null), ('3', '这个请假内容', '1', '2016-08-01 17:15:51', '2016-08-01 17:15:51', '1', null), ('4', '这个请假内容', '1', '2016-08-01 17:17:44', '2016-08-01 17:17:44', '1', null);
COMMIT;

-- ----------------------------
-- Table structure for absenceapply
-- ----------------------------
DROP TABLE IF EXISTS `absenceapply`;
CREATE TABLE `absenceapply` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`RECEIVER`  bigint(20) NULL DEFAULT NULL ,
`ISAGREE`  bit(1) NULL DEFAULT NULL ,
`ISHANDLED`  bit(1) NULL DEFAULT NULL ,
`HANDLETIME`  datetime NULL DEFAULT NULL ,
`ABSENCE`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`ABSENCE`) REFERENCES `absence` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`RECEIVER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_bve666udo9opqq8t6wnncbbkv` (`RECEIVER`) USING BTREE ,
INDEX `FK_2nwsr1m0bjllep3ch2359jntw` (`ABSENCE`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=5

;

-- ----------------------------
-- Records of absenceapply
-- ----------------------------
BEGIN;
INSERT INTO `absenceapply` VALUES ('1', '1', '', '', '2016-08-02 11:14:36', '1'), ('2', '1', '\0', '', '2016-08-02 11:14:46', '2'), ('3', '1', '\0', '\0', null, '3'), ('4', '1', '\0', '\0', null, '4');
COMMIT;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ACTIVITYTIME`  datetime NULL DEFAULT NULL ,
`CREATETIME`  datetime NULL DEFAULT NULL ,
`ACITVITYCONTENT`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BELONGTO`  bigint(20) NULL DEFAULT NULL ,
`CREATER`  bigint(20) NULL DEFAULT NULL ,
`COUNTER`  int(11) NULL DEFAULT NULL ,
`INFO`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`TITLE`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CONTENT`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ORGANIZATION`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`BELONGTO`) REFERENCES `organization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`CREATER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_47hgb5kk0yn46m2w1fi2r6ov4` (`BELONGTO`) USING BTREE ,
INDEX `FK_bua2r3ic0j7gkc8r4ujhqsh2o` (`CREATER`) USING BTREE ,
INDEX `FK_5jf62vxm942ls56nbpwtgjc2e` (`ORGANIZATION`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=9

;

-- ----------------------------
-- Records of activity
-- ----------------------------
BEGIN;
INSERT INTO `activity` VALUES ('1', null, '2016-08-10 14:00:27', null, null, '1', null, '', '吃饭', '拿外卖', '1'), ('2', null, '2016-08-10 15:02:06', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '吃饭', '吃饭吃啥', '1'), ('3', null, '2016-08-10 15:03:08', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '吃饭', '吃饭吃啥', '1'), ('4', null, '2016-08-10 15:05:49', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '吃饭', '吃饭吃啥', '1'), ('6', null, '2016-08-14 16:33:39', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '新活动', '明天', '1'), ('7', null, '2016-08-14 16:35:42', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '活动来了', '你要参加吗', '1'), ('8', null, '2016-08-14 16:38:31', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '活动来了', '你要参加吗', '1');
COMMIT;

-- ----------------------------
-- Table structure for activityenroll
-- ----------------------------
DROP TABLE IF EXISTS `activityenroll`;
CREATE TABLE `activityenroll` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`USERS`  bigint(20) NULL DEFAULT NULL ,
`SIGNUPTIME`  datetime NULL DEFAULT NULL ,
`ACTIVITY`  bigint(20) NULL DEFAULT NULL ,
`INFO`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`MEMBER`  bigint(20) NULL DEFAULT NULL ,
`ENROLLTIME`  datetime NULL DEFAULT NULL ,
`ISHANDLED`  bit(1) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`ACTIVITY`) REFERENCES `activity` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`MEMBER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`USERS`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_sf11jopfdt7g08nmsft89eaxu` (`USERS`) USING BTREE ,
INDEX `FK_30ouog298a3kxf1s2uy2xdken` (`ACTIVITY`) USING BTREE ,
INDEX `FK_s4p7n8ctaimedh15ae9rnpsxu` (`MEMBER`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=17

;

-- ----------------------------
-- Records of activityenroll
-- ----------------------------
BEGIN;
INSERT INTO `activityenroll` VALUES ('1', null, null, '1', '', '1', null, '\0'), ('2', null, null, '1', '', '4', null, '\0'), ('3', null, null, '2', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0'), ('4', null, null, '2', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0'), ('5', null, null, '3', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0'), ('6', null, null, '3', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0'), ('7', null, null, '4', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0'), ('8', null, null, '4', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0'), ('11', null, null, '6', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0'), ('12', null, null, '6', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0'), ('13', null, null, '7', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0'), ('14', null, null, '7', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0'), ('15', null, null, '8', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0'), ('16', null, null, '8', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0');
COMMIT;

-- ----------------------------
-- Table structure for assignment
-- ----------------------------
DROP TABLE IF EXISTS `assignment`;
CREATE TABLE `assignment` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`CONTENT`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PUBLISHDATE`  datetime NULL DEFAULT NULL ,
`DEADLINE`  datetime NULL DEFAULT NULL ,
`ISVALID`  bit(1) NULL DEFAULT NULL ,
`UPDATETIME`  datetime NULL DEFAULT NULL ,
`PUBLISHER`  bigint(20) NULL DEFAULT NULL ,
`PROJECT_ID`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`PROJECT_ID`) REFERENCES `project` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`PUBLISHER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_njcfdm0yh76myhwn06f60a11d` (`PUBLISHER`) USING BTREE ,
INDEX `FK_lvv8chinfcmk57ugta15jr3ij` (`PROJECT_ID`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=15

;

-- ----------------------------
-- Records of assignment
-- ----------------------------
BEGIN;
INSERT INTO `assignment` VALUES ('8', '这个工作被修改了', '2016-07-31 16:38:45', '2016-08-01 12:13:08', '', '2016-08-01 12:13:08', '1', '9'), ('9', '这个工作被修改了', '2016-07-31 18:50:03', '2016-08-09 13:46:36', '', '2016-08-09 13:46:36', '1', '18'), ('10', '新的工作', '2016-07-31 18:56:46', '2016-07-31 18:56:46', '', '2016-07-31 18:56:46', '1', '4'), ('11', '新的工作', '2016-07-31 18:57:54', '2016-07-31 18:57:54', '', '2016-07-31 18:57:54', '1', '5'), ('12', '新的工作', '2016-07-31 19:03:26', '2016-07-31 19:03:26', '\0', '2016-07-31 19:03:26', '1', '6'), ('13', null, null, null, '\0', null, null, null), ('14', '这个工作被修改了', '2016-08-09 13:47:33', '2016-08-09 13:47:33', '', '2016-08-09 13:47:33', '1', '19');
COMMIT;

-- ----------------------------
-- Table structure for assignmentmember
-- ----------------------------
DROP TABLE IF EXISTS `assignmentmember`;
CREATE TABLE `assignmentmember` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`EXERCISER`  bigint(20) NULL DEFAULT NULL ,
`FINISHTIME`  datetime NULL DEFAULT NULL ,
`ISFINISHED`  bit(1) NULL DEFAULT NULL ,
`ASSIGNMENT`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`ASSIGNMENT`) REFERENCES `assignment` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`EXERCISER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_jlqyr2mnw9i2126hr48ous7e7` (`EXERCISER`) USING BTREE ,
INDEX `FK_hpj68taieojg0h96e2yf6kew8` (`ASSIGNMENT`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=41

;

-- ----------------------------
-- Records of assignmentmember
-- ----------------------------
BEGIN;
INSERT INTO `assignmentmember` VALUES ('15', '4', null, '\0', '12'), ('16', '1', null, '\0', '12'), ('17', '4', null, '\0', '13'), ('18', '1', null, '\0', '13'), ('19', '1', null, '\0', '8'), ('20', '4', null, '\0', '8'), ('21', '1', null, '\0', '9'), ('22', '4', null, '\0', '9'), ('23', '1', null, '\0', '9'), ('24', '4', null, '\0', '9'), ('25', '1', null, '\0', '9'), ('26', '4', null, '\0', '9'), ('27', '4', null, '\0', '9'), ('28', '1', null, '\0', '9'), ('29', '1', null, '\0', '9'), ('30', '4', null, '\0', '9'), ('31', '4', null, '\0', '9'), ('32', '1', null, '\0', '9'), ('33', '1', null, '\0', '9'), ('34', '4', null, '\0', '9'), ('35', '1', null, '\0', '9'), ('36', '4', null, '\0', '9'), ('37', '4', null, '\0', '9'), ('38', '1', null, '\0', '9'), ('39', '1', null, '\0', '14'), ('40', '4', null, '\0', '14');
COMMIT;

-- ----------------------------
-- Table structure for campus
-- ----------------------------
DROP TABLE IF EXISTS `campus`;
CREATE TABLE `campus` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UNIVERSITY_ID`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`UNIVERSITY_ID`) REFERENCES `university` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_gbddrfjn8tu7fwb7mtsjm68yi` (`UNIVERSITY_ID`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=8

;

-- ----------------------------
-- Records of campus
-- ----------------------------
BEGIN;
INSERT INTO `campus` VALUES ('1', '龙洞校区', '1'), ('2', '大学城校区', '1'), ('3', '东风路校区', '1'), ('4', '番禺校区', '1'), ('5', '龙洞校区', '1'), ('6', '大学城', '1'), ('7', '东风路', '1');
COMMIT;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`GRADE`  bigint(20) NULL DEFAULT NULL ,
`MAJOR`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`GRADE`) REFERENCES `grade` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`MAJOR`) REFERENCES `major` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_2geedy50mr8ry36syy8mo0od8` (`GRADE`) USING BTREE ,
INDEX `FK_re0jypucgi6om24fl2u5ogqa8` (`MAJOR`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of clazz
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ORGANIZATION`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_ge6u98rhrsmufxlxk5mc0wjmx` (`ORGANIZATION`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=4

;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` VALUES ('1', '技术部', '1'), ('2', '宣传部', '1'), ('3', '秘书部', '1');
COMMIT;

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`FILETYPE`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`URL`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UPLOADER`  bigint(20) NULL DEFAULT NULL ,
`ORGANIZATION`  bigint(20) NULL DEFAULT NULL ,
`UPLOADTIME`  datetime NULL DEFAULT NULL ,
`isPublic`  bit(1) NULL DEFAULT NULL ,
`FOLDER`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`FOLDER`) REFERENCES `folder` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`UPLOADER`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_iggtp8imn91d5hnyb8weygmcx` (`UPLOADER`) USING BTREE ,
INDEX `FK_ng4whqdw88g8prrgfo2un20w8` (`ORGANIZATION`) USING BTREE ,
INDEX `FK_5qe90s8tyo3elru0h3r01fre4` (`FOLDER`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of document
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for dynamicstate
-- ----------------------------
DROP TABLE IF EXISTS `dynamicstate`;
CREATE TABLE `dynamicstate` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`OPERATOR`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`DESCRIPTION`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MEMBER`  bigint(20) NULL DEFAULT NULL ,
`PROJECT`  bigint(20) NULL DEFAULT NULL ,
`ASSIGNMENT`  bigint(20) NULL DEFAULT NULL ,
`OPERATIME`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`PROJECT`) REFERENCES `project` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`MEMBER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`ASSIGNMENT`) REFERENCES `assignment` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_b6k49mahukyl6q6jolac65fof` (`MEMBER`) USING BTREE ,
INDEX `FK_31hwehuhqbavjhmfo9hpv72dd` (`PROJECT`) USING BTREE ,
INDEX `FK_t679ow9j56ovmoqd4deij9oj2` (`ASSIGNMENT`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=17

;

-- ----------------------------
-- Records of dynamicstate
-- ----------------------------
BEGIN;
INSERT INTO `dynamicstate` VALUES ('1', null, '收到了小明布置的任务：这个工作被修改了', '1', '19', '14', '2016-08-09 13:47:33'), ('2', null, '收到了小明布置的任务：这个工作被修改了', '4', '19', '14', '2016-08-09 13:47:33'), ('3', null, '添加了任务：这个工作被修改了', '1', '19', '14', '2016-08-09 13:47:33'), ('4', null, '的萨芬阿萨德发生的费大幅', '1', '19', '14', '2016-09-03 18:01:25'), ('5', null, '啊斯蒂芬撒旦法萨芬的', '1', '19', '14', '2016-09-03 18:01:39'), ('6', null, '撒谎复活节枯叶投入于', '1', '19', '14', '2016-09-03 18:01:51'), ('7', null, '穆it月如同一同一间讨厌异界iuk一就', '1', '19', '14', '2016-09-03 18:02:05'), ('8', null, '5好地方听日工会速度公司三国斯蒂芬', '1', '19', '14', '2016-09-03 18:02:25'), ('9', null, '地方吗uy加工费刷得过而特啊任务监狱兔', '1', '19', '14', '2016-09-03 18:02:37'), ('11', null, '晕晕晕晕晕晕晕晕晕晕晕晕晕晕晕', '1', '19', '14', '2016-09-03 18:02:51'), ('13', null, '钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱', '1', '19', '14', '2016-09-03 18:03:15'), ('15', null, '停停停停停停停停停停停停停停停停停', '1', '19', '14', '2016-09-03 18:03:32'), ('16', null, '冰冰冰冰冰冰冰冰冰冰冰冰冰冰', '1', '19', '14', '2016-09-03 18:04:16');
COMMIT;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`USER`  bigint(20) NULL DEFAULT NULL ,
`FEEDBACKCONTENT`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SUBMITTIME`  datetime NULL DEFAULT NULL ,
`REPLYTIME`  datetime NULL DEFAULT NULL ,
`REPLY`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`USER`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_me8wq7ma7d14b5j7wmkiw28dq` (`USER`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of feedback
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for folder
-- ----------------------------
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`DATE`  datetime NULL DEFAULT NULL ,
`AVARIBALE`  datetime NULL DEFAULT NULL ,
`ORGANIZATION`  bigint(20) NULL DEFAULT NULL ,
`PARENT`  bigint(20) NULL DEFAULT NULL ,
`AVARIABLE`  bit(1) NULL DEFAULT NULL ,
`ISPUBLIC`  bit(1) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`PARENT`) REFERENCES `folder` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_rhfi5o2wr59w2malppw8m6pyo` (`ORGANIZATION`) USING BTREE ,
INDEX `FK_3v7ur8hpvi3omlnp1nyh1b0hf` (`PARENT`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of folder
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2

;

-- ----------------------------
-- Records of grade
-- ----------------------------
BEGIN;
INSERT INTO `grade` VALUES ('1', '13级');
COMMIT;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`URL`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IMAGENAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UPLOADTIME`  datetime NULL DEFAULT NULL ,
`UPLOADER`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`UPLOADER`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_m3rph11ljaqk2w3sb3v5csksl` (`UPLOADER`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2

;

-- ----------------------------
-- Records of image
-- ----------------------------
BEGIN;
INSERT INTO `image` VALUES ('1', '/logo1.jpg', 'logo1', '2016-08-02 13:04:05', '1');
COMMIT;

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SCHOOL`  bigint(20) NULL DEFAULT NULL ,
`UNIVERSITY`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`UNIVERSITY`) REFERENCES `university` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`SCHOOL`) REFERENCES `school` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_pfpi22vri921sf6uqd4e5sqmk` (`SCHOOL`) USING BTREE ,
INDEX `FK_5ooikg8atbcj58s2bffco8fk` (`UNIVERSITY`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2

;

-- ----------------------------
-- Records of major
-- ----------------------------
BEGIN;
INSERT INTO `major` VALUES ('1', '信管', '1', '1');
COMMIT;

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`TOTALCOUNT`  int(11) NULL DEFAULT NULL ,
`LENTCOUNT`  int(11) NULL DEFAULT NULL ,
`EXISTCOUNT`  int(11) NULL DEFAULT NULL ,
`STORAGELOCATION`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ORGANIZATION`  bigint(20) NULL DEFAULT NULL ,
`CREATETIME`  datetime NULL DEFAULT NULL ,
`ISDELETED`  bit(1) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_rldbpo16ec2rhj24jhbftttyw` (`ORGANIZATION`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of material
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for materiallendlog
-- ----------------------------
DROP TABLE IF EXISTS `materiallendlog`;
CREATE TABLE `materiallendlog` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`COUNT`  int(11) NULL DEFAULT NULL ,
`BORROWERNAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BORROWERPHONENUMBER`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BORROWDATE`  datetime NULL DEFAULT NULL ,
`REVERTDATE`  datetime NULL DEFAULT NULL ,
`STATE`  smallint(6) NULL DEFAULT NULL ,
`MATERIAL_ID`  bigint(20) NULL DEFAULT NULL ,
`REVERTCOUNT`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`MATERIAL_ID`) REFERENCES `material` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_lmvuwtpds4l5prq41e9n9i4fe` (`MATERIAL_ID`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of materiallendlog
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for materiallendlogchild
-- ----------------------------
DROP TABLE IF EXISTS `materiallendlogchild`;
CREATE TABLE `materiallendlogchild` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`REVERTTIME`  datetime NULL DEFAULT NULL ,
`COUNT`  int(11) NULL DEFAULT NULL ,
`FATHERLOG`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`FATHERLOG`) REFERENCES `materiallendlog` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_6qoipc4b9ve8rji7xjprlicje` (`FATHERLOG`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of materiallendlogchild
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`POSITION`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`EDITION`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ISCURRENTEDITION`  bit(1) NULL DEFAULT NULL ,
`USER`  bigint(20) NULL DEFAULT NULL ,
`DEPARTMENT`  bigint(20) NULL DEFAULT NULL ,
`STATE`  tinyint(4) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`USER`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`DEPARTMENT`) REFERENCES `department` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_1lr88o6t0yc663hkcs5lb9mfh` (`USER`) USING BTREE ,
INDEX `FK_n5c6ydoxbi21d0tvixtmdjwxd` (`DEPARTMENT`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=5

;

-- ----------------------------
-- Records of member
-- ----------------------------
BEGIN;
INSERT INTO `member` VALUES ('1', '部长', '2014', '', '1', '1', null), ('4', '干事', '2014', '', '2', '1', null);
COMMIT;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`CONTENT`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PUBLISHTIME`  datetime NULL DEFAULT NULL ,
`PUBLISHER`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`PUBLISHER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_dehptuh17oqxgx2x8qas1akwk` (`PUBLISHER`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of message
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CONTACTNAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CONTACTPHONENUMBER`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OFFICEADDRESS`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`LOGO`  bigint(20) NULL DEFAULT NULL ,
`ORGDESCRIPTION`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ISPUBLIC`  bit(1) NULL DEFAULT NULL ,
`CAMPUS`  bigint(20) NULL DEFAULT NULL ,
`CURRENTEDITION`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`CAMPUS`) REFERENCES `campus` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`LOGO`) REFERENCES `image` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_me9c61wy5rp8ly63rwjr5aufg` (`LOGO`) USING BTREE ,
INDEX `FK_jobjvjib5i7bvu528g8admue7` (`CAMPUS`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=4

;

-- ----------------------------
-- Records of organization
-- ----------------------------
BEGIN;
INSERT INTO `organization` VALUES ('1', '东海龙宫', '东海龙王', null, null, '1', null, '', '1', null), ('2', '大唐官府', '程咬金', null, null, '1', null, '', '2', null), ('3', '追追社团', null, '13249016630', null, null, null, null, '1', null);
COMMIT;

-- ----------------------------
-- Table structure for organizationoperation
-- ----------------------------
DROP TABLE IF EXISTS `organizationoperation`;
CREATE TABLE `organizationoperation` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`TIME`  datetime NULL DEFAULT NULL ,
`ORGANIZATION`  bigint(20) NULL DEFAULT NULL ,
`DESCRIPTION`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_2ycicu5ba4noekeqfiwqfynf0` (`ORGANIZATION`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of organizationoperation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for orgjoinapplication
-- ----------------------------
DROP TABLE IF EXISTS `orgjoinapplication`;
CREATE TABLE `orgjoinapplication` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`USER`  bigint(20) NULL DEFAULT NULL ,
`ORGANIZATION`  bigint(20) NULL DEFAULT NULL ,
`APPLYTIME`  datetime NULL DEFAULT NULL ,
`ISPASSED`  bit(1) NULL DEFAULT NULL ,
`REASON`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`HANDLETIME`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`USER`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_svi9toc16th1kyl5ok4j877bp` (`USER`) USING BTREE ,
INDEX `FK_69qbwql0t63o4uxwgbinl06py` (`ORGANIZATION`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of orgjoinapplication
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PUBLISHTIME`  datetime NULL DEFAULT NULL ,
`PUBLISHER`  bigint(20) NULL DEFAULT NULL ,
`ORGANIZATION`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`PUBLISHER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_biiunpxcu2duxo8q3f541cna7` (`PUBLISHER`) USING BTREE ,
INDEX `FK_anb9dwtcchd4035rm324hqc9r` (`ORGANIZATION`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=20

;

-- ----------------------------
-- Records of project
-- ----------------------------
BEGIN;
INSERT INTO `project` VALUES ('2', null, null, null, null), ('3', '开发', null, '1', '1'), ('4', '开发', null, '1', '1'), ('5', '开发', null, '1', '1'), ('6', '开发', null, '1', '1'), ('7', '开发', null, '1', '1'), ('9', '开发', null, '1', '1'), ('10', '开发', null, '1', '1'), ('11', '开发', null, '1', '1'), ('12', '开发', null, '1', '1'), ('13', '开发', null, '1', '1'), ('14', '开发', null, '1', '1'), ('15', '开发', null, '1', '1'), ('16', '开发', null, '1', '1'), ('17', '开发', null, '1', '1'), ('18', '开发', null, '1', '1'), ('19', '开发', null, '1', '1');
COMMIT;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CAMPUS`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`CAMPUS`) REFERENCES `campus` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_6de4i4f8wh1qlqapfevjoh58v` (`CAMPUS`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2

;

-- ----------------------------
-- Records of school
-- ----------------------------
BEGIN;
INSERT INTO `school` VALUES ('1', '管理学院', '1');
COMMIT;

-- ----------------------------
-- Table structure for systemmessage
-- ----------------------------
DROP TABLE IF EXISTS `systemmessage`;
CREATE TABLE `systemmessage` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`CONTENT`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PUBLISHTIME`  datetime NULL DEFAULT NULL ,
`TOALL`  bit(1) NULL DEFAULT NULL ,
`TARGET`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ISDELETED`  bit(1) NULL DEFAULT NULL ,
`USER`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`USER`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_ffs8r28vlbq2cqmm05r4n1p87` (`USER`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of systemmessage
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for systemmessageuser
-- ----------------------------
DROP TABLE IF EXISTS `systemmessageuser`;
CREATE TABLE `systemmessageuser` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`MESSAGE`  bigint(20) NULL DEFAULT NULL ,
`USER`  bigint(20) NULL DEFAULT NULL ,
`ISREAD`  bit(1) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`MESSAGE`) REFERENCES `systemmessage` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`USER`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_8jjbniatsjh4frr6hoowlg6al` (`MESSAGE`) USING BTREE ,
INDEX `FK_iu8lpu7typgyt2uvdlmsrb5g` (`USER`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of systemmessageuser
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for university
-- ----------------------------
DROP TABLE IF EXISTS `university`;
CREATE TABLE `university` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of university
-- ----------------------------
BEGIN;
INSERT INTO `university` VALUES ('1', '广东工业大学'), ('2', '宇宙供热大学');
COMMIT;

-- ----------------------------
-- Table structure for unread
-- ----------------------------
DROP TABLE IF EXISTS `unread`;
CREATE TABLE `unread` (
`ID`  bigint(20) NOT NULL ,
`ABSENCE`  int(11) NULL DEFAULT NULL ,
`ACTIVITY`  int(11) NULL DEFAULT NULL ,
`ASSIGNMENT`  int(11) NULL DEFAULT NULL ,
`MESSAGE`  int(11) NULL DEFAULT NULL ,
`SYSTEMMESSAGE`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of unread
-- ----------------------------
BEGIN;
INSERT INTO `unread` VALUES ('1', '0', '1', '0', '0', '0'), ('4', '0', '3', '0', '0', '0');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`LOGINNAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PASSWORD`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`REGISTERDATE`  datetime NULL DEFAULT NULL ,
`LASTLOGINTIME`  datetime NULL DEFAULT NULL ,
`LASTLOGINIP`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ROLE`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`REALNAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`GENDER`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`LOGO`  bigint(20) NULL DEFAULT NULL ,
`CAMPUS`  bigint(20) NULL DEFAULT NULL ,
`GRADE`  bigint(20) NULL DEFAULT NULL ,
`defaultMember`  bigint(20) NULL DEFAULT NULL ,
`ORGANIZATION`  bigint(20) NULL DEFAULT NULL ,
`ISVALID`  bit(1) NULL DEFAULT NULL ,
`CLAZZ`  bigint(20) NULL DEFAULT NULL ,
`PHONENUMBER`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MAJOR`  bigint(20) NULL DEFAULT NULL ,
`STATE`  tinyint(4) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`CLAZZ`) REFERENCES `clazz` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`CAMPUS`) REFERENCES `campus` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`MAJOR`) REFERENCES `major` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`LOGO`) REFERENCES `image` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`defaultMember`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`GRADE`) REFERENCES `grade` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_a5wudnlfb9i28s2we4rm4g2a5` (`LOGO`) USING BTREE ,
INDEX `FK_8tbl0qrvuiia2a2iwgf8fxkbo` (`CAMPUS`) USING BTREE ,
INDEX `FK_mhsht1ku8k0fwkr217fqp8kpi` (`GRADE`) USING BTREE ,
INDEX `FK_ksjh8x0v61nt418ka3j02noi2` (`defaultMember`) USING BTREE ,
INDEX `FK_o5duyqr06nbh26hmqj3nfosnq` (`ORGANIZATION`) USING BTREE ,
INDEX `FK_2y81wugorvkoy5q3uo2durjys` (`CLAZZ`) USING BTREE ,
INDEX `FK_947uuqofk1mv9wcmiaexem41a` (`MAJOR`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=10

;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'abc', 'e10adc3949ba59abbe56e057f20f883e', '2016-07-31 14:02:08', '2016-09-01 18:01:53', '本地', 'ORG_USER', '小明', null, null, null, null, '1', null, '', null, null, null, '1'), ('2', 'daming', 'e10adc3949ba59abbe56e057f20f883e', '2016-07-31 14:08:47', null, null, 'ORG_USER', '大明', null, null, null, null, '4', null, '', null, null, null, '1'), ('8', 'jjj', '3abf00fa61bfae2fff9133375e142416', '2016-09-02 21:04:30', '2016-09-04 04:17:30', '本地', 'ORG_USER', null, 'FEMALE', '1', '7', '1', '1', null, '', null, '15622189056', '1', '1'), ('9', 'qqq', '343b1c4a3ea721b2d640fc8700db0f36', '2016-09-02 21:09:18', '2016-09-04 04:18:25', '本地', 'ORG_ADMIN', null, 'MALE', '1', '1', '1', '1', '3', '', null, '13249016630', '1', '1');
COMMIT;

-- ----------------------------
-- Table structure for userregister
-- ----------------------------
DROP TABLE IF EXISTS `userregister`;
CREATE TABLE `userregister` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`CAPTCHA`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ISVALID`  bit(1) NULL DEFAULT NULL ,
`CREATETIME`  datetime NULL DEFAULT NULL ,
`USER`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`USER`) REFERENCES `user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_4y744u500i8swusxud920gua7` (`USER`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of userregister
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for usersmessage
-- ----------------------------
DROP TABLE IF EXISTS `usersmessage`;
CREATE TABLE `usersmessage` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`RECEIVER`  bigint(20) NULL DEFAULT NULL ,
`ISREAD`  bit(1) NULL DEFAULT NULL ,
`MESSAGE`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
FOREIGN KEY (`RECEIVER`) REFERENCES `member` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`MESSAGE`) REFERENCES `message` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_6xrlwti19njxnqawfyttlnoex` (`RECEIVER`) USING BTREE ,
INDEX `FK_icc0l2qvf5mkak8gwnm1s0eo4` (`MESSAGE`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of usersmessage
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Auto increment value for absence
-- ----------------------------
ALTER TABLE `absence` AUTO_INCREMENT=5;

-- ----------------------------
-- Auto increment value for absenceapply
-- ----------------------------
ALTER TABLE `absenceapply` AUTO_INCREMENT=5;

-- ----------------------------
-- Auto increment value for activity
-- ----------------------------
ALTER TABLE `activity` AUTO_INCREMENT=9;

-- ----------------------------
-- Auto increment value for activityenroll
-- ----------------------------
ALTER TABLE `activityenroll` AUTO_INCREMENT=17;

-- ----------------------------
-- Auto increment value for assignment
-- ----------------------------
ALTER TABLE `assignment` AUTO_INCREMENT=15;

-- ----------------------------
-- Auto increment value for assignmentmember
-- ----------------------------
ALTER TABLE `assignmentmember` AUTO_INCREMENT=41;

-- ----------------------------
-- Auto increment value for campus
-- ----------------------------
ALTER TABLE `campus` AUTO_INCREMENT=8;

-- ----------------------------
-- Auto increment value for clazz
-- ----------------------------
ALTER TABLE `clazz` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for department
-- ----------------------------
ALTER TABLE `department` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for document
-- ----------------------------
ALTER TABLE `document` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for dynamicstate
-- ----------------------------
ALTER TABLE `dynamicstate` AUTO_INCREMENT=17;

-- ----------------------------
-- Auto increment value for feedback
-- ----------------------------
ALTER TABLE `feedback` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for folder
-- ----------------------------
ALTER TABLE `folder` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for grade
-- ----------------------------
ALTER TABLE `grade` AUTO_INCREMENT=2;

-- ----------------------------
-- Auto increment value for image
-- ----------------------------
ALTER TABLE `image` AUTO_INCREMENT=2;

-- ----------------------------
-- Auto increment value for major
-- ----------------------------
ALTER TABLE `major` AUTO_INCREMENT=2;

-- ----------------------------
-- Auto increment value for material
-- ----------------------------
ALTER TABLE `material` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for materiallendlog
-- ----------------------------
ALTER TABLE `materiallendlog` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for materiallendlogchild
-- ----------------------------
ALTER TABLE `materiallendlogchild` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for member
-- ----------------------------
ALTER TABLE `member` AUTO_INCREMENT=5;

-- ----------------------------
-- Auto increment value for message
-- ----------------------------
ALTER TABLE `message` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for organization
-- ----------------------------
ALTER TABLE `organization` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for organizationoperation
-- ----------------------------
ALTER TABLE `organizationoperation` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for orgjoinapplication
-- ----------------------------
ALTER TABLE `orgjoinapplication` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for project
-- ----------------------------
ALTER TABLE `project` AUTO_INCREMENT=20;

-- ----------------------------
-- Auto increment value for school
-- ----------------------------
ALTER TABLE `school` AUTO_INCREMENT=2;

-- ----------------------------
-- Auto increment value for systemmessage
-- ----------------------------
ALTER TABLE `systemmessage` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for systemmessageuser
-- ----------------------------
ALTER TABLE `systemmessageuser` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for university
-- ----------------------------
ALTER TABLE `university` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for user
-- ----------------------------
ALTER TABLE `user` AUTO_INCREMENT=10;

-- ----------------------------
-- Auto increment value for userregister
-- ----------------------------
ALTER TABLE `userregister` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for usersmessage
-- ----------------------------
ALTER TABLE `usersmessage` AUTO_INCREMENT=1;
