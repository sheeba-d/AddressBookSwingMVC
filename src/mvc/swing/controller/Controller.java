/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.swing.controller;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import mvc.swing.dao.DbConnect;
import mvc.swing.model.User;
import mvc.swing.view.EditForm;
import mvc.swing.view.MainPage;

/**
 *
 * @author admin
 */
public class Controller {
    User user;
    MainPage mPage;
    

    public Controller(User user, MainPage mPage) {
        this.user = user;
        this.mPage = mPage;
    }
    public void control(Object obj){
        
       
    }
  public void loadUsers(){
     Connection con = new DbConnect().getConnect();
        try {
            Statement st = con.createStatement();
            String qry = "select * from addbook order by name asc";
            ResultSet rs = st.executeQuery(qry);
            DefaultTableModel dtm = (DefaultTableModel)mPage.getjTable1().getModel();
            dtm.setRowCount(0);
            while(rs.next()){
                Object[] ob = {rs.getString("name").toString()};
                dtm.addRow(ob);
            }
            con.close();
        }catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void loadUser(){
      User user = getUser();
       mPage.getjTextField1().setText(user.getName());
        mPage.getjTextField2().setText(user.getMob());
        mPage.getjTextField3().setText(user.getEmail());
   //   populateMain(user);
  }
   public User getUser(){
        User u = new User();
        int idx = mPage.getjTable1().getSelectedRow();
        if(idx==-1){
            JOptionPane.showMessageDialog(mPage, "No name is selected");
        }
        else{
        TableModel tbl = mPage.getjTable1().getModel();
        String name = tbl.getValueAt(idx, 0).toString();
        
       try {
           Connection con = new DbConnect().getConnect();
           Statement st = con.createStatement();
           String qry = "select * from addbook where name='"+name+"'";
           ResultSet rs = st.executeQuery(qry);
           while(rs.next()){
            
             u.setName(rs.getString("name"));
             u.setMob(rs.getString("mob"));
             u.setEmail(rs.getString("email"));
             
           }
       } catch (Exception e) {
       }
        }
        return u;
   }
   void populateMain(User user){
       mPage.getjTextField1().setText(user.getName());
        mPage.getjTextField2().setText(user.getMob());
        mPage.getjTextField3().setText(user.getEmail());
   }
   public void addUser(User user){
       try {
           Connection con = new DbConnect().getConnect();
           Statement st = con.createStatement();
           String qry = "insert into addbook values ('"+user.getName()+"','"+user.getMob()+"','"+user.getEmail()+"')";
           st.executeUpdate(qry);
           loadUsers();
                    
       } catch (Exception e) {
           System.out.println("Add Error :"+ e);
       }
   }
   public void populateEdit(){
       
       User u = getUser();
       try{
       if(u.getName().isEmpty()||u.getMob().isEmpty()||u.getEmail().isEmpty()){}
       else{
       EditForm editForm = new EditForm(mPage,u);
       editForm.getjTextField1().setText(u.getName());
       editForm.getjTextField2().setText(u.getMob());
       editForm.getjTextField3().setText(u.getEmail());
       editForm.setVisible(true);
       }
       }catch(NullPointerException ne){}
   }
   public void editUser(User user,String uname){
       try {
           Connection con = new DbConnect().getConnect();
           Statement st = con.createStatement();
           String qry="update addbook set name='"+user.getName()+"',mob='"+user.getMob()+"',email='"+user.getEmail()+"' where name='"+uname+"'";
           int flag = st.executeUpdate(qry);
           loadUsers();
          
                    
       } catch (Exception e) {
           System.out.println("Update Error :"+ e);
       }
   }
   public void deleteUser(){
       User u = getUser();
        try{
             if(u.getName().isEmpty()||u.getMob().isEmpty()||u.getEmail().isEmpty()){}
            else{
            String uname = u.getName();
            int ch = JOptionPane.showConfirmDialog(mPage, "Do u really want to delete '"+uname+"'?");
            if(ch==0){
            try {
               Connection con = new DbConnect().getConnect();
               Statement st = con.createStatement();
               String qry="delete from addbook where name='"+uname+"'";
               int flag = st.executeUpdate(qry);
               loadUsers();
            } catch (Exception e) {
            System.out.println("Delete Error :"+ e);
            }
           }
          }
       }catch(NullPointerException ne){}
   }
}
