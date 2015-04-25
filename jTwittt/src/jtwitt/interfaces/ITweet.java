/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.interfaces;

import java.util.List;
import jtwitt.server.model.Tweet;

/**
 * The interface that exposes the main methods Tweet related classes implement
 * @author Nikos
 */
public interface ITweet {
     // Tweet ---------------------------------------------------------
    public List<Tweet> loadTweets(int userId,byte flag);//0 only mine, 1 show all, 2 only fav
    public int saveTweet(Tweet tweet, byte flag); //0 save , 1 edit    
    public int deleteTweet(int tweetId);
    
    public List<Tweet> loadFavTweets(int userId);
    public int saveFavTweet(int userId, int tweetId, int ownerId);     
    public int deleteFavTweet(int userId, int tweetId);
    
    public List<Tweet> searchTweets(String searchKeyWords);
    //---------------------------------------------------------------------
}
