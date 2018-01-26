package com.project.pudin.myapplication.util;

/**
 * Created by WIN10 test on 12/15/2017.
 */

public class keranjangUser {
    private String nama,porsi,idmak;
    private int status,total;

    public keranjangUser (String idmak,String nama,String porsi){
        this.nama = nama;
        this.porsi = porsi;
        this.idmak = idmak;

    }

    public String getIdmak() { return idmak;}
    public String getNama() {
        return nama;
    }
    public String getPorsi() {
        return porsi;
    }

}
