

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table transport.companyaddress
CREATE TABLE IF NOT EXISTS transport.`companyaddress` (
`addressid` int(11) NOT NULL AUTO_INCREMENT,
`address` varchar(300) DEFAULT NULL,
`city` varchar(35) DEFAULT NULL,
`pincode` varchar(10) DEFAULT NULL,
`username` varchar(20) DEFAULT NULL,
`name` varchar(100) NOT NULL,
PRIMARY KEY (`addressid`),
KEY `USERNAME_idx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.companyinvoice
CREATE TABLE IF NOT EXISTS transport.`companyinvoice` (
`invoiceid` int(11) NOT NULL AUTO_INCREMENT,
`requestid` int(11) NOT NULL,
PRIMARY KEY (`invoiceid`),
UNIQUE KEY `requestid_UNIQUE` (`requestid`),
CONSTRAINT `requestid` FOREIGN KEY (`requestid`) REFERENCES `transportrequest` (`requestid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.costconfiguration
CREATE TABLE IF NOT EXISTS transport.`costconfiguration` (
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
CREATE TABLE IF NOT EXISTS transport.`destination` (
`DESTINATIONID` int(11) NOT NULL AUTO_INCREMENT,
`DESTINATIONNAME` varchar(45) NOT NULL,
PRIMARY KEY (`DESTINATIONID`),
UNIQUE KEY `DESTINATIONNAME_UNIQUE` (`DESTINATIONNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.driver
CREATE TABLE IF NOT EXISTS transport.`driver` (
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
CREATE TABLE IF NOT EXISTS transport.`product` (
`PRODUCTID` int(11) NOT NULL AUTO_INCREMENT,
`PRODUCTNAME` varchar(45) NOT NULL,
PRIMARY KEY (`PRODUCTID`),
UNIQUE KEY `PRODUCTNAME_UNIQUE` (`PRODUCTNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.service
CREATE TABLE IF NOT EXISTS transport.`service` (
`SERVICEID` int(11) NOT NULL AUTO_INCREMENT,
`SERVICENAME` varchar(45) NOT NULL,
PRIMARY KEY (`SERVICEID`),
UNIQUE KEY `SERVICENAME_UNIQUE` (`SERVICENAME`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.signup
CREATE TABLE IF NOT EXISTS transport.`signup` (
`email` varchar(75) NOT NULL,
`mobile` varchar(15) NOT NULL,
`usertype` enum('ADMIN','CUSTOMER','COMPANY','DRIVER') NOT NULL,
`companyname` varchar(75) DEFAULT NULL,
`password` varchar(45) NOT NULL,
PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.source
CREATE TABLE IF NOT EXISTS transport.`source` (
`sourceid` int(11) NOT NULL AUTO_INCREMENT,
`sourcename` varchar(45) NOT NULL,
PRIMARY KEY (`sourceid`),
UNIQUE KEY `sourcename_UNIQUE` (`sourcename`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitcostconfiguration
CREATE TABLE IF NOT EXISTS transport.`transitcostconfiguration` (
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
-- Dumping structure for table transport.transitcustomsdocs
CREATE TABLE IF NOT EXISTS transport.`transitcustomsdocs` (
`docid` int(11) NOT NULL AUTO_INCREMENT,
`requestid` varchar(45) NOT NULL,
`customsdoc` blob NOT NULL,
PRIMARY KEY (`docid`),
KEY `transitdocsreqid_idx` (`requestid`),
CONSTRAINT `transitdocsreqid` FOREIGN KEY (`requestid`) REFERENCES `transitrequest` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitdestination
CREATE TABLE IF NOT EXISTS transport.`transitdestination` (
`DESTINATIONID` int(11) NOT NULL AUTO_INCREMENT,
`DESTINATIONNAME` varchar(45) NOT NULL,
PRIMARY KEY (`DESTINATIONID`),
UNIQUE KEY `DESTINATIONNAME_UNIQUE` (`DESTINATIONNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitproducts
CREATE TABLE IF NOT EXISTS transport.`transitproducts` (
`PRODUCTID` int(11) NOT NULL AUTO_INCREMENT,
`PRODUCTNAME` varchar(45) NOT NULL,
PRIMARY KEY (`PRODUCTID`),
UNIQUE KEY `PRODUCTNAME_UNIQUE` (`PRODUCTNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitrequest
CREATE TABLE IF NOT EXISTS transport.`transitrequest` (
`requestid` int(11) NOT NULL AUTO_INCREMENT,
`sourceid` int(11) NOT NULL,
`destinationid` int(11) NOT NULL,
`datetime` datetime NOT NULL,
`requeststatus` enum('P','R','A') NOT NULL,
`productid` int(11) DEFAULT NULL,
`serviceid` int(11) DEFAULT NULL,
`trucksize` decimal(6,2) NOT NULL,
`customclear` tinyint(1) NOT NULL,
`cost` decimal(6,2) DEFAULT NULL,
`username` varchar(125) NOT NULL,
`updateddate` datetime DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`requestid`),
KEY `usernametransitreq` (`username`),
CONSTRAINT `usernametransitreq` FOREIGN KEY (`username`) REFERENCES `signup` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitservices
CREATE TABLE IF NOT EXISTS transport.`transitservices` (
`SERVICEID` int(11) NOT NULL AUTO_INCREMENT,
`SERVICENAME` varchar(45) NOT NULL,
PRIMARY KEY (`SERVICEID`),
UNIQUE KEY `SERVICENAME_UNIQUE` (`SERVICENAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transitsource
CREATE TABLE IF NOT EXISTS transport.`transitsource` (
`sourceid` int(11) NOT NULL AUTO_INCREMENT,
`sourcename` varchar(45) NOT NULL,
PRIMARY KEY (`sourceid`),
UNIQUE KEY `sourcename_UNIQUE` (`sourcename`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table transport.transportrequest
CREATE TABLE IF NOT EXISTS transport.`transportrequest` (
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
