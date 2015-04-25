/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.client.da;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtwitt.interfaces.ITweet;
import jtwitt.server.model.Tweet;
import jtwitt.socket.CommunicationThread;
import jtwitt.util.Actions;


/**
 * Handles all the Tweet related actions using the current Communication Thread
 * @author Nikos
 */
public class TweetDa implements ITweet{

    private int isRetweet = -1; 
    private List<Tweet> tweetList = new ArrayList<>();
    CommunicationThread commThread;
    DataOutputStream dataOutStr=null;

    /**
     * Gets the current CommunicationThread and assigns it to local variable
     * @param commThread
     */
    public TweetDa(CommunicationThread commThread){        
        this.commThread = commThread;       
    }

    /**
     * Notifies the Server Thread to prepare for a ONLY_MINE or REFRESH_TWEETS or ONLY_FAV action 
     * by writing on the stream the action ordinal according to what the flag value is
     * Returns a List of Tweet objects
     * @param userId integer the current user id
     * @param flag byte to specify action
     * @return List<Tweet> a list of Tweet objects
     */
    @Override
    public List<Tweet> loadTweets(int userId, byte flag) {
        tweetList.clear();
        try {
            if(flag == 0){            
                commThread.writeInt(Actions.ONLY_MINE.ordinal());
            }
            else if(flag == 1){
                commThread.writeInt(Actions.REFRESH_TWEETS.ordinal());
            }    
            else if(flag == 2){
                commThread.writeInt(Actions.ONLY_FAV.ordinal());
            }
            commThread.writeInt(userId);
            commThread.writeObject(flag);                        
            
            tweetList = (List<Tweet>) commThread.readObject();                  
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
                                
        return tweetList;
    }

    /**
     * Notifies the Server Thread to prepare for a SAVE_TWEET action by writing on the stream the action ordinal
     * Sends the Tweet object and the flag to specify action and gets the result of the save action 
     * @param tweet Tweet object 
     * @param flag byte to specify whether new (0) or edit (1)
     * @return integer to indicate successful (1) or not (-1) save
     */
    @Override
    public int saveTweet(Tweet tweet, byte flag) {
       int tweetSaved=-1;
        
        try { 
            //System.out.println("tweet"+tweet.getTweet());
            commThread.writeInt(Actions.SAVE_TWEET.ordinal());
            commThread.writeObject(tweet);
            commThread.writeObject(flag);
            
            tweetSaved = commThread.readInt();
        
        } catch (IOException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tweetSaved; 
    }

    /**
     * Notifies the Server Thread to prepare for a DELETE_TWEET action by writing on the stream the action ordinal
     * Sends the tweetId to delete
     * @param tweetId integer to specify the tweet to be deleted
     * @return integer  to indicate successful (1) or not (-1) delete
     */
    @Override
    public int deleteTweet(int tweetId) {
        int tweetDeleted = -1;
        
        try { 
            commThread.writeInt(Actions.DELETE_TWEET.ordinal());
            commThread.writeInt(tweetId);           
            tweetDeleted = commThread.readInt();
        
        } catch (IOException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tweetDeleted; 
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public List<Tweet> loadFavTweets(int userId) {
    throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Notifies the Server Thread to prepare for a FAVORITE action by writing on the stream the action ordinal
     * @param userId integer the currently log in user that performs the favorite action
     * @param tweetId integer the tweetId to favorite
     * @param ownerId integer the userId who is the owner of the tweet
     * @return integer  to indicate successful (1) or not (-1) favorite
     */
    @Override
    public int saveFavTweet(int userId, int tweetId, int ownerId) {
        int favSaved = -1;
        try {
                commThread.writeInt(Actions.FAVORITE.ordinal());
                commThread.writeInt(ownerId);
                commThread.writeInt(tweetId);
                commThread.writeInt(userId);
                favSaved = commThread.readInt();
                
                
                
        } catch (IOException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("fav"+favSaved);
        return favSaved;
    }
    
    /**
     * Notifies the Server Thread to prepare for a UNFAVORITE action by writing on the stream the action ordinal
     * 
     * @param userId integer the currently log in user that performs the unfavorite action
     * @param tweetId integer the tweetId to unfavorite
     * @return integer  to indicate successful (1) or not (-1) unfavorite
     */
    @Override
    public int deleteFavTweet(int userId, int tweetId){
          
        int unfavorited = -1;
        
        try {         
            commThread.writeInt(Actions.UNFAVORITE.ordinal());
            commThread.writeInt(userId);
            commThread.writeInt(tweetId);
            unfavorited = commThread.readInt();
        } catch (IOException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
     return unfavorited;
    }

    /**
     * Notifies the Server Thread to prepare for a SEARCH_TWEETS action by writing on the stream the action ordinal
     * Sends the keywords 
     * @param searchKeyWords String the keywords the user searches for
     * @return List<Tweet> a list of the tweet objects that match the keywords
     */
    @Override
    public List<Tweet> searchTweets(String searchKeyWords) {
        List<Tweet> li= null; 
        try { 
            commThread.writeInt(Actions.SEARCH_TWEETS.ordinal());
            commThread.writeObject(searchKeyWords);
            li = (List<Tweet>) commThread.readObject();
  
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(TweetDa.class.getName()).log(Level.SEVERE, null, ex);
        }
         return li;
   
    }
    
    /**
     * Notifies the Server Thread to prepare for a RETWEET action by writing on the stream the action ordinal
     * 
     * @param tweetId integer the tweetId to retweet
     * @param userId integer the currently log in user that performs the retweet
     * @return integer  to indicate successful (1) or not (-1) retweet
     * @throws IOException
     */
    public int retweet(int tweetId, int userId) throws IOException{
        int retweeted=-1;
        commThread.writeInt(Actions.RETWEET.ordinal());
        commThread.writeInt(tweetId);
        commThread.writeInt(userId);
        retweeted = commThread.readInt();
        
        return retweeted;
    }
    
    /**
     * Notifies the Server Thread to prepare for a IS_FAVORITED action by writing on the stream the action ordinal
     * 
     * @param tweetId integer the tweetId to check
     * @param userId integer the currently log in user
     * @return integer  to indicate if the tweet  is favorited by the current user (1) or not (-1) =
     * @throws IOException
     */
    public int isFavorited(int tweetId, int userId) throws IOException
    {
        int isFavorited=-1;
        
        commThread.writeInt(Actions.IS_FAVORITED.ordinal());
        commThread.writeInt(userId);
        commThread.writeInt(tweetId);
        
        isFavorited = commThread.readInt();
        isRetweet = commThread.readInt();
        return isFavorited;
    
    }

    /**
     * Returns isRetweet from isFavorited function
     * @return integer that indicates whether a Tweet is a retweet (1) or not (-1)
     */
    public int getIsRetweet() {
        return isRetweet;
    }
    
}
