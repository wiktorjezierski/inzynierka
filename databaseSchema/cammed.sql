-- MySQL dump 10.13  Distrib 5.7.13, for Win64 (x86_64)
--
-- Host: localhost    Database: cammed
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `logins`
--

DROP TABLE IF EXISTS `logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logins` (
  `UUID` varchar(45) NOT NULL,
  `LOGIN` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  UNIQUE KEY `LOGIN_UNIQUE` (`LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logins`
--

LOCK TABLES `logins` WRITE;
/*!40000 ALTER TABLE `logins` DISABLE KEYS */;
INSERT INTO `logins` VALUES ('e2a68517-f442-4e01-8407-0f9aa9ce48c8','wjeziorko1','admin123'),('16b12622-87ba-4bd1-a84b-a2527f1fb229','wjeziorko6','admin123');
/*!40000 ALTER TABLE `logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relations`
--

DROP TABLE IF EXISTS `relations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relations` (
  `UUID` varchar(45) NOT NULL,
  `LOGIN1` varchar(45) DEFAULT NULL,
  `LOGIN2` varchar(45) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relations`
--

LOCK TABLES `relations` WRITE;
/*!40000 ALTER TABLE `relations` DISABLE KEYS */;
INSERT INTO `relations` VALUES ('20289186-6719-11e6-8b77-86f30ca893d3','c4eb4330-944b-4454-9e68-31d9fd366550','6f383763-f392-46f4-8785-ff2039c35431',NULL),('424385aa-6719-11e6-8b77-86f30ca893d3','6f383763-f392-46f4-8785-ff2039c35431','c4eb4330-944b-4454-9e68-31d9fd366550',NULL),('e9d5bc96-17f3-4737-9f9b-a24aface98d0','2205db8e-7c9c-4916-8ed2-58936ef46b8d','c4eb4330-944b-4454-9e68-31d9fd366550',NULL);
/*!40000 ALTER TABLE `relations` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cammed`.`relations_AFTER_INSERT` AFTER INSERT ON `relations` FOR EACH ROW
BEGIN
SET SQL_SAFE_UPDATES=0;
UPDATE USERS u SET u.AMOUNT_FRIENDS=u.AMOUNT_FRIENDS+1 WHERE u.uuid=NEW.LOGIN1;
UPDATE USERS u SET u.AMOUNT_FRIENDS=u.AMOUNT_FRIENDS+1 WHERE u.uuid=NEW.LOGIN2;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_current_details`
--

DROP TABLE IF EXISTS `user_current_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_current_details` (
  `UUID` varchar(45) NOT NULL,
  `DEVICE` int(4) DEFAULT NULL,
  `IP` varchar(16) DEFAULT NULL,
  `SESSION_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_current_details`
--

LOCK TABLES `user_current_details` WRITE;
/*!40000 ALTER TABLE `user_current_details` DISABLE KEYS */;
INSERT INTO `user_current_details` VALUES ('4d25cebf-7081-4275-b4dd-39f9bdb3a837',0,'192.168.0.4','b6812302-3c5d-4314-9b90-1d1182fe774e'),('a6620caa-65e1-4097-8434-2fd8f666f149',0,'192.168.0.4','cc09d948-bf0e-4d4b-a90b-f3777e3766e9'),('bc8aec54-d2a5-43c5-943d-a5c10ea0aef5',0,'192.168.0.4','4c24b1c8-c2f1-45fa-99fe-b74b0c50bef6'),('f3050e49-6b6f-4540-9ce9-af7a75629f83',0,'192.168.0.4','e396b234-4886-4cc4-9175-730da4247f66');
/*!40000 ALTER TABLE `user_current_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UUID` varchar(50) NOT NULL,
  `LOGIN` varchar(32) DEFAULT NULL,
  `IMIE` varchar(45) DEFAULT NULL,
  `NAZWISKO` varchar(45) DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT NULL,
  `OR_USER_DETAIL` varchar(50) DEFAULT NULL,
  `AMOUNT_FRIENDS` int(11) DEFAULT '0',
  PRIMARY KEY (`UUID`),
  KEY `OR_USER_DETAILS_idx` (`OR_USER_DETAIL`),
  CONSTRAINT `OR_USER_DETAILS` FOREIGN KEY (`OR_USER_DETAIL`) REFERENCES `user_current_details` (`UUID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('2205db8e-7c9c-4916-8ed2-58936ef46b8d','wjeziorko2','wiktor','jezierski',0,NULL,1),('6f383763-f392-46f4-8785-ff2039c35431','wjeziorko1','Wiktor','Jezierski',1,'4d25cebf-7081-4275-b4dd-39f9bdb3a837',0),('c4eb4330-944b-4454-9e68-31d9fd366550','wjeziorko6','wiktor','jezierski',1,'f3050e49-6b6f-4540-9ce9-af7a75629f83',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-15 15:36:39
