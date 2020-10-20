/*
MySQL Data Transfer
Source Host: localhost
Source Database: ipldb
Target Host: localhost
Target Database: ipldb
Date: 4/9/2016 5:54:43 PM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for play_result
-- ----------------------------
DROP TABLE IF EXISTS `play_result`;
CREATE TABLE `play_result` (
  `PLAY_MASTER_ID` int(6) NOT NULL auto_increment,
  `LOGIN_ID` varchar(50) NOT NULL default 'A',
 `RULE_ID` varchar(50) NOT NULL,
  `RULE_VALUE` varchar(100) NOT NULL,
  `MATCH_ID` varchar(10) NOT NULL,
  `POINTS_INVESTED` double(10) NOT NULL default 0.00,
  --sql`POINTS_INVESTED` double(10,2) NOT NULL default 0.00,
  `RESULT` varchar(50) default NULL,
  `INDICATIVE_POINTS_EARNED` double(10) default 0.00,
  --sql`INDICATIVE_POINTS_EARNED` double(10,2) default 0.00,
  --sql`TOTAL_POINTS_EARNED` double(10,2) default 0.00,
  `TOTAL_POINTS_EARNED` double(10) default 0.00,
  `RULE_RESULT` varchar(50) default NULL,
  PRIMARY KEY  (`PLAY_MASTER_ID`)
  ) ;
--ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;
-- ----------------------------
-- Records 
-- ----------------------------
