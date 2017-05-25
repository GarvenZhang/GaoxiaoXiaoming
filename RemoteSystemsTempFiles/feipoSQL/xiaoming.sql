/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : xiaoming

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2016-09-04 16:08:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for absence
-- ----------------------------
DROP TABLE IF EXISTS `absence`;
CREATE TABLE `absence` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(255) DEFAULT NULL,
  `MEBMER` bigint(20) DEFAULT NULL,
  `ABSENCETIME` datetime DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `MEMBER` bigint(20) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_9omiobjt4ausm8ipt58xfrijs` (`MEBMER`),
  KEY `FK_aetwo5bn0jrcwybw2lldbkeib` (`MEMBER`),
  CONSTRAINT `FK_9omiobjt4ausm8ipt58xfrijs` FOREIGN KEY (`MEBMER`) REFERENCES `member` (`ID`),
  CONSTRAINT `FK_aetwo5bn0jrcwybw2lldbkeib` FOREIGN KEY (`MEMBER`) REFERENCES `member` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of absence
-- ----------------------------
INSERT INTO `absence` VALUES ('1', '这个请假内容', '1', '2016-08-01 17:01:13', '2016-08-01 17:01:13', '1', null);
INSERT INTO `absence` VALUES ('2', '这个请假内容', '1', '2016-08-01 17:01:46', '2016-08-01 17:01:46', '1', null);
INSERT INTO `absence` VALUES ('3', '这个请假内容', '1', '2016-08-01 17:15:51', '2016-08-01 17:15:51', '1', null);
INSERT INTO `absence` VALUES ('4', '这个请假内容', '1', '2016-08-01 17:17:44', '2016-08-01 17:17:44', '1', null);

-- ----------------------------
-- Table structure for absenceapply
-- ----------------------------
DROP TABLE IF EXISTS `absenceapply`;
CREATE TABLE `absenceapply` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RECEIVER` bigint(20) DEFAULT NULL,
  `ISAGREE` bit(1) DEFAULT NULL,
  `ISHANDLED` bit(1) DEFAULT NULL,
  `HANDLETIME` datetime DEFAULT NULL,
  `ABSENCE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_bve666udo9opqq8t6wnncbbkv` (`RECEIVER`),
  KEY `FK_2nwsr1m0bjllep3ch2359jntw` (`ABSENCE`),
  CONSTRAINT `FK_2nwsr1m0bjllep3ch2359jntw` FOREIGN KEY (`ABSENCE`) REFERENCES `absence` (`ID`),
  CONSTRAINT `FK_bve666udo9opqq8t6wnncbbkv` FOREIGN KEY (`RECEIVER`) REFERENCES `member` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of absenceapply
-- ----------------------------
INSERT INTO `absenceapply` VALUES ('1', '1', '', '', '2016-08-02 11:14:36', '1');
INSERT INTO `absenceapply` VALUES ('2', '1', '\0', '', '2016-08-02 11:14:46', '2');
INSERT INTO `absenceapply` VALUES ('3', '1', '\0', '\0', null, '3');
INSERT INTO `absenceapply` VALUES ('4', '1', '\0', '\0', null, '4');

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVITYTIME` datetime DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `ACITVITYCONTENT` varchar(255) DEFAULT NULL,
  `BELONGTO` bigint(20) DEFAULT NULL,
  `CREATER` bigint(20) DEFAULT NULL,
  `COUNTER` int(11) DEFAULT NULL,
  `INFO` longtext,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `ORGANIZATION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_47hgb5kk0yn46m2w1fi2r6ov4` (`BELONGTO`),
  KEY `FK_bua2r3ic0j7gkc8r4ujhqsh2o` (`CREATER`),
  KEY `FK_5jf62vxm942ls56nbpwtgjc2e` (`ORGANIZATION`),
  CONSTRAINT `FK_47hgb5kk0yn46m2w1fi2r6ov4` FOREIGN KEY (`BELONGTO`) REFERENCES `organization` (`ID`),
  CONSTRAINT `FK_5jf62vxm942ls56nbpwtgjc2e` FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`),
  CONSTRAINT `FK_bua2r3ic0j7gkc8r4ujhqsh2o` FOREIGN KEY (`CREATER`) REFERENCES `member` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', null, '2016-08-10 14:00:27', null, null, '1', null, '', '吃饭', '拿外卖', '1');
