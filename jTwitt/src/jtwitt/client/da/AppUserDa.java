/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.client.da;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtwitt.interfaces.IAppUser;
import jtwitt.server.model.AppUser;
import jtwitt.socket.CommunicationThread;
import jtwitt.util.Actions;

/**
 * Handles all the AppUser related actions using the current Communication Thread
 * @author Nikos
 */
public class AppUserDa implements IAppUser{

    CommunicationThread commThread;
    private List<AppUser> appUserList = new ArrayList<>();
    
    /**
     * Gets a Communication Thread as an argument
     * and assigns it to the local variable 
     * @param commThread
     */
    public AppUserDa(CommunicationThread commThread) {
        this.commThread = commThread;
    }    
    
    /**
     * Notifies the Server Thread to prepare for a REFRESH_MY_PROFILE action 
     * by writing on the stream the action ordinal
     * Returns the corresponding AppUser object that will be used to have access to basic user information
     * @param userId the integer that corresponds to the current logged in user and is set after the log in action
     * @return an AppUser object
     */
    @Override
    public AppUser loadMyProfile(int userId) {
        AppUser localUser = null;
        try {                        
            commThread.writeInt(Actions.REFRESH_MY_PROFILE.ordinal());                        
            commThread.writeInt(userId);                                                      
            localUser =(AppUser)commThread.readObject();            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AppUserDa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return localUser;
    }

    /**
     * Notifies the Server Thread to prepare for a LOAD_FOLLOWING_PROFILES action 
     * by writing on the stream the action ordinal
     * Returns a list of AppUser objects that represent the users that the current user follows
     * @param userId the integer that corresponds to the current logged in user and is set after the log in action
     * @return a List of AppUser objects
     */
    @Override
    public List<AppUser> loadFollowingProfiles(int userId) {
        appUserList.clear();
        try {            
            commThread.writeInt(Actions.LOAD_FOLLOWING_PROFILES.ordinal());                        
            commThread.writeInt(userId);                                                      
            appUserList = (List<AppUser>)commThread.readObject();            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AppUserDa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appUserList;
    }

    /**
     * Notifies the Server Thread to prepare for a LOAD_FOLLOWER_PROFILES action 
     * by writing on the stream the action ordinal
     * Returns a list of AppUser objects that represent the users who follow the current user
     * @param userId the integer that corresponds to the currently logged in user
     * @return a List of AppUser objects
     */
    @Override
    public List<AppUser> loadFollowerProfiles(int userId) {
        appUserList.clear();
        try {            
            commThread.writeInt(Actions.LOAD_FOLLOWER_PROFILES.ordinal());                        
            commThread.writeInt(userId);                                                      
            appUserList = (List<AppUser>)commThread.readObject();            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AppUserDa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appUserList;
    }

    /**
     * Notifies the Server Thread to prepare for a SAVE_MY_PROFILE action 
     * by writing on the stream the action ordinal
     * Returns an integer used to determine whether the edited user profile was saved successfully
     * the byte flag specifies whether the profile is new (0), thus it will trigger an insert to the APP_USER table
     * or already exists (1), thus an update will occur
     * @param profile an AppUser object that corresponds to the currently logged in user
     * @param flag a byte to specify action
     * @return an integer to tell whether the process completed successfully (1) or not (-1)
     */
    @Override
    public int saveProfile(AppUser profile, byte flag) {
        int profileSaved = -1;
        if(flag==1)
        {
        try { 
            commThread.writeInt(Actions.SAVE_MY_PROFILE.ordinal());
            commThread.writeObject(profile);
            commThread.writeObject(flag);
            
            profileSaved = commThread.readInt();
        
        } catch (IOException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
//        else {
//            try { 
//            commThread.writeInt(Actions.REGISTER.ordinal());
//            commThread.writeObject(profile);
//            commThread.writeObject(flag);
//            
//            profileSaved = commThread.readInt();
//        
//        } catch (IOException ex) {
//            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
//        }
        return profileSaved; 
    }

    /**
     *
     * @param userId
     * @param numOfTweets
     * @return
     */
    @Override
    public int saveNumOfTweets(int userId, int numOfTweets) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param userId
     * @param numOfFollowers
     * @return
     */
    @Override
    public int saveNumOfFollowers(int userId, int numOfFollowers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param userId
     * @param numOfFollowing
     * @return
     */
    @Override
    public int saveNumOfFollowing(int userId, int numOfFollowing) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public int deleteAccount(int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Notifies the Server Thread to prepare for a SEARCH_USERS action 
     * by writing on the stream the action ordinal
     * Returns a list of AppUser objects whose attribute 'Username' contains the given string 
     * the user performs the search for
     * @param searchKeyWords a string that is used to perform the search in Username field of APP_USER table
     * @param ownerId an integer that corresponds to the user requesting the search - to be excluded from the result list
     * @return the list of the users that match the criteria
     */
    @Override
    public List<AppUser> searchUsers(String searchKeyWords, int ownerId) {
        List<AppUser> li= null; 
        try { 
            commThread.writeInt(Actions.SEARCH_USERS.ordinal());
            commThread.writeObject(searchKeyWords);
            commThread.writeInt(ownerId);
            li = (List<AppUser>) commThread.readObject();
  
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
         return li;
    }

    
    
}
