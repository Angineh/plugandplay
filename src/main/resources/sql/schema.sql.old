-- MySQL dump 10.13  Distrib 5.6.28, for Win64 (x86_64)
--
-- Host: localhost    Database: plugandplay
-- ------------------------------------------------------
-- Server version	5.6.28-log

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
-- Table structure for table `business`
--

DROP TABLE IF EXISTS `business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(200) NOT NULL,
  `BLURB` varchar(2000) NOT NULL,
  `WEBSITE` varchar(200) NOT NULL,
  `CONTACT` varchar(200) DEFAULT NULL,
  `CONTACT_TITLE` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `PHONE_NUMBER` varchar(200) DEFAULT NULL,
  `TOTAL_MONEY_RAISED` varchar(200) DEFAULT NULL,
  `STAGE` varchar(200) DEFAULT NULL,
  `INDUSTRIES` varchar(200) DEFAULT NULL,
  `EMPLOYEE_COUNT` varchar(200) DEFAULT NULL,
  `HQ_LOCATION` varchar(200) DEFAULT NULL,
  `HQ_ADDRESS_L1` varchar(200) DEFAULT NULL,
  `HQ_ADDRESS_L2` varchar(200) DEFAULT NULL,
  `HQ_CITY` varchar(200) DEFAULT NULL,
  `HQ_STATE` varchar(200) DEFAULT NULL,
  `HQ_ZIP` varchar(200) DEFAULT NULL,
  `ADVANTAGE` varchar(200) DEFAULT NULL,
  `BACKGROUND` varchar(200) DEFAULT NULL,
  `FOUNDED` varchar(200) DEFAULT NULL,
  `APPLICATION` varchar(200) DEFAULT NULL,
  `CASE_STUDY` varchar(200) DEFAULT NULL,
  `TAGS` varchar(200) DEFAULT NULL,
  `PROGRAM` varchar(200) DEFAULT NULL,
  `BATCH` varchar(200) DEFAULT NULL,
  `NOTES` varchar(200) DEFAULT NULL,
  `INVESTORS` varchar(200) DEFAULT NULL,
  `NUMBER_OF_INVESTORS` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `top100`
--

DROP TABLE IF EXISTS `top100`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `top100` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LIST_NAME` varchar(255) DEFAULT NULL,
  `ORDER_ID` int(11) DEFAULT NULL,
  `VENTURE_ID` int(11) DEFAULT NULL,
  `listName` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `top100` (`listName`),
  CONSTRAINT `top100` FOREIGN KEY (`listName`) REFERENCES `ventures` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `top100list`
--

DROP TABLE IF EXISTS `top100list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `top100list` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ARCHIVE` tinyint(1) DEFAULT NULL,
  `LIST_NAME` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LIST_NAME` (`LIST_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ventures`
--

DROP TABLE IF EXISTS `ventures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventures` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADVANTAGE` varchar(255) DEFAULT NULL,
  `B2B_B2C` varchar(255) DEFAULT NULL,
  `BACKGROUND` varchar(255) DEFAULT NULL,
  `BLURB` varchar(255) DEFAULT NULL,
  `CASE_STUDY` varchar(255) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `COMPANY_NAME` varchar(255) DEFAULT NULL,
  `COMPETITION` varchar(255) DEFAULT NULL,
  `CONTACT_NAME` varchar(255) DEFAULT NULL,
  `DATE_OF_INVESTMENT` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `EMPLOYEES` varchar(255) DEFAULT NULL,
  `FOUNDED` varchar(255) DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `MATERIALS` varchar(255) DEFAULT NULL,
  `PARTNER_INTERESTS` varchar(255) DEFAULT NULL,
  `PHONE_NUMBER` varchar(255) DEFAULT NULL,
  `PNP_CONTACT` varchar(255) DEFAULT NULL,
  `PORTFOLIO` tinyint(1) DEFAULT NULL,
  `STAGE` varchar(255) DEFAULT NULL,
  `TAGS` varchar(255) DEFAULT NULL,
  `TIME_STAMP` varchar(255) DEFAULT NULL,
  `TOTAL_MONEY_RAISED` varchar(255) DEFAULT NULL,
  `VERTICALS` varchar(255) DEFAULT NULL,
  `WEBSITE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ventures2`
--

