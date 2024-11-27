/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rentalmobil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin
 */
public class TabelTransaksi extends javax.swing.JFrame {

    /**
     * Creates new form TabelTransaksi
     */
   
    public TabelTransaksi() {
        initComponents();
        String username = UserSession.getUsername();
        UsernameLBL.setText(username.toUpperCase());
        loadTableData();
        setTitle("Aplikasi Rental Mobil");
        setLocationRelativeTo(null);
    }
    
    // Method untuk memuat data transaksi
    private void loadTableData() {
        // ArrayList untuk menyimpan data transaksi
    ArrayList<DataTabel> transaksiList = new ArrayList<>();
    
    // Membuat model untuk JTable
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Id_Transaksi");
    model.addColumn("Id_Mobil");
    model.addColumn("Username");
    model.addColumn("HargaSewa");
    model.addColumn("TotalPemasukan");

    try {
        // Query untuk mengambil data transaksi
        String sql = "SELECT * FROM transaksi";
        Connection conn = koneksi.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        // Ambil data dari ResultSet dan masukkan ke dalam ArrayList
        while (rs.next()) {
            // Membuat objek Transaksi dan menambahkannya ke ArrayList
            DataTabel transaksi = new Transaksi(
                    rs.getString("Id_Transaksi"),
                    rs.getString("Id_Mobil"),
                    rs.getString("username"),
                    rs.getInt("HargaSewa"),
                    rs.getInt("TotalPemasukan")
            );
            
            // Menambahkan objek transaksi ke ArrayList
            transaksiList.add(transaksi);
        }

        // Menambahkan data dari ArrayList ke dalam JTable
        for (DataTabel transaksi : transaksiList) {
            model.addRow(new Object[]{
                    transaksi instanceof Transaksi ? ((Transaksi) transaksi).getIdTransaksi() : "N/A",
                    transaksi.getIdMobil(),
                    transaksi.getUsername(),
                    transaksi instanceof Transaksi ? ((Transaksi) transaksi).getHargaSewa() : "N/A",
                    transaksi instanceof Transaksi ? ((Transaksi) transaksi).getTotalPemasukan() : "N/A"
            });
        }

        // Set model ke JTable
        TblTransaksi.setModel(model);

    } catch (SQLException e) {
        System.err.println("Error membaca data transaksi: " + e.getMessage());
    }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UsernameLBL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblTransaksi = new javax.swing.JTable();
        TabelTransaksi = new javax.swing.JLabel();
        BtnKelola = new javax.swing.JButton();
        BtnPeminjaman = new javax.swing.JButton();
        BtnTransaksi = new javax.swing.JButton();
        BtnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UsernameLBL.setFont(new java.awt.Font("Arial", 2, 36)); // NOI18N
        UsernameLBL.setForeground(new java.awt.Color(96, 197, 108));
        UsernameLBL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(UsernameLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 50, 180, 40));

        TblTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 32));
        TblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID_Transaksi", "ID_Mobil", "Username", "Harga Sewa", "Total Pemasukan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TblTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TblTransaksi.setRowHeight(50);
        TblTransaksi.getTableHeader().setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        jScrollPane1.setViewportView(TblTransaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 1020, 490));

        TabelTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/Tabel Transaksi.png"))); // NOI18N
        TabelTransaksi.setText("jLabel1");
        getContentPane().add(TabelTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        BtnKelola.setText("Kelola Mobil");
        BtnKelola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelolaActionPerformed(evt);
            }
        });
        getContentPane().add(BtnKelola, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 170, 40));

        BtnPeminjaman.setText("Peminjaman");
        BtnPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPeminjamanActionPerformed(evt);
            }
        });
        getContentPane().add(BtnPeminjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 170, 50));

        BtnTransaksi.setText("Transaksi");
        BtnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(BtnTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 170, 40));

        BtnKeluar.setText("Keluar");
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        getContentPane().add(BtnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 170, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKelolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelolaActionPerformed
        // TODO add your handling code here:
        KelolaMobil km = new KelolaMobil();
        km.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnKelolaActionPerformed

    private void BtnPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPeminjamanActionPerformed
        // TODO add your handling code here:
        TabelPeminjaman pin = new TabelPeminjaman();
        pin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnPeminjamanActionPerformed

    private void BtnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTransaksiActionPerformed
        // TODO add your handling code here:
        TabelTransaksi tra = new TabelTransaksi();
        tra.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnTransaksiActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        // TODO add your handling code here:
        HalamanAwal ha = new HalamanAwal();
        ha.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TabelTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TabelTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TabelTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TabelTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TabelTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnKelola;
    private javax.swing.JButton BtnKeluar;
    private javax.swing.JButton BtnPeminjaman;
    private javax.swing.JButton BtnTransaksi;
    private javax.swing.JLabel TabelTransaksi;
    private javax.swing.JTable TblTransaksi;
    private javax.swing.JLabel UsernameLBL;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
