-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.21 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table transport.companyaddress
CREATE TABLE IF NOT EXISTS `companyaddress` (
`addressid` int(11) NOT NULL AUTO_INCREMENT,
`address` varchar(300) DEFAULT NULL,
`city` varchar(35) DEFAULT NULL,
`pincode` varchar(10) DEFAULT NULL,
`username` varchar(20) DEFAULT NULL,
`name` varchar(100) NOT NULL,
PRIMARY KEY (`addressid`),
KEY `USERNAME_idx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.companyinvoice
CREATE TABLE IF NOT EXISTS `companyinvoice` (
`invoiceid` int(11) NOT NULL AUTO_INCREMENT,
`requestid` int(11) NOT NULL,
PRIMARY KEY (`invoiceid`),
UNIQUE KEY `requestid_UNIQUE` (`requestid`),
CONSTRAINT `requestid` FOREIGN KEY (`requestid`) REFERENCES `transportrequest` (`requestid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.costconfiguration
CREATE TABLE IF NOT EXISTS `costconfiguration` (
`SOURCEID` int(11) NOT NULL,
`DESTINATIONID` int(11) NOT NULL,
`COST` decimal(8,2) NOT NULL,
PRIMARY KEY (`SOURCEID`,`DESTINATIONID`),
KEY `SOURCEID_idx` (`SOURCEID`),
KEY `DESTINATIONID_idx` (`DESTINATIONID`),
CONSTRAINT `destinationid` FOREIGN KEY (`DESTINATIONID`) REFERENCES `destination` (`DESTINATIONID`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `sourceid` FOREIGN KEY (`SOURCEID`) REFERENCES `source` (`sourceid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.destination
CREATE TABLE IF NOT EXISTS `destination` (
`DESTINATIONID` int(11) NOT NULL AUTO_INCREMENT,
`DESTINATIONNAME` varchar(45) NOT NULL,
PRIMARY KEY (`DESTINATIONID`),
UNIQUE KEY `DESTINATIONNAME_UNIQUE` (`DESTINATIONNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.driver
CREATE TABLE IF NOT EXISTS `driver` (
`name` varchar(65) NOT NULL,
`email` varchar(65) NOT NULL,
`mobile` varchar(15) NOT NULL,
`vehiclemake` varchar(45) NOT NULL,
`vehiclenum` varchar(65) NOT NULL,
`vehiclecolor` varchar(45) NOT NULL,
`status` enum('UNBLOCK','BLOCK') NOT NULL,
`companyname` varchar(45) NOT NULL,
PRIMARY KEY (`email`),
UNIQUE KEY `email_UNIQUE` (`email`),
CONSTRAINT `Email` FOREIGN KEY (`email`) REFERENCES `signup` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.product
CREATE TABLE IF NOT EXISTS `product` (
`PRODUCTID` int(11) NOT NULL AUTO_INCREMENT,
`PRODUCTNAME` varchar(45) NOT NULL,
PRIMARY KEY (`PRODUCTID`),
UNIQUE KEY `PRODUCTNAME_UNIQUE` (`PRODUCTNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.service
CREATE TABLE IF NOT EXISTS `service` (
`SERVICEID` int(11) NOT NULL AUTO_INCREMENT,
`SERVICENAME` varchar(45) NOT NULL,
PRIMARY KEY (`SERVICEID`),
UNIQUE KEY `SERVICENAME_UNIQUE` (`SERVICENAME`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.signup
CREATE TABLE IF NOT EXISTS `signup` (
`email` varchar(75) NOT NULL,
`mobile` varchar(15) NOT NULL,
`usertype` enum('ADMIN','CUSTOMER','COMPANY','DRIVER') NOT NULL,
`companyname` varchar(75) DEFAULT NULL,
`password` varchar(45) NOT NULL,
PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.source
CREATE TABLE IF NOT EXISTS `source` (
`sourceid` int(11) NOT NULL AUTO_INCREMENT,
`sourcename` varchar(45) NOT NULL,
PRIMARY KEY (`sourceid`),
UNIQUE KEY `sourcename_UNIQUE` (`sourcename`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitcompanyinvoice
CREATE TABLE IF NOT EXISTS `transitcompanyinvoice` (
`invoiceid` int(11) NOT NULL AUTO_INCREMENT,
`requestid` int(11) NOT NULL,
PRIMARY KEY (`invoiceid`),
KEY `transitrequestid_idx` (`requestid`),
CONSTRAINT `transitrequestid` FOREIGN KEY (`requestid`) REFERENCES `transitrequest` (`requestid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitcostconfiguration
CREATE TABLE IF NOT EXISTS `transitcostconfiguration` (
`SOURCEID` int(11) NOT NULL,
`DESTINATIONID` int(11) NOT NULL,
`COST` decimal(8,2) NOT NULL,
`TRUCKSIZE` decimal(8,2) NOT NULL,
PRIMARY KEY (`SOURCEID`,`DESTINATIONID`,`TRUCKSIZE`),
KEY `TRANSITSOURCEID_idx` (`SOURCEID`),
KEY `TRANSITDESTINATIONID_idx` (`DESTINATIONID`),
CONSTRAINT `TRANSITdestinationid1` FOREIGN KEY (`DESTINATIONID`) REFERENCES `transitdestination` (`DESTINATIONID`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `TRANSITsourceid1` FOREIGN KEY (`SOURCEID`) REFERENCES `transitsource` (`sourceid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitdestination
CREATE TABLE IF NOT EXISTS `transitdestination` (
`DESTINATIONID` int(11) NOT NULL AUTO_INCREMENT,
`DESTINATIONNAME` varchar(45) NOT NULL,
PRIMARY KEY (`DESTINATIONID`),
UNIQUE KEY `DESTINATIONNAME_UNIQUE` (`DESTINATIONNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitproducts
CREATE TABLE IF NOT EXISTS `transitproducts` (
`PRODUCTID` int(11) NOT NULL AUTO_INCREMENT,
`PRODUCTNAME` varchar(45) NOT NULL,
PRIMARY KEY (`PRODUCTID`),
UNIQUE KEY `PRODUCTNAME_UNIQUE` (`PRODUCTNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitrequest
CREATE TABLE IF NOT EXISTS `transitrequest` (
`requestid` int(11) NOT NULL AUTO_INCREMENT,
`sourceid` int(11) NOT NULL,
`destinationid` int(11) NOT NULL,
`datetime` datetime NOT NULL,
`requeststatus` enum('P','R','A','ACK','START','FINISH') NOT NULL,
`productid` int(11) DEFAULT NULL,
`serviceid` int(11) DEFAULT NULL,
`trucksize` decimal(6,2) NOT NULL,
`customclear` tinyint(1) NOT NULL,
`cost` decimal(6,2) DEFAULT NULL,
`email` varchar(125) NOT NULL,
`updateddate` datetime DEFAULT CURRENT_TIMESTAMP,
`warehousecharges` decimal(6,2) DEFAULT NULL,
`mobile` varchar(15) NOT NULL,
`driveremail` varchar(65) DEFAULT NULL,
`otp` varchar(4) DEFAULT NULL,
PRIMARY KEY (`requestid`),
KEY `usernametransitreq` (`email`),
KEY `sourceidtransreq_idx` (`sourceid`),
KEY `destiidtransitreq_idx` (`destinationid`),
KEY `prodidtransitreq_idx` (`productid`),
KEY `servicetransitreq_idx` (`serviceid`),
KEY `transitdriveremailid_idx` (`driveremail`),
CONSTRAINT `transitdestinationid` FOREIGN KEY (`destinationid`) REFERENCES `transitdestination` (`DESTINATIONID`),
CONSTRAINT `transitdriveremailid` FOREIGN KEY (`driveremail`) REFERENCES `driver` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `transitproductid` FOREIGN KEY (`productid`) REFERENCES `transitproducts` (`PRODUCTID`),
CONSTRAINT `transitservicesid` FOREIGN KEY (`serviceid`) REFERENCES `transitservices` (`SERVICEID`),
CONSTRAINT `transitsourceid` FOREIGN KEY (`sourceid`) REFERENCES `transitsource` (`sourceid`),
CONSTRAINT `transituserid` FOREIGN KEY (`email`) REFERENCES `signup` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitservices
CREATE TABLE IF NOT EXISTS `transitservices` (
`SERVICEID` int(11) NOT NULL AUTO_INCREMENT,
`SERVICENAME` varchar(45) NOT NULL,
PRIMARY KEY (`SERVICEID`),
UNIQUE KEY `SERVICENAME_UNIQUE` (`SERVICENAME`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitsource
CREATE TABLE IF NOT EXISTS `transitsource` (
`sourceid` int(11) NOT NULL AUTO_INCREMENT,
`sourcename` varchar(45) NOT NULL,
PRIMARY KEY (`sourceid`),
UNIQUE KEY `sourcename_UNIQUE` (`sourcename`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transportrequest
CREATE TABLE IF NOT EXISTS `transportrequest` (
`requestid` int(11) NOT NULL AUTO_INCREMENT,
`sourceid` int(11) NOT NULL,
`destinationid` int(11) NOT NULL,
`mobileno` varchar(13) NOT NULL,
`datetime` datetime NOT NULL,
`cashinhand` decimal(6,2) DEFAULT NULL,
`multipletrips` int(11) DEFAULT NULL,
`roundtrips` int(11) DEFAULT NULL,
`requeststatus` enum('P','R','A','ACK','START','FINISH') NOT NULL,
`cost` decimal(6,2) NOT NULL,
`email` varchar(25) NOT NULL,
`usertype` enum('ADMIN','CUSTOMER','COMPANY') NOT NULL,
`driveremail` varchar(65) DEFAULT NULL,
`otp` varchar(4) DEFAULT NULL,
PRIMARY KEY (`requestid`),
KEY `sourceidtransreq_idx` (`sourceid`),
KEY `destinationidtransreq_idx` (`destinationid`),
KEY `emailidtransreq` (`email`),
KEY `driveremail_idx` (`driveremail`),
CONSTRAINT `destinationidtransreq` FOREIGN KEY (`destinationid`) REFERENCES `destination` (`DESTINATIONID`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `driveremail` FOREIGN KEY (`driveremail`) REFERENCES `driver` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
CONSTRAINT `emailidtransreq` FOREIGN KEY (`email`) REFERENCES `signup` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `sourceidtransreq` FOREIGN KEY (`sourceid`) REFERENCES `source` (`sourceid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
