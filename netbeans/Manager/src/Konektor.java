
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIN10 test
 */
public class Konektor {
    String[] db;
    Connection conn;
    public Konektor(String[] db){
        this.db = db;
    }
    
    public Connection getConn(){
        try{
            conn = (Connection) DriverManager.getConnection(db[0], db[1], db[2]);
        } catch(SQLException e ){
            System.out.println(e);
            System.out.println("Koneksi Error!");
            
        }
        return conn;
    }
}
