/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalmobil;

/**
 *
 * @author Kevin
 */

import java.sql.*;
import javax.swing.JOptionPane;

public class TransaksiPembayaran {
    private String idMobil; //Tipe data String pada Variabel idMobil
    private int harga, jumlahHari, jumlahUang, totalHarga, kembalian;

    // Constructor
    public TransaksiPembayaran(String idMobil, int harga, int jumlahHari, int jumlahUang) {
        this.idMobil = idMobil;
        this.harga = harga;
        this.jumlahHari = jumlahHari;
        this.jumlahUang = jumlahUang;
        this.totalHarga = harga * jumlahHari;
        this.kembalian = jumlahUang - totalHarga;
    }

    // Method untuk memproses pembayaran
    public void prosesPembayaran(String username) {
        try {
            int mobilTersisa;
            Connection conn = koneksi.configDB();

            // Cek data mobil di database
            String sqlMobil = "SELECT * FROM mobil WHERE ID_Mobil = ?";
            PreparedStatement pstMobil = conn.prepareStatement(sqlMobil);
            pstMobil.setString(1, idMobil);
            ResultSet rsMobil = pstMobil.executeQuery();

            if (rsMobil.next()) {
                int jumlahMobil = rsMobil.getInt("Jumlah_Mobil");

                // Periksa ketersediaan mobil
                if (jumlahMobil > 0) {
                    mobilTersisa = jumlahMobil - 1;

                    // Update jumlah mobil
                    String sqlUpdateMobil = "UPDATE mobil SET Jumlah_Mobil = ? WHERE ID_Mobil = ?";
                    PreparedStatement pstUpdateMobil = conn.prepareStatement(sqlUpdateMobil);
                    pstUpdateMobil.setInt(1, mobilTersisa);
                    pstUpdateMobil.setString(2, idMobil);
                    pstUpdateMobil.executeUpdate();

                    // Masukkan data peminjaman
                    String sqlPeminjaman = "INSERT INTO peminjaman (ID_Mobil, Username, JumlahHari, MobilTersisa, Status) " +
                                           "VALUES (?, ?, ?, ?, 'Belum dikembalikan')";
                    PreparedStatement pstPeminjaman = conn.prepareStatement(sqlPeminjaman);
                    pstPeminjaman.setString(1, idMobil);
                    pstPeminjaman.setString(2, username);
                    pstPeminjaman.setInt(3, jumlahHari);
                    pstPeminjaman.setInt(4, mobilTersisa);
                    pstPeminjaman.executeUpdate();

                    // Masukkan data transaksi
                    String sqlTransaksi = "INSERT INTO transaksi (ID_Mobil, Username, HargaSewa, TotalPemasukan) " +
                                          "VALUES (?, ?, ?, ?)";
                    PreparedStatement pstTransaksi = conn.prepareStatement(sqlTransaksi);
                    pstTransaksi.setString(1, idMobil);
                    pstTransaksi.setString(2, username);
                    pstTransaksi.setInt(3, totalHarga);
                    pstTransaksi.setInt(4, 0); // Asumsi TotalPemasukan adalah harga total
                    pstTransaksi.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Pembayaran berhasil! Mobil disewa.");
                } else {
                    JOptionPane.showMessageDialog(null, "Mobil tidak tersedia.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mobil tidak ditemukan.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    // Getter untuk informasi pembayaran
    public int getTotalHarga() {
        return totalHarga;
    }

    public int getKembalian() {
        return kembalian;
    }
}

