/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.swing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author admin
 */
public class DbConnect {
   
     private  String driver = "com.mysql.jdbc.Driver";
     private String userName = "root";
     private String password = "";
     private String dBase = "addressbook";
     private String url = "jdbc:mysql://localhost/"+dBase+"?user="+userName+"&password="+password;
    
     Connection con=null ;
     //Statement st=null;
    public Connection getConnect(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url);
        //    st = con.createStatement();
        }catch(Throwable e){
            e.printStackTrace();
        }
        return con;
    }
}
