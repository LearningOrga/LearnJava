 /*
MySQL Data Transfer
Source Host: localhost
Source Database: test
Target Host: localhost
Target Database: test
Date: 10-03-2016 17:26:58
*/

--SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for player_master
-- ----------------------------
DROP TABLE IF EXISTS player_master;
CREATE TABLE player_master (
  PLAYER_ID int NOT NULL,
  PLAYER_NAME varchar(100) default NULL,
  PLAYER_TYPE varchar(10) default NULL,
  PLAYER_TEAM varchar(4) NOT NULL,
  PLAYER_STAR_STATUS char(255) default NULL,
  PRIMARY KEY  ( PLAYER_ID )
);
-- ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO player_master VALUES ('100', 'RobinUthappa', '', 'T05', '');
INSERT INTO player_master VALUES ('101', 'SheldonJackson', '', 'T05', '');
INSERT INTO player_master VALUES ('102', 'SuryakumarYadav', '', 'T05', '');
INSERT INTO player_master VALUES ('103', 'UmeshYadav', '', 'T05', '');
INSERT INTO player_master VALUES ('104', 'YusufPathan', '', 'T05', '');
INSERT INTO player_master VALUES ('105', 'SunilNarine', '', 'T05', '');
INSERT INTO player_master VALUES ('106', 'AndreRussell', '', 'T05', '');
INSERT INTO player_master VALUES ('107', 'BradHogg', '', 'T05', '');
INSERT INTO player_master VALUES ('108', 'ChrisLynn', '', 'T05', '');
INSERT INTO player_master VALUES ('109', 'MorneMorkel', '', 'T05', '');
INSERT INTO player_master VALUES ('110', 'ShakibAlHasan.JaydevUnadkat', '', 'T05', '');
INSERT INTO player_master VALUES ('111', 'AnkitSinghRajpoot', '', 'T05', '');
INSERT INTO player_master VALUES ('112', 'JohnHastings', '', 'T05', '');
INSERT INTO player_master VALUES ('113', 'JasonHolder', '', 'T05', '');
INSERT INTO player_master VALUES ('114', 'ColinMunro', '', 'T05', '');
INSERT INTO player_master VALUES ('115', 'RajgopalSathish', '', 'T05', '');
INSERT INTO player_master VALUES ('116', 'MananAjaySharma', '', 'T05', '');
INSERT INTO player_master VALUES ('117', 'AshishReddy', '', 'T06', '');
INSERT INTO player_master VALUES ('118', 'BhuvneshwarKumar', '', 'T06', '');
INSERT INTO player_master VALUES ('119', 'BipulSharma', '', 'T06', '');
INSERT INTO player_master VALUES ('120', 'KarnSharma', '', 'T06', '');
INSERT INTO player_master VALUES ('121', 'KLRahul', '', 'T06', '');
INSERT INTO player_master VALUES ('122', 'NamanOjha', '', 'T06', '');
INSERT INTO player_master VALUES ('123', 'ParvezRasool', '', 'T06', '');
INSERT INTO player_master VALUES ('124', 'RickyBhui', '', 'T06', '');
INSERT INTO player_master VALUES ('125', 'ShikharDhawan', '', 'T06', '');
INSERT INTO player_master VALUES ('126', 'SiddharthKaul', '', 'T06', '');
INSERT INTO player_master VALUES ('127', 'DavidWarner', '', 'T06', '');
INSERT INTO player_master VALUES ('128', 'MoisesHenriques', '', 'T06', '');
INSERT INTO player_master VALUES ('129', 'EoinMorgan', '', 'T06', '');
INSERT INTO player_master VALUES ('130', 'KaneWilliamson', '', 'T06', '');
INSERT INTO player_master VALUES ('131', 'TrentBoult', '', 'T06', '');
INSERT INTO player_master VALUES ('132', 'YuvrajSingh', '', 'T06', '');
INSERT INTO player_master VALUES ('133', 'AshishNehra', '', 'T06', '');
INSERT INTO player_master VALUES ('134', 'DeepakHooda', '', 'T06', '');
INSERT INTO player_master VALUES ('135', 'MustafizurRahman', '', 'T06', '');
INSERT INTO player_master VALUES ('136', 'AdityaTare', '', 'T06', '');
INSERT INTO player_master VALUES ('137', 'BarinderSinghSran', '', 'T06', '');
INSERT INTO player_master VALUES ('138', 'BenCutting', '', 'T06', '');
INSERT INTO player_master VALUES ('139', 'VijayShankar', '', 'T06', '');
INSERT INTO player_master VALUES ('140', 'AbhimanyuMithun', '', 'T06', '');
INSERT INTO player_master VALUES ('141', 'TirumalsettiSuman', '', 'T06', '');
INSERT INTO player_master VALUES ('142', 'MSDhoni', '', 'T07', '');
INSERT INTO player_master VALUES ('143', 'StevenSmith', '', 'T07', '');
INSERT INTO player_master VALUES ('144', 'FafduPlessis', '', 'T07', '');
INSERT INTO player_master VALUES ('145', 'AjinkyaRahane', '', 'T07', '');
INSERT INTO player_master VALUES ('146', 'RavichandranAshwin', '', 'T07', '');
INSERT INTO player_master VALUES ('147', 'MitchellMarsh', '', 'T07', '');
INSERT INTO player_master VALUES ('148', 'AshwinMurugan', '', 'T07', '');
INSERT INTO player_master VALUES ('149', 'IshantSharma', '', 'T07', '');
INSERT INTO player_master VALUES ('150', 'KevinPietersen', '', 'T07', '');
INSERT INTO player_master VALUES ('151', 'IrfanPathan', '', 'T07', '');
INSERT INTO player_master VALUES ('152', 'ThisaraPerera', '', 'T07', '');
INSERT INTO player_master VALUES ('153', 'RajatBhatia', '', 'T07', '');
INSERT INTO player_master VALUES ('154', 'AshokeDinda', '', 'T07', '');
INSERT INTO player_master VALUES ('155', 'ScottBoland', '', 'T07', '');
INSERT INTO player_master VALUES ('156', 'PeterHandscomb', '', 'T07', '');
INSERT INTO player_master VALUES ('157', 'RPSingh', '', 'T07', '');
INSERT INTO player_master VALUES ('158', 'AdamZampa', '', 'T07', '');
INSERT INTO player_master VALUES ('159', 'IshwarPandey', '', 'T07', '');
INSERT INTO player_master VALUES ('160', 'DeepakChahar', '', 'T07', '');
INSERT INTO player_master VALUES ('161', 'JaskaranSingh', '', 'T07', '');
INSERT INTO player_master VALUES ('162', 'BabaAparajith', '', 'T07', '');
INSERT INTO player_master VALUES ('163', 'AnkushBains', '', 'T07', '');
INSERT INTO player_master VALUES ('164', 'AnkitSharma', '', 'T07', '');
INSERT INTO player_master VALUES ('165', 'SureshRaina', '', 'T08', '');
INSERT INTO player_master VALUES ('166', 'RavindraJadeja', '', 'T08', '');
INSERT INTO player_master VALUES ('167', 'DwayneBravo', '', 'T08', '');
INSERT INTO player_master VALUES ('168', 'JamesFaulkner', '', 'T08', '');
INSERT INTO player_master VALUES ('169', 'BrendonMcCullum', '', 'T08', '');
INSERT INTO player_master VALUES ('170', 'PraveenKumar', '', 'T08', '');
INSERT INTO player_master VALUES ('171', 'DaleSteyn', '', 'T08', '');
INSERT INTO player_master VALUES ('172', 'DineshKarthik', '', 'T08', '');
INSERT INTO player_master VALUES ('173', 'DwayneSmith', '', 'T08', '');
INSERT INTO player_master VALUES ('174', 'DhawalKulkarni', '', 'T08', '');
INSERT INTO player_master VALUES ('175', 'AaronFinch', '', 'T08', '');
INSERT INTO player_master VALUES ('176', 'EklavyaDwivedi', '', 'T08', '');
INSERT INTO player_master VALUES ('177', 'AndrewTye', '', 'T08', '');
INSERT INTO player_master VALUES ('178', 'IshanKishan', '', 'T08', '');
INSERT INTO player_master VALUES ('179', 'JaydevShah', '', 'T08', '');
INSERT INTO player_master VALUES ('180', 'ShadabJakati', '', 'T08', '');
INSERT INTO player_master VALUES ('181', 'PravinTambe', '', 'T08', '');
INSERT INTO player_master VALUES ('182', 'PradeepSangwan', '', 'T08', '');
INSERT INTO player_master VALUES ('183', 'AmitMishra', '', 'T08', '');
INSERT INTO player_master VALUES ('184', 'ShivilKaushik', '', 'T08', '');
INSERT INTO player_master VALUES ('185', 'AkshDeepNath', '', 'T08', '');
INSERT INTO player_master VALUES ('186', 'SarabjeetLadda', '', 'T08', '');
INSERT INTO player_master VALUES ('187', 'UmangSharma', '', 'T08', '');
INSERT INTO player_master VALUES ('188', 'ParasDogra', '', 'T08', '');
INSERT INTO player_master VALUES ('22', 'ZaheerKhan', '', 'T02', '');
INSERT INTO player_master VALUES ('23', 'AmitMishra', '', 'T02', '');
INSERT INTO player_master VALUES ('24', 'ShreyasIyer', '', 'T02', '');
INSERT INTO player_master VALUES ('25', 'MohammadShami', '', 'T02', '');
INSERT INTO player_master VALUES ('26', 'SaurabhTiwary', '', 'T02', '');
INSERT INTO player_master VALUES ('27', 'ShahbazNadeem', '', 'T02', '');
INSERT INTO player_master VALUES ('28', 'MayankAgarwal', '', 'T02', '');
INSERT INTO player_master VALUES ('29', 'JayantYadav', '', 'T02', '');
INSERT INTO player_master VALUES ('30', 'TravisHead', '', 'T02', '');
INSERT INTO player_master VALUES ('31', 'NathanCoulter-Nile', '', 'T02', '');
INSERT INTO player_master VALUES ('32', 'ImranTahir', '', 'T02', '');
INSERT INTO player_master VALUES ('33', 'JPDuminy', '', 'T02', '');
INSERT INTO player_master VALUES ('34', 'AlbieMorkel', '', 'T02', '');
INSERT INTO player_master VALUES ('35', 'QuintondeKock', '', 'T02', '');
INSERT INTO player_master VALUES ('36', 'PawanNegi', '', 'T02', '');
INSERT INTO player_master VALUES ('37', 'ChrisMorris', '', 'T02', '');
INSERT INTO player_master VALUES ('38', 'SanjuSamson', '', 'T02', '');
INSERT INTO player_master VALUES ('39', 'CarlosBrathwaite', '', 'T02', '');
INSERT INTO player_master VALUES ('40', 'KarunNair', '', 'T02', '');
INSERT INTO player_master VALUES ('41', 'RishabhPant', '', 'T02', '');
INSERT INTO player_master VALUES ('42', 'JoelParis', '', 'T02', '');
INSERT INTO player_master VALUES ('43', 'SamBillings', '', 'T02', '');
INSERT INTO player_master VALUES ('44', 'ChamaMilind', '', 'T02', '');
INSERT INTO player_master VALUES ('45', 'PratyushSingh', '', 'T02', '');
INSERT INTO player_master VALUES ('46', 'MahipalLomror', '', 'T02', '');
INSERT INTO player_master VALUES ('47', 'Saeed Khaleel Ahmed', '', 'T02', '');
INSERT INTO player_master VALUES ('48', 'Akhil Herwadkar', '', 'T02', '');
INSERT INTO player_master VALUES ('49', 'Pawan Suyal', '', 'T02', '');
INSERT INTO player_master VALUES ('50', 'RohitSharma', '', 'T03', '');
INSERT INTO player_master VALUES ('51', 'HarbhajanSingh', '', 'T03', '');
INSERT INTO player_master VALUES ('52', 'HardikPandya', '', 'T03', '');
INSERT INTO player_master VALUES ('53', 'AmbatiRayudu', '', 'T03', '');
INSERT INTO player_master VALUES ('54', 'JagadeeshaSuchith', '', 'T03', '');
INSERT INTO player_master VALUES ('55', 'JaspritBumrah', '', 'T03', '');
INSERT INTO player_master VALUES ('56', 'ParthivPatel', '', 'T03', '');
INSERT INTO player_master VALUES ('57', 'RVinayKumar', '', 'T03', '');
INSERT INTO player_master VALUES ('58', 'ShreyasGopal', '', 'T03', '');
INSERT INTO player_master VALUES ('59', 'NitishRana', '', 'T03', '');
INSERT INTO player_master VALUES ('60', 'SiddheshLad', '', 'T03', '');
INSERT INTO player_master VALUES ('61', 'UnmuktChand', '', 'T03', '');
INSERT INTO player_master VALUES ('62', 'AkshayWakhare', '', 'T03', '');
INSERT INTO player_master VALUES ('63', 'MitchellMcClenaghan', '', 'T03', '');
INSERT INTO player_master VALUES ('64', 'CoreyAnderson', '', 'T03', '');
INSERT INTO player_master VALUES ('65', 'LendlSimmons', '', 'T03', '');
INSERT INTO player_master VALUES ('66', 'KieronPollard', '', 'T03', '');
INSERT INTO player_master VALUES ('67', 'MarchantdeLange', '', 'T03', '');
INSERT INTO player_master VALUES ('68', 'LasithMalinga', '', 'T03', '');
INSERT INTO player_master VALUES ('69', 'JosButtler', '', 'T03', '');
INSERT INTO player_master VALUES ('70', 'NathuSingh', '', 'T03', '');
INSERT INTO player_master VALUES ('71', 'TimSouthee', '', 'T03', '');
INSERT INTO player_master VALUES ('72', 'KrunalPandya', '', 'T03', '');
INSERT INTO player_master VALUES ('73', 'KishorePramodKamath', '', 'T03', '');
INSERT INTO player_master VALUES ('74', 'DeepakPunia', '', 'T03', '');
INSERT INTO player_master VALUES ('75', 'Jitesh Sharma', '', 'T03', '');
INSERT INTO player_master VALUES ('76', 'ViratKohli', '', 'T04', '');
INSERT INTO player_master VALUES ('77', 'ABdeVilliers', '', 'T04', '');
INSERT INTO player_master VALUES ('78', 'ChrisGayle', '', 'T04', '');
INSERT INTO player_master VALUES ('79', 'AdamMilne', '', 'T04', '');
INSERT INTO player_master VALUES ('80', 'MitchellStarc', '', 'T04', '');
INSERT INTO player_master VALUES ('81', 'SarfarazKhan', '', 'T04', '');
INSERT INTO player_master VALUES ('82', 'MandeepSingh', '', 'T04', '');
INSERT INTO player_master VALUES ('83', 'YuzvendraChahal', '', 'T04', '');
INSERT INTO player_master VALUES ('84', 'HarshalPatel', '', 'T04', '');
INSERT INTO player_master VALUES ('85', 'ShaneWatson', '', 'T04', '');
INSERT INTO player_master VALUES ('86', 'StuartBinny', '', 'T04', '');
INSERT INTO player_master VALUES ('87', 'KaneRichardson', '', 'T04', '');
INSERT INTO player_master VALUES ('88', 'SamuelBadree', '', 'T04', '');
INSERT INTO player_master VALUES ('89', 'TravisHead', '', 'T04', '');
INSERT INTO player_master VALUES ('90', 'PraveenDubey', '', 'T04', '');
INSERT INTO player_master VALUES ('91', 'VikramjeetMalik', '', 'T04', '');
INSERT INTO player_master VALUES ('92', 'AkshayKarnewar', '', 'T04', '');
INSERT INTO player_master VALUES ('93', 'IqbalAbdullah', '', 'T04', '');
INSERT INTO player_master VALUES ('94', 'SachinBaby', '', 'T04', '');
INSERT INTO player_master VALUES ('95', 'VikasTokas', '', 'T04', '');
INSERT INTO player_master VALUES ('96', 'GautamGambhir', '', 'T05', '');
INSERT INTO player_master VALUES ('97', 'KuldeepYadav', '', 'T05', '');
INSERT INTO player_master VALUES ('98', 'ManishPandey', '', 'T05', '');
INSERT INTO player_master VALUES ('99', 'PiyushChawla', '', 'T05', '');
