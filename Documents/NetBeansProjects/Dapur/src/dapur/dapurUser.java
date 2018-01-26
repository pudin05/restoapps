/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dapur;

/**
 *
 * @author WIN10 test
 */
public class dapurUser {
    int idPesanan,meja,statusPesanan;
    String fName,lName,listPesanan,keterangan;
    
    public dapurUser(int idPesanan,String fName,String lName,int meja,String listPesanan,String keterangan,int statusPesanan){
        this.idPesanan = idPesanan;
        this.fName = fName;
        this.lName = lName;
        this.meja = meja;
        this.listPesanan = listPesanan;
        this.keterangan = keterangan;
        this.statusPesanan = statusPesanan;
        
    }
    
    public int getId(){
        return idPesanan;
    }
    public String getfName(){
        return fName;
    }
    public String getlName(){
        return lName;
    }
    public int getMeja(){
        return meja;
    }
    public String getlistPesanan(){
        return listPesanan;
    }
    public String getKeterangan(){
        return keterangan;
    }
    public int getstatusPesanan(){
        return statusPesanan;
    }
}
