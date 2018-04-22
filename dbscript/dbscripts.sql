-- MySQL dump 10.13  Distrib 5.7.21, for macos10.13 (x86_64)
--
-- Host: localhost    Database: transport
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `COMPANYADDRESS`
--

DROP TABLE IF EXISTS `COMPANYADDRESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMPANYADDRESS` (
  `addressid` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(300) NOT NULL,
  `city` varchar(35) NOT NULL,
  `pincode` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`addressid`),
  KEY `USERNAME_idx` (`username`),
  CONSTRAINT `USERNAME` FOREIGN KEY (`username`) REFERENCES `USER` (`USERNAME`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `COMPANYINVOICE`
--

DROP TABLE IF EXISTS `COMPANYINVOICE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMPANYINVOICE` (
  `invoiceid` int(11) NOT NULL AUTO_INCREMENT,
  `requestid` int(11) NOT NULL,
  PRIMARY KEY (`invoiceid`),
  UNIQUE KEY `requestid_UNIQUE` (`requestid`),
  CONSTRAINT `requestid` FOREIGN KEY (`requestid`) REFERENCES `TRANSPORTREQUEST` (`requestid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `COSTCONFIGURATION`
--

DROP TABLE IF EXISTS `COSTCONFIGURATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COSTCONFIGURATION` (
  `SOURCEID` int(11) NOT NULL,
  `DESTINATIONID` int(11) NOT NULL,
  `COST` decimal(5,2) NOT NULL,
  PRIMARY KEY (`SOURCEID`,`DESTINATIONID`),
  KEY `SOURCEID_idx` (`SOURCEID`),
  KEY `DESTINATIONID_idx` (`DESTINATIONID`),
  CONSTRAINT `destinationid` FOREIGN KEY (`DESTINATIONID`) REFERENCES `DESTINATION` (`DESTINATIONID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sourceid` FOREIGN KEY (`SOURCEID`) REFERENCES `SOURCE` (`sourceid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DESTINATION`
--

DROP TABLE IF EXISTS `DESTINATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DESTINATION` (
  `DESTINATIONID` int(11) NOT NULL AUTO_INCREMENT,
  `DESTINATIONNAME` varchar(45) NOT NULL,
  PRIMARY KEY (`DESTINATIONID`),
  UNIQUE KEY `DESTINATIONNAME_UNIQUE` (`DESTINATIONNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PRODUCT`
--

DROP TABLE IF EXISTS `PRODUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCT` (
  `PRODUCTID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCTNAME` varchar(45) NOT NULL,
  PRIMARY KEY (`PRODUCTID`),
  UNIQUE KEY `PRODUCTNAME_UNIQUE` (`PRODUCTNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SERVICE`
--

DROP TABLE IF EXISTS `SERVICE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SERVICE` (
  `SERVICEID` int(11) NOT NULL AUTO_INCREMENT,
  `SERVICENAME` varchar(45) NOT NULL,
  PRIMARY KEY (`SERVICEID`),
  UNIQUE KEY `SERVICENAME_UNIQUE` (`SERVICENAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SOURCE`
--

DROP TABLE IF EXISTS `SOURCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SOURCE` (
  `sourceid` int(11) NOT NULL AUTO_INCREMENT,
  `sourcename` varchar(45) NOT NULL,
  PRIMARY KEY (`sourceid`),
  UNIQUE KEY `sourcename_UNIQUE` (`sourcename`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TRANSITREQUEST`
--

DROP TABLE IF EXISTS `TRANSITREQUEST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRANSITREQUEST` (
  `requestid` int(11) NOT NULL AUTO_INCREMENT,
  `sourceid` int(11) NOT NULL,
  `destinationid` int(11) NOT NULL,
  `mobileno` varchar(13) NOT NULL,
  `datetime` datetime NOT NULL,
  `requeststatus` enum('P','R','A') NOT NULL,
  `product` varchar(25) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `sizes` varchar(20) DEFAULT NULL,
  `transType` varchar(25) DEFAULT NULL,
  `customClear` tinyint(1) DEFAULT NULL,
  `cost` decimal(6,2) NOT NULL,
  `username` varchar(25) NOT NULL,
  `customsDoc` blob,
  `updateddate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`requestid`),
  KEY `usernametransitreq` (`username`),
  CONSTRAINT `usernametransitreq` FOREIGN KEY (`username`) REFERENCES `USER` (`USERNAME`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TRANSPORTREQUEST`
--

DROP TABLE IF EXISTS `TRANSPORTREQUEST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRANSPORTREQUEST` (
  `requestid` int(11) NOT NULL AUTO_INCREMENT,
  `sourceid` int(11) NOT NULL,
  `destinationid` int(11) NOT NULL,
  `mobileno` varchar(13) NOT NULL,
  `datetime` datetime NOT NULL,
  `cashinhand` decimal(6,2) DEFAULT NULL,
  `multipletrips` int(11) DEFAULT NULL,
  `roundtrips` int(11) DEFAULT NULL,
  `requeststatus` enum('P','R','A') NOT NULL,
  `updateddate` datetime NOT NULL,
  `cost` decimal(6,2) NOT NULL,
  `username` varchar(25) NOT NULL,
  `usertype` enum('ADMIN','CUSTOMER','COMPANY') NOT NULL,
  PRIMARY KEY (`requestid`),
  KEY `usernametransreq` (`username`),
  CONSTRAINT `usernametransreq` FOREIGN KEY (`username`) REFERENCES `USER` (`USERNAME`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `USERNAME` varchar(20) NOT NULL,
  `FIRSTNAME` varchar(45) NOT NULL,
  `LASTNAME` varchar(25) NOT NULL,
  `USERTYPE` enum('ADMIN','CUSTOMER','COMPANY') NOT NULL,
  `GENDER` enum('OTHER','FEMALE','MALE') NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `MOBILE` varchar(45) DEFAULT NULL,
  `DOB` datetime DEFAULT NULL,
  `PASSWORD` varchar(25) NOT NULL,
  `UPDATEDDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`USERNAME`),
  UNIQUE KEY `USER_ID_UNIQUE` (`USERNAME`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-22  9:48:59
