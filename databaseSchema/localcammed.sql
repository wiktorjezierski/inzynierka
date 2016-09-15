-- MySQL dump 10.13  Distrib 5.7.13, for Win64 (x86_64)
--
-- Host: localhost    Database: localcammed
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
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `UUID` varchar(50) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `DATE` datetime DEFAULT NULL,
  `LOCAL_PATH` varchar(50) DEFAULT NULL,
  `USER` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES ('f20be106-0fb0-4d5c-9153-e70fcf4cd5aa','Desert.jpg','2016-09-05 21:16:41',NULL,NULL);
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `UUID` varchar(50) NOT NULL,
  `USER` varchar(50) DEFAULT NULL,
  `CONTENT` varchar(500) DEFAULT NULL,
  `DATE` datetime DEFAULT NULL,
  `FILE` varchar(50) DEFAULT NULL,
  `IS_FILE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES ('1da81663-f4dd-4053-9e03-2cda6c5bc529','c4eb4330-944b-4454-9e68-31d9fd366550','siema 1000','2016-09-03 22:42:02',NULL,0),('23b915d5-c60a-438e-8e45-6d3ecdc8f74c','6f383763-f392-46f4-8785-ff2039c35431','siema siema 1','2016-09-05 20:19:57',NULL,0),('2a6f6a49-732f-4dd8-8e5d-59f1416ffa3b','6f383763-f392-46f4-8785-ff2039c35431','dupa dupa','2016-09-05 22:49:00',NULL,0),('4f8ef041-6aab-4416-8e1c-c8ad024d1b9c','c4eb4330-944b-4454-9e68-31d9fd366550','siema :D','2016-09-04 22:07:06',NULL,0),('57dcca70-3446-4d78-a425-27e14b1330e6','c4eb4330-944b-4454-9e68-31d9fd366550','siema 1000000','2016-09-04 22:07:14',NULL,0),('5c70f9f3-b367-4bd1-9dde-2aef513f0fa2',NULL,'siema 2 siema 666','2016-09-12 21:56:52',NULL,1),('8bf6e243-9ac3-411d-969b-7cd370ea2ab2','6f383763-f392-46f4-8785-ff2039c35431','siema','2016-09-05 21:16:41',NULL,0),('a71305fc-b330-4519-9c29-00c78a33fdda',NULL,'siema 2 siema 666','2016-09-12 22:01:14',NULL,1),('ae07e191-49b0-49c3-bd3f-bef325ea9797',NULL,'siema 2 siema 3','2016-09-12 21:38:09',NULL,0),('c2422bff-f3b8-4416-bee2-5e5633bdb3ee','6f383763-f392-46f4-8785-ff2039c35431','siema siema ..................................................','2016-08-31 22:26:02',NULL,0),('c89c4962-008e-4e1f-a61c-26103f8549bd',NULL,'siema 2 siema 000000X','2016-09-12 22:42:43','45fcca67-6ddc-418d-8794-db045cc55a04',1),('d3e8e347-ad3d-42cf-ba36-87ff9430433c','c4eb4330-944b-4454-9e68-31d9fd366550','siema 8','2016-09-03 22:43:02',NULL,0),('d783bac7-74e4-4f37-9b4c-c2971e80f8ef','6f383763-f392-46f4-8785-ff2039c35431','siema 2 siema 3','2016-08-31 22:26:02',NULL,0),('d7e841ed-1303-4373-8167-fd996cb0853b',NULL,'siema 2 siema 3','2016-09-12 21:51:42',NULL,1),('ec3fe48f-ff0d-4554-90e1-1f35c4b6f76f',NULL,'siema 2 siema 666','2016-09-12 22:12:17','45fcca67-6ddc-418d-8794-db045cc55a04',1),('f68f4993-1214-4f39-bf1a-c23855313513','6f383763-f392-46f4-8785-ff2039c35431','siema siema','2016-09-05 19:10:03',NULL,0);
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `UUID` varchar(50) NOT NULL,
  `PL` varchar(50) DEFAULT NULL,
  `DE` varchar(50) DEFAULT NULL,
  `EN` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES ('16308b11-b882-4db5-b5fd-043408a7f4b7','cos','siema','ojojoj'),('19628f94-2d35-4079-9e74-4cb6fe0b0b37','Tak','JA','Yes'),('21398952-8b02-4869-8038-1d6b94e92d8f','Tak','JA','Yes'),('3f954431-e8d1-4140-bb4b-49906cb4d0b4','Tak','JA','Yes'),('529ea3e4-5223-4b35-8f95-30313bcc8023','Tak','JA','Yes'),('5a289cbc-e9da-4d75-aabf-4043e98cb09d','Tak','JA','Yes'),('81ee3103-5eaa-4be4-a8aa-9a89f2c217ec','Tak','JA','Yes'),('db8d299a-6bc4-4c65-a891-9baa93c8119e','Tak','JA','Yes'),('eaaddbc4-fa2c-4674-9756-9744cea7a6c0','Tak','JA','Yes'),('eeb248b5-1f7f-4f0a-b63d-a02441dd84e1','Tak','JA','Yes'),('f01fcfa2-1199-4169-b7b3-de2e5efaef2b','Tak','JA','Yes'),('f61d4261-3261-4ee9-b358-d1b52fa59560','Tak','JA','Yes'),('fad3e273-c36a-4c5a-8d18-06b16555b3db','Tak','JA','Yes');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UUID` varchar(50) NOT NULL,
  `LOGIN` varchar(32) NOT NULL,
  `IMIE` varchar(45) DEFAULT NULL,
  `NAZWISKO` varchar(45) DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`UUID`),
  UNIQUE KEY `LOGIN` (`LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('6f383763-f392-46f4-8785-ff2039c35431','wjeziorko1','wiktor','jezierski',1),('c4eb4330-944b-4454-9e68-31d9fd366550','wjeziorko6','wiktor','jezierski',1);
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
