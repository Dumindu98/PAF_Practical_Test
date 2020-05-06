-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 08:48 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paf`
--

-- --------------------------------------------------------

--
-- Table structure for table `reg_user`
--

CREATE TABLE `reg_user` (
  `Ruser_ID` int(10) NOT NULL,
  `Ruser_name` varchar(20) CHARACTER SET latin1 NOT NULL,
  `Ruser_address` varchar(40) CHARACTER SET latin1 NOT NULL,
  `Ruser_gender` varchar(10) CHARACTER SET latin1 NOT NULL,
  `Ruser_age` varchar(10) NOT NULL,
  `Ruser_notes` varchar(100) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reg_user`
--

INSERT INTO `reg_user` (`Ruser_ID`, `Ruser_name`, `Ruser_address`, `Ruser_gender`, `Ruser_age`, `Ruser_notes`) VALUES
(6, 'Amal perera', '2nd lane, Moratuwa', 'male', '55', 'I\'m cancer patient'),
(7, 'A.B.C Fonseka', '55, Karapitiya, Galle.', 'female', '30', 'I want to check my ECG'),
(16, 'Dumindu Shamika', '1st lane, Pilapitiya, kelaniya.', 'male', '22', 'I have covid-19'),
(17, 'Mahela Wijekoon', '5th lane, Gampaha.', 'male', '25', 'I have kidney disease'),
(19, 'Harshi gamage', '3rd lane, Nugegoda.', 'female', '27', 'I\'m pregnent');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reg_user`
--
ALTER TABLE `reg_user`
  ADD PRIMARY KEY (`Ruser_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reg_user`
--
ALTER TABLE `reg_user`
  MODIFY `Ruser_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
