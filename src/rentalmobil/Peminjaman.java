/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalmobil;

/**
 *
 * @author Kevin
 */
public class Peminjaman extends DataTabel {
    private String idPeminjaman;
    private int jumlahHari;
    private int mobilTersisa;
    private String status;

    // Constructor
    public Peminjaman(String idPeminjaman, String idMobil, String username, int jumlahHari, int mobilTersisa, String status) {
        super(idMobil, username);  // Memanggil konstruktor kelas induk (DataTabel)
        this.idPeminjaman = idPeminjaman;
        this.jumlahHari = jumlahHari;
        this.mobilTersisa = mobilTersisa;
        this.status = status;
    }

    // Getter and Setter (encapsulation)
    public String getIdPeminjaman() { return idPeminjaman; }
    public void setIdPeminjaman(String idPeminjaman) { this.idPeminjaman = idPeminjaman; }

    public int getJumlahHari() { return jumlahHari; }
    public void setJumlahHari(int jumlahHari) { this.jumlahHari = jumlahHari; }

    public int getMobilTersisa() { return mobilTersisa; }
    public void setMobilTersisa(int mobilTersisa) { this.mobilTersisa = mobilTersisa; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Peminjaman{" +
                "idPeminjaman='" + idPeminjaman + '\'' +
                ", idMobil='" + getIdMobil() + '\'' +  // Inherited dari DataTabel
                ", username='" + getUsername() + '\'' +  // Inherited dari DataTabel
                ", jumlahHari=" + jumlahHari +
                ", mobilTersisa=" + mobilTersisa +
                ", status='" + status + '\'' +
                '}';
    }
}
