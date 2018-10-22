CREATE DATABASE  IF NOT EXISTS `spotitube` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `spotitube`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: spotitube
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `full_name` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'jan','janpass','Jan Klaassen'),(2,'piet','pietpass','Piet Huisman'),(3,'maria','mariapass','Maria Jansen'),(4,'lotus','lotuspass','Lotus ter Haar'),(5,'willem','willempass','Willem Knoop');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `library` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `length` int(11) DEFAULT NULL,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`,`username`),
  KEY `account_idx` (`username`),
  CONSTRAINT `account` FOREIGN KEY (`username`) REFERENCES `account` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library`
--

LOCK TABLES `library` WRITE;
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
INSERT INTO `library` VALUES (1,NULL,'lotus');
/*!40000 ALTER TABLE `library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `playlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `owner` tinyint(4) NOT NULL DEFAULT '0',
  `library_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `library_id_idx` (`library_id`),
  CONSTRAINT `library_id` FOREIGN KEY (`library_id`) REFERENCES `library` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'Pop',1,1),(2,'Country',1,1);
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_has_tracks`
--

DROP TABLE IF EXISTS `playlist_has_tracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `playlist_has_tracks` (
  `playlist_id` int(11) NOT NULL,
  `track_id` int(11) NOT NULL,
  PRIMARY KEY (`playlist_id`,`track_id`),
  KEY `track_id_idx` (`track_id`),
  CONSTRAINT `playlist_id` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`),
  CONSTRAINT `track_id` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_has_tracks`
--

LOCK TABLES `playlist_has_tracks` WRITE;
/*!40000 ALTER TABLE `playlist_has_tracks` DISABLE KEYS */;
INSERT INTO `playlist_has_tracks` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(2,7),(2,8),(2,9);
/*!40000 ALTER TABLE `playlist_has_tracks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songtrack`
--

DROP TABLE IF EXISTS `songtrack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `songtrack` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `album` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `song_or_video1` FOREIGN KEY (`id`) REFERENCES `track` (`song_or_video`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songtrack`
--

LOCK TABLES `songtrack` WRITE;
/*!40000 ALTER TABLE `songtrack` DISABLE KEYS */;
INSERT INTO `songtrack` VALUES (1,'%'),(2,'X'),(3,'+'),(7,'Expectations'),(8,'Florida Georgia Line');
/*!40000 ALTER TABLE `songtrack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `token` (
  `token` varchar(255) COLLATE utf8_bin NOT NULL,
  `account_user` varchar(45) COLLATE utf8_bin NOT NULL,
  `expiry_date` datetime NOT NULL,
  PRIMARY KEY (`token`,`account_user`),
  KEY `user_idx` (`account_user`),
  CONSTRAINT `user` FOREIGN KEY (`account_user`) REFERENCES `account` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `track` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `performer` varchar(45) COLLATE utf8_bin NOT NULL,
  `duration` int(11) NOT NULL,
  `playcount` int(11) NOT NULL,
  `offlineAvailable` tinyint(4) NOT NULL,
  `song_or_video` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`) /*!80000 INVISIBLE */,
  KEY `song_id_idx` (`song_or_video`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (1,'Castle on the Hill','Ed Sheeran',261,124,1,1),(2,'Perfect','Ed Sheeran',263,153,1,1),(3,'Tenerife Sea','Ed Sheeran',241,239,0,2),(4,'The A Team','Ed Sheeran',258,247,1,3),(5,'Girls Like You ft. Cardi B','Maroon 5',270,167,0,4),(6,'Stereo Hearts ft. Adam Levine','Gym Class Heroes',216,84,0,5),(7,'Babe ft. Taylor Swift','Sugarland',279,26,1,6),(8,'Meant To Be ft. Florida Georgia Line','Bebe Rexsha',164,5,0,7),(9,'Simple','Florida Georgia Line',3,185,0,8);
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `tracks_view`
--

DROP TABLE IF EXISTS `tracks_view`;
/*!50001 DROP VIEW IF EXISTS `tracks_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `tracks_view` AS SELECT 
 1 AS `id`,
 1 AS `title`,
 1 AS `performer`,
 1 AS `duration`,
 1 AS `playcount`,
 1 AS `offlineAvailable`,
 1 AS `album`,
 1 AS `publicationDate`,
 1 AS `description`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `videotrack`
--

DROP TABLE IF EXISTS `videotrack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `videotrack` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `publicationDate` date DEFAULT NULL,
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`) /*!80000 INVISIBLE */,
  CONSTRAINT `song_or_video2` FOREIGN KEY (`id`) REFERENCES `track` (`song_or_video`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videotrack`
--

LOCK TABLES `videotrack` WRITE;
/*!40000 ALTER TABLE `videotrack` DISABLE KEYS */;
INSERT INTO `videotrack` VALUES (4,'2018-05-30','https://youtu.be/aJOTlE1K90k'),(5,'2011-08-12','https://youtu.be/T3E9Wjbq44E'),(6,'2018-06-09','https://youtu.be/l25AL0BdD6w');
/*!40000 ALTER TABLE `videotrack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `tracks_view`
--

/*!50001 DROP VIEW IF EXISTS `tracks_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `tracks_view` AS select `track`.`id` AS `id`,`track`.`title` AS `title`,`track`.`performer` AS `performer`,`track`.`duration` AS `duration`,`track`.`playcount` AS `playcount`,`track`.`offlineAvailable` AS `offlineAvailable`,`songtrack`.`album` AS `album`,`videotrack`.`publicationDate` AS `publicationDate`,`videotrack`.`description` AS `description` from ((`track` left join `songtrack` on((`songtrack`.`id` = `track`.`song_or_video`))) left join `videotrack` on((`videotrack`.`id` = `track`.`song_or_video`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-22 20:08:37
