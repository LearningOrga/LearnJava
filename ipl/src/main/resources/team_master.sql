 /*
MySQL Data Transfer
Source Host: localhost
Source Database: test
Target Host: localhost
Target Database: test
Date: 10-03-2016 17:27:14
*/

--SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for team_master
-- ----------------------------
DROP TABLE IF EXISTS team_master;
CREATE TABLE team_master (
  TEAM_ID int NOT NULL,
  TEAM_NAME varchar(100) NOT NULL,
  TEAM_HOME_VENUE varchar(100) default NULL,
  TEAM_CAPTAIN varchar(100) NOT NULL,
  PRIMARY KEY  ( TEAM_ID )
) ;
--ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO team_master VALUES ('01', 'Kings XI Punjab (KXIP)', null, ' David Miller');
INSERT INTO team_master VALUES ('02', 'Delhi Capitals (DC)', null, 'JP Duminy');
INSERT INTO team_master VALUES ('03', 'Mumbai Indians (MI)', null, 'Rohit Sharma');
INSERT INTO team_master VALUES ('04', 'Royal Challengers Bangalore (RCB)', null, 'Virat Kohli');
INSERT INTO team_master VALUES ('05', 'Kolkata Knight Riders (KKR)', null, 'Gautam Gambhir');
INSERT INTO team_master VALUES ('06', 'Sunrisers Hyderabad (SRH)', null, 'Shikar Dhavan');
INSERT INTO team_master VALUES ('07', 'Rajasthan Royals (RR)', null, 'MS Dhoni');
INSERT INTO team_master VALUES ('08', 'Chennai Super Kings (CSK)', null, 'Suresh Raina');
