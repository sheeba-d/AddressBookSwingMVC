/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.swing.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author admin
 */
public class DbConnect {
    private  String driver = "";
     private String userName = "";
     private String password = "";
     private String dBase = "";
   /*
     private  String driver = "com.mysql.jdbc.Driver";
     private String userName = "root";
     private String password = "";
     private String dBase = "addressbook";
    */
    private static DbConnect dbConnect = new DbConnect();
     Connection con=null ;
     //Statement st=null;
     private DbConnect(){}
     public static DbConnect getInstance(){
         return dbConnect;
     }
    public Connection getConnect(){
        try{
            FileInputStream file=new FileInputStream("dbconfig.properties");
            Properties prop = new Properties();
            prop.load(file);
        
     
            driver = prop.getProperty("driver");
            userName = prop.getProperty("userName");
            password = prop.getProperty("password");
            dBase = prop.getProperty("dBase");
            Class.forName(driver);
           String url = "jdbc:mysql://localhost/"+dBase+"?user="+userName+"&password="+password;
            con = DriverManager.getConnection(url);
        //    st = con.createStatement();
        }catch(Throwable e){
            e.printStackTrace();
        }
        return con;
    }
}
