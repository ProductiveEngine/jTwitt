/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.interfaces;

import jtwitt.server.model.AppUser;

/**
 * The interface that exposes the main methods Login related classes implement
 * @author Nikos
 */
public interface ILogin {
    // Login\out -------------------------------------------------------
    //1 email , 2 username
    //return 1 ok , -1 error
    public int login(String email, String password, int chooser);
    public int logout(int userId);
   // public int signUp(String username, String password, String firstName, String lastName, String email);
    public int signUp(AppUser appUser); 
}
