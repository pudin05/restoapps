package com.project.pudin.myapplication.util;

/**
 * Created by WIN10 test on 12/8/2017.
 */

public class userMenu {
    String id,nama,harga,promo,status,image;
    public userMenu(String id,String nama,String harga,String promo,String status,String image){
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.promo = promo;
        this.status = status;
        this.image = image;
    }

    public String getId() {
        return id;
    }
    public String getNamaMakanan(){return nama;}
    public String getHarga(){return harga;}
    public String getPromo() {
        return promo;
    }
    public String getStatus() {
        return status;
    }
    public String getImage(){ return image;}
}
