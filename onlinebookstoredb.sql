CREATE DATABASE  IF NOT EXISTS `onlinebookstoredb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `onlinebookstoredb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinebookstoredb
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `street` varchar(100) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `userID` int(10) unsigned DEFAULT NULL,
  `agencyID` int(10) unsigned DEFAULT NULL,
  `supplierID` int(10) unsigned DEFAULT NULL,
  `addressID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`addressID`),
  KEY `userID` (`userID`),
  KEY `agencyID` (`agencyID`),
  KEY `supplierID` (`supplierID`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `address_ibfk_2` FOREIGN KEY (`agencyID`) REFERENCES `agency` (`agencyID`),
  CONSTRAINT `address_ibfk_3` FOREIGN KEY (`supplierID`) REFERENCES `supplier` (`supplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('341 East Lake Circle','Canton','GA','30115',32,NULL,NULL,14),('341 East Lake Circle','Canton','GA','30115',33,NULL,NULL,15);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agency`
--

DROP TABLE IF EXISTS `agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agency` (
  `agencyID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `aName` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `contactName` varchar(45) DEFAULT NULL,
  `contactPhone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`agencyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency`
--

LOCK TABLES `agency` WRITE;
/*!40000 ALTER TABLE `agency` DISABLE KEYS */;
/*!40000 ALTER TABLE `agency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `isbn` int(45) NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `authorName` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `picture` text,
  `edition` int(11) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `publicationYear` int(11) DEFAULT NULL,
  `qtyInStock` int(11) DEFAULT NULL,
  `minThreshold` int(11) DEFAULT NULL,
  `buyingPrice` double DEFAULT NULL,
  `sellingPrice` double DEFAULT NULL,
  `supplierID` int(10) unsigned DEFAULT NULL,
  `description` text,
  `status` varchar(45) DEFAULT 'Active',
  `rating` double DEFAULT '0',
  PRIMARY KEY (`isbn`),
  KEY `supplierID` (`supplierID`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`supplierID`) REFERENCES `supplier` (`supplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1145678945,'Historical','Mark Twain','The Adventures of Tom Sawyer','https://images-na.ssl-images-amazon.com/images/I/51zpYBtB-FL.jpg',2,'Penguin Classics',2015,58,15,4,7,NULL,'Follow the adventures of Tom Sawyer, as he tricks other people into painting fences.','Active',4),(1234432155,'Fiction','Oscar Wilde','The Picture of Dorian Gray','https://images-na.ssl-images-amazon.com/images/I/41OCVr-5ujL._SX322_BO1,204,203,200_.jpg',1,'Penguin',2006,53,43,4.56,9.1,NULL,'Dorian Gray gets a painting of him made by a mysterious old man, this painting keeps him from aging and reflects his true self.','Active',0),(1234567891,'Fiction','Edgar Rice Burroughs','Tarzan of the Apes','https://images-na.ssl-images-amazon.com/images/I/61beB3RAzbL._SY346_.jpg',1,'Dover Publications',2012,47,30,6,10,NULL,'Tarzan was raised by apes, now he has to learn how to interact with other humans.','Active',3),(1452666675,'Fiction','William Golding','Lord of the Flies','https://images-na.ssl-images-amazon.com/images/I/512mrQMBIOL._SY346_.jpg',3,'Penguin Books',1967,24,20,5.32,7.12,NULL,'A group of children are left to their own devices when their ship crashes on a deserted island.','Active',4.5),(1853260088,'Fiction','Herman Melville','Moby Dick','https://images-na.ssl-images-amazon.com/images/I/417%2B4OtvXhL._SX319_BO1,204,203,200_.jpg',1,'Wordsworth Publications',1999,14,10,4.55,7,NULL,'A man hunts down a white whale.','Active',0);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `cartID` int(11) NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `promoID` int(10) unsigned DEFAULT NULL,
  `isbn` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`cartID`),
  KEY `isbn` (`isbn`),
  KEY `promoID` (`promoID`),
  KEY `userID` (`userID`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`promoID`) REFERENCES `promotion` (`promoID`),
  CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,32,0,1234567891,2,20),(2,33,0,1145678945,1,7);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `userID` int(10) unsigned NOT NULL,
  `CCnumber` varchar(16) DEFAULT NULL,
  `CCtype` varchar(45) DEFAULT NULL,
  `expireDate` date DEFAULT NULL,
  `CCid` int(11) NOT NULL AUTO_INCREMENT,
  `csc` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`CCid`),
  KEY `userID` (`userID`),
  CONSTRAINT `creditcard_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (32,'7777000011112222','MasterCard','2018-12-01',19,'341'),(33,'4567444456786666','Visa','2018-02-01',20,'341');
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderNumber` int(11) NOT NULL,
  `agencyID` int(10) unsigned DEFAULT NULL,
  `orderStatus` varchar(45) DEFAULT NULL,
  `orderDate` datetime DEFAULT NULL,
  `shippingAddress` text,
  `billingAddress` text,
  `paymentMethod` varchar(45) DEFAULT NULL,
  `confirmationNumber` varchar(45) DEFAULT NULL,
  `transactionID` int(11) DEFAULT NULL,
  `userID` int(10) unsigned NOT NULL,
  `orderTotal` double DEFAULT NULL,
  `CCid` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`),
  KEY `userID` (`userID`),
  KEY `agencyID` (`agencyID`),
  KEY `orders_ibfk_1_idx` (`CCid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CCid`) REFERENCES `creditcard` (`CCid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`agencyID`) REFERENCES `agency` (`agencyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,NULL,'shipping','2017-12-04 00:00:00','341 East Lake Circle, Canton, GA, 30115','341 East Lake Circle, Canton, GA, 30115','MasterCard 2222','1',NULL,32,15.68,NULL),(2,NULL,'pending','2017-12-04 00:00:00','341 East Lake Circle, Canton, GA, 30115','341 East Lake Circle, Canton, GA, 30115','MasterCard 2222','2',NULL,32,13.65,NULL),(3,NULL,'completed','2017-12-04 00:00:00','341 East Lake Circle, Canton, GA, 30115','341 East Lake Circle, Canton, GA, 30115','MasterCard 2222','3',NULL,32,3.5,NULL),(4,NULL,'pending','2017-12-04 00:00:00','341 East Lake Circle, Canton, GA, 30115','341 East Lake Circle, Canton, GA, 30115','Visa 6666','4',NULL,33,10.68,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion` (
  `promoID` int(10) unsigned NOT NULL,
  `pName` varchar(45) DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `expiration` date DEFAULT NULL,
  PRIMARY KEY (`promoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES (0,'noPromo',0,'2099-12-31'),(1234,'Half Off',50,'2017-12-20'),(1423,'Winter Sale',25,'2017-12-25'),(4321,'New Winter Sale',25,'2017-12-31');
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplierID` int(10) unsigned NOT NULL,
  `sName` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `contactName` varchar(45) DEFAULT NULL,
  `contactPhone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`supplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `orderNumber` int(11) NOT NULL,
  `transactionID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `isbn` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `promoID` int(10) unsigned DEFAULT NULL,
  `total` double DEFAULT NULL,
  `rating` double DEFAULT '0',
  PRIMARY KEY (`transactionID`),
  KEY `isbn` (`isbn`),
  KEY `promoID` (`promoID`),
  KEY `orderNumber` (`orderNumber`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`),
  CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`promoID`) REFERENCES `promotion` (`promoID`),
  CONSTRAINT `transactions_ibfk_3` FOREIGN KEY (`orderNumber`) REFERENCES `orders` (`orderNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,1,1452666675,3,0,21.36,5),(1,2,1234567891,1,0,10,3),(2,3,1234432155,3,0,27.299999999999997,0),(3,4,1145678945,1,1234,3.5,4),(4,5,1452666675,3,1234,10.68,4);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fName` varchar(45) DEFAULT NULL,
  `lName` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `paymentInfo` varchar(45) DEFAULT NULL,
  `userType` varchar(45) DEFAULT NULL,
  `userPassword` varchar(45) DEFAULT NULL,
  `userCode` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'unverified',
  `subscribed` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Brad','Reeves','7706559256','bkr02962@uga.edu',NULL,'SystemAdmin','pass13',1234,'verified',0),(30,'Lakshay','Sharma','7707777777','lakshay35@gmail.com',NULL,'Manager','pass',4868,'verified',1),(31,'Melinda','Reeves','4047902890','reevesmb@comcast.net',NULL,'Shipping','pass',8510,'verified',0),(32,'Brad','Reeves','7706559256','bradrules13@comcast.net',NULL,'Customer','pass13',5604,'verified',1),(33,'Brad','Reeves','7706559256','reevesbk@gmail.com',NULL,'Customer','A4muZBz1',4494,'verified',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-04 18:46:49
