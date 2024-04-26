/*
MySQL Data Transfer
Source Host: localhost
Source Database: ipldb
Target Host: localhost
Target Database: ipldb
Date: 4/17/2016 2:25:31 PM
*/

--SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for predict_master
-- ----------------------------
DROP TABLE IF EXISTS predict_master;
CREATE TABLE predict_master (
  PREDICT_ID int NOT NULL auto_increment,
  USER_ID int NOT NULL,
  QF_TEAM1 int default NULL,
  QF_TEAM2 int default NULL,
  QF_TEAM3 int default NULL,
  QF_TEAM4 int default NULL,
  SF_TEAM1 int default NULL,
  SF_TEAM2 int default NULL,
  FINAL_WINNING_TEAM int default NULL,
  QF_TEAM1_RESULT int default NULL,
  QF_TEAM2_RESULT int default NULL,
  QF_TEAM3_RESULT int default NULL,
  QF_TEAM4_RESULT int default NULL,
  SF_TEAM1_RESULT int default NULL,
  SF_TEAM2_RESULT int default NULL,
  FINAL_TEAM_RESULT int default NULL,
  USER_PROB_INDEX int default NULL,
  PRIMARY KEY  ( PREDICT_ID )
);
-- ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- Records 
-- ----------------------------
