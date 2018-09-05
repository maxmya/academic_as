-- MySQL dump 10.16  Distrib 10.2.16-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: acadmic_as
-- ------------------------------------------------------
-- Server version	10.2.16-MariaDB

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_user_id_fk` (`user_id`),
  CONSTRAINT `admin_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,1),(2,3),(5,8),(6,9),(7,24);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assistant`
--

DROP TABLE IF EXISTS `assistant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assistant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `assist_user_id_fk` (`user_id`),
  CONSTRAINT `assist_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assistant`
--

LOCK TABLES `assistant` WRITE;
/*!40000 ALTER TABLE `assistant` DISABLE KEYS */;
INSERT INTO `assistant` VALUES (1,25);
/*!40000 ALTER TABLE `assistant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `required_points` int(11) DEFAULT NULL,
  `awarded_points` int(11) DEFAULT NULL,
  `lecture_hrs` float DEFAULT NULL,
  `training_hrs` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_code_uindex` (`code`),
  KEY `course_department_id_fk` (`department_id`),
  CONSTRAINT `course_department_id_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (2,'general math','math 101',1,0,3,2,2);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `department_depname_uindex` (`depname`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'mathematics'),(2,'physics');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_speciality`
--

DROP TABLE IF EXISTS `department_speciality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_speciality` (
  `department_id` int(11) NOT NULL,
  `speciality_id` int(11) NOT NULL,
  PRIMARY KEY (`department_id`,`speciality_id`),
  KEY `speciality_fk` (`speciality_id`),
  CONSTRAINT `department_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `speciality_fk` FOREIGN KEY (`speciality_id`) REFERENCES `specialization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_speciality`
--

LOCK TABLES `department_speciality` WRITE;
/*!40000 ALTER TABLE `department_speciality` DISABLE KEYS */;
INSERT INTO `department_speciality` VALUES (1,1),(1,2),(2,1);
/*!40000 ALTER TABLE `department_speciality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `prof_user_id_fk` (`user_id`),
  CONSTRAINT `prof_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,2);
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialization`
--

DROP TABLE IF EXISTS `specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `specialityname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `specialization_specialityname_uindex` (`specialityname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialization`
--

LOCK TABLES `specialization` WRITE;
/*!40000 ALTER TABLE `specialization` DISABLE KEYS */;
INSERT INTO `specialization` VALUES (1,'math_phys'),(2,'math_special');
/*!40000 ALTER TABLE `specialization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `gpa` float DEFAULT 0,
  `level` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `student_user_id_fk` (`user_id`),
  CONSTRAINT `student_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,4,0,1),(2,5,0,1),(3,30,0,1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supervisor`
--

DROP TABLE IF EXISTS `supervisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supervisor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `supv_user_id_fk` (`user_id`),
  CONSTRAINT `supv_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supervisor`
--

LOCK TABLES `supervisor` WRITE;
/*!40000 ALTER TABLE `supervisor` DISABLE KEYS */;
/*!40000 ALTER TABLE `supervisor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email_uindex` (`email`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'super','super','super','root','super','2018-08-19'),(2,'ahmed','mohamed','ahmed@as.com','test','ahmed','2018-08-19'),(3,'ahmed','ahmed','teste3','test','om32ar','2018-08-19'),(4,'mahmoud','aref','aref@as.com','test','aref','2018-08-19'),(5,'3awad','adel','mohanad@as.com','$2a$10$tU2pnQZI4uu4NY3gww9Rhe8wLAdaHH23keDXT/NwfA9C.Yv3AHuku','mohnad','2018-08-19'),(6,'mohanad','adel','mohanad_admin@as.com','$2a$10$45qzvAxEv3dd2C1Z3aVND.HoNvPii3fgm11IPP8z0Vn1XbDuGFKQu','mohnad_admin','2018-08-19'),(7,'mohanad','adel','1mohanad_admin@as.com','$2a$10$C2IR60mDsohn5Yf9viYOeeLZt.7OGRiETOSn3XqPAzFrnvmUiJFGy','mohnad_admin1','2018-08-19'),(8,'mohanad','mohanad','mohanad','$2a$10$pvNLYc8GO3BM73ZqoGpInuZg5jK4FTjbliNmo3yYuE81cjnkbTLqq','mohanad','2018-08-19'),(9,'omar','waeel','omar@as.com','$2a$10$Te7/OAZm1rCWk0NTBHu2uOH8V/t4kRkE89SqsziC1DSJIhjXzkrqW','omar','2018-08-21'),(10,'omar','waeel','omar@as.c4om','$2a$10$/6YdS1hIJ7343UH/3a7iWO1m1RsUfdtZ9N5ZLN5XUHiyeHb43uNny','omar4','2018-08-22'),(14,'qwer',NULL,'qwer','$2a$10$cY0lEnn/v66Ft1BmOybXGOCXMrA.WS2UKsNSxfHkNpBdS3b7Ai0jW','qwer','2018-08-22'),(16,'test',NULL,'test','$2a$10$3bo9.iEMR/95GVYanCXsTu4A6xKGNqJVgeOFMQGfLhaJo.7R4C8na','test','2018-08-22'),(20,'a','a','a','$2a$10$AiR9dilO2W9hnYkKo86hwe5SUy1TQRl4FTGtBt/bag8VU9.1scUXm','a','2018-08-22'),(22,'2','3','5','$2a$10$i.H7ioMzHTU34LS4HN36/e3ov1IoxodL4zott9YKz3fBsMX/b9UMC','4','2018-08-22'),(24,'ahmed','mohamed','mohameee','$2a$10$cfj.CwXeLjRTXdcjfMUuU.AS9H87ZM.HkAP8awJmRAOd3bv9NHUJy','moahmed','2018-08-22'),(25,'afasdf','asdfasdfasdf','asdfasdf','$2a$10$aR7TYWKYv2nPoRtZqUwBFe7F39Q14y1G8EKZe9Wvwf/fuMG86phEm','asdfasdf','2018-08-22'),(30,'mohamed','mahmoud','lll','$2a$10$yQpPWWdBDpNKKgo/fN/29O0zk79SvQU.Kt1rryLwLbwpvI2vI/eyC','ali','2018-08-28');
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

-- Dump completed on 2018-09-05 15:18:21
