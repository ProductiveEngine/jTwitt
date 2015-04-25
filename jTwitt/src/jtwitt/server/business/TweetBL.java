/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.business;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import jtwitt.interfaces.ITweet;
import jtwitt.server.dao.AppUserDao;
import jtwitt.server.dao.TweetDao;
import jtwitt.server.model.Tweet;
import jtwitt.socket.CommunicationThread;
import jtwitt.util.AppUserStats;


/**
 *
 * @author Nikos
 */
public class TweetBL{
    
    TweetDao tweetDAO;
    Tweet tweet;
    Byte flag;
    List<Tweet> tweetList = new ArrayList<>();
    
    ObjectInputStream objectInStr=null;
    ObjectOutputStream objectOutStr=null;
    CommunicationThread commThread;
    AppUserDao appUserDAO;
    
    public TweetBL(EntityManager em, CommunicationThread commThread, AppUserDao appUserDAO){
        tweetDAO = new TweetDao(em); 
        this.commThread = commThread;
        this.appUserDAO = appUserDAO;
    }
    
    public void saveTweet() throws IOException, ClassNotFoundException{
        
        int savedTweet = -1;
        
        tweet = (Tweet)commThread.readObject();
        flag = (byte)commThread.readObject();
                        
        try{            
            savedTweet = tweetDAO.saveTweet(tweet, flag);              
            appUserDAO.refreshAppUserStats(tweet.getUserId().getUserId(),AppUserStats.REFRESH_TWEET);
            tweetDAO.getEm().getTransaction().commit();
            tweetDAO.getEm().getTransaction().begin();
        }
        catch (RollbackException rex) {
            rex.printStackTrace();
            tweetDAO.getEm().getTransaction().begin();
        }
        System.out.println("read: "+savedTweet);
        
        commThread.writeInt(savedTweet);
       
    }
   public void deleteTweet() throws IOException{
       
       int deletedTweetId = commThread.readInt();       
       int deletedTweetUserId = 0;
       
       try{            
            deletedTweetUserId = tweetDAO.deleteTweet(deletedTweetId);             
            appUserDAO.refreshAppUserStats(deletedTweetUserId,AppUserStats.REFRESH_TWEET);
            tweetDAO.getEm().getTransaction().commit();
            tweetDAO.getEm().getTransaction().begin();
            commThread.writeInt(1);
        }
        catch (RollbackException rex) {
            rex.printStackTrace();
            tweetDAO.getEm().getTransaction().begin();
            commThread.writeInt(-1);
        }
   }
   public void deleteFavTweet() throws IOException{
     int unfavorited = -1;
     int tweetId=-1;
     int userId=-1;
     
     userId = commThread.readInt();
     tweetId = commThread.readInt();     
     try{ 
        unfavorited = tweetDAO.deleteFavTweet(userId, tweetId);
        tweetDAO.getEm().getTransaction().commit();
        tweetDAO.getEm().getTransaction().begin();
        commThread.writeInt(unfavorited);
     }
     catch (RollbackException rex) {
            rex.printStackTrace();
            tweetDAO.getEm().getTransaction().begin();            
            commThread.writeInt(-1);
     }
                   
   }
   public void saveFavTweet() throws IOException{
       int ownerId=-1;
       int userId=-1;
       int tweetId=-1;
       int favSaved =-1;
       
       ownerId = commThread.readInt();
       tweetId  = commThread.readInt();
       userId = commThread.readInt();
       try{ 
        favSaved = tweetDAO.saveFavTweet(userId, tweetId, ownerId);
        tweetDAO.getEm().getTransaction().commit();
        tweetDAO.getEm().getTransaction().begin();
       }
       catch (RollbackException rex) {
            rex.printStackTrace();
            tweetDAO.getEm().getTransaction().begin();            
        }
       commThread.writeInt(favSaved);              
   }
                             
   public void loadTweetList() throws IOException, ClassNotFoundException{
       int userId = -1;
       byte flag = -1;
       tweetList.clear();
       
       userId = commThread.readInt();
       flag = (byte)commThread.readObject();
       System.out.println("after receiving "+flag+userId);
       
       tweetList = tweetDAO.loadTweets(userId,flag);
       
        commThread.writeObject(tweetList);
   }
   
   public void search() throws IOException, ClassNotFoundException{
      String keyWord;
      List sList;
      
      keyWord = (String) commThread.readObject();
      
      sList = tweetDAO.searchTweets(keyWord);
      commThread.writeObject(sList);         
   }
   
   public void retweet() throws IOException{
       int retweeted=-1;
       int userId =-1;
       int tweetId=-1;
       
       tweetId=commThread.readInt();
       userId= commThread.readInt();
       
       try{                      
           retweeted = tweetDAO.retweet(userId,tweetId);
           tweetDAO.getEm().getTransaction().commit();
           tweetDAO.getEm().getTransaction().begin();              
           commThread.writeInt(retweeted);
       }
       catch (RollbackException rex) {
            rex.printStackTrace();
            tweetDAO.getEm().getTransaction().begin();
            commThread.writeInt(-1);
        }              
   }
   
   public void checkFavTweet() throws IOException
   {
       int tweetId=-1;
       int userId=-1;
       int isFavorited=-1;
       
       userId = commThread.readInt();
       tweetId = commThread.readInt();                
       isFavorited = tweetDAO.checkFavTweet(tweetId, userId);                         
       commThread.writeInt(isFavorited);
       commThread.writeInt(tweetDAO.checkReTweet(tweetId, userId));
     }    

   
}