DROP TABLE IF EXISTS `ventures2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventures2` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(200) NOT NULL,
  `BLURB` varchar(2000) NOT NULL,
  `WEBSITE` varchar(200) NOT NULL,
  `CONTACT` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `PHONE_NUMBER` varchar(200) DEFAULT NULL,
  `TOTAL_MONEY_RAISED` varchar(200) DEFAULT NULL,
  `STAGE` varchar(200) DEFAULT NULL,
  `BUSINESS_MODEL` varchar(200) DEFAULT NULL,
  `EMPLOYEE_COUNT` varchar(200) DEFAULT NULL,
  `LOCATION` varchar(200) DEFAULT NULL,
  `CITY` varchar(200) DEFAULT NULL,
  `COMPETITION` varchar(200) DEFAULT NULL,
  `ADVANTAGE` varchar(200) DEFAULT NULL,
  `BACKGROUND` varchar(200) DEFAULT NULL,
  `FOUNDED` varchar(200) DEFAULT NULL,
  `INDUSTRY` varchar(200) DEFAULT NULL,
  `CASE_STUDY` varchar(200) DEFAULT NULL,
  `NOTES` varchar(200) DEFAULT NULL,
  `TAGS` varchar(200) DEFAULT NULL,
  `MATERIALS` varchar(200) DEFAULT NULL,
  `PROGRAM` varchar(200) DEFAULT NULL,
  `BATCH` varchar(200) DEFAULT NULL,
  `INVEST` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2833 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ventures3`
--

DROP TABLE IF EXISTS `ventures3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventures3` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TIME_STAMP` varchar(200) NOT NULL,
  `COMPANY_NAME` varchar(200) NOT NULL,
  `BLURB` varchar(2000) NOT NULL,
  `VERTICALS` varchar(200) NOT NULL,
  `WEBSITE` varchar(200) NOT NULL,
  `PNP_CONTACT` varchar(200) DEFAULT NULL,
  `CONTACT_NAME` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `PHONE_NUMBER` varchar(200) DEFAULT NULL,
  `TOTAL_MONEY_RAISED` varchar(500) DEFAULT NULL,
  `STAGE` varchar(200) DEFAULT NULL,
  `B2B_B2C` varchar(200) DEFAULT NULL,
  `EMPLOYEES` varchar(200) DEFAULT NULL,
  `LOCATION` varchar(200) DEFAULT NULL,
  `CITY` varchar(200) DEFAULT NULL,
  `COMPETITION` varchar(2000) DEFAULT NULL,
  `ADVANTAGE` varchar(3000) DEFAULT NULL,
  `BACKGROUND` text,
  `FOUNDED` varchar(200) DEFAULT NULL,
  `PARTNER_INTERESTS` varchar(200) DEFAULT NULL,
  `CASE_STUDY` varchar(3500) DEFAULT NULL,
  `COMMENTS` varchar(3000) DEFAULT NULL,
  `TAGS` varchar(200) DEFAULT NULL,
  `MATERIALS` varchar(1000) DEFAULT NULL,
  `DATE_OF_INVESTMENT` varchar(200) DEFAULT NULL,
  `PORTFOLIO` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ventures4`
--

DROP TABLE IF EXISTS `ventures4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventures4` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TIME_STAMP` varchar(200) NOT NULL,
  `COMPANY_NAME` varchar(200) NOT NULL,
  `BLURB` varchar(2000) NOT NULL,
  `VERTICALS` varchar(200) NOT NULL,
  `WEBSITE` varchar(200) NOT NULL,
  `PNP_CONTACT` varchar(200) DEFAULT NULL,
  `CONTACT_NAME` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `PHONE_NUMBER` varchar(200) DEFAULT NULL,
  `TOTAL_MONEY_RAISED` varchar(500) DEFAULT NULL,
  `STAGE` varchar(200) DEFAULT NULL,
  `B2B_B2C` varchar(200) DEFAULT NULL,
  `EMPLOYEES` varchar(200) DEFAULT NULL,
  `LOCATION` varchar(200) DEFAULT NULL,
  `CITY` varchar(200) DEFAULT NULL,
  `COMPETITION` varchar(2000) DEFAULT NULL,
  `ADVANTAGE` varchar(3000) DEFAULT NULL,
  `BACKGROUND` text,
  `FOUNDED` varchar(200) DEFAULT NULL,
  `PARTNER_INTERESTS` varchar(200) DEFAULT NULL,
  `CASE_STUDY` varchar(3500) DEFAULT NULL,
  `COMMENTS` varchar(3000) DEFAULT NULL,
  `TAGS` varchar(200) DEFAULT NULL,
  `MATERIALS` varchar(1000) DEFAULT NULL,
  `DATE_OF_INVESTMENT` varchar(200) DEFAULT NULL,
  `PORTFOLIO` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=584 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-26 22:20:19
