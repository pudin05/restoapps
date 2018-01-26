/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIN10 test
 */
public class MakananUser {
    int hargaMakanan,statusMakanan;
    String namaMakanan,promoMakanan,idMakanan;
    
    public MakananUser(String idMakanan,String namaMakanan,int hargaMakanan,String promoMakanan,int statusMakanan){
        this.idMakanan = idMakanan;
        this.namaMakanan = namaMakanan;
        this.hargaMakanan = hargaMakanan;
        this.promoMakanan = promoMakanan;
        this.statusMakanan = statusMakanan;
    }
    
    public String getidMakanan(){
        return this.idMakanan;
    }
    
    public String getnamaMakanan(){
        return this.namaMakanan;
    }
    
    public int gethargaMakanan(){
        return this.hargaMakanan;
    }
    
    public String getpromoMakanan(){
        return this.promoMakanan;
    }
    
    public int getstatusMakanan(){
        return this.statusMakanan;
    }
}
