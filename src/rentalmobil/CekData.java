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

public class CekData implements Proses {
    private final int IDpeminjam;
    private final JTextField TFnama;
    private final JTextField TFidmobil;
    private final JTextField TFidtransaksi;

    public CekData(int IDpeminjam, JTextField TFnama, JTextField TFidmobil, JTextField TFidtransaksi) {
        this.IDpeminjam = IDpeminjam;
        this.TFnama = TFnama;
        this.TFidmobil = TFidmobil;
        this.TFidtransaksi = TFidtransaksi;
    }

    @Override
    public void proses() {
        try {
            String sql = "SELECT * FROM peminjaman WHERE ID_Peminjaman = ?";
            Connection conn = koneksi.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, IDpeminjam);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String IdMobil = rs.getString("ID_Mobil");
                String nama = rs.getString("username");

                String sqlTransaksi = "SELECT * FROM transaksi WHERE username = ? AND ID_Mobil = ?";
                PreparedStatement pstTransaksi = conn.prepareStatement(sqlTransaksi);
                pstTransaksi.setString(1, nama);
                pstTransaksi.setString(2, IdMobil);
                ResultSet rstr = pstTransaksi.executeQuery();

                if (rstr.next()) {
                    int IdTransaksi = rstr.getInt("ID_Transaksi");
                    TFnama.setText(nama);
                    TFidmobil.setText(IdMobil);
                    TFidtransaksi.setText(String.valueOf(IdTransaksi));
                } else {
                    JOptionPane.showMessageDialog(null, "Data transaksi tidak ditemukan.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data peminjaman tidak ditemukan.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
        }
    }
}

