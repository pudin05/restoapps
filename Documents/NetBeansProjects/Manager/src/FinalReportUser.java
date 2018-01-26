/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIN10 test
 */
class FinalReportUser {
    private int idFR,telpCust,nomorMeja,totalBiaya,kodePembayaran,statusPemabayaran;
    private String fName,lName,alamat,ListPesanan,keterangan,Date;
    
        public FinalReportUser(int IdFR,String fName,String lName,int telpCust,String alamat,int nomorMeja,String ListPesanan,String keterangan,int totalBiaya,int kodePembayaran,int statusPembayaran,String Date){
            this.idFR = IdFR;
            this.fName = fName;
            this.lName = lName;
            this.telpCust = telpCust;
            this.alamat = alamat;
            this.nomorMeja = nomorMeja;
            this.ListPesanan = ListPesanan;
            this.keterangan = keterangan;
            this.totalBiaya = totalBiaya;
            this.kodePembayaran = kodePembayaran;
            this.statusPemabayaran = statusPembayaran;
            this.Date = Date;
            
        }
        
        public int getId(){
            return idFR;
        }
        public String getfName(){
            return fName;
        }
        public String getlName(){
            return lName;
        }
        public int gettelpCust(){
            return telpCust;
        }
        public String getalamat(){
            return alamat;
        }
        public int getnomorMeja(){
            return nomorMeja;
        }
        public String getListPesanan(){
            return ListPesanan;
        }
        public String getketerangan(){
            return keterangan;
        }
        public int gettotalBiaya(){
            return totalBiaya;
        }
        public int getkodePembayaran(){
            return kodePembayaran;
        }
        public int getstatusPembayaran(){
            return statusPemabayaran;
        }
        public String getDate(){
            return Date;
        }
}
