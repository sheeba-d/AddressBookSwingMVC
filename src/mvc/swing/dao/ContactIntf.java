/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.swing.dao;

import mvc.swing.model.User;
import mvc.swing.view.MainPage;

/**
 *
 * @author admin
 */
public interface ContactIntf {
    public void loadUsers(MainPage mPage);
    public void loadUser(MainPage mPage);
    public User getUser(MainPage mPage);
    public void populateMain(User user,MainPage mPage);
    public void addUser(User user,MainPage mPage);
    public void populateEdit(MainPage mPage);
    public void editUser(User user,MainPage mPage,String uname);
    public void deleteUser(MainPage mPage);
}
