/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashier;

/**
 *
 * @author WIN10 test
 */
public class CashierUser {
int idPembayaran,totalBiaya,nomorMeja,statusPembayaran;
String fName,lName,ListPesanan,kodePembayaran;
    /**
     * @param args the command line arguments
     */
     public CashierUser(int idPembayaran,String fName,String lName,int nomorMeja,String ListPesanan,int totalBiaya,String kodePembayaran,int statusPembayaran){
         this.idPembayaran = idPembayaran;
         this.fName = fName;
         this.lName = lName;
         this.nomorMeja = nomorMeja;
         this.ListPesanan = ListPesanan;
         this.totalBiaya = totalBiaya;
         this.kodePembayaran = kodePembayaran;
         this.statusPembayaran = statusPembayaran;
     }
    
     public int getId(){
         return idPembayaran;
     }
     public String getfName(){
         return fName;
     }
     public String getlName(){
         return lName;
     }
     public int getnomorMeja(){
         return nomorMeja;
     }
     public String getListPesanan(){
         return ListPesanan;
     }
     public int getTotalBiaya(){
         return totalBiaya;
     }
     public String getKodePembayaran(){
         return kodePembayaran;
     }
     public int getStatusPembayaran(){
         return statusPembayaran;
     }
}
