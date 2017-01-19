/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.swing.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import mvc.swing.controller.Controller;
import mvc.swing.model.User;
import mvc.swing.view.EditForm;
import mvc.swing.view.MainPage;

/**
 *
 * @author admin
 */
public class ContactImpl implements ContactIntf{

    @Override
    public void loadUsers(MainPage mPage) {
       Connection con =DbConnect.getInstance().getConnect();
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

    @Override
    public void loadUser(MainPage mPage) {
       User user = getUser(mPage);
       mPage.getjTextField1().setText(user.getName());
        mPage.getjTextField2().setText(user.getMob());
        mPage.getjTextField3().setText(user.getEmail());
    }

    @Override
    public User getUser(MainPage mPage) {
         User u = new User();
        int idx = mPage.getjTable1().getSelectedRow();
        if(idx==-1){
            JOptionPane.showMessageDialog(mPage, "No name is selected");
        }
        else{
        TableModel tbl = mPage.getjTable1().getModel();
        String name = tbl.getValueAt(idx, 0).toString();
        
       try {
           Connection con =DbConnect.getInstance().getConnect();
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

    @Override
    public void populateMain(User user, MainPage mPage) {
       mPage.getjTextField1().setText(user.getName());
        mPage.getjTextField2().setText(user.getMob());
        mPage.getjTextField3().setText(user.getEmail());
    }

    @Override
    public void addUser(User user,MainPage mPage) {
       try {
           Connection con =DbConnect.getInstance().getConnect();
           Statement st = con.createStatement();
           String qry = "insert into addbook values ('"+user.getName()+"','"+user.getMob()+"','"+user.getEmail()+"')";
           st.executeUpdate(qry);
           loadUsers(mPage);
                    
       } catch (Exception e) {
           System.out.println("Add Error :"+ e);
       }
    }

    @Override
    public void populateEdit(MainPage mPage) {
         
       User u = getUser(mPage);
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

    @Override
    public void editUser(User user,MainPage mPage, String uname) {
        try {
          Connection con =DbConnect.getInstance().getConnect();
           Statement st = con.createStatement();
           String qry="update addbook set name='"+user.getName()+"',mob='"+user.getMob()+"',email='"+user.getEmail()+"' where name='"+uname+"'";
           int flag = st.executeUpdate(qry);
           loadUsers(mPage);
           populateMain(user, mPage);
          
                    
       } catch (Exception e) {
           System.out.println("Update Error :"+ e);
       }
    }

    @Override
    public void deleteUser(MainPage mPage) {
       User u = getUser(mPage);
        try{
             if(u.getName().isEmpty()||u.getMob().isEmpty()||u.getEmail().isEmpty()){}
            else{
            String uname = u.getName();
            int ch = JOptionPane.showConfirmDialog(mPage, "Do u really want to delete '"+uname+"'?");
            if(ch==0){
            try {
               Connection con =DbConnect.getInstance().getConnect();
               Statement st = con.createStatement();
               String qry="delete from addbook where name='"+uname+"'";
               int flag = st.executeUpdate(qry);
               loadUsers(mPage);
            } catch (Exception e) {
            System.out.println("Delete Error :"+ e);
            }
           }
          }
       }catch(NullPointerException ne){}
    }

}
