/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIN10 test
 */
public class CustomerUser {
    int idCust,telp;
    String fname,lname,alamat;
    public CustomerUser(int idCust,String fname,String lname,int telp,String alamat){
        this.idCust = idCust;
        this.fname = fname;
        this.lname = lname;
        this.telp = telp;
        this.alamat = alamat;
    }
    
    public int getIdCust(){
        return this.idCust;
    }
    
    public String getfName(){
        return this.fname;
    }
    
    public String getlName(){
        return this.lname;
    }
    
    public int getTelp(){
        return this.telp;
    }
    
    public String getAlamat(){
        return this.alamat;
    }
}
