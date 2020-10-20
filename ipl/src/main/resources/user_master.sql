/*
MySQL Data Transfer
Source Host: localhost
Source Database: ipldb
Target Host: localhost
Target Database: ipldb
Date: 4/9/2016 5:13:22 PM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for user_master
-- ----------------------------
DROP TABLE IF EXISTS `user_master`;
CREATE TABLE `user_master` (
  `LOGIN_ID` int(10) NOT NULL auto_increment,
  `LOGIN_NAME` varchar(50) default NULL,
  `LOGIN_PASS` varchar(200) default NULL,
  `LOGIN_ROLE` varchar(15) default NULL,
  `ENABLED` tinyint(10) default '1',
  `LOGIN_AVALI_POINTS` double(10) default NULL,
   --sql`LOGIN_AVALI_POINTS` double(10,2) default NULL,
  `GOLDEN_PREDICT` varchar(30) default NULL,
  PRIMARY KEY  (`LOGIN_ID`)
);
--ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `user_master` VALUES ('1', 'Jitendra', '$2a$10$6OUSpQV.BVgB5iP8NyChdewV/3xiCEQkhxB9cibGXc/BTl7gVWiW6', 'ROLE_ADMIN', '1', '505', null);

