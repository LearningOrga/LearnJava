/*
MySQL Data Transfer
Source Host: localhost
Source Database: ipldb
Target Host: localhost
Target Database: ipldb
Date: 4/5/2016 11:04:38 PM
*/

--liquibase

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for rule_master
-- ----------------------------
DROP TABLE IF EXISTS `rule_master`;
CREATE TABLE `rule_master` (
  `RULE_ID` int(4) NOT NULL auto_increment,
  `RULE_NAME` varchar(50) default NULL,
  `RULE_DESC` varchar(100) default NULL,
  `RULE_STATUS` varchar(1) default NULL,
  `RULE_BASED_ON` varchar(20) default NULL,
  PRIMARY KEY  (`RULE_ID`)
);
--ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `rule_master` VALUES ('1', 'Winning Team', 'Which team is winning', 'A', 'Team');
INSERT INTO `rule_master` VALUES ('2', 'Toss Winning Team', 'Which team is winning toss', 'A', 'Team');
