/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.business;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import jtwitt.server.dao.AppUserDao;
import jtwitt.server.dao.LoginDao;
import jtwitt.server.model.AppUser;
import jtwitt.socket.CommunicationThread;
/**
 *
 * @author Nikos
 */
public class LoginBL {
    
    LoginDao LoginDAO; 
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
    int userFound=0;
    int userRegistered=-1;
    DataInputStream dataInStr;
    ObjectInputStream objectInStr;
    ObjectOutputStream objectOutStr;
    CommunicationThread commThread;
    AppUser newUser;
    AppUserDao appUserDAO;
    /**
     *
     * @param em
     * @aparam sock
     * @throws IOException
     */
    public LoginBL(EntityManager em, CommunicationThread commThread) throws IOException{
        LoginDAO = new LoginDao(em);
        this.commThread = commThread;
       
    }
    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void login() throws IOException, ClassNotFoundException{
      
                try{ 
                    username = (String) commThread.readObject();
                    password = (String) commThread.readObject();
                    
                    userFound = LoginDAO.login(username, password);
            
           // appUserDAO.refreshAppUserStats(tweet.getUserId().getUserId(),AppUserStats.REFRESH_TWEET);
                  
                  LoginDAO.getEm().getTransaction().commit();
                  LoginDAO.getEm().getTransaction().begin();
        }
        catch (RollbackException rex) {
            rex.printStackTrace();
            LoginDAO.getEm().getTransaction().begin();
        }
                commThread.writeInt(userFound);
        
        
    }
    
    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void signUp() throws IOException, ClassNotFoundException{
         try{ 
             
                    newUser = (AppUser)commThread.readObject();
 //                   userRegistered = LoginDAO.signUp(username,password,firstName,lastName,email);
                    userRegistered = LoginDAO.signUp(newUser);
                    
                    LoginDAO.getEm().getTransaction().commit();
                    LoginDAO.getEm().getTransaction().begin();
                    
         }catch (RollbackException rex) {
            rex.printStackTrace();
            LoginDAO.getEm().getTransaction().begin();
        }
                commThread.writeInt(userRegistered);
        
        
    }
    
    /**
     *
     * @throws IOException
     */
    public void logout () throws IOException{
        
        commThread.writeInt(1);
        System.out.println("user logged out, closing streams");
        commThread.closeAll();
        
    }
}
