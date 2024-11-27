-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 23 Nov 2024 pada 14.52
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rentalmobil`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `akun`
--

CREATE TABLE `akun` (
  `username` varchar(50) NOT NULL,
  `password` varchar(12) NOT NULL,
  `role` enum('admin','pelanggan') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `akun`
--

INSERT INTO `akun` (`username`, `password`, `role`) VALUES
('admin', '123', 'admin'),
('Agus', '1', 'pelanggan'),
('Bella', '123', 'pelanggan'),
('faiz', '123', 'admin'),
('kevin', '123', 'admin'),
('mamad', '123', 'admin'),
('rian', '123', 'pelanggan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mobil`
--

CREATE TABLE `mobil` (
  `ID_Mobil` varchar(10) NOT NULL,
  `Nama_Mobil` varchar(50) NOT NULL,
  `Harga` int(11) NOT NULL,
  `Jumlah_Mobil` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mobil`
--

INSERT INTO `mobil` (`ID_Mobil`, `Nama_Mobil`, `Harga`, `Jumlah_Mobil`) VALUES
('H2021', 'Hijau 2021', 400000, 5),
('H2024', 'Hijau 2024', 500000, 9),
('K2021', 'Kuning 2021', 200000, 7),
('K2022', 'Kuning 2022', 250000, 5),
('M2020', 'Merah 2020', 300000, 4),
('M2023', 'Merah 2023', 300000, 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman`
--

CREATE TABLE `peminjaman` (
  `ID_Peminjaman` int(5) NOT NULL,
  `ID_Mobil` varchar(10) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `JumlahHari` int(11) DEFAULT NULL,
  `MobilTersisa` int(11) DEFAULT NULL,
  `Status` enum('Belum dikembalikan','Sudah dikembalikan') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `peminjaman`
--

INSERT INTO `peminjaman` (`ID_Peminjaman`, `ID_Mobil`, `username`, `JumlahHari`, `MobilTersisa`, `Status`) VALUES
(1, 'H2024', 'Agus', 1, 8, 'Sudah dikembalikan'),
(2, 'K2021', 'rian', 2, 7, 'Belum dikembalikan'),
(3, 'M2023', 'Bella', 2, 3, 'Belum dikembalikan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `ID_Transaksi` int(11) NOT NULL,
  `ID_Mobil` varchar(10) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `HargaSewa` int(11) DEFAULT NULL,
  `TotalPemasukan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`ID_Transaksi`, `ID_Mobil`, `username`, `HargaSewa`, `TotalPemasukan`) VALUES
(1, 'H2024', 'Agus', 500000, 500000),
(2, 'K2021', 'rian', 400000, 0),
(3, 'M2023', 'Bella', 600000, 0);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `mobil`
--
ALTER TABLE `mobil`
  ADD PRIMARY KEY (`ID_Mobil`);

--
-- Indeks untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`ID_Peminjaman`),
  ADD KEY `ID_Mobil` (`ID_Mobil`),
  ADD KEY `username` (`username`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`ID_Transaksi`),
  ADD KEY `ID_Mobil` (`ID_Mobil`),
  ADD KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `ID_Peminjaman` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `ID_Transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `peminjaman_ibfk_1` FOREIGN KEY (`ID_Mobil`) REFERENCES `mobil` (`ID_Mobil`);

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`ID_Mobil`) REFERENCES `mobil` (`ID_Mobil`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
