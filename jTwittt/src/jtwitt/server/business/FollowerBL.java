/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import jtwitt.server.dao.AppUserDao;
import jtwitt.server.dao.FollowerDao;
import jtwitt.server.model.Follower;
import jtwitt.server.model.FollowerPK;
import jtwitt.socket.CommunicationThread;
import jtwitt.util.AppUserStats;

/**
 *
 * @author Nikos
 */
public class FollowerBL {
   
    FollowerDao followerDAO;
    Follower follower;
    FollowerPK followerPK;
    List<Follower> followerList = new ArrayList<>();;
    CommunicationThread commThread;
    Byte flag = -1;
    AppUserDao appUserDAO;
    
    /**
     *
     * @param em
     * @param commThread
     * @param appUserDAO
     */
    public FollowerBL(EntityManager em, CommunicationThread commThread, AppUserDao appUserDAO){
        followerDAO = new FollowerDao(em);   
        this.commThread = commThread;
        this.appUserDAO = appUserDAO;
    }
    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void saveFollower() throws IOException, ClassNotFoundException {
        
         int savedFollower = -1;
         int ownerId = -1;
         follower = (Follower)commThread.readObject();         
         flag = (byte)commThread.readObject();                  
         try{
            savedFollower = followerDAO.saveFollower(follower, flag);                         
            appUserDAO.refreshAppUserStats(follower.getFollowersPK().getFollowerUserId(),AppUserStats.REFRESH_FOLLOW);            
            appUserDAO.refreshAppUserStats(follower.getFollowersPK().getFollowingUserId(),AppUserStats.REFRESH_FOLLOW);            
            followerDAO.getEm().getTransaction().commit();
            followerDAO.getEm().getTransaction().begin();
        }
        catch (RollbackException rex) {
            followerDAO.getEm().getTransaction().begin();
            savedFollower = -1;
        }               
        commThread.writeInt(savedFollower);                  
    }
    /**
     *
     * @throws IOException
     */
    public void loadFollowing() throws IOException {
       int userId = -1;      
       followerList.clear();
       
       userId = commThread.readInt();                     
       followerList = followerDAO.loadFollowing(userId);       
       commThread.writeObject(followerList);
    }
        
    /**
     *
     * @throws IOException
     */
    public void loadFollowers() throws IOException {
       int userId = -1;      
       followerList.clear();
       
       userId = commThread.readInt();                     
       followerList = followerDAO.loadFollowers(userId);       
        commThread.writeObject(followerList);
    }
    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void deleteFollow() throws IOException, ClassNotFoundException{
        try{           
            int ownerId = -1;
            followerPK = (FollowerPK) commThread.readObject();            
            followerDAO.deleteFollower(followerPK);                                 
            appUserDAO.refreshAppUserStats(followerPK.getFollowerUserId(),AppUserStats.REFRESH_FOLLOW);
            appUserDAO.refreshAppUserStats(followerPK.getFollowingUserId(),AppUserStats.REFRESH_FOLLOW);
            followerDAO.getEm().getTransaction().commit();
            followerDAO.getEm().getTransaction().begin();
            commThread.writeInt(1);
        }
        catch (RollbackException rex) {
            followerDAO.getEm().getTransaction().begin();
            commThread.writeInt(-1);
        }
    }    
    public void searchFollowers() {
        throw new UnsupportedOperationException("Not supported yet.");
    } 
    /**
     *
     * @throws IOException
     */
    public void hasConnectionsWith() throws IOException{
           
        List li = new ArrayList();
        int owner = -1,other = -1;
        owner = commThread.readInt();
        other = commThread.readInt();

        li = followerDAO.hasConnectionsWith(owner,other);                                 
        commThread.writeObject(li);           
    }
}
