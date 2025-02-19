-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2024 at 07:39 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `BID` int(11) NOT NULL,
  `BName` varchar(50) NOT NULL,
  `BAuthor` varchar(50) NOT NULL,
  `BPYear` varchar(10) NOT NULL,
  `BookAvailable` varchar(10) NOT NULL DEFAULT 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`BID`, `BName`, `BAuthor`, `BPYear`, `BookAvailable`) VALUES
(2, 'ewgag', 'q', '15', 'true'),
(13, 'mai mara prasangaya', '', '2022', 'false'),
(14, 'madol duva', '', '1967', 'false'),
(15, 'madol duva', '', '1967', 'false'),
(16, 'madol duva', '', '1967', 'false'),
(17, '', '', '', 'false'),
(18, 'mal kiniththaka puraurthaya', '', '2023', 'false');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `MID` int(11) NOT NULL,
  `MName` varchar(50) NOT NULL,
  `MContact` varchar(10) NOT NULL,
  `MType` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL DEFAULT '5678',
  `CardNumber` int(11) DEFAULT NULL,
  `IsDelete` varchar(10) NOT NULL DEFAULT 'false',
  `ExpireDate` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`MID`, `MName`, `MContact`, `MType`, `Password`, `CardNumber`, `IsDelete`, `ExpireDate`) VALUES
(1, 'Kamal Perara', '0715423764', 'Admin', '1234', NULL, 'false', 0),
(3, 'Ravindra Bandara', '0756347184', 'User', '1234', 1000, 'true', 0),
(8, 'Jayamal', '0114563569', 'User', '1234', 0, 'true', 2026),
(28, 'lakiya', '0777577877', 'User', '5678', NULL, 'false', 2026),
(29, 'lakiya', '0777577877', 'User', '5678', NULL, 'false', 2026),
(30, 'akalanka', '0765456783', 'Admin', '5678', NULL, 'false', 2026),
(31, 'sd', '567788900', 'User', '5678', NULL, 'false', 2026);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`BID`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`MID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `BID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `MID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
