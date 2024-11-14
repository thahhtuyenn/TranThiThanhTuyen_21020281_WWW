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


-- Dumping database structure for week02
CREATE DATABASE IF NOT EXISTS `week02` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `week02`;

-- Dumping structure for table week02.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `cust_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(250) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `cust_name` varchar(150) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table week02.customers: ~8 rows (approximately)
INSERT INTO `customers` (`cust_id`, `address`, `email`, `cust_name`, `phone`) VALUES
	(1, '12 Nguyen Van Bao', 'tuyen@gmail.com', 'Thanh Tuyen', '0396172224'),
	(2, '44/10 le lai', 'luiluikhongcoyangho@gmail.com', 'Lui Lui', '0867713552'),
	(3, 'An Giang', 'men@gmail.com', 'Nguyen Thi Men', '0953468879'),
	(4, 'Gia Lai', 'chung@gmail.com', 'Le Dong Chung', '0953468879'),
	(5, 'Thanh Hoa', 'vothanhnho8005@gmail.com', 'Le Don Chung', '0965711773'),
	(6, 'Ha Tinh', 'selinaanh_wmru6@hotmail.com', 'Ngan', '0397103813'),
	(7, 'An Phu, An Giang', 'yenhoang@gmail.com', 'Hoang Yen', '0393570689'),
	(8, 'Long Binh - An Giang', 'tu@gmail.com', 'Cam Tu', '0382216119');

-- Dumping structure for table week02.employeess
CREATE TABLE IF NOT EXISTS `employeess` (
  `emp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(250) DEFAULT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `full_name` varchar(150) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table week02.employeess: ~4 rows (approximately)
INSERT INTO `employeess` (`emp_id`, `address`, `dob`, `email`, `full_name`, `phone`, `status`) VALUES
	(1, 'Lai vung, Dong Thap', '2012-06-09 07:34:39.000000', 'lui@gmail.com', 'Nguyen Thanh Lui Lui', '0376225886', 1),
	(2, '612 xa lo dai han', '2024-09-25 21:35:31.000000', 'camtien@gmail.com', 'Vo Tran Cam Tien', '0967446213', 0),
	(3, '12 Nguyen Van Bao, Go Vap', '2008-03-27 21:34:39.000000', 'haivo@gmail.com', 'Vo Quoc Hai', '0964227356', -1),
	(4, '44 Le Lai, Go Vap', '2005-09-18 21:34:39.000000', 'nhan@gmail.com', 'Le Thanh Nhan', '0376325446', 0);

-- Dumping structure for table week02.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_date` datetime(6) DEFAULT NULL,
  `cust_id` bigint(20) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_orders_employee_id` (`employee_id`),
  KEY `FK_orders_cust_id` (`cust_id`),
  CONSTRAINT `FK_orders_cust_id` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`cust_id`),
  CONSTRAINT `FK_orders_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employeess` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table week02.orders: ~6 rows (approximately)
INSERT INTO `orders` (`order_id`, `order_date`, `cust_id`, `employee_id`) VALUES
	(1, '2024-09-27 16:44:13.000000', 2, 1),
	(2, '2024-09-27 17:40:02.332199', 3, 2),
	(3, '2024-09-27 17:44:44.988901', 3, 2),
	(4, '2024-09-27 17:54:26.997552', 3, 2),
	(5, '2024-10-18 00:11:47.151477', 1, 3),
	(6, '2024-10-18 00:20:17.778082', 8, 3),
	(7, '2024-10-18 00:23:05.713057', 1, 4);

