/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalmobil;

/**
 *
 * @author Kevin
 */
public class DataTabel {
    private String idMobil;
    private String username;

    public DataTabel(String idMobil, String username) {
        this.idMobil = idMobil;
        this.username = username;
    }

    public String getIdMobil() {
        return idMobil;
    }

    public String getUsername() {
        return username;
    }
}
