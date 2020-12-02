-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2020 at 11:32 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test_shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(8) NOT NULL,
  `item_name` varchar(40) NOT NULL,
  `quantity` int(8) NOT NULL,
  `price` double(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `item_name`, `quantity`, `price`) VALUES
(1, 'Hameer', 500, 12.22),
(2, 'Nail', 20000, 0.12),
(5, 'Skru', 49802, 0.20);

-- --------------------------------------------------------

--
-- Table structure for table `order_costermer_log`
--

CREATE TABLE `order_costermer_log` (
  `id_order` int(8) NOT NULL,
  `Costermer` varchar(40) NOT NULL,
  `Item_id` int(8) NOT NULL,
  `item_name` varchar(40) NOT NULL,
  `quantity` int(8) NOT NULL,
  `price_pr_pc` double(11,2) NOT NULL,
  `total_price` double(11,2) NOT NULL,
  `approved` tinyint(1) NOT NULL DEFAULT 0,
  `approver` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_costermer_log`
--

INSERT INTO `order_costermer_log` (`id_order`, `Costermer`, `Item_id`, `item_name`, `quantity`, `price_pr_pc`, `total_price`, `approved`, `approver`) VALUES
(4000001, 'Chrih', 0, 'Skru', 0, 0.00, 0.00, 0, NULL),
(4000011, 'Chrih', 5, 'Skru', 51, 0.20, 10.20, 0, NULL),
(4000012, 'Chrih', 5, 'Skru', 51, 0.20, 10.20, 0, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_costermer_log`
--
ALTER TABLE `order_costermer_log`
  ADD PRIMARY KEY (`id_order`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `order_costermer_log`
--
ALTER TABLE `order_costermer_log`
  MODIFY `id_order` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4000013;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