-- Dumping structure for table week02.order_details
CREATE TABLE IF NOT EXISTS `order_details` (
  `note` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `FK_order_details_product_id` (`product_id`),
  CONSTRAINT `FK_order_details_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FK_order_details_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table week02.order_details: ~11 rows (approximately)
INSERT INTO `order_details` (`note`, `price`, `quantity`, `order_id`, `product_id`) VALUES
	(NULL, 12, 8, 4, 1),
	(NULL, 21, 4, 4, 2),
	(NULL, 81, 12, 4, 7),
	(NULL, 12, 2, 5, 1),
	(NULL, 81, 1, 5, 7),
	(NULL, 21, 1, 6, 2),
	(NULL, 36, 2, 6, 3),
	(NULL, 85.09, 2, 6, 6),
	(NULL, 12, 2, 7, 1),
	(NULL, 47.6, 1, 7, 4),
	(NULL, 47.6, 1, 7, 5),
	(NULL, 36, 1, 7, 9);

-- Dumping structure for table week02.products
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(250) DEFAULT NULL,
  `manufacturer_name` varchar(100) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `unit` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table week02.products: ~9 rows (approximately)
INSERT INTO `products` (`product_id`, `description`, `manufacturer_name`, `name`, `status`, `unit`) VALUES
	(1, 'Andy shoes are designed to keeping in mind durability as well as trends, the most stylish range of shoes & sandals', 'Lamborghini', 'Small Cotton Bike', 1, 'Hz'),
	(2, 'The Nagasaki Lander is the trademarked name of several series of Nagasaki sport bikes, that started with the 1984 ABC800J', 'Bugatti', 'Licensed Wooden Table', 1, 'm'),
	(3, 'Ergonomic executive chair upholstered in bonded black leather and PVC padded seat and back for all-day comfort and support', 'Land Rover', 'Recycled Soft Gloves', 1, 'K'),
	(4, 'Boston\'s most advanced compression wear technology increases muscle oxygenation, stabilizes active muscles', 'Mercedes Benz', 'Ergonomic Cotton Pizza', 1, 'T'),
	(5, 'The Apollotech B340 is an affordable wireless mouse with reliable connectivity, 12 months battery life and modern design', 'Aston Martin', 'Gorgeous Soft Salad', 1, 'kg'),
	(6, 'Carbonite web goalkeeper gloves are ergonomically designed to give easy fit', 'Land Rover', 'Oriental Granite Pants', 1, 'H'),
	(7, 'Boston\'s most advanced compression wear technology increases muscle oxygenation, stabilizes active muscles', 'Polestar', 'Handcrafted Soft Salad', 1, 'H'),
	(8, 'New range of formal shirts are designed keeping you in mind. With fits and styling that will make you stand apart', 'Bugatti', 'Fantastic Steel Shirt', 1, 'K'),
	(9, 'The Nagasaki Lander is the trademarked name of several series of Nagasaki sport bikes, that started with the 1984 ABC800J', 'Bugatti', 'Licensed Wooden Table', 1, 'm');

-- Dumping structure for table week02.product_images
CREATE TABLE IF NOT EXISTS `product_images` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alternative` varchar(250) DEFAULT NULL,
  `path` varchar(250) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FK_product_images_product_id` (`product_id`),
  CONSTRAINT `FK_product_images_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table week02.product_images: ~9 rows (approximately)
INSERT INTO `product_images` (`image_id`, `alternative`, `path`, `product_id`) VALUES
	(1, NULL, 'https://loremflickr.com/640/480/food', 8),
	(2, NULL, 'https://loremflickr.com/640/480/food', 7),
	(3, NULL, 'https://loremflickr.com/640/480/food', 1),
	(4, NULL, 'https://loremflickr.com/640/480/food', 2),
	(5, NULL, 'https://loremflickr.com/640/480/food', 3),
	(6, NULL, 'https://loremflickr.com/640/480/food', 4),
	(7, NULL, 'https://loremflickr.com/640/480/food', 5),
	(8, NULL, 'https://loremflickr.com/640/480/food', 5),
	(9, NULL, 'https://loremflickr.com/640/480/food', 5),
	(10, NULL, 'https://loremflickr.com/640/480/food', 6),
	(11, NULL, 'https://loremflickr.com/640/480/food', 9);

-- Dumping structure for table week02.product_prices
CREATE TABLE IF NOT EXISTS `product_prices` (
  `note` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `price_date_time` datetime(6) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`price_date_time`,`product_id`),
  KEY `FK_product_prices_product_id` (`product_id`),
  CONSTRAINT `FK_product_prices_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table week02.product_prices: ~18 rows (approximately)
INSERT INTO `product_prices` (`note`, `price`, `price_date_time`, `product_id`) VALUES
	('', 27.4, '2024-09-27 15:37:45.869970', 1),
	('', 64.02, '2024-09-27 15:44:49.580946', 1),
	('', 12, '2024-09-27 15:45:24.405662', 1),
	('', 12, '2024-09-27 15:45:31.249279', 2),
	('', 34.6, '2024-09-27 15:45:37.785830', 2),
	('', 21, '2024-09-27 15:45:43.848545', 2),
	('', 21, '2024-09-27 15:45:53.105908', 6),
	('', 85.09, '2024-09-27 15:46:02.162286', 6),
	('', 85.09, '2024-09-27 15:46:10.635174', 5),
	('', 34, '2024-09-27 15:46:15.657359', 5),
	('', 47.6, '2024-09-27 15:46:26.275198', 5),
	('', 47.6, '2024-09-27 15:46:33.885036', 4),
	('', 28.6, '2024-09-27 15:46:43.738584', 7),
	('', 45, '2024-09-27 15:46:48.805541', 7),
	('', 81, '2024-09-27 15:46:53.098883', 7),
	('', 36, '2024-10-03 23:59:47.692803', 3),
	('', 36, '2024-10-04 00:00:07.346701', 8),
	('', 36, '2024-10-04 00:00:12.835839', 9);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