INSERT INTO `activity` VALUES ('2', null, '2016-08-10 15:02:06', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '吃饭', '吃饭吃啥', '1');
INSERT INTO `activity` VALUES ('3', null, '2016-08-10 15:03:08', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '吃饭', '吃饭吃啥', '1');
INSERT INTO `activity` VALUES ('4', null, '2016-08-10 15:05:49', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '吃饭', '吃饭吃啥', '1');
INSERT INTO `activity` VALUES ('6', null, '2016-08-14 16:33:39', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '新活动', '明天', '1');
INSERT INTO `activity` VALUES ('7', null, '2016-08-14 16:35:42', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '活动来了', '你要参加吗', '1');
INSERT INTO `activity` VALUES ('8', null, '2016-08-14 16:38:31', null, null, '1', null, '{\"birth\":\"1998\",\"size\":\"xx\"}', '活动来了', '你要参加吗', '1');

-- ----------------------------
-- Table structure for activityenroll
-- ----------------------------
DROP TABLE IF EXISTS `activityenroll`;
CREATE TABLE `activityenroll` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USERS` bigint(20) DEFAULT NULL,
  `SIGNUPTIME` datetime DEFAULT NULL,
  `ACTIVITY` bigint(20) DEFAULT NULL,
  `INFO` longtext,
  `MEMBER` bigint(20) DEFAULT NULL,
  `ENROLLTIME` datetime DEFAULT NULL,
  `ISHANDLED` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_sf11jopfdt7g08nmsft89eaxu` (`USERS`),
  KEY `FK_30ouog298a3kxf1s2uy2xdken` (`ACTIVITY`),
  KEY `FK_s4p7n8ctaimedh15ae9rnpsxu` (`MEMBER`),
  CONSTRAINT `FK_30ouog298a3kxf1s2uy2xdken` FOREIGN KEY (`ACTIVITY`) REFERENCES `activity` (`ID`),
  CONSTRAINT `FK_s4p7n8ctaimedh15ae9rnpsxu` FOREIGN KEY (`MEMBER`) REFERENCES `member` (`ID`),
  CONSTRAINT `FK_sf11jopfdt7g08nmsft89eaxu` FOREIGN KEY (`USERS`) REFERENCES `member` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activityenroll
