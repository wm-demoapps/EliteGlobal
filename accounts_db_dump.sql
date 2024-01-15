-- MySQL dump 10.13  Distrib 5.6.51, for Linux (x86_64)
--
-- Host: localhost    Database: AccountsDB
-- ------------------------------------------------------
-- Server version	5.6.51

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `account_no` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `net_balance` int(11) DEFAULT NULL,
  `account_type_id` int(11) DEFAULT NULL,
  `corporate_id` int(11) DEFAULT NULL,
  `fund_manager` varchar(255) DEFAULT NULL,
  `fund_house` varchar(255) DEFAULT NULL,
  `amount_invested` int(11) DEFAULT NULL,
  `folio_no` int(11) DEFAULT NULL,
  `fd_number` int(11) DEFAULT NULL,
  `fd_upper_limit` int(11) DEFAULT NULL,
  `fd_amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_account_TO_account_tyel4vL` (`account_type_id`),
  KEY `FK_account_TO_corporate_uEuiW` (`corporate_id`),
  CONSTRAINT `FK_account_TO_account_tyel4vL` FOREIGN KEY (`account_type_id`) REFERENCES `account_type` (`id`),
  CONSTRAINT `FK_account_TO_corporate_uEuiW` FOREIGN KEY (`corporate_id`) REFERENCES `corporate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Business Checking','7770000000302','CURRENT','USD',15052522,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'Merchant Account','7770000000342','CURRENT','USD',15098170,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'Business Savings','7770000000367','SAVING','USD',5099550,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'Virtual Savings','7770000000501','Virtual','USD',2000000,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'Virtual Checking Account','7770000000523','Virtual','USD',2500000,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'ABC Bank FD','7770000000603',NULL,'USD',5000000,4,1,NULL,NULL,NULL,NULL,32555555,10000000,5000000),(8,'DXY Bank FD','7770000000634',NULL,'USD',700000,4,1,NULL,NULL,NULL,NULL,32555777,8000000,700000),(9,'QWE MF Fund','null',NULL,'USD',47900000,5,1,'Amit Misra','QWE MF',47900000,33242414,NULL,NULL,NULL),(10,'JOY MF Fund','null',NULL,'USD',23900000,5,1,'Stuart Binny','JOY MF',23900000,332424334,NULL,NULL,NULL),(11,'ERT MF Fund','null',NULL,'USD',29900000,5,1,'Max Bird','ERT MF',29900000,33242424,NULL,NULL,NULL),(12,'TY External Account','7770000000812','External Account','USD',4600000,6,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'UDK External Account','7770000000889','External Account','USD',6990000,6,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'V2R External Account','7770000000867','External Account','USD',11122000,6,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'TS Checking Account','999000000123','CURRENT','USD',202323750,1,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'TS Savings Account','999000000134','CURRENT','USD',200022950,1,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'TS Virtual Savings','999000000230','Virtual','USD',130000400,3,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'TS Virtual Business','999000000240','Virtual','USD',3000000,3,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'TS YY Bank FD','999000000304',NULL,'USD',5000000,4,2,NULL,NULL,NULL,NULL,349999323,10000000,5000000),(20,'TS UCD Bank FD','999000000345',NULL,'USD',900000,4,2,NULL,NULL,NULL,NULL,349999233,30000000,900000),(21,'TS WER Fund',NULL,NULL,'USD',400000,5,2,'Gopal Nath','WER MF',400000,2144342,NULL,NULL,NULL),(22,'TS HBFC Fund',NULL,NULL,'USD',34888900,5,2,'Sai ram','HBFC MF',34888900,2353235,NULL,NULL,NULL),(23,'TS AB Bank External','99900002342','External Account','USD',4900000,6,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,'TS HHD Bank External','999000023454','External Account','USD',230000,6,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_type`
--

DROP TABLE IF EXISTS `account_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_type`
--

