-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 01, 2026 at 07:39 AM
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
-- Database: `db_sekolah_231011401598`
--

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran_spp`
--

CREATE TABLE `pembayaran_spp` (
  `nis` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `kelas` varchar(50) NOT NULL,
  `bulan` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pembayaran_spp`
--

INSERT INTO `pembayaran_spp` (`nis`, `nama`, `kelas`, `bulan`, `status`) VALUES
('081314', 'Ela Simo', 'XI', 'Juni', 'LUNAS'),
('112233', 'Amalia', 'X', 'Maret', 'LUNAS'),
('121314', 'Saripah', 'IX', 'Mei', 'LUNAS'),
('231011401598', 'Putri Amalia', 'XII', 'Desember', 'Lunas'),
('23151617', 'Anissa Janiyar', 'X', 'Januari', 'Lunas'),
('23222222', 'Febby Feb', 'XI', 'Februari', 'Lunas'),
('23242526', 'Rahma', 'X', 'Maret', 'Belum Lunas'),
('23333333', 'Maryam Mar', 'XII', 'Maret', 'Belum Lunas'),
('23444444', 'Aprilia', 'X', 'April', 'Lunas'),
('23555555', 'Mei Mei', 'XI', 'Mei', 'Belum Lunas'),
('23666666', 'Junyar', 'XII', 'Juni', 'Lunas'),
('23777777', 'Juliyah', 'X', 'Juli', 'Lunas'),
('23888888', 'Agustin', 'XII', 'Agustus', 'Belum Lunas'),
('23999999', 'Septia', 'XII', 'September', 'Lunas');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`) VALUES
('admin', 'admin'),
('PutriAmalia_231011401598', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pembayaran_spp`
--
ALTER TABLE `pembayaran_spp`
  ADD PRIMARY KEY (`nis`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
