/*
MySQL Data Transfer
Source Host: localhost
Source Database: ipldb
Target Host: localhost
Target Database: ipldb
Date: 4/9/2017 7:34:41 PM
*/

--SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for match_master
-- ----------------------------
DROP TABLE IF EXISTS match_master;
CREATE TABLE match_master (
  MATCH_NO int NOT NULL auto_increment,
  MATCH_DATE varchar(50) default NULL,
  MATCH_DAY varchar(50) default NULL,
  MATCH_TIME varchar(100) default NULL,
  MATCH_DETAILS varchar(100) default NULL,
  MATCH_VENUE varchar(50) default NULL,
  MATCH_STATUS varchar(2) default 'A',
  PRIMARY KEY  ( MATCH_NO )
);
--ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- Records 
-- ----------------------------
 

INSERT INTO match_master VALUES ( 1,  '23-04-2024', 'Sat', '20:00 PM (02:30 PM GMT)', 'Chennai Super Kings vs Royal Challengers Bangalore', 'Chennai', 'A');
INSERT INTO match_master VALUES ( 2,  '24-04-2024', 'Sun', '16:00 PM (10:30 AM GMT)', 'Kolkata Knight Riders vs Sunrisers Hyderabad', 'Kolkata', 'A');
INSERT INTO match_master VALUES ( 3,  '24-04-2024', 'Sun', '20:00 PM (02:30 PM GMT)', 'Mumbai Indians vs Delhi Capitals', 'Mumbai', 'A');
INSERT INTO match_master VALUES ( 4,  '25-04-2024', 'Mon', '20:00 PM (02:30 PM GMT)', 'Rajasthan Royals vs Kings XI Punjab','Jaipur', 'A');
INSERT INTO match_master VALUES ( 5,  '26-04-2024', 'Tue', '20:00 PM (02:30 PM GMT)', 'Delhi Capitals vs Chennai Super Kings', 'Delhi', 'A');
INSERT INTO match_master VALUES ( 6,  '27-04-2024', 'Wed', '20:00 PM (02:30 PM GMT)', 'Kolkata Knight Riders vs Kings XI Punjab', 'Kolkata', 'A');
INSERT INTO match_master VALUES ( 7,  '28-04-2024', 'Thu', '20:00 PM (02:30 PM GMT)', 'Royal Challengers Bangalore vs Mumbai Indians', 'Bengaluru', 'A');
INSERT INTO match_master VALUES ( 8,  '29-04-2024', 'Fri', '20:00 PM (02:30 PM GMT)', 'Sunrisers Hyderabad vs Rajasthan Royals', 'Hyderabad', 'A');
INSERT INTO match_master VALUES ( 9,  '30-04-2024', 'Sat', '16:00 PM (10:30 AM GMT)', 'Kings XI Punjab vs Mumbai Indians', 'Mohali', 'A');
INSERT INTO match_master VALUES ( 10, '30-04-2024', 'Sat', '20:00 PM (02:30 PM GMT)', 'Delhi Capitals vs Kolkata Knight Riders', 'Delhi', 'A');
INSERT INTO match_master VALUES ( 11, '31-04-2024', 'Sun', '16:00 PM (10:30 AM GMT)', 'Sunrisers Hyderabad vs Royal Challengers Bangalore', 'Hyderabad', 'A');
INSERT INTO match_master VALUES ( 12, '31-04-2024', 'Sun', '20:00 PM (02:30 PM GMT)', 'Chennai Super Kings vs Rajasthan Royals', 'Chennai', 'A');
INSERT INTO match_master VALUES ( 13, '01-05-2024', 'Mon', '20:00 PM (02:30 PM GMT)', 'Kings XI Punjab vs Delhi Capitals', 'Mohali', 'A');
INSERT INTO match_master VALUES ( 14, '02-05-2024', 'Tue', '20:00 PM (02:30 PM GMT)', 'Rajasthan Royals vs Royal Challengers Bangalore', 'Jaipur', 'A');
INSERT INTO match_master VALUES ( 15, '03-05-2024', 'Wed', '20:00 PM (02:30 PM GMT)', 'Mumbai Indians vs Chennai Super Kings', 'Mumbai', 'A');
INSERT INTO match_master VALUES ( 16, '04-05-2024', 'Thu', '20:00 PM (02:30 PM GMT)', 'Delhi Capitals vs Sunrisers Hyderabad', 'Delhi', 'A');
INSERT INTO match_master VALUES ( 17, '05-05-2024', 'Fri', '20:00 PM (02:30 PM GMT)', 'Royal Challengers Bangalore vs Kolkata Knight Riders', 'Bengaluru', 'A');
INSERT INTO match_master VALUES ( 18, '06-05-2024', 'Sat', '16:00 PM (10:30 AM GMT)', 'Chennai Super Kings vs Kings XI Punjab', 'Chennai', 'A');
INSERT INTO match_master VALUES ( 19, '06-05-2024', 'Sat', '20:00 PM (02:30 PM GMT)', 'Sunrisers Hyderabad vs Mumbai Indians', 'Hyderabad', 'A');
INSERT INTO match_master VALUES ( 20, '07-05-2024', 'Sun', '16:00 PM (10:30 AM GMT)', 'Royal Challengers Bangalore vs Delhi Capitals', 'Bangalore', 'A');
INSERT INTO match_master VALUES ( 21, '07-05-2024', 'Sun', '20:00 PM (02:30 PM GMT)', 'Rajasthan Royals vs Kolkata Knight Riders', 'Jaipur', 'A');
INSERT INTO match_master VALUES ( 22, '08-05-2024', 'Mon', '20:00 PM (02:30 PM GMT)', 'Kings XI Punjab vs Sunrisers Hyderabad', 'Mohali', 'A');
INSERT INTO match_master VALUES ( 23, '09-05-2024', 'Tue', '20:00 PM (02:30 PM GMT)', 'Chennai Super Kings vs Kolkata Knight Riders', 'Chennai', 'A');
INSERT INTO match_master VALUES ( 24, '10-05-2024', 'Wed', '20:00 PM (02:30 PM GMT)', 'Mumbai Indians vs Kings XI Punjab', 'Mumbai', 'A');
INSERT INTO match_master VALUES ( 25, '11-05-2024', 'Thu', '20:00 PM (02:30 PM GMT)', 'Rajasthan Royals vs Chennai Super Kings', 'Jaipur', 'A');
INSERT INTO match_master VALUES ( 26, '12-05-2024', 'Fri', '20:00 PM (02:30 PM GMT)', 'Kolkata Knight Riders vs Delhi Capitals', 'Kolkata', 'A');
INSERT INTO match_master VALUES ( 27, '13-05-2024', 'Sat', '16:00 PM (10:30 AM GMT)', 'Mumbai Indians vs Rajasthan Royals', 'Mumbai', 'A');
INSERT INTO match_master VALUES ( 28, '13-05-2024', 'Sat', '20:00 PM (02:30 PM GMT)', 'Kings XI Punjab vs Royal Challengers Bangalore', 'Mohali', 'A');
INSERT INTO match_master VALUES ( 29, '14-05-2024', 'Sun', '16:00 PM (10:30 AM GMT)', 'Kolkata Knight Riders vs Chennai Super Kings', 'Kolkata', 'A');
INSERT INTO match_master VALUES ( 30, '14-05-2024', 'Sun', '20:00 PM (02:30 PM GMT)', 'Sunrisers Hyderabad vs Delhi Capitals', 'Hyderabad', 'A');
INSERT INTO match_master VALUES ( 31, '15-05-2024', 'Mon', '20:00 PM (02:30 PM GMT)', 'Mumbai Indians vs Royal Challengers Bangalore', 'Mumbai', 'A');
INSERT INTO match_master VALUES ( 32, '16-05-2024', 'Tue', '20:00 PM (02:30 PM GMT)', 'Kings XI Punjab vs Rajasthan Royals', 'Mohali', 'A');
INSERT INTO match_master VALUES ( 33, '17-05-2024', 'Wed', '20:00 PM (02:30 PM GMT)', 'Sunrisers Hyderabad vs Chennai Super Kings', 'Hyderabad', 'A');
INSERT INTO match_master VALUES ( 34, '18-05-2024', 'Thu', '20:00 PM (02:30 PM GMT)', 'Delhi Capitals vs Mumbai Indians', 'Delhi', 'A');
INSERT INTO match_master VALUES ( 35, '19-05-2024', 'Fri', '20:00 PM (02:30 PM GMT)', 'Kolkata Knight Riders vs Royal Challengers Bangalore', 'Kolkata', 'A');
INSERT INTO match_master VALUES ( 36, '20-05-2024', 'Sat', '16:00 PM (10:30 AM GMT)', 'Rajasthan Royals vs Mumbai Indians', 'Jaipur', 'A');
INSERT INTO match_master VALUES ( 37, '20-05-2024', 'Sat', '20:00 PM (02:30 PM GMT)', 'Delhi Capitals vs Kings XI Punjab', 'Delhi', 'A');
INSERT INTO match_master VALUES ( 38, '21-05-2024', 'Sun', '16:00 PM (10:30 AM GMT)', 'Sunrisers Hyderabad vs Kolkata Knight Riders', 'Hyderabad', 'A');
INSERT INTO match_master VALUES ( 39, '21-05-2024', 'Sun', '20:00 PM (02:30 PM GMT)', 'Royal Challengers Bangalore vs Chennai Super Kings', 'Chennai', 'A');
INSERT INTO match_master VALUES ( 40, '22-05-2024', 'Mon', '20:00 PM (02:30 PM GMT)', 'Rajasthan Royals vs Delhi Capitals', 'Jaipur', 'A');
INSERT INTO match_master VALUES ( 41, '23-05-2024', 'Tue', '20:00 PM (02:30 PM GMT)', 'Chennai Super Kings vs Sunrisers Hyderabad', 'Chennai', 'A');
INSERT INTO match_master VALUES ( 42, '24-05-2024', 'Wed', '20:00 PM (02:30 PM GMT)', 'Royal Challengers Bangalore vs Kings XI Punjab', 'Bangalore', 'A');
INSERT INTO match_master VALUES ( 43, '25-05-2024', 'Thu', '20:00 PM (02:30 PM GMT)', 'Kolkata Knight Riders vs Rajasthan Royals', 'Kolkata', 'A');
INSERT INTO match_master VALUES ( 44, '26-05-2024', 'Fri', '20:00 PM (02:30 PM GMT)', 'Chennai Supers Kings vs Mumbai Indians', 'Chennai', 'A');
INSERT INTO match_master VALUES ( 45, '27-05-2024', 'Sat', '20:00 PM (02:30 PM GMT)', 'Rajasthan Royals vs Sunrisers Hyderabad', 'Jaipur', 'A');
INSERT INTO match_master VALUES ( 46, '28-05-2024', 'Sun', '16:00 PM (10:30 AM GMT)', 'Delhi Capitals vs Royal Challengers Bangalore', 'Delhi', 'A');
INSERT INTO match_master VALUES ( 47, '28-05-2024', 'Sun', '20:00 PM (02:30 PM GMT)', 'Kolkata Knight Riders vs Mumbai Indians', 'Kolkata', 'A');
INSERT INTO match_master VALUES ( 48, '29-05-2024', 'Mon', '20:00 PM (02:30 PM GMT)', 'Sunrisers Hyderabad vs Kings XI Punjab', 'Hyderabad', 'A');
INSERT INTO match_master VALUES ( 49, '30-05-2024', 'Tue', '20:00 PM (02:30 PM GMT)', 'Royal Challengers Bangalore vs Rajasthan Royals', 'Bangalore', 'A');
INSERT INTO match_master VALUES ( 50, '01-06-2024', 'Wed', '20:00 PM (02:30 PM GMT)', 'Chennai Super Kings vs Delhi Capitals', 'Chennai', 'A');
INSERT INTO match_master VALUES ( 51, '02-06-2024', 'Thu', '20:00 PM (02:30 PM GMT)', 'Mumbai Indians vs Sunrisers Hyderabad', 'Mumbai', 'A');
INSERT INTO match_master VALUES ( 52, '03-06-2024', 'Fri', '20:00 PM (02:30 PM GMT)', 'Kings XI Punjab vs Kolkata Knight Riders', 'Mohali', 'A');
INSERT INTO match_master VALUES ( 53, '04-06-2024', 'Sat', '16:00 PM (10:30 AM GMT)', 'Delhi Capitals vs Rajasthan Royals', 'Delhi', 'A');
INSERT INTO match_master VALUES ( 54, '04-06-2024', 'Sat', '20:00 PM (02:30 PM GMT)', 'Royal Challengers Bangalore vs Sunrisers Hyderabad', 'Bangalore', 'A');
INSERT INTO match_master VALUES ( 55, '05-06-2024', 'Sun', '16:00 PM (10:30 AM GMT)', 'Kings XI Punjab vs Chennai Super Kings', 'Mohali', 'A');
INSERT INTO match_master VALUES ( 56, '05-06-2024', 'Sun', '20:00 PM (02:30 PM GMT)', 'Mumbai Indians vs Kolkata Knight Riders', 'Mumbai', 'A');