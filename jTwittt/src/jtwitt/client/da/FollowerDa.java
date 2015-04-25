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
import jtwitt.interfaces.IFollower;
import jtwitt.server.model.Follower;
import jtwitt.server.model.FollowerPK;
import jtwitt.socket.CommunicationThread;
import jtwitt.util.Actions;

/**
 * Handles all the Follower related actions using the current Communication Thread
 * @author Nikos
 */
public class FollowerDa implements IFollower{

    CommunicationThread commThread;
    private List<Follower> followerList = new ArrayList<>();
    
    /**
     * Gets the current CommunicationThread and assigns it to local variable
     * @param commThread
     */
    public FollowerDa(CommunicationThread commThread) {
        this.commThread = commThread;
    }    
    
    /**
     * Notifies the Server Thread to prepare for a SAVE_FOLLOW action by writing on the stream the action ordinal
     * Sends the Follower object and the byte to specify 
     * whether it is a new follow or an edit 
     * @param follow Follower object to be handled 
     * @param flag byte to specify handling (0) persist new follow (1) edit follow
     * @return integer to indicate a successful save or edit result (1) or not (-1)
     */
    @Override
    public int saveFollower(Follower follow, byte flag) {
        int followerSaved = -1;
        
        try{
            commThread.writeInt(Actions.SAVE_FOLLOW.ordinal());
            commThread.writeObject(follow);            
            commThread.writeObject(flag);            
            followerSaved = commThread.readInt();
        }catch (IOException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return followerSaved;
    }

    /**
     *
     * @param searchKeyWords
     * @return
     */
    @Override
    public List<Follower> searchFollowers(String searchKeyWords) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Notifies the Server Thread to prepare for a LOAD_FOLLOWERS action 
     * by writing on the stream the action ordinal 
     * Sends the Follower object and the byte to specify 
     * Returns a list of Follower objects that represent the users who follow the current user
     * @param follower
     * @return
     */
    @Override
    public List<Follower> loadFollowers(int follower) {
        followerList.clear();
        try {                      
            commThread.writeInt(Actions.LOAD_FOLLOWERS.ordinal());                        
            commThread.writeInt(follower);                                   
            
            followerList = (List<Follower>) commThread.readObject();                  
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }                                
        return followerList;
    }

    /**
     * Notifies the Server Thread to prepare for a LOAD_FOLLOWING action by writing on the stream the action ordinal
     * Returns a list of Follower objects that represent the users followed by the current user
     * @param following integer the userId of the currently logged in user
     * @return List of
     */
    @Override
    public List<Follower> loadFollowing(int following) {
        followerList.clear();
        try {                      
            commThread.writeInt(Actions.LOAD_FOLLOWING.ordinal());                        
            commThread.writeInt(following);                                   
            
            followerList = (List<Follower>) commThread.readObject();                  
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }                                
        return followerList;
    }

    /**
     * Notifies the Server Thread to prepare for a DELETE_FOLLOW action by writing on the stream the action ordinal
     * and reads the result from the stream
     * @param fPK
     * @return integer the result of DELETE_FOLLOW action, successful (1) or nor (-1)
     */
    @Override
    public int deleteFollower(FollowerPK fPK) {
        int followDeleted = -1;
        
        try { 
            commThread.writeInt(Actions.DELETE_FOLLOW.ordinal());
            commThread.writeObject(fPK);                    
            followDeleted = commThread.readInt();
        
        } catch (IOException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return followDeleted; 
    }

    /**
     * Notifies the Server Thread to prepare for a HAS_CONNECTIONS_WITH action by writing on the stream the action ordinal
     * Gets the list of the current users connections
     * @param owner integer the current userId
     * @param other integer 
     * @return list of integers that represent the userIds of current user connections 
     */
    @Override
    public List hasConnectionsWith(int owner, int other) {
        List li = new ArrayList();
        try { 
            commThread.writeInt(Actions.HAS_CONNECTIONS_WITH.ordinal());
            commThread.writeInt(owner);
            commThread.writeInt(other);           
            li = (List) commThread.readObject();//proto stin lista ean einai folloer , k deutero ean einai following
               
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return li; 
    }
    
}
