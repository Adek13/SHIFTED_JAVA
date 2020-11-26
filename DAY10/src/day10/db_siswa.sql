-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2020 at 09:01 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_siswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_data`
--

CREATE TABLE `tbl_data` (
  `id` int(11) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `address` text NOT NULL,
  `status` varchar(20) NOT NULL,
  `physics` int(11) NOT NULL,
  `calculus` int(11) NOT NULL,
  `biologi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_data`
--

INSERT INTO `tbl_data` (`id`, `fullname`, `address`, `status`, `physics`, `calculus`, `biologi`) VALUES
(24, 'Ahmad Purwoko', 'Jogja', 'Non Active', 0, 0, 0),
(25, 'Ridwan Jamil', 'Bandung', 'On Leave', 75, 95, 65),
(26, 'Soleh Said', 'Jakarta', 'Active', 80, 70, 85),
(27, 'Ahmad Purwoko', 'Jogja', 'Non Active', 0, 0, 0),
(28, 'Ridwan Jamil', 'Bandung', 'On Leave', 75, 95, 65),
(29, 'Soleh Said', 'Jakarta', 'Active', 80, 70, 85),
(30, 'Ahmad Purwoko', 'Jogja', 'Non Active', 0, 0, 0),
(31, 'Ridwan Jamil', 'Bandung', 'On Leave', 75, 95, 65),
(32, 'Soleh Said', 'Jakarta', 'Active', 80, 70, 85),
(33, 'Charles Pain', 'Melbourne', 'Active', 90, 85, 80),
(34, 'Steve Marcus', 'Boston', 'Active', 65, 55, 75),
(35, 'Charles Pain', 'Melbourne', 'Active', 90, 85, 80),
(36, 'Steve Marcus', 'Boston', 'Active', 65, 55, 75),
(37, 'Charles Pain', 'Melbourne', 'Active', 90, 85, 80),
(38, 'Steve Marcus', 'Boston', 'Active', 65, 55, 75),
(40, 'Ahmad Purwoko', 'Jogja', 'Non Active', 0, 0, 0),
(41, 'Ridwan Jamil', 'Bandung', 'On Leave', 75, 95, 65),
(42, 'Soleh Said', 'Jakarta', 'Active', 80, 70, 85),
(43, 'Charles Pain', 'Melbourne', 'Active', 90, 85, 80);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `username`, `password`) VALUES
(1, 'admin@admin.com', 'Admin@1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_data`
--
ALTER TABLE `tbl_data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_data`
--
ALTER TABLE `tbl_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