LOCK TABLES `account_type` WRITE;
/*!40000 ALTER TABLE `account_type` DISABLE KEYS */;
INSERT INTO `account_type` VALUES (1,'CASA'),(3,'Virtual Account'),(4,'FD Summary'),(5,'MF Summary'),(6,'External Account Summary');
/*!40000 ALTER TABLE `account_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficiary`
--

DROP TABLE IF EXISTS `beneficiary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beneficiary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `ifsc` varchar(255) DEFAULT NULL,
  `account_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiary`
--

LOCK TABLES `beneficiary` WRITE;
/*!40000 ALTER TABLE `beneficiary` DISABLE KEYS */;
INSERT INTO `beneficiary` VALUES (1,'MKG Tyres','AXIC00000916','7770000061793'),(2,'KTU Steel','UNDC00036271','8728300094625'),(3,'Blaze Enterprise','UNIC000008162','928152637271');
/*!40000 ALTER TABLE `beneficiary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corporate`
--

DROP TABLE IF EXISTS `corporate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `corporate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corporate`
--

LOCK TABLES `corporate` WRITE;
/*!40000 ALTER TABLE `corporate` DISABLE KEYS */;
INSERT INTO `corporate` VALUES (1,'Toyota Motors','resources/images/imagelists/toyota-logo.svg'),(2,'Tata Steel','resources/images/imagelists/tata-logo.svg');
/*!40000 ALTER TABLE `corporate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corporate_summary`
--

DROP TABLE IF EXISTS `corporate_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `corporate_summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `net_available_balance` int(11) DEFAULT NULL,
  `available_limit` int(11) DEFAULT NULL,
  `all_payables` int(11) DEFAULT NULL,
  `all_receivables` int(11) DEFAULT NULL,
  `corporate_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_corporate_summary_corQ6EGJ` (`corporate_id`),
  CONSTRAINT `FK_corporate_summary_TO_lnJ4t` FOREIGN KEY (`corporate_id`) REFERENCES `corporate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corporate_summary`
--

LOCK TABLES `corporate_summary` WRITE;
/*!40000 ALTER TABLE `corporate_summary` DISABLE KEYS */;
INSERT INTO `corporate_summary` VALUES (1,37400000,3565000,3860000,6090000,1),(2,4792012,372812,348011,471829,2);
/*!40000 ALTER TABLE `corporate_summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corporate_user`
--

DROP TABLE IF EXISTS `corporate_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `corporate_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `profile_pic` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `corporate_id` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_corporate_user_usernatUUVB` (`username`,`email`),
  KEY `FK_corporate_user_TO_corx3ALa` (`corporate_id`),
  CONSTRAINT `FK_corporate_user_TO_corx3ALa` FOREIGN KEY (`corporate_id`) REFERENCES `corporate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corporate_user`
--

LOCK TABLES `corporate_user` WRITE;
/*!40000 ALTER TABLE `corporate_user` DISABLE KEYS */;
INSERT INTO `corporate_user` VALUES (1,'John Doe',NULL,'johndoe',NULL,1,'johndoe@gmail.com'),(2,'Jane Smith',NULL,'janesmith',NULL,2,'janesmith@gmail.com'),(3,'Steve Jackson',NULL,'stevejackson','Wavemaker@123',1,'stevejackson@gmail.com');
/*!40000 ALTER TABLE `corporate_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_line_details`
--

DROP TABLE IF EXISTS `credit_line_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_line_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `month` varchar(255) DEFAULT NULL,
  `expected_credit` int(11) DEFAULT NULL,
  `actual_credit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_line_details`
--

LOCK TABLES `credit_line_details` WRITE;
/*!40000 ALTER TABLE `credit_line_details` DISABLE KEYS */;
INSERT INTO `credit_line_details` VALUES (1,'JAN',150,50),(2,'FEB',140,80),(3,'MAR',190,70),(4,'APR',200,150),(5,'MAY',280,90);
/*!40000 ALTER TABLE `credit_line_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partial_account_type_mappping`
--

DROP TABLE IF EXISTS `partial_account_type_mappping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partial_account_type_mappping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_type_id` int(11) DEFAULT NULL,
  `partial` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_partial_account_type_56gQM` (`account_type_id`),
  CONSTRAINT `FK_partial_account_type_7BQL8` FOREIGN KEY (`account_type_id`) REFERENCES `account_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partial_account_type_mappping`
--

LOCK TABLES `partial_account_type_mappping` WRITE;
/*!40000 ALTER TABLE `partial_account_type_mappping` DISABLE KEYS */;
INSERT INTO `partial_account_type_mappping` VALUES (1,1,'partialCASAAccountServices',''),(2,3,'partialVirtualAccountServices',''),(3,4,'partialFDAccountServices',''),(4,5,'partialMFAccountServices',''),(5,6,'partialExternalAccServices','\0');
/*!40000 ALTER TABLE `partial_account_type_mappping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'CASA'),(2,'Credit Card'),(3,'Debit Card'),(4,'Virtual Account');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `payment_method_id` int(11) DEFAULT NULL,
  `source_account_id` int(11) DEFAULT NULL,
  `benericiary_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `corporate_ref_no` varchar(255) DEFAULT NULL,
  `transaction_status_id` int(11) DEFAULT NULL,
  `transaction_type_id` int(11) DEFAULT NULL,
  `payment_instruction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_transaction_TO_accounVz6oF` (`source_account_id`),
  KEY `FK_transaction_TO_benefik73TZ` (`benericiary_id`),
  KEY `FK_transaction_TO_paymenk2Dwd` (`payment_method_id`),
  KEY `FK_transaction_TO_transaFMA6r` (`transaction_type_id`),
  KEY `FK_transaction_TO_transaWohpe` (`transaction_status_id`),
  CONSTRAINT `FK_transaction_TO_accounVz6oF` FOREIGN KEY (`source_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK_transaction_TO_benefik73TZ` FOREIGN KEY (`benericiary_id`) REFERENCES `beneficiary` (`id`),
  CONSTRAINT `FK_transaction_TO_paymenk2Dwd` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`),
  CONSTRAINT `FK_transaction_TO_transaFMA6r` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`id`),
  CONSTRAINT `FK_transaction_TO_transaWohpe` FOREIGN KEY (`transaction_status_id`) REFERENCES `transaction_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (5,'2023-12-19',1,1,1,500,'','1',1,1,NULL),(6,'2023-12-19',1,1,1,100,'','1',3,1,NULL),(7,'2023-12-19',1,1,1,100,'','1',2,1,NULL),(8,'2023-12-19',1,3,1,100,'','1',2,1,NULL),(9,'2023-12-19',1,3,1,100,'','1',3,1,NULL),(10,'2023-12-19',1,3,1,100,'','1',2,1,NULL),(11,'2023-12-19',1,2,1,50,'','1',2,1,NULL),(12,'2023-12-19',1,2,1,10,'','1',2,1,NULL),(13,'2023-12-20',1,2,3,200,'Tax Payment','dasda',1,NULL,NULL),(14,'2023-12-21',1,2,2,300,'','1',1,1,NULL),(15,'2023-12-21',1,1,3,300,'','1',1,1,NULL),(16,'2023-12-21',1,3,2,150,'','1',2,1,NULL),(17,'2023-12-21',1,NULL,NULL,NULL,'','1',1,1,NULL),(18,'2023-12-21',1,16,3,350,'','2',1,1,NULL),(19,'2023-12-26',1,15,3,250,'Business Purchase','DSAD2718',1,1,'Business raw material purchase'),(20,'2023-12-20',1,1,3,300,'Tax Payment','DSAD2718',1,1,NULL),(21,'2023-12-26',1,2,2,450,'','1',1,1,NULL),(22,'2023-12-26',1,1,1,500,'Business Purchase','DSAD2718',2,1,NULL),(23,'2023-12-26',1,2,3,120,NULL,NULL,2,1,NULL),(24,'2023-12-26',1,1,1,240,NULL,NULL,2,1,NULL),(25,'2023-12-26',1,3,2,400,NULL,NULL,2,1,NULL),(26,'2023-12-26',1,1,1,530,NULL,NULL,1,1,NULL),(27,'2023-12-26',1,2,3,120,NULL,NULL,1,1,NULL),(28,'2023-12-26',1,2,2,180,NULL,NULL,1,1,NULL),(29,'2023-12-26',1,3,1,180,NULL,NULL,1,1,NULL),(30,'2023-12-27',1,2,3,470,'Tax Payment','DSAD2718',1,1,NULL),(31,'2023-12-27',1,1,3,300,NULL,NULL,1,1,NULL),(32,'2023-12-27',1,1,3,45678,'Tax Payment','100009',1,1,NULL),(33,'2024-01-03',1,2,2,120,NULL,NULL,1,1,NULL),(34,'2024-01-03',1,3,3,200,'','1',1,1,NULL),(35,'2024-01-03',1,2,1,350,'Business Purchase','DSAD2718',1,1,NULL);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_instruction`
--

DROP TABLE IF EXISTS `transaction_instruction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_instruction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instruction` varchar(255) DEFAULT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_transaction_instructiiie44` (`transaction_id`),
  CONSTRAINT `FK_transaction_instructiiie44` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_instruction`
--

LOCK TABLES `transaction_instruction` WRITE;
/*!40000 ALTER TABLE `transaction_instruction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_instruction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_status`
--

DROP TABLE IF EXISTS `transaction_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_status`
--

LOCK TABLES `transaction_status` WRITE;
/*!40000 ALTER TABLE `transaction_status` DISABLE KEYS */;
INSERT INTO `transaction_status` VALUES (1,'Pending'),(2,'Completed'),(3,'Rejected');
/*!40000 ALTER TABLE `transaction_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_type`
--

DROP TABLE IF EXISTS `transaction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_type`
--

LOCK TABLES `transaction_type` WRITE;
/*!40000 ALTER TABLE `transaction_type` DISABLE KEYS */;
INSERT INTO `transaction_type` VALUES (1,'Single'),(2,'Bulk');
/*!40000 ALTER TABLE `transaction_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-15  5:43:04
