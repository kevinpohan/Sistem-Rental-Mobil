/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalmobil;

/**
 *
 * @author Kevin
 */

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KonfirmasiData implements Proses {
    private final int IDpeminjam; // Menyimpan ID peminjam

    // Konstruktor untuk menerima ID peminjam
    public KonfirmasiData(int IDpeminjam) {
        this.IDpeminjam = IDpeminjam;
    }

    @Override
    public void proses() {
        // Inisialisasi objek untuk koneksi dan statement
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement pstUpdateMobil = null;
        PreparedStatement pstUpdatePeminjaman = null;
        PreparedStatement pstTransaksi = null;
        PreparedStatement pstTotal = null;
        ResultSet rs = null;
        ResultSet rstr = null;

        try {
            // Membuka koneksi ke database
            conn = koneksi.configDB();

            // Query untuk mendapatkan data peminjaman berdasarkan ID_Peminjaman
            String sql = "SELECT * FROM peminjaman WHERE ID_Peminjaman = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, IDpeminjam);  // Menyisipkan ID peminjam ke query
            rs = pst.executeQuery();

            if (rs.next()) { // Jika data ditemukan
                String IdMobil = rs.getString("ID_Mobil"); // Mendapatkan ID Mobil dari peminjaman

                // Update jumlah mobil yang dikembalikan (jumlah mobil ditambah 1)
                String sqlUpdateMobil = "UPDATE mobil SET Jumlah_Mobil = Jumlah_Mobil + 1 WHERE ID_Mobil = ?";
                pstUpdateMobil = conn.prepareStatement(sqlUpdateMobil);
                pstUpdateMobil.setString(1, IdMobil);
                pstUpdateMobil.executeUpdate();

                // Update status peminjaman menjadi "Sudah dikembalikan"
                String sqlUpdatePeminjaman = "UPDATE peminjaman SET Status = 'Sudah dikembalikan' WHERE ID_Peminjaman = ?";
                pstUpdatePeminjaman = conn.prepareStatement(sqlUpdatePeminjaman);
                pstUpdatePeminjaman.setInt(1, IDpeminjam);
                pstUpdatePeminjaman.executeUpdate();

                // Query untuk mendapatkan data transaksi berdasarkan ID_Mobil
                String sqlTransaksi = "SELECT * FROM transaksi WHERE ID_Mobil = ?";
                pstTransaksi = conn.prepareStatement(sqlTransaksi);
                pstTransaksi.setString(1, IdMobil);  // Menyisipkan ID Mobil ke query
                rstr = pstTransaksi.executeQuery();

                if (rstr.next()) { // Jika data transaksi ditemukan
                    int Harga = rstr.getInt("HargaSewa"); // Mendapatkan harga sewa mobil
                    int TotalPemasukan = rstr.getInt("TotalPemasukan"); // Mendapatkan total pemasukan

                    // Menambahkan pemasukan baru ke total pemasukan
                    TotalPemasukan += Harga;

                    // Query untuk update total pemasukan pada transaksi
                    String sqlTotal = "UPDATE transaksi SET TotalPemasukan = ? WHERE ID_Mobil = ?";
                    pstTotal = conn.prepareStatement(sqlTotal);
                    pstTotal.setInt(1, TotalPemasukan); // Menyisipkan total pemasukan baru
                    pstTotal.setString(2, IdMobil); // Menyisipkan ID Mobil
                    pstTotal.executeUpdate();
                }

                // Menampilkan pesan sukses jika pengembalian berhasil
                JOptionPane.showMessageDialog(null, "Pengembalian berhasil!");
            } else {
                // Menampilkan pesan jika data peminjaman tidak ditemukan
                JOptionPane.showMessageDialog(null, "Data peminjaman tidak ditemukan.");
            }
        } catch (Exception e) {
            // Menampilkan pesan error jika terjadi pengecualian
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
        } finally {
            // Menutup semua resource database setelah selesai digunakan
            try {
                if (rstr != null) rstr.close();
                if (rs != null) rs.close();
                if (pstTransaksi != null) pstTransaksi.close();
                if (pstTotal != null) pstTotal.close();
                if (pstUpdateMobil != null) pstUpdateMobil.close();
                if (pstUpdatePeminjaman != null) pstUpdatePeminjaman.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                // Menampilkan pesan error jika terjadi kesalahan saat menutup resource
                JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage());
            }
        }
    }
}
