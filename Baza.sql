-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: leavemanagementsystem
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `leaverequests`
--

DROP TABLE IF EXISTS `leaverequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leaverequests` (
  `id` varchar(50) NOT NULL,
  `ime` varchar(100) NOT NULL,
  `razlog` text NOT NULL,
  `dani` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(255) DEFAULT 'Cekanje'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leaverequests`
--

LOCK TABLES `leaverequests` WRITE;
/*!40000 ALTER TABLE `leaverequests` DISABLE KEYS */;
INSERT INTO `leaverequests` VALUES ('3','emin','bolest',5,'2025-01-04 10:51:29','Odobren'),('4','osman','dr',4,'2025-01-04 11:48:23','Odbijen'),('22','ahmed','gripa',10,'2025-01-04 17:32:31','Cekanje'),('5','zlata','godisnji odmor',7,'2025-01-08 12:55:23','Cekanje');
/*!40000 ALTER TABLE `leaverequests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'amina','amina','superadmin'),(2,'medina','medina','manager'),(3,'emin','emin','employee'),(4,'osman','osman','employee'),(5,'zlata','zlata','employee'),(6,'rasema','rasema','employee'),(7,'dzenita','dzenita','employee'),(8,'adna','adna','employee'),(9,'belma','belma','employee'),(10,'ajsa','ajsa','employee'),(11,'adnan','adnan','employee'),(12,'dzenan','dzenan','employee'),(13,'kerim','kerim','employee'),(14,'anis','anis','employee'),(15,'demir','demir','employee'),(16,'lejla','lejla','employee'),(17,'dijana','dijana','employee'),(18,'edina','edina','employee'),(19,'aida','aida','employee'),(20,'dino','dino','employee'),(21,'mahir','mahir','employee'),(22,'ahmed','ahmed','employee');
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

-- Dump completed on 2025-01-11 11:15:25
