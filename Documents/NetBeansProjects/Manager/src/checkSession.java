
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIN10 test
 */
public class checkSession {
    Connection conn;
    String[] db = "jdbc:mysql://putraput.eu.org:3306/depot,putra,delfira26".split(",");
    FileInputStream fr;
    Properties gg = new Properties();
    Statement st;
    ResultSet rs;
    Integer resultrow;
    String[] td;
    public void getDate(){
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        String as = date.toString();
        td = as.split("-");
    }
    public void checkSession(){
        
        getDate();
        try {
           FileInputStream fr = new FileInputStream("myProperties.properties");
           conn = DriverManager.getConnection(db[0],db[1],db[2]);
           gg.load(fr);
           String sessid = gg.getProperty("sessid");
           String lastuser = new String(gg.getProperty("lastuser"));
           String theDate = td[0]+td[1]+td[2];
           st = conn.createStatement();
           rs = st.executeQuery("SELECT * FROM datamanager WHERE username = '"+lastuser+"' AND session = '"+sessid+"' AND lastlogin = '"+theDate+"'");
           rs.next();
           Thread.sleep(6000);
           if(rs.getRow()==1){
               System.out.println("Row = 1");
               System.out.println("if checksession");
           }
           
           else{    
               System.out.println("else checkSession");
               new MainFrame().setVisible(false);
           }
       } catch (Exception e) {
           System.out.println("aaaa"+e);
       }
   }
}
