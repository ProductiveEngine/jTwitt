/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jtwitt.interfaces.ILogin;
import jtwitt.server.model.AppUser;

/**
 * Handling the database transactions concerning the login process
 * @author Nikos
 */
public class LoginDao implements ILogin{
    
    EntityManager em;
    /**
     * 
     * @param em EntityManager
     */
    public LoginDao(EntityManager em){
        this.em = em;
    }
    
    /**
     * Checks if a user with the given data exists in the database 
     * @param email String username or email of the current user
     * @param password String 
     * @return integer the userId if user exists, else (0) 
     */
    public int login(String username, String password) {
        int uid=-1;
        
        Query query = em.createNamedQuery("AppUser.login");
        query.setParameter("username",username);
        query.setParameter("password",password);

       List<AppUser> userLogin = query.getResultList();
       if(userLogin.size()>0){
       AppUser userId = userLogin.get(0);
       uid = userId.getUserId();
       }
       
//        return userLogin.size() == 1 ? 1 : -1;
        return uid ;
    }

    @Override
    public int logout(int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *  Checks if a user with the given username, password or email exists in the database
     * if not, proceeds to inserting the new user in APP_USER table
     * @param newUser AppUser object the new user who requests register
     * @return integer register result (1) if successful else (-1)
     */
    @Override
    public int signUp(AppUser newUser) {
        int register =-1;
        Query query = em.createNamedQuery("AppUser.login");
        query.setParameter("username",newUser.getUsername());
        query.setParameter("password",newUser.getPassword());

       List<AppUser> userLogin = query.getResultList();
       
       if(userLogin.size()==0){
        em.persist(newUser);
        register = 1;
       }
        
        return register;
    }

    @Override
    public int login(String email, String password, int chooser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
   public EntityManager getEm() {
        return em;
    }
    
}
