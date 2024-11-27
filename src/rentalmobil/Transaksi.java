/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalmobil;

/**
 *
 * @author Kevin
 */
public class Transaksi extends DataTabel {
    private String idTransaksi;
    private int hargaSewa;
    private int totalPemasukan;

    public Transaksi(String idTransaksi, String idMobil, String username, int hargaSewa, int totalPemasukan) {
        super(idMobil, username);  // Memanggil konstruktor kelas induk (DataTabel)
        this.idTransaksi = idTransaksi;
        this.hargaSewa = hargaSewa;
        this.totalPemasukan = totalPemasukan;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public int getTotalPemasukan() {
        return totalPemasukan;
    }

    @Override
    public String toString() {
        return "Transaksi{" +
                "idTransaksi='" + idTransaksi + '\'' +
                ", idMobil='" + getIdMobil() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", hargaSewa=" + hargaSewa +
                ", totalPemasukan=" + totalPemasukan +
                '}';
    }
}


