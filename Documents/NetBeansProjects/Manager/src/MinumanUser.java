/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIN10 test
 */
public class MinumanUser {
    int hargaMinuman,statusMinuman;
    String namaMinuman,promoMinuman,idMinuman;
    
    public MinumanUser(String idMinuman,String namaMinuman,int hargaMinuman,String promoMinuman,int statusMinuman){
        this.idMinuman = idMinuman;
        this.namaMinuman = namaMinuman;
        this.hargaMinuman = hargaMinuman;
        this.promoMinuman = promoMinuman;
        this.statusMinuman = statusMinuman;
    }
    
    public String getidMinuman(){
        return this.idMinuman;
    }
    
    public String getnamaMinuman(){
        return this.namaMinuman;
    }
    
    public int gethargaMinuman(){
        return this.hargaMinuman;
    }
    
    public String getpromoMinuman(){
        return this.promoMinuman;
    }
    
    public int getstatusMinuman(){
        return this.statusMinuman;
    }
}
