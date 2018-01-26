
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIN10 test
 */
public class databaseConnect {
    private Connection connect = null;
    private Statement statement = null;
    
    public void Connect(){
        // Connect to host
    
    try {
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3309/depot","root","");
    } catch(SQLException a){
      
    return;
    }
    try {
        statement = connect.createStatement(); 
        ResultSet rs = statement.executeQuery("SHOW TABLES");
        System.out.println(rs.toString());
    } catch (Exception e) {
        System.out.println("aaaax");
    }
    
    }
}
