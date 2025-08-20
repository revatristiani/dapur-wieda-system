-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2025 at 03:45 PM
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
-- Database: `penjualan2`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_roti`
--

CREATE TABLE `data_roti` (
  `kd_roti` varchar(6) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama_roti` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `stok` int(50) NOT NULL,
  `harga` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `harga_jual` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `deskripsi` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_roti`
--

INSERT INTO `data_roti` (`kd_roti`, `nama_roti`, `stok`, `harga`, `harga_jual`, `deskripsi`) VALUES
('RTI001', 'Kuasong', 16, '10000', '15000', 'Roti Enak');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `kd_member` varchar(7) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `no_tlp` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`kd_member`, `nama`, `no_tlp`, `alamat`, `email`) VALUES
('MEM001', 'Jojo', '789870', 'Solo', '682023027@student.uksw.edu'),
('MEM002', 'Gondrong', '081617619862', 'Semarang', '0'),
('MEM003', 'Malih', '0816879899', 'Salatiga', '0'),
('MEM004', 'Udin', '789870', 'solo', '0'),
('MEM005', 'Arum', '085747075125', 'Salatiga', '682024084@student.uksw.edu');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `no_transaksi` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `kd_roti` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama_roti` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `stok` int(50) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `harga` bigint(10) NOT NULL,
  `total` bigint(10) NOT NULL,
  `kd_member` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama_member` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `diskon` int(5) NOT NULL,
  `total2` bigint(10) NOT NULL,
  `bayar` bigint(10) NOT NULL,
  `kembalian` bigint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`no_transaksi`, `tgl_transaksi`, `kd_roti`, `nama_roti`, `stok`, `jumlah`, `harga`, `total`, `kd_member`, `nama_member`, `diskon`, `total2`, `bayar`, `kembalian`) VALUES
('NTR001', '2025-05-30', 'RTI001', 'Kuasong', 100, 20, 15000, 300000, 'MEM002', 'Gondrong', 10, 270000, 300000, 30000),
('NTR002', '2025-06-06', 'RTI001', 'Kuasong', 70, 10, 15000, 150000, 'MEM001', 'Jojo', 10, 135000, 135000, 0),
('NTR003', '2025-06-06', 'RTI001', 'Kuasong', 60, 5, 15000, 75000, 'MEM001', 'Jojo', 10, 67500, 67500, 0),
('NTR004', '2025-06-06', 'RTI001', 'Kuasong', 55, 3, 15000, 45000, '', '', 0, 45000, 45000, 0),
('NTR005', '2025-06-06', 'RTI001', 'Kuasong', 52, 2, 15000, 30000, 'MEM001', 'Jojo', 10, 27000, 30000, 3000),
('NTR006', '2025-06-06', 'RTI001', 'Kuasong', 50, 2, 15000, 30000, 'MEM005', 'Arum', 10, 27000, 27000, 0),
('NTR007', '2025-06-10', 'RTI001', 'Kuasong', 48, 5, 15000, 75000, 'MEM001', 'Jojo', 10, 67500, 67500, 0),
('NTR008', '2025-06-10', 'RTI001', 'Kuasong', 43, 3, 15000, 45000, 'MEM001', 'Jojo', 10, 40500, 40500, 0),
('NTR009', '2025-06-10', 'RTI001', 'Kuasong', 40, 1, 15000, 15000, '', '', 0, 15000, 15000, 0),
('NTR010', '2025-06-10', 'RTI001', 'Kuasong', 39, 2, 15000, 30000, 'MEM001', 'Jojo', 10, 27000, 27000, 0),
('NTR011', '2025-06-10', 'RTI001', 'Kuasong', 37, 2, 15000, 30000, '', '', 0, 30000, 30000, 0),
('NTR012', '2025-06-14', 'RTI001', 'Kuasong', 35, 2, 15000, 30000, 'MEM005', 'Arum', 10, 27000, 27000, 0),
('NTR013', '2025-06-14', 'RTI001', 'Kuasong', 33, 2, 15000, 30000, '', '', 0, 30000, 30000, 0),
('NTR014', '2025-06-14', 'RTI001', 'Kuasong', 31, 1, 15000, 15000, '', '', 0, 15000, 15000, 0),
('NTR015', '2025-06-14', 'RTI001', 'Kuasong', 30, 2, 15000, 30000, 'MEM005', 'Arum', 10, 27000, 27000, 0),
('NTR016', '2025-06-14', 'RTI001', 'Kuasong', 28, 1, 15000, 15000, '', '', 0, 15000, 15000, 0),
('NTR017', '2025-06-14', 'RTI001', 'Kuasong', 27, 1, 15000, 15000, 'MEM005', 'Arum', 10, 13500, 13500, 0),
('NTR018', '2025-06-14', 'RTI001', 'Kuasong', 26, 1, 15000, 15000, '', '', 0, 15000, 15000, 0),
('NTR019', '2025-06-14', 'RTI001', 'Kuasong', 25, 1, 15000, 15000, 'MEM005', 'Arum', 10, 13500, 13500, 0),
('NTR020', '2025-06-14', 'RTI001', 'Kuasong', 24, 1, 15000, 15000, '', '', 0, 15000, 15000, 0),
('NTR021', '2025-06-14', 'RTI001', 'Kuasong', 23, 1, 15000, 15000, '', '', 0, 15000, 15000, 0),
('NTR022', '2025-06-17', 'RTI001', 'Kuasong', 22, 2, 15000, 30000, '', '', 0, 30000, 30000, 0),
('NTR023', '2025-06-21', 'RTI001', 'Kuasong', 20, 2, 15000, 30000, 'MEM001', 'Jojo', 10, 27000, 27000, 0),
('NTR024', '2025-06-22', 'RTI001', 'Kuasong', 18, 2, 15000, 30000, 'MEM001', 'Jojo', 10, 27000, 30000, 3000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `role`) VALUES
('admin', 'admin', 'admin'),
('kasir', 'kasir', 'kasir'),
('jonathan', 'sayang', 'kasir'),
('jojo', 'jojo', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_roti`
--
ALTER TABLE `data_roti`
  ADD PRIMARY KEY (`kd_roti`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`kd_member`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`no_transaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
