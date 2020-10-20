/*
MySQL Data Transfer
Source Host: localhost
Source Database: ipldb
Target Host: localhost
Target Database: ipldb
Date: 4/17/2016 2:25:31 PM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for predict_master
-- ----------------------------
DROP TABLE IF EXISTS `predict_master`;
CREATE TABLE `predict_master` (
  `PREDICT_ID` int(10) NOT NULL auto_increment,
  `USER_ID` int(5) NOT NULL,
  `QF_TEAM1` int(5) default NULL,
  `QF_TEAM2` int(5) default NULL,
  `QF_TEAM3` int(5) default NULL,
  `QF_TEAM4` int(5) default NULL,
  `SF_TEAM1` int(5) default NULL,
  `SF_TEAM2` int(5) default NULL,
  `FINAL_WINNING_TEAM` int(5) default NULL,
  `QF_TEAM1_RESULT` int(3) default NULL,
  `QF_TEAM2_RESULT` int(3) default NULL,
  `QF_TEAM3_RESULT` int(3) default NULL,
  `QF_TEAM4_RESULT` int(3) default NULL,
  `SF_TEAM1_RESULT` int(3) default NULL,
  `SF_TEAM2_RESULT` int(3) default NULL,
  `FINAL_TEAM_RESULT` int(3) default NULL,
  `USER_PROB_INDEX` int(5) default NULL,
  PRIMARY KEY  (`PREDICT_ID`)
);
-- ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- Records 
-- ----------------------------
