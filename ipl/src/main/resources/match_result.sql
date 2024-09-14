/*
MySQL Data Transfer
Source Host: localhost
Source Database: ipldb
Target Host: localhost
Target Database: ipldb
Date: 4/9/2016 5:54:28 PM
*/

--SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for match_result
-- ----------------------------
DROP TABLE IF EXISTS match_result;
CREATE TABLE match_result (
  MATCH_RESULT_ID int NOT NULL auto_increment,
  MATCH_ID varchar(10) default NULL,
  RULE_ID varchar(10) default NULL,
  RULE_RESULT varchar(100) default NULL,
  PRIMARY KEY  ( MATCH_RESULT_ID )
) ;
--ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- Records 
-- ----------------------------
