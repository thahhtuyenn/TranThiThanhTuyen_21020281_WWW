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


-- Dumping database structure for productdb
CREATE DATABASE IF NOT EXISTS `productdb` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `productdb`;

-- Dumping structure for table productdb.products
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(250) DEFAULT NULL,
  `manufacturer_name` varchar(100) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `unit` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table productdb.products: ~14 rows (approximately)
INSERT INTO `products` (`product_id`, `description`, `manufacturer_name`, `name`, `status`, `unit`) VALUES
	(1, 'The Nagasaki Lander is the trademarked name of several series of Nagasaki sport bikes, that started with the 1984 ABC800J', 'Bugatti', 'Licensed Wooden Table', 1, 'm'),
	(2, 'Andy shoes are designed to keeping in mind durability as well as trends, the most stylish range of shoes & sandals', 'Lamborghini', 'Small Cotton Bike', -1, 'V'),
	(3, 'Boston\'s most advanced compression wear technology increases muscle oxygenation, stabilizes active muscles', 'Mercedes Benz', 'Ergonomic Cotton Pizza', -1, 'ch'),
	(5, 'Boston\'s most advanced compression wear technology increases muscle oxygenation, stabilizes active muscles', 'Mercedes Benz', 'Ergonomic Cotton Pizza', -1, 'Hz'),
	(6, 'Boston\'s most ', 'Mercedes Benz', 'Ergonomic Cotton Pizza 3', -1, 'Hz'),
	(7, 'Boston\'s most ', 'Mercedes Benz', 'Ergonomic Cotton Pizza 3', -1, 'Hz'),
	(8, 'Áo s&#417; mi ng&#7855;n tay Thái Hòa ch&#7845;t li&#7879;u cotton Cao c&#7845;p b&#7843;n n&#7865;p khuy nhi&#7873;u màu 24ASW-240', 'Thái Hòa', 'Áo s&#417; mi ng&#7855;n tay Thái Hòa', -1, 'c'),
	(9, 'quan sort nam nu bigsize', 'Thái Hòa', 'Quan sort nam nu', 1, 'c'),
	(10, 'Áo s&#417; mi ng&#7855;n tay Thái Hòa ch&#7845;t li&#7879;u cotton Cao c&#7845;p b&#7843;n n&#7865;p khuy nhi&#7873;u màu 24ASW-240', 'Thái Hòa', 'Áo s&#417; mi ng&#7855;n tay Thái Hòa', -1, 'c'),
	(11, 'Áo s&#417; mi ng&#7855;n tay Thái Hòa ch&#7845;t li&#7879;u cotton Cao c&#7845;p b&#7843;n n&#7865;p khuy nhi&#7873;u màu 24ASW-240', 'Thái Hòa', 'Áo s&#417; mi ng&#7855;n tay Thái Hòa', -1, 'c'),
	(12, 'ao so mi nam gom 4 mau sac: xanh ,hong, trang, den. Size L, M, S', 'Xuong mai Thien Kim', 'Ao so mi dai tay', 1, 'cai'),
	(13, 'vay nu cong chua, mau trang xinh dep, chat lieu co dan', 'Xuong mai Ngoc Linh', 'Vay nu', -1, 'ch'),
	(14, 'Sach van hoa va xa hoi ', 'Thái Hòa', 'Sa&#769;ch v&#259;n hoa&#769; xa&#771; hô&#803;i 01', -1, 'q'),
	(15, 'Non bo sang trong, phu hop di bien', 'Xuong may Thanh Ha', 'Non bo tron danh cho nu', 1, 'c');

-- Dumping structure for table productdb.product_prices
CREATE TABLE IF NOT EXISTS `product_prices` (
  `note` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `price_date_time` datetime(6) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`price_date_time`,`product_id`),
  KEY `FK_product_prices_product_id` (`product_id`),
  CONSTRAINT `FK_product_prices_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table productdb.product_prices: ~23 rows (approximately)
INSERT INTO `product_prices` (`note`, `price`, `price_date_time`, `product_id`) VALUES
	('', 81, '2024-09-28 19:20:39.143152', 2),
	('', 1562, '2024-09-28 19:20:50.650567', 1),
	('', 62, '2024-09-28 19:20:56.275512', 3),
	('', 24, '2024-09-28 19:21:00.517245', 3),
	('', 98, '2024-09-28 19:21:06.405158', 1),
	('', 65, '2024-09-28 19:21:14.508480', 2),
	('', 65, '2024-10-02 22:41:08.137960', 7),
	('', 36, '2024-10-02 22:41:21.689228', 7),
	('Initial price', 301, '2024-10-03 15:21:40.465487', 8),
	('Initial price', 301, '2024-10-03 15:22:21.710277', 9),
	('Initial price', 301, '2024-10-03 15:22:29.777040', 10),
	('Initial price', 301, '2024-10-03 15:25:01.444892', 11),
	('Initial price', 84, '2024-10-03 15:34:48.869503', 12),
	('Update price', 45, '2024-10-03 15:43:34.939299', 12),
	('Update price', 45, '2024-10-03 15:43:54.150116', 12),
	('Update price', 64, '2024-10-03 15:46:25.906328', 12),
	('Update price', 102, '2024-10-03 15:48:45.143490', 9),
	('Initial price', 165, '2024-10-03 15:49:40.854859', 13),
	('Initial price', 57, '2024-10-03 16:39:53.822826', 14),
	('Update price', 78, '2024-10-03 16:40:08.827290', 9),
	('Update price', 89, '2024-11-15 09:51:38.774262', 12),
	('Initial price', 97, '2024-11-15 09:53:04.659643', 15),
	('Update price', 45, '2024-11-15 10:01:40.769887', 15);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
