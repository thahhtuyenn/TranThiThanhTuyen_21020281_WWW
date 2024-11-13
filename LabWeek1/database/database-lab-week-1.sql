-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.3.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for week01
CREATE DATABASE IF NOT EXISTS `week01` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `week01`;

-- Dumping structure for table week01.account
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week01.account: ~4 rows (approximately)
INSERT INTO `account` (`account_id`, `full_name`, `password`, `email`, `phone`, `status`) VALUES
	('luilui', 'Lui Lui', '123', 'lui@gmail.com', '0396172224', 1),
	('met', 'Tran Thi Met', '123', 'met@gmail.com', '0904567890', 1),
	('teo', 'NGuyen Van Teo', '123', 'teo@gmail.com', '0903123456', 1),
	('thanhtuyen', 'Tran Thi Thanh Tuyen', '123', 'thanhtuyen9623@gmail.com', '0396172224', 1);

-- Dumping structure for table week01.grant_access
CREATE TABLE IF NOT EXISTS `grant_access` (
  `role_id` varchar(50) NOT NULL,
  `account_id` varchar(50) NOT NULL,
  `is_grant` bit(1) NOT NULL DEFAULT b'1',
  `note` varchar(250) DEFAULT '',
  PRIMARY KEY (`role_id`,`account_id`),
  KEY `account_grant` (`account_id`),
  CONSTRAINT `account_grant` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_grant` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week01.grant_access: ~5 rows (approximately)
INSERT INTO `grant_access` (`role_id`, `account_id`, `is_grant`, `note`) VALUES
	('admin', 'luilui', b'1', ''),
	('admin', 'teo', b'1', ''),
	('user', 'luilui', b'1', ''),
	('user', 'met', b'1', ''),
	('user', 'thanhtuyen', b'1', '');

-- Dumping structure for table week01.log
CREATE TABLE IF NOT EXISTS `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(50) NOT NULL,
  `login_time` datetime DEFAULT current_timestamp(),
  `logout_time` datetime DEFAULT current_timestamp(),
  `notes` varchar(250) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='ghi logs';

-- Dumping data for table week01.log: ~11 rows (approximately)
INSERT INTO `log` (`id`, `account_id`, `login_time`, `logout_time`, `notes`) VALUES
	(5, 'teo', '2024-09-14 06:55:53', '2024-09-14 13:58:01', NULL),
	(6, 'teo', '2024-09-14 08:32:21', '2024-09-14 15:33:00', NULL),
	(7, 'met', '2024-09-14 08:33:07', '2024-09-14 15:43:51', NULL),
	(8, 'met', '2024-09-14 08:45:48', '2024-09-14 15:58:55', NULL),
	(9, 'met', '2024-09-19 03:20:24', '2024-09-19 10:20:28', NULL),
	(10, 'teo', '2024-09-19 03:25:06', '2024-09-19 10:29:55', NULL),
	(11, 'teo', '2024-09-19 03:31:59', '2024-09-19 10:46:02', NULL),
	(12, 'teo', '2024-09-19 03:49:43', '2024-09-19 10:51:17', NULL),
	(13, 'teo', '2024-09-19 06:59:46', '2024-09-20 20:18:08', NULL),
	(14, 'teo', '2024-09-20 13:23:30', '2024-09-20 20:26:16', NULL),
	(15, 'luilui', '2024-09-20 13:27:44', NULL, NULL);

-- Dumping structure for table week01.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` varchar(50) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week01.role: ~2 rows (approximately)
INSERT INTO `role` (`role_id`, `role_name`, `description`, `status`) VALUES
	('admin', 'administrator', 'admin role', 1),
	('user', 'user', 'user role', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
