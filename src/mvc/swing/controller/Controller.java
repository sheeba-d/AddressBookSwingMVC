/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.swing.controller;

import mvc.swing.dao.ContactImpl;
import mvc.swing.dao.ContactIntf;
import mvc.swing.model.User;
import mvc.swing.view.MainPage;

/**
 *
 * @author admin
 */
public class Controller {
    User user;
    MainPage mPage;
    ContactIntf contactIntf;
    

    public Controller(User user, MainPage mPage) {
        this.user = user;
        this.mPage = mPage;
        contactIntf = new ContactImpl();
    }
    public static void loadMain(){
        new MainPage().setVisible(true);
    }
  public void loadUsers(){
      contactIntf.loadUsers(mPage);
     }
  public void loadUser(){
      contactIntf.loadUser(mPage);     
  }
   void populateMain(User user){
       contactIntf.populateMain(user, mPage);
   }
   public void addUser(User user){
       contactIntf.addUser(user, mPage);
   }
   public void populateEdit(){
       contactIntf.populateEdit(mPage);
   }
   public void editUser(User user,MainPage mPage,String uname){
       contactIntf.editUser(user, mPage, uname);
   }
   public void deleteUser(){
      contactIntf.deleteUser(mPage);
   }
}
