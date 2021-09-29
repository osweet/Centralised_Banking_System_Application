CREATE DATABASE  IF NOT EXISTS `bma` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bma`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: bma
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `address_id` varchar(10) NOT NULL,
  `address_landmark` varchar(255) NOT NULL,
  `address_line` varchar(255) NOT NULL,
  `address_verified` bit(1) NOT NULL,
  `address_zip_code` varchar(10) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_on` date NOT NULL,
  `last_updated_by` varchar(255) NOT NULL,
  `last_updated_on` date NOT NULL,
  `address_city` varchar(10) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `FK7siilcw2pbpqiq885jwi7k6yg` (`address_city`),
  CONSTRAINT `FK7siilcw2pbpqiq885jwi7k6yg` FOREIGN KEY (`address_city`) REFERENCES `cities` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `banking_operation_statuses`
--

DROP TABLE IF EXISTS `banking_operation_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banking_operation_statuses` (
  `status_id` varchar(10) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_on` date NOT NULL,
  `last_updated_by` varchar(255) NOT NULL,
  `last_updated_on` date NOT NULL,
  `status_description` varchar(255) NOT NULL,
  `status_name` varchar(30) NOT NULL,
  PRIMARY KEY (`status_id`),
  UNIQUE KEY `UK_lqj6lu16978vb6r38j6pna9k5` (`status_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `banking_operations`
--

DROP TABLE IF EXISTS `banking_operations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banking_operations` (
  `operation_id` varchar(10) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_on` date NOT NULL,
  `last_updated_by` varchar(255) NOT NULL,
  `last_updated_on` date NOT NULL,
  `operation_description` varchar(255) NOT NULL,
  `operation_name` varchar(30) NOT NULL,
  PRIMARY KEY (`operation_id`),
  UNIQUE KEY `UK_9pp2v6soresl1fmbyu3old5wy` (`operation_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `banks`
--

DROP TABLE IF EXISTS `banks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banks` (
  `bank_id` varchar(10) NOT NULL,
  `bank_email` varchar(50) NOT NULL,
  `bank_name` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_on` date NOT NULL,
  `last_updated_by` varchar(255) NOT NULL,
  `last_updated_on` date NOT NULL,
  `bank_address` varchar(10) NOT NULL,
  PRIMARY KEY (`bank_id`),
  UNIQUE KEY `UK_pualxhv0lvmkgk10k6q4o868d` (`bank_email`),
  UNIQUE KEY `UK_pm44fp6lf4gf4qj7tr4a2b5iq` (`bank_name`),
  KEY `FKetxdj2nioaoj5wr8iqw3s5hsb` (`bank_address`),
  CONSTRAINT `FKetxdj2nioaoj5wr8iqw3s5hsb` FOREIGN KEY (`bank_address`) REFERENCES `addresses` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `branches`
--

DROP TABLE IF EXISTS `branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branches` (
  `branch_id` varchar(10) NOT NULL,
  `branch_contact` bigint NOT NULL,
  `branch_email` varchar(50) NOT NULL,
  `branch_name` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_on` date NOT NULL,
  `last_updated_by` varchar(255) NOT NULL,
  `last_updated_on` date NOT NULL,
  `bank_id` varchar(10) NOT NULL,
  `branch_address` varchar(10) NOT NULL,
  PRIMARY KEY (`branch_id`),
  UNIQUE KEY `UK_fb01ieqpc8n0524ak4h0u0bxp` (`branch_contact`),
  UNIQUE KEY `UK_1ydh5va4a9iqobwmludkv6dn1` (`branch_email`),
  UNIQUE KEY `UK_cg8xnm1f8r6ohberdss40rhmm` (`branch_name`),
  KEY `FK4wy5cnsa71b4d9w9ida7uqu54` (`bank_id`),
  KEY `FKc71vd6sisn6cp1e0n0fkknbvd` (`branch_address`),
  CONSTRAINT `FK4wy5cnsa71b4d9w9ida7uqu54` FOREIGN KEY (`bank_id`) REFERENCES `banks` (`bank_id`),
  CONSTRAINT `FKc71vd6sisn6cp1e0n0fkknbvd` FOREIGN KEY (`branch_address`) REFERENCES `addresses` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `city_id` varchar(10) NOT NULL,
  `city_name` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_on` date NOT NULL,
  `last_updated_by` varchar(255) NOT NULL,
  `last_updated_on` date NOT NULL,
  `state` varchar(10) NOT NULL,
  PRIMARY KEY (`city_id`),
  KEY `FK6k1ir0ecvx63yy9e3w5opb9cn` (`state`),
  CONSTRAINT `FK6k1ir0ecvx63yy9e3w5opb9cn` FOREIGN KEY (`state`) REFERENCES `states` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `country_id` varchar(10) NOT NULL,
  `country_name` varchar(255) NOT NULL,
  `country_short_name` varchar(5) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_on` date NOT NULL,
  `last_updated_by` varchar(255) NOT NULL,
  `last_updated_on` date NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `UK_lx3r8cp4g7xkaqximbtxum74r` (`country_name`),
  UNIQUE KEY `UK_a8adg1djov4fplj8uc30aluqt` (`country_short_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` varchar(10) NOT NULL,
  `role_description` varchar(255) NOT NULL,
  `role_name` varchar(30) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_716hgxp60ym1lifrdgp67xt5k` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `states` (
  `state_id` varchar(10) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_on` date NOT NULL,
  `last_updated_by` varchar(255) NOT NULL,
  `last_updated_on` date NOT NULL,
  `state_name` varchar(255) NOT NULL,
  `country` varchar(10) NOT NULL,
  PRIMARY KEY (`state_id`),
  KEY `FKf5l4ddc17ulqguyhv74kvbelw` (`country`),
  CONSTRAINT `FKf5l4ddc17ulqguyhv74kvbelw` FOREIGN KEY (`country`) REFERENCES `countries` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
  `token_id` varchar(10) NOT NULL,
  `token_timestamp` datetime(6) NOT NULL,
  `token_bank` varchar(10) NOT NULL,
  `token_branch` varchar(10) NOT NULL,
  `token_operation` varchar(10) NOT NULL,
  `token_operation_status` varchar(10) NOT NULL,
  `token_user` varchar(10) NOT NULL,
  PRIMARY KEY (`token_id`),
  KEY `FKjlufg6c9bixnn2dbhpxls6s1v` (`token_bank`),
  KEY `FKi0c3cp2fs31tcj9bv2at5i3dn` (`token_branch`),
  KEY `FK8cya0h108j4mrrox5om7iwd75` (`token_operation`),
  KEY `FKp3qrem5l5vwvfrnon3a64elh5` (`token_operation_status`),
  KEY `FKk2q2xtaou08clfujanah1tsup` (`token_user`),
  CONSTRAINT `FK8cya0h108j4mrrox5om7iwd75` FOREIGN KEY (`token_operation`) REFERENCES `banking_operations` (`operation_id`),
  CONSTRAINT `FKi0c3cp2fs31tcj9bv2at5i3dn` FOREIGN KEY (`token_branch`) REFERENCES `branches` (`branch_id`),
  CONSTRAINT `FKjlufg6c9bixnn2dbhpxls6s1v` FOREIGN KEY (`token_bank`) REFERENCES `banks` (`bank_id`),
  CONSTRAINT `FKk2q2xtaou08clfujanah1tsup` FOREIGN KEY (`token_user`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKp3qrem5l5vwvfrnon3a64elh5` FOREIGN KEY (`token_operation_status`) REFERENCES `banking_operation_statuses` (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` varchar(10) NOT NULL,
  `role_id` varchar(10) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` varchar(10) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_on` date NOT NULL,
  `last_updated_by` varchar(255) NOT NULL,
  `last_updated_on` date NOT NULL,
  `user_contact` bigint NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_enabled` bit(1) NOT NULL,
  `user_first_name` varchar(20) NOT NULL,
  `user_last_name` varchar(20) NOT NULL,
  `user_middle_name` varchar(20) DEFAULT NULL,
  `user_password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_jt72m47m1fa2kqaqawmvxs2rw` (`user_contact`),
  UNIQUE KEY `UK_33uo7vet9c79ydfuwg1w848f` (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-21 16:54:43
