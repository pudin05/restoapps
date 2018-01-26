package com.project.pudin.myapplication.util;

/**
 * Created by WIN10 test on 11/29/2017.
 */

public class User {
    private String username,idcustomer;

    public User(String username,String idcustomer){
        this.username = username;
        this.idcustomer = idcustomer;
    }
    public String getUsername(){
        return username;
    }
    public String getId(){ return idcustomer;}
}
