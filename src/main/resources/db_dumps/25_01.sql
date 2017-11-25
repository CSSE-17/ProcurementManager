-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: procurement_db
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `delivery_note`
--

DROP TABLE IF EXISTS `delivery_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_note` (
  `noteID` char(6) NOT NULL,
  `purchID` char(6) DEFAULT NULL,
  `requisitionID` char(6) DEFAULT NULL,
  `totalAmount` double DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `paidDate` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`noteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_note`
--

LOCK TABLES `delivery_note` WRITE;
/*!40000 ALTER TABLE `delivery_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `itemName` varchar(50) NOT NULL,
  `price` float NOT NULL,
  `brand` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`itemName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This are item we select to give to suppliers and people';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES ('Cement',2000,'Holcim'),('Fine Sand 1 Cubic metre',10000,'N/A');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requisition`
--

DROP TABLE IF EXISTS `requisition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requisition` (
  `requisition_id` varchar(50) NOT NULL,
  `date` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `value` double DEFAULT NULL,
  PRIMARY KEY (`requisition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requisition`
--

LOCK TABLES `requisition` WRITE;
/*!40000 ALTER TABLE `requisition` DISABLE KEYS */;
INSERT INTO `requisition` VALUES ('51a0bd3a-dd4e-41fe-b720-12c89314076d','2017-11-25 08:05:48','mahendra',NULL),('ccbba358-cbe8-4cd1-b66d-31b4a641c189','2017-11-25 07:14:57','mahendra',NULL);
/*!40000 ALTER TABLE `requisition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requisition_item`
--

DROP TABLE IF EXISTS `requisition_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requisition_item` (
  `reqID` varchar(50) NOT NULL,
  `itemName` varchar(45) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reqID`,`itemName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requisition_item`
--

LOCK TABLES `requisition_item` WRITE;
/*!40000 ALTER TABLE `requisition_item` DISABLE KEYS */;
INSERT INTO `requisition_item` VALUES ('51a0bd3a-dd4e-41fe-b720-12c89314076d','Cement',1,'pending',NULL),('51a0bd3a-dd4e-41fe-b720-12c89314076d','Fine Sand 1 Cubic metre',1,'pending',NULL),('927df150-98dd-418f-9eb7-2053d81311f7','Cement',1,'pending',NULL),('927df150-98dd-418f-9eb7-2053d81311f7','Fine Sand 1 Cubic metre',1,'pending',NULL),('c483cfde-5ae2-4900-93bf-93794a99d31c','Cement',1,'pending',NULL),('c483cfde-5ae2-4900-93bf-93794a99d31c','Fine Sand 1 Cubic metre',1,'pending',NULL),('d9fb62e4-04fc-4cfe-a5e6-a135bb49ada3','Cement',1,NULL,NULL),('d9fb62e4-04fc-4cfe-a5e6-a135bb49ada3','Fine Sand 1 Cubic metre',1,NULL,NULL);
/*!40000 ALTER TABLE `requisition_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplier_id` char(6) NOT NULL,
  `name` varchar(25) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('s001','supplier','sup@gmail.com','0988989890','streetaddr1, ssasas, jhjhsjahsjhajshjhj'),('s002','mkmk','mkmkm','kmk','mkmk');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `acc_type` varchar(45) DEFAULT NULL,
  `access_privileges` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES ('mahendra','1c63129ae9db9c60c3e8aa94d3e00495','mahendrathennakoon@gmail.com','mahendra','tennakoon','admin','101010');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-25  8:10:21