-- ----------------------------
INSERT INTO `activityenroll` VALUES ('1', null, null, '1', '', '1', null, '\0');
INSERT INTO `activityenroll` VALUES ('2', null, null, '1', '', '4', null, '\0');
INSERT INTO `activityenroll` VALUES ('3', null, null, '2', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0');
INSERT INTO `activityenroll` VALUES ('4', null, null, '2', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0');
INSERT INTO `activityenroll` VALUES ('5', null, null, '3', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0');
INSERT INTO `activityenroll` VALUES ('6', null, null, '3', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0');
INSERT INTO `activityenroll` VALUES ('7', null, null, '4', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0');
INSERT INTO `activityenroll` VALUES ('8', null, null, '4', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0');
INSERT INTO `activityenroll` VALUES ('11', null, null, '6', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0');
INSERT INTO `activityenroll` VALUES ('12', null, null, '6', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0');
INSERT INTO `activityenroll` VALUES ('13', null, null, '7', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0');
INSERT INTO `activityenroll` VALUES ('14', null, null, '7', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0');
INSERT INTO `activityenroll` VALUES ('15', null, null, '8', '{\"birth\":\"1998\",\"size\":\"xx\"}', '4', null, '\0');
INSERT INTO `activityenroll` VALUES ('16', null, null, '8', '{\"birth\":\"1998\",\"size\":\"xx\"}', '1', null, '\0');

-- ----------------------------
-- Table structure for assignment
-- ----------------------------
DROP TABLE IF EXISTS `assignment`;
CREATE TABLE `assignment` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(255) DEFAULT NULL,
  `PUBLISHDATE` datetime DEFAULT NULL,
  `DEADLINE` datetime DEFAULT NULL,
  `ISVALID` bit(1) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  `PUBLISHER` bigint(20) DEFAULT NULL,
  `PROJECT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_njcfdm0yh76myhwn06f60a11d` (`PUBLISHER`),
  KEY `FK_lvv8chinfcmk57ugta15jr3ij` (`PROJECT_ID`),
  CONSTRAINT `FK_lvv8chinfcmk57ugta15jr3ij` FOREIGN KEY (`PROJECT_ID`) REFERENCES `project` (`ID`),
  CONSTRAINT `FK_njcfdm0yh76myhwn06f60a11d` FOREIGN KEY (`PUBLISHER`) REFERENCES `member` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assignment
-- ----------------------------
INSERT INTO `assignment` VALUES ('8', '这个工作被修改了', '2016-07-31 16:38:45', '2016-08-01 12:13:08', '', '2016-08-01 12:13:08', '1', '9');
INSERT INTO `assignment` VALUES ('9', '这个工作被修改了', '2016-07-31 18:50:03', '2016-08-09 13:46:36', '', '2016-08-09 13:46:36', '1', '18');
INSERT INTO `assignment` VALUES ('10', '新的工作', '2016-07-31 18:56:46', '2016-07-31 18:56:46', '', '2016-07-31 18:56:46', '1', '4');
INSERT INTO `assignment` VALUES ('11', '新的工作', '2016-07-31 18:57:54', '2016-07-31 18:57:54', '', '2016-07-31 18:57:54', '1', '5');
INSERT INTO `assignment` VALUES ('12', '新的工作', '2016-07-31 19:03:26', '2016-07-31 19:03:26', '\0', '2016-07-31 19:03:26', '1', '6');
INSERT INTO `assignment` VALUES ('13', null, null, null, '\0', null, null, null);
INSERT INTO `assignment` VALUES ('14', '这个工作被修改了', '2016-08-09 13:47:33', '2016-08-09 13:47:33', '', '2016-08-09 13:47:33', '1', '19');

-- ----------------------------
-- Table structure for assignmentmember
-- ----------------------------
DROP TABLE IF EXISTS `assignmentmember`;
CREATE TABLE `assignmentmember` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EXERCISER` bigint(20) DEFAULT NULL,
  `FINISHTIME` datetime DEFAULT NULL,
  `ISFINISHED` bit(1) DEFAULT NULL,
  `ASSIGNMENT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_jlqyr2mnw9i2126hr48ous7e7` (`EXERCISER`),
  KEY `FK_hpj68taieojg0h96e2yf6kew8` (`ASSIGNMENT`),
  CONSTRAINT `FK_hpj68taieojg0h96e2yf6kew8` FOREIGN KEY (`ASSIGNMENT`) REFERENCES `assignment` (`ID`),
  CONSTRAINT `FK_jlqyr2mnw9i2126hr48ous7e7` FOREIGN KEY (`EXERCISER`) REFERENCES `member` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assignmentmember
-- ----------------------------
INSERT INTO `assignmentmember` VALUES ('15', '4', null, '\0', '12');
INSERT INTO `assignmentmember` VALUES ('16', '1', null, '\0', '12');
INSERT INTO `assignmentmember` VALUES ('17', '4', null, '\0', '13');
INSERT INTO `assignmentmember` VALUES ('18', '1', null, '\0', '13');
INSERT INTO `assignmentmember` VALUES ('19', '1', null, '\0', '8');
INSERT INTO `assignmentmember` VALUES ('20', '4', null, '\0', '8');
INSERT INTO `assignmentmember` VALUES ('21', '1', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('22', '4', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('23', '1', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('24', '4', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('25', '1', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('26', '4', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('27', '4', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('28', '1', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('29', '1', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('30', '4', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('31', '4', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('32', '1', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('33', '1', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('34', '4', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('35', '1', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('36', '4', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('37', '4', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('38', '1', null, '\0', '9');
INSERT INTO `assignmentmember` VALUES ('39', '1', null, '\0', '14');
INSERT INTO `assignmentmember` VALUES ('40', '4', null, '\0', '14');

-- ----------------------------
-- Table structure for campus
-- ----------------------------
DROP TABLE IF EXISTS `campus`;
CREATE TABLE `campus` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `UNIVERSITY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_gbddrfjn8tu7fwb7mtsjm68yi` (`UNIVERSITY_ID`),
  CONSTRAINT `FK_gbddrfjn8tu7fwb7mtsjm68yi` FOREIGN KEY (`UNIVERSITY_ID`) REFERENCES `university` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of campus
-- ----------------------------
INSERT INTO `campus` VALUES ('1', '龙洞校区', '1');
INSERT INTO `campus` VALUES ('2', '大学城校区', '1');
INSERT INTO `campus` VALUES ('3', '东风路校区', '1');
INSERT INTO `campus` VALUES ('4', '番禺校区', '1');
INSERT INTO `campus` VALUES ('5', '龙洞校区', '1');
INSERT INTO `campus` VALUES ('6', '大学城', '1');
INSERT INTO `campus` VALUES ('7', '东风路', '1');

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `GRADE` bigint(20) DEFAULT NULL,
  `MAJOR` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_2geedy50mr8ry36syy8mo0od8` (`GRADE`),
  KEY `FK_re0jypucgi6om24fl2u5ogqa8` (`MAJOR`),
  CONSTRAINT `FK_2geedy50mr8ry36syy8mo0od8` FOREIGN KEY (`GRADE`) REFERENCES `grade` (`ID`),
  CONSTRAINT `FK_re0jypucgi6om24fl2u5ogqa8` FOREIGN KEY (`MAJOR`) REFERENCES `major` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clazz
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `ORGANIZATION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ge6u98rhrsmufxlxk5mc0wjmx` (`ORGANIZATION`),
  CONSTRAINT `FK_ge6u98rhrsmufxlxk5mc0wjmx` FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '技术部', '1');
INSERT INTO `department` VALUES ('2', '宣传部', '1');
INSERT INTO `department` VALUES ('3', '秘书部', '1');

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `FILETYPE` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `UPLOADER` bigint(20) DEFAULT NULL,
  `ORGANIZATION` bigint(20) DEFAULT NULL,
  `UPLOADTIME` datetime DEFAULT NULL,
  `isPublic` bit(1) DEFAULT NULL,
  `FOLDER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_iggtp8imn91d5hnyb8weygmcx` (`UPLOADER`),
  KEY `FK_ng4whqdw88g8prrgfo2un20w8` (`ORGANIZATION`),
  KEY `FK_5qe90s8tyo3elru0h3r01fre4` (`FOLDER`),
  CONSTRAINT `FK_5qe90s8tyo3elru0h3r01fre4` FOREIGN KEY (`FOLDER`) REFERENCES `folder` (`ID`),
  CONSTRAINT `FK_iggtp8imn91d5hnyb8weygmcx` FOREIGN KEY (`UPLOADER`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK_ng4whqdw88g8prrgfo2un20w8` FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document
-- ----------------------------

-- ----------------------------
-- Table structure for dynamicstate
-- ----------------------------
DROP TABLE IF EXISTS `dynamicstate`;
CREATE TABLE `dynamicstate` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `MEMBER` bigint(20) DEFAULT NULL,
  `PROJECT` bigint(20) DEFAULT NULL,
  `ASSIGNMENT` bigint(20) DEFAULT NULL,
  `OPERATIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_b6k49mahukyl6q6jolac65fof` (`MEMBER`),
  KEY `FK_31hwehuhqbavjhmfo9hpv72dd` (`PROJECT`),
  KEY `FK_t679ow9j56ovmoqd4deij9oj2` (`ASSIGNMENT`),
  CONSTRAINT `FK_31hwehuhqbavjhmfo9hpv72dd` FOREIGN KEY (`PROJECT`) REFERENCES `project` (`ID`),
  CONSTRAINT `FK_b6k49mahukyl6q6jolac65fof` FOREIGN KEY (`MEMBER`) REFERENCES `member` (`ID`),
  CONSTRAINT `FK_t679ow9j56ovmoqd4deij9oj2` FOREIGN KEY (`ASSIGNMENT`) REFERENCES `assignment` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamicstate
-- ----------------------------
INSERT INTO `dynamicstate` VALUES ('1', null, '收到了小明布置的任务：这个工作被修改了', '1', '19', '14', '2016-08-09 13:47:33');
INSERT INTO `dynamicstate` VALUES ('2', null, '收到了小明布置的任务：这个工作被修改了', '4', '19', '14', '2016-08-09 13:47:33');
INSERT INTO `dynamicstate` VALUES ('3', null, '添加了任务：这个工作被修改了', '1', '19', '14', '2016-08-09 13:47:33');
INSERT INTO `dynamicstate` VALUES ('4', null, '的萨芬阿萨德发生的费大幅', '1', '19', '14', '2016-09-03 18:01:25');
INSERT INTO `dynamicstate` VALUES ('5', null, '啊斯蒂芬撒旦法萨芬的', '1', '19', '14', '2016-09-03 18:01:39');
INSERT INTO `dynamicstate` VALUES ('6', null, '撒谎复活节枯叶投入于', '1', '19', '14', '2016-09-03 18:01:51');
INSERT INTO `dynamicstate` VALUES ('7', null, '穆it月如同一同一间讨厌异界iuk一就', '1', '19', '14', '2016-09-03 18:02:05');
INSERT INTO `dynamicstate` VALUES ('8', null, '5好地方听日工会速度公司三国斯蒂芬', '1', '19', '14', '2016-09-03 18:02:25');
INSERT INTO `dynamicstate` VALUES ('9', null, '地方吗uy加工费刷得过而特啊任务监狱兔', '1', '19', '14', '2016-09-03 18:02:37');
INSERT INTO `dynamicstate` VALUES ('11', null, '晕晕晕晕晕晕晕晕晕晕晕晕晕晕晕', '1', '19', '14', '2016-09-03 18:02:51');
INSERT INTO `dynamicstate` VALUES ('13', null, '钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱', '1', '19', '14', '2016-09-03 18:03:15');
INSERT INTO `dynamicstate` VALUES ('15', null, '停停停停停停停停停停停停停停停停停', '1', '19', '14', '2016-09-03 18:03:32');
INSERT INTO `dynamicstate` VALUES ('16', null, '冰冰冰冰冰冰冰冰冰冰冰冰冰冰', '1', '19', '14', '2016-09-03 18:04:16');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER` bigint(20) DEFAULT NULL,
  `FEEDBACKCONTENT` varchar(255) DEFAULT NULL,
  `SUBMITTIME` datetime DEFAULT NULL,
  `REPLYTIME` datetime DEFAULT NULL,
  `REPLY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_me8wq7ma7d14b5j7wmkiw28dq` (`USER`),
  CONSTRAINT `FK_me8wq7ma7d14b5j7wmkiw28dq` FOREIGN KEY (`USER`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for folder
-- ----------------------------
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `DATE` datetime DEFAULT NULL,
  `AVARIBALE` datetime DEFAULT NULL,
  `ORGANIZATION` bigint(20) DEFAULT NULL,
  `PARENT` bigint(20) DEFAULT NULL,
  `AVARIABLE` bit(1) DEFAULT NULL,
  `ISPUBLIC` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_rhfi5o2wr59w2malppw8m6pyo` (`ORGANIZATION`),
  KEY `FK_3v7ur8hpvi3omlnp1nyh1b0hf` (`PARENT`),
  CONSTRAINT `FK_3v7ur8hpvi3omlnp1nyh1b0hf` FOREIGN KEY (`PARENT`) REFERENCES `folder` (`ID`),
  CONSTRAINT `FK_rhfi5o2wr59w2malppw8m6pyo` FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of folder
-- ----------------------------

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', '13级');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `URL` varchar(255) DEFAULT NULL,
  `IMAGENAME` varchar(255) DEFAULT NULL,
  `UPLOADTIME` datetime DEFAULT NULL,
  `UPLOADER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_m3rph11ljaqk2w3sb3v5csksl` (`UPLOADER`),
  CONSTRAINT `FK_m3rph11ljaqk2w3sb3v5csksl` FOREIGN KEY (`UPLOADER`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('1', '/logo1.jpg', 'logo1', '2016-08-02 13:04:05', '1');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `SCHOOL` bigint(20) DEFAULT NULL,
  `UNIVERSITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_pfpi22vri921sf6uqd4e5sqmk` (`SCHOOL`),
  KEY `FK_5ooikg8atbcj58s2bffco8fk` (`UNIVERSITY`),
  CONSTRAINT `FK_5ooikg8atbcj58s2bffco8fk` FOREIGN KEY (`UNIVERSITY`) REFERENCES `university` (`ID`),
  CONSTRAINT `FK_pfpi22vri921sf6uqd4e5sqmk` FOREIGN KEY (`SCHOOL`) REFERENCES `school` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('1', '信管', '1', '1');

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `TOTALCOUNT` int(11) DEFAULT NULL,
  `LENTCOUNT` int(11) DEFAULT NULL,
  `EXISTCOUNT` int(11) DEFAULT NULL,
  `STORAGELOCATION` varchar(255) DEFAULT NULL,
  `ORGANIZATION` bigint(20) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `ISDELETED` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_rldbpo16ec2rhj24jhbftttyw` (`ORGANIZATION`),
  CONSTRAINT `FK_rldbpo16ec2rhj24jhbftttyw` FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------

-- ----------------------------
-- Table structure for materiallendlog
-- ----------------------------
DROP TABLE IF EXISTS `materiallendlog`;
CREATE TABLE `materiallendlog` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COUNT` int(11) DEFAULT NULL,
  `BORROWERNAME` varchar(255) DEFAULT NULL,
  `BORROWERPHONENUMBER` varchar(255) DEFAULT NULL,
  `BORROWDATE` datetime DEFAULT NULL,
  `REVERTDATE` datetime DEFAULT NULL,
  `STATE` smallint(6) DEFAULT NULL,
  `MATERIAL_ID` bigint(20) DEFAULT NULL,
  `REVERTCOUNT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_lmvuwtpds4l5prq41e9n9i4fe` (`MATERIAL_ID`),
  CONSTRAINT `FK_lmvuwtpds4l5prq41e9n9i4fe` FOREIGN KEY (`MATERIAL_ID`) REFERENCES `material` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of materiallendlog
-- ----------------------------

-- ----------------------------
-- Table structure for materiallendlogchild
-- ----------------------------
DROP TABLE IF EXISTS `materiallendlogchild`;
CREATE TABLE `materiallendlogchild` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REVERTTIME` datetime DEFAULT NULL,
  `COUNT` int(11) DEFAULT NULL,
  `FATHERLOG` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_6qoipc4b9ve8rji7xjprlicje` (`FATHERLOG`),
  CONSTRAINT `FK_6qoipc4b9ve8rji7xjprlicje` FOREIGN KEY (`FATHERLOG`) REFERENCES `materiallendlog` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of materiallendlogchild
-- ----------------------------

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `POSITION` varchar(255) DEFAULT NULL,
  `EDITION` varchar(255) DEFAULT NULL,
  `ISCURRENTEDITION` bit(1) DEFAULT NULL,
  `USER` bigint(20) DEFAULT NULL,
  `DEPARTMENT` bigint(20) DEFAULT NULL,
  `STATE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_1lr88o6t0yc663hkcs5lb9mfh` (`USER`),
  KEY `FK_n5c6ydoxbi21d0tvixtmdjwxd` (`DEPARTMENT`),
  CONSTRAINT `FK_1lr88o6t0yc663hkcs5lb9mfh` FOREIGN KEY (`USER`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK_n5c6ydoxbi21d0tvixtmdjwxd` FOREIGN KEY (`DEPARTMENT`) REFERENCES `department` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', '部长', '2014', '', '1', '1', null);
INSERT INTO `member` VALUES ('4', '干事', '2014', '', '2', '1', null);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(255) DEFAULT NULL,
  `PUBLISHTIME` datetime DEFAULT NULL,
  `PUBLISHER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_dehptuh17oqxgx2x8qas1akwk` (`PUBLISHER`),
  CONSTRAINT `FK_dehptuh17oqxgx2x8qas1akwk` FOREIGN KEY (`PUBLISHER`) REFERENCES `member` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `CONTACTNAME` varchar(255) DEFAULT NULL,
  `CONTACTPHONENUMBER` varchar(255) DEFAULT NULL,
  `OFFICEADDRESS` varchar(255) DEFAULT NULL,
  `LOGO` bigint(20) DEFAULT NULL,
  `ORGDESCRIPTION` varchar(255) DEFAULT NULL,
  `ISPUBLIC` bit(1) DEFAULT NULL,
  `CAMPUS` bigint(20) DEFAULT NULL,
  `CURRENTEDITION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_me9c61wy5rp8ly63rwjr5aufg` (`LOGO`),
  KEY `FK_jobjvjib5i7bvu528g8admue7` (`CAMPUS`),
  CONSTRAINT `FK_jobjvjib5i7bvu528g8admue7` FOREIGN KEY (`CAMPUS`) REFERENCES `campus` (`ID`),
  CONSTRAINT `FK_me9c61wy5rp8ly63rwjr5aufg` FOREIGN KEY (`LOGO`) REFERENCES `image` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('1', '东海龙宫', '东海龙王', null, null, '1', null, '', '1', null);
INSERT INTO `organization` VALUES ('2', '大唐官府', '程咬金', null, null, '1', null, '', '2', null);
INSERT INTO `organization` VALUES ('3', '追追社团', null, '13249016630', null, null, null, null, '1', null);

-- ----------------------------
-- Table structure for organizationoperation
-- ----------------------------
DROP TABLE IF EXISTS `organizationoperation`;
CREATE TABLE `organizationoperation` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TIME` datetime DEFAULT NULL,
  `ORGANIZATION` bigint(20) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_2ycicu5ba4noekeqfiwqfynf0` (`ORGANIZATION`),
  CONSTRAINT `FK_2ycicu5ba4noekeqfiwqfynf0` FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organizationoperation
-- ----------------------------

-- ----------------------------
-- Table structure for orgjoinapplication
-- ----------------------------
DROP TABLE IF EXISTS `orgjoinapplication`;
CREATE TABLE `orgjoinapplication` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER` bigint(20) DEFAULT NULL,
  `ORGANIZATION` bigint(20) DEFAULT NULL,
  `APPLYTIME` datetime DEFAULT NULL,
  `ISPASSED` bit(1) DEFAULT NULL,
  `REASON` varchar(255) DEFAULT NULL,
  `HANDLETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_svi9toc16th1kyl5ok4j877bp` (`USER`),
  KEY `FK_69qbwql0t63o4uxwgbinl06py` (`ORGANIZATION`),
  CONSTRAINT `FK_69qbwql0t63o4uxwgbinl06py` FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`),
  CONSTRAINT `FK_svi9toc16th1kyl5ok4j877bp` FOREIGN KEY (`USER`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orgjoinapplication
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `PUBLISHTIME` datetime DEFAULT NULL,
  `PUBLISHER` bigint(20) DEFAULT NULL,
  `ORGANIZATION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_biiunpxcu2duxo8q3f541cna7` (`PUBLISHER`),
  KEY `FK_anb9dwtcchd4035rm324hqc9r` (`ORGANIZATION`),
  CONSTRAINT `FK_anb9dwtcchd4035rm324hqc9r` FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`),
  CONSTRAINT `FK_biiunpxcu2duxo8q3f541cna7` FOREIGN KEY (`PUBLISHER`) REFERENCES `member` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('2', null, null, null, null);
INSERT INTO `project` VALUES ('3', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('4', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('5', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('6', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('7', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('9', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('10', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('11', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('12', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('13', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('14', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('15', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('16', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('17', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('18', '开发', null, '1', '1');
INSERT INTO `project` VALUES ('19', '开发', null, '1', '1');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `CAMPUS` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_6de4i4f8wh1qlqapfevjoh58v` (`CAMPUS`),
  CONSTRAINT `FK_6de4i4f8wh1qlqapfevjoh58v` FOREIGN KEY (`CAMPUS`) REFERENCES `campus` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('1', '管理学院', '1');

-- ----------------------------
-- Table structure for systemmessage
-- ----------------------------
DROP TABLE IF EXISTS `systemmessage`;
CREATE TABLE `systemmessage` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(255) DEFAULT NULL,
  `PUBLISHTIME` datetime DEFAULT NULL,
  `TOALL` bit(1) DEFAULT NULL,
  `TARGET` varchar(255) DEFAULT NULL,
  `ISDELETED` bit(1) DEFAULT NULL,
  `USER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ffs8r28vlbq2cqmm05r4n1p87` (`USER`),
  CONSTRAINT `FK_ffs8r28vlbq2cqmm05r4n1p87` FOREIGN KEY (`USER`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemmessage
-- ----------------------------

-- ----------------------------
-- Table structure for systemmessageuser
-- ----------------------------
DROP TABLE IF EXISTS `systemmessageuser`;
CREATE TABLE `systemmessageuser` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MESSAGE` bigint(20) DEFAULT NULL,
  `USER` bigint(20) DEFAULT NULL,
  `ISREAD` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_8jjbniatsjh4frr6hoowlg6al` (`MESSAGE`),
  KEY `FK_iu8lpu7typgyt2uvdlmsrb5g` (`USER`),
  CONSTRAINT `FK_8jjbniatsjh4frr6hoowlg6al` FOREIGN KEY (`MESSAGE`) REFERENCES `systemmessage` (`ID`),
  CONSTRAINT `FK_iu8lpu7typgyt2uvdlmsrb5g` FOREIGN KEY (`USER`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemmessageuser
-- ----------------------------

-- ----------------------------
-- Table structure for university
-- ----------------------------
DROP TABLE IF EXISTS `university`;
CREATE TABLE `university` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of university
-- ----------------------------
INSERT INTO `university` VALUES ('1', '广东工业大学');
INSERT INTO `university` VALUES ('2', '宇宙供热大学');

-- ----------------------------
-- Table structure for unread
-- ----------------------------
DROP TABLE IF EXISTS `unread`;
CREATE TABLE `unread` (
  `ID` bigint(20) NOT NULL,
  `ABSENCE` int(11) DEFAULT NULL,
  `ACTIVITY` int(11) DEFAULT NULL,
  `ASSIGNMENT` int(11) DEFAULT NULL,
  `MESSAGE` int(11) DEFAULT NULL,
  `SYSTEMMESSAGE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unread
-- ----------------------------
INSERT INTO `unread` VALUES ('1', '0', '1', '0', '0', '0');
INSERT INTO `unread` VALUES ('4', '0', '3', '0', '0', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGINNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `REGISTERDATE` datetime DEFAULT NULL,
  `LASTLOGINTIME` datetime DEFAULT NULL,
  `LASTLOGINIP` varchar(255) DEFAULT NULL,
  `ROLE` varchar(255) DEFAULT NULL,
  `REALNAME` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `LOGO` bigint(20) DEFAULT NULL,
  `CAMPUS` bigint(20) DEFAULT NULL,
  `GRADE` bigint(20) DEFAULT NULL,
  `defaultMember` bigint(20) DEFAULT NULL,
  `ORGANIZATION` bigint(20) DEFAULT NULL,
  `ISVALID` bit(1) DEFAULT NULL,
  `CLAZZ` bigint(20) DEFAULT NULL,
  `PHONENUMBER` varchar(255) DEFAULT NULL,
  `MAJOR` bigint(20) DEFAULT NULL,
  `STATE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_a5wudnlfb9i28s2we4rm4g2a5` (`LOGO`),
  KEY `FK_8tbl0qrvuiia2a2iwgf8fxkbo` (`CAMPUS`),
  KEY `FK_mhsht1ku8k0fwkr217fqp8kpi` (`GRADE`),
  KEY `FK_ksjh8x0v61nt418ka3j02noi2` (`defaultMember`),
  KEY `FK_o5duyqr06nbh26hmqj3nfosnq` (`ORGANIZATION`),
  KEY `FK_2y81wugorvkoy5q3uo2durjys` (`CLAZZ`),
  KEY `FK_947uuqofk1mv9wcmiaexem41a` (`MAJOR`),
  CONSTRAINT `FK_2y81wugorvkoy5q3uo2durjys` FOREIGN KEY (`CLAZZ`) REFERENCES `clazz` (`ID`),
  CONSTRAINT `FK_8tbl0qrvuiia2a2iwgf8fxkbo` FOREIGN KEY (`CAMPUS`) REFERENCES `campus` (`ID`),
  CONSTRAINT `FK_947uuqofk1mv9wcmiaexem41a` FOREIGN KEY (`MAJOR`) REFERENCES `major` (`ID`),
  CONSTRAINT `FK_a5wudnlfb9i28s2we4rm4g2a5` FOREIGN KEY (`LOGO`) REFERENCES `image` (`ID`),
  CONSTRAINT `FK_ksjh8x0v61nt418ka3j02noi2` FOREIGN KEY (`defaultMember`) REFERENCES `member` (`ID`),
  CONSTRAINT `FK_mhsht1ku8k0fwkr217fqp8kpi` FOREIGN KEY (`GRADE`) REFERENCES `grade` (`ID`),
  CONSTRAINT `FK_o5duyqr06nbh26hmqj3nfosnq` FOREIGN KEY (`ORGANIZATION`) REFERENCES `organization` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'abc', 'e10adc3949ba59abbe56e057f20f883e', '2016-07-31 14:02:08', '2016-09-01 18:01:53', '本地', 'ORG_USER', '小明', null, null, null, null, '1', null, '', null, null, null, '1');
INSERT INTO `user` VALUES ('2', 'daming', 'e10adc3949ba59abbe56e057f20f883e', '2016-07-31 14:08:47', null, null, 'ORG_USER', '大明', null, null, null, null, '4', null, '', null, null, null, '1');
INSERT INTO `user` VALUES ('8', 'jjj', '3abf00fa61bfae2fff9133375e142416', '2016-09-02 21:04:30', '2016-09-04 04:17:30', '本地', 'ORG_USER', null, 'FEMALE', '1', '7', '1', '1', null, '', null, '15622189056', '1', '1');
INSERT INTO `user` VALUES ('9', 'qqq', '343b1c4a3ea721b2d640fc8700db0f36', '2016-09-02 21:09:18', '2016-09-04 04:18:25', '本地', 'ORG_ADMIN', null, 'MALE', '1', '1', '1', '1', '3', '', null, '13249016630', '1', '1');

-- ----------------------------
-- Table structure for userregister
-- ----------------------------
DROP TABLE IF EXISTS `userregister`;
CREATE TABLE `userregister` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CAPTCHA` varchar(255) DEFAULT NULL,
  `ISVALID` bit(1) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `USER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_4y744u500i8swusxud920gua7` (`USER`),
  CONSTRAINT `FK_4y744u500i8swusxud920gua7` FOREIGN KEY (`USER`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userregister
-- ----------------------------

-- ----------------------------
-- Table structure for usersmessage
-- ----------------------------
DROP TABLE IF EXISTS `usersmessage`;
CREATE TABLE `usersmessage` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RECEIVER` bigint(20) DEFAULT NULL,
  `ISREAD` bit(1) DEFAULT NULL,
  `MESSAGE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_6xrlwti19njxnqawfyttlnoex` (`RECEIVER`),
  KEY `FK_icc0l2qvf5mkak8gwnm1s0eo4` (`MESSAGE`),
  CONSTRAINT `FK_6xrlwti19njxnqawfyttlnoex` FOREIGN KEY (`RECEIVER`) REFERENCES `member` (`ID`),
  CONSTRAINT `FK_icc0l2qvf5mkak8gwnm1s0eo4` FOREIGN KEY (`MESSAGE`) REFERENCES `message` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usersmessage
-- ----------------------------
