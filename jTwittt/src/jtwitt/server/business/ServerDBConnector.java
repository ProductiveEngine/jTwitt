/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.business;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jtwitt.interfaces.IConnector;
import jtwitt.server.dao.AppUserDao;
import jtwitt.socket.CommunicationThread;
import jtwitt.util.Actions;


/**
 * Creates the connection to the database
 * Gets the action the server receives from the client and switches it to execute the Business Logic method
 * @author Nikos
 */
public class ServerDBConnector implements IConnector{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jTwittPU");
    public EntityManager em ;
    
    AppUserBL auBL;
    FollowerBL foBL;
    LoginBL loBL;
    TweetBL twBL;
    
    AppUserDao appUserDAO;
    
    /**
     *
     * @param commThread
     * @throws IOException
     */
    public ServerDBConnector(CommunicationThread commThread) throws IOException{
             
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        auBL = new AppUserBL(em, commThread);
        foBL = new FollowerBL(em, commThread,auBL.appUserDAO);
        loBL = new LoginBL(em, commThread);
        twBL = new TweetBL(em, commThread,auBL.appUserDAO);
    }
   
    /**
     * Switches the action the server received to execute the corresponding method
     * @param action Actions received from client
     * @return integer 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public int execute(Actions action) throws IOException, ClassNotFoundException{
        
        switch(action){

            case REFRESH_MY_PROFILE:                    
                auBL.loadMyProfile();   
                break;
            case LOGIN:                
                loBL.login();
                break;
            case LOGOUT:                
                loBL.logout();
                break;
             case REGISTER:                
                loBL.signUp();
                break;
            case SAVE_TWEET:                
                twBL.saveTweet();
                break;
            case REFRESH_TWEETS:                
                twBL.loadTweetList();                
                break;
            case ONLY_MINE:                
                twBL.loadTweetList();                
                break;
            case SEARCH_TWEETS:
                twBL.search();                
                break;
            case SEARCH_USERS:
                auBL.search();                
                break;
            case SAVE_FOLLOW:
                foBL.saveFollower();
                break;
            case LOAD_FOLLOWERS:
                foBL.loadFollowers();
                break;
            case LOAD_FOLLOWING:
                foBL.loadFollowing();
                break;
            case LOAD_FOLLOWING_PROFILES:
                auBL.loadFollowingProfiles();
                break;
            case LOAD_FOLLOWER_PROFILES:
                auBL.loadFollowerProfiles();
                break;
            case SAVE_MY_PROFILE:
                auBL.saveMyProfile();
                break;
           case DELETE_TWEET:
                twBL.deleteTweet();
                break;
           case RETWEET:
                twBL.retweet();
               break;
           case DELETE_FOLLOW:
               foBL.deleteFollow();
               break;
           case FAVORITE:
               twBL.saveFavTweet();
               break;
           case HAS_CONNECTIONS_WITH:
               foBL.hasConnectionsWith();
               break;    
           case IS_FAVORITED:
               twBL.checkFavTweet();
               break;
           case UNFAVORITE:
               twBL.deleteFavTweet();
               break;
           case ONLY_FAV:
               twBL.loadTweetList();   
               break;
        }
        return 1;
    }

}
