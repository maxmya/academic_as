-- MySQL dump 10.16  Distrib 10.3.9-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: acadmic_as
-- ------------------------------------------------------
-- Server version	10.3.9-MariaDB

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,1),(2,1);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assistant`
--

LOCK TABLES `assistant` WRITE;
/*!40000 ALTER TABLE `assistant` DISABLE KEYS */;
/*!40000 ALTER TABLE `assistant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group`
--

DROP TABLE IF EXISTS `auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_group` varchar(128) DEFAULT NULL,
  `username` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `auth_group_user_username_fk` (`username`),
  CONSTRAINT `auth_group_user_username_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group`
--

LOCK TABLES `auth_group` WRITE;
/*!40000 ALTER TABLE `auth_group` DISABLE KEYS */;
INSERT INTO `auth_group` VALUES (1,'ADMIN','root'),(2,'STUDENT','gwerg3'),(3,'STUDENT','moo'),(4,'PROFESSOR','elgeneral');
/*!40000 ALTER TABLE `auth_group` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_code_uindex` (`code`),
  KEY `course_department_id_fk` (`department_id`),
  CONSTRAINT `course_department_id_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (32,'math','101',3,3,6),(34,'physics','23',6,60,2),(35,'oop','201',3,120,3),(37,'pio2','102',8,66,3),(38,'pio3','103',8,70,3),(39,'pio4','104',8,80,3),(40,'pio5','105',8,90,3),(41,'pio6','106',8,100,3),(42,'pio7','107',8,110,3),(43,'pio8','108',8,120,3),(44,'pio9','109',8,130,3),(45,'pio10','110',8,140,3),(46,'pio11','111',8,150,3),(47,'pio12','112',8,160,3),(48,'pio13','113',8,170,3),(50,'general kemya','114',5,55,3);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_dependencies`
--

