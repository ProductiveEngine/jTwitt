/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.client.da;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtwitt.interfaces.ILogin;
import jtwitt.server.model.AppUser;
import jtwitt.socket.CommunicationThread;
import jtwitt.util.Actions;

/**
 * Handles all the Login related actions using the current Communication Thread
 * @author Nikos
 */
public class LoginDa implements ILogin, Serializable{
    
    CommunicationThread commThread;
    AppUser appUser;
    
    /**
     * Gets the current CommunicationThread and assigns it to local variable
     * @param commThread CommunicationThread
     */
    public LoginDa(CommunicationThread commThread){
        this.commThread = commThread;
    }
    
    /**
     * Notifies the Server Thread to prepare for a LOGIN action by writing on the stream the action ordinal
     * Gets the LOGIN action result
     * @param username string 
     * @param password string
     * @return integer to indicate successful (1) or not (-1) login
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public int login(String username, String password) throws IOException, ClassNotFoundException {
        int foundUser=0;
        
        try {
            commThread.writeInt(Actions.LOGIN.ordinal());
            commThread.writeObject(username);
            commThread.writeObject(password);
            
            foundUser = commThread.readInt();
            
        
        }catch(Exception ex){}
                
        return foundUser;
    }

    /**
     * Notifies the Server Thread to prepare for a LOGOUT action by writing on the stream the action ordinal
     * @param userId
     * @return
     */
    @Override
    public int logout(int userId) {
        
        int loggedOut =-1; 
        
         try {
            commThread.writeInt(Actions.LOGOUT.ordinal());
         
           loggedOut = commThread.readInt();
            
        
        }catch(Exception ex){}
                
        return loggedOut;
        
    }



    /**
     * Notifies the Server Thread to prepare for a REGISTER action by writing on the stream the action ordinal
     * Sends the AppUser object to be saved in the database and gets the save result
     * @param newUser AppUser object created by Register
     * @return integer to indicate success (1) or failure (-1)
     */
    @Override
    public int signUp(AppUser newUser) {
        int  profileSaved =-1;

        try { 
                commThread.writeInt(Actions.REGISTER.ordinal());

                commThread.writeObject(newUser);
            
                profileSaved = commThread.readInt();
        
        } catch (IOException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return profileSaved; 
    }

    @Override
    public int login(String email, String password, int chooser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
