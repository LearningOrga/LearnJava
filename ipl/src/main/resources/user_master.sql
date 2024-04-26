/*
MySQL Data Transfer
Source Host: localhost
Source Database: ipldb
Target Host: localhost
Target Database: ipldb
Date: 4/9/2016 5:13:22 PM
*/

--SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for user_master
-- ----------------------------
DROP TABLE IF EXISTS user_master;
CREATE TABLE user_master (
  LOGIN_ID int NOT NULL auto_increment,
  LOGIN_NAME varchar(50) default NULL,
  LOGIN_PASS varchar(200) default NULL,
  LOGIN_ROLE varchar(15) default NULL,
  ENABLED int default '1',
  LOGIN_AVALI_POINTS double default NULL,
   --sql`LOGIN_AVALI_POINTS` double(10,2) default NULL,
  GOLDEN_PREDICT varchar(30) default NULL,
  PRIMARY KEY  ( LOGIN_ID )
);
--ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO user_master VALUES ('1', 'Jitendra', '$2a$10$nRWaDn5ejnYnhByj8FZdoeDxIf3zM4264/YQR9VxqD1q6DQ7tt4LC', 'ROLE_ADMIN', '1', '505', null);
INSERT INTO user_master VALUES ('2', 'Priyesh', '$2a$10$pPJuzwEf.SYwmwQJoj7nseJ6zyApTj7pUx444aOM4mUqw8/30xL/C', 'ROLE_ADMIN', '1', '500', null);