DROP TABLE IF EXISTS `course_dependencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_dependencies` (
  `course_id` int(11) NOT NULL,
  `dependency_id` int(11) NOT NULL,
  PRIMARY KEY (`course_id`,`dependency_id`),
  KEY `course_dependencies_course_id_fk_2` (`dependency_id`),
  CONSTRAINT `course_dependencies_course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `course_dependencies_course_id_fk_2` FOREIGN KEY (`dependency_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_dependencies`
--

LOCK TABLES `course_dependencies` WRITE;
/*!40000 ALTER TABLE `course_dependencies` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_dependencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_instance`
--

DROP TABLE IF EXISTS `course_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_instance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `hall_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `specialization_id` int(11) DEFAULT NULL,
  `type` enum('lecture','training','lab') DEFAULT NULL,
  `semester_id` int(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_instance_hall_id_fk` (`hall_id`),
  KEY `course_instance_course_id_fk` (`course_id`),
  KEY `course_instance_specialization_id_fk` (`specialization_id`),
  KEY `course_instance_semester_id_fk` (`semester_id`),
  CONSTRAINT `course_instance_course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `course_instance_hall_id_fk` FOREIGN KEY (`hall_id`) REFERENCES `hall` (`id`),
  CONSTRAINT `course_instance_semester_id_fk` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`),
  CONSTRAINT `course_instance_specialization_id_fk` FOREIGN KEY (`specialization_id`) REFERENCES `specialization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_instance`
--

LOCK TABLES `course_instance` WRITE;
/*!40000 ALTER TABLE `course_instance` DISABLE KEYS */;
INSERT INTO `course_instance` VALUES (4,'2018-12-01 09:00:00','2018-12-01 11:00:00',NULL,37,76,'lecture',NULL),(5,'2018-12-02 08:16:43','2018-12-02 10:16:49',NULL,38,76,'lecture',NULL),(6,'2018-12-03 08:19:02','2018-12-03 10:19:19',NULL,39,76,'lecture',NULL),(7,'2018-12-04 08:19:25','2018-12-04 10:19:31',NULL,40,76,'lecture',NULL),(8,'2018-12-05 08:19:37','2018-12-05 10:19:43',NULL,41,76,'lecture',NULL),(9,'2018-12-06 08:19:52','2018-12-06 10:19:58',NULL,42,76,'lecture',NULL),(10,'2018-12-07 08:20:11','2018-12-07 10:20:16',NULL,43,76,'lecture',NULL),(11,'2018-12-08 08:24:16','2018-12-08 10:27:03',NULL,44,76,'lecture',NULL),(12,'2018-12-09 08:26:13','2018-12-09 10:27:16',NULL,45,76,'lecture',NULL),(13,'2018-12-10 08:26:20','2018-12-10 10:27:32',NULL,46,76,'lecture',NULL),(14,'2018-12-11 08:26:26','2018-12-11 10:27:40',NULL,47,76,'lecture',NULL),(15,'2018-12-12 08:26:38','2018-12-12 10:27:49',NULL,48,76,'lecture',NULL);
/*!40000 ALTER TABLE `course_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_registration`
--

DROP TABLE IF EXISTS `course_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_registration` (
  `course_instance_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  PRIMARY KEY (`course_instance_id`,`student_id`),
  KEY `course_regestration_student_id_fk` (`student_id`),
  CONSTRAINT `course_regestration_course_instance_id_fk` FOREIGN KEY (`course_instance_id`) REFERENCES `course_instance` (`id`),
  CONSTRAINT `course_regestration_student_id_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_registration`
--

LOCK TABLES `course_registration` WRITE;
/*!40000 ALTER TABLE `course_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_responsibility`
--

DROP TABLE IF EXISTS `course_responsibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_responsibility` (
  `course_instance_id` int(11) NOT NULL,
  `instructor_id` int(11) NOT NULL,
  PRIMARY KEY (`course_instance_id`,`instructor_id`),
  KEY `course_responsibility_instructor_id_fk` (`instructor_id`),
  CONSTRAINT `course_responsibility_course_instance_id_fk` FOREIGN KEY (`course_instance_id`) REFERENCES `course_instance` (`id`),
  CONSTRAINT `course_responsibility_instructor_id_fk` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_responsibility`
--

LOCK TABLES `course_responsibility` WRITE;
/*!40000 ALTER TABLE `course_responsibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_responsibility` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (7,'AA'),(4,'falak'),(1,'gelogia'),(5,'kemya'),(3,'math'),(8,'pio'),(6,'vesia');
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
INSERT INTO `department_speciality` VALUES (7,12),(8,76);
/*!40000 ALTER TABLE `department_speciality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'ow',7875460,65,5545),(2,'waly',45,45,120),(3,'mosarfa',100,100,120),(4,'waly',45,45,120),(5,'EE',85,85,85),(6,'AA',100,100,100),(7,'QW',125,125,125),(8,'TY',145,145,145),(9,'RR',55,16,55),(10,'FF',88,44,105),(11,'SS',66,66,25),(12,'HH',44,55,88),(13,'QQ',555,55,11),(14,'ZZ',555,55,55),(15,'ZZ',555,55,55),(16,'YY',55,55,55),(17,'MM',44,55,66),(18,'FDD',56,45,33),(19,'FDD',56,45,33),(20,'CC',53,535,53),(21,'GG',55,55,33),(22,'XX',525214000,458,525),(23,'XX',525214000,458,525),(24,'ZZ',55,55,55),(25,'lk',5445,5,5),(26,'mn',545,54,5),(27,'JK',54,45,48),(28,'SS',8,5,5),(29,'hh',3,1,5),(30,'jj',5,23,37),(31,'qw',39,47,100),(32,'rt',45,45,46),(33,'M',15,15,5),(34,'LO',168,123,500);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instructor_user_id_fk` (`user_id`),
  CONSTRAINT `instructor_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (1,4);
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1);
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `semester_code` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `semester_semester_code_uindex` (`semester_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,'401','2018-11-12','2018-11-20');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialization`
--

LOCK TABLES `specialization` WRITE;
/*!40000 ALTER TABLE `specialization` DISABLE KEYS */;
INSERT INTO `specialization` VALUES (54,''),(36,'AA'),(53,'B'),(12,'general'),(26,'general1'),(28,'general2'),(33,'general3'),(34,'GG'),(39,'JJ'),(40,'JP'),(51,'M'),(45,'ol'),(41,'Pc'),(76,'pio-general'),(49,'WW'),(47,'zamalek');
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
  `specialization_id` int(11) DEFAULT NULL,
  `credit_points` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_user_id_fk` (`user_id`),
  CONSTRAINT `student_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,2,0,1,76,180),(2,3,4,1,76,177);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','admin','admin@admin.com','$2a$10$pDIL62tCNR4vIi4B0cqwhuCG36jU7KEF6nmcOO/4eCmmUVqr3YtU6','root','2018-11-14'),(2,'ghhg','gsdf','msmsmsms@nn.c','$2a$10$Ab1JIz0U0tacjvThFt2EJub1HrSWTiVmiVzwn3XQwuihzQ0PYPY6e','gwerg3','2018-11-30'),(3,'mohaned','adel','mohaned@moo.com','$2a$10$MajhJu3clMtG8PewzzqzJOisrNG4UTwSuk6W67FZXFkIn/q8a/tMe','moo','2018-12-03'),(4,'alaa','hosny','all@hosny.com','$2a$10$r2G8qP7njMn284YBWQV1q.ZBTAubZkwioGgheiDNlsNcuUdnHfPDu','elgeneral','2018-12-07');
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

-- Dump completed on 2018-12-14  1:06:14
