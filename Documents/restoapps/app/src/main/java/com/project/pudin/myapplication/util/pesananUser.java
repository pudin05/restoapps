package com.project.pudin.myapplication.util;

/**
 * Created by WIN10 test on 1/12/2018.
 */

public class pesananUser {
    private String listP = "";
    private String idPesanan = "";
    private String status = "";

    public pesananUser(String lP,String idPesanan,String status){
        if(this.listP.length()<=2) {
            this.listP += lP;
            this.idPesanan += idPesanan;
            this.status += status;
        }
    }

    public String getIdPesanan(){
        return idPesanan;
    }
    public String getListP(){
        return listP;
    }
    public String getStatus(){ return status;}
}
