/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jtwitt.interfaces.ITweet;
import jtwitt.server.model.FavTweet;
import jtwitt.server.model.FavTweetPK;
import jtwitt.server.model.ReTweet;
import jtwitt.server.model.ReTweetPK;
import jtwitt.server.model.Tweet;

/**
 *
 * @author Nikos
 */
public class TweetDao implements ITweet, Serializable{
   
    protected EntityManager em;
    public TweetDao(EntityManager em ){
        this.em = em;
    }
    @Override
    public List<Tweet> loadTweets(int userId, byte flag) {
       
        List<Tweet> result = null;
        Query query = null;
        if(flag == 0){            
            query = em.createNamedQuery("Tweet.findByUserId");      
            query.setParameter("userId",userId);           
        }
        else if(flag == 1){   
              query = em.createNativeQuery(" SELECT t.TWEET_ID, t.CREATE_DATE, t.MODIFY_DATE, t.REPLY_ID, t.TWEET, t.USER_ID " +
                                           " FROM TWEET t" +
                                           " WHERE t.USER_ID IN ( ?1,(" +
                                            "    SELECT f.FOLLOWING_USER_ID" +
                                            "    FROM APP_USER a" +
                                            "    JOIN FOLLOWER f ON f.FOLLOWER_USER_ID = a.USER_ID AND a.USER_ID = ?1" +
                                                ")" +
                                             ")" +
                                            " ORDER BY t.CREATE_DATE DESC"
                                           ,Tweet.class);   
              query.setParameter(1,userId);           
        }      
        else if(flag == 2){           
            query = em.createNamedQuery("FavTweet.OnlyFav"); 
            query.setParameter("userId",userId);                   
        }
        result = (List<Tweet>)query.getResultList();                 
        return result; 
    }

    @Override
    public int saveTweet(Tweet tweet, byte flag) {
       
   //     tweet = em.merge(tweet);
        
       if(flag == 0){ 
           
           em.persist(tweet);            
           System.out.println("inside Dao savetweet");
           return 1;
       
       } //new
       else if(flag == 1){ //edit      

            Tweet editTweet = em.find(Tweet.class,tweet.getTweetId());            
            editTweet.setTweet(tweet.getTweet());       
            em.persist(editTweet);
            return 1;
        }
        else{
            return -1;
        }
    }

    @Override
    public int deleteTweet(int tweetId) {
        
        Tweet deleteTweet = em.find(Tweet.class,tweetId); 
        int userId = deleteTweet.getUserId().getUserId();
        em.remove(deleteTweet);
        return userId;
    }

    @Override
    public List<Tweet> loadFavTweets(int userId) {
        Query query = em.createNamedQuery("FavTweet.findByUserId");
        query.setParameter("userId",userId);      
        return query.getResultList();
    }

    @Override
    public int saveFavTweet(int userId, int tweetId, int ownerId) {        

        FavTweetPK favPK = new FavTweetPK(userId, tweetId);
        FavTweet fav = new FavTweet(favPK,ownerId);
        
        em.persist(fav);
        
        return 1;
    }

    @Override
    public int deleteFavTweet(int userId, int tweetId) {
       
        
        FavTweetPK fPK = new FavTweetPK();
        fPK.setTweetId(tweetId);
        fPK.setUserId(userId);
 
        FavTweet deleteFavorite = em.find(FavTweet.class,fPK);                      
        em.remove(deleteFavorite);

        return 1;
    }

    @Override
    public List<Tweet> searchTweets(String searchKeyWords) {
        List<Tweet> li;
        String finalSearchWord = "%"+searchKeyWords+"%";        
        Query q = em.createNamedQuery("Tweet.searchTweets");
        q.setParameter("sTweet", finalSearchWord);        
        li = q.getResultList();       
        return li;
    }
    
    public int retweet(int tweetId, int userId){      
       
       ReTweet rt = new ReTweet(tweetId, userId);
       em.persist(rt);
       return  1;
    }

    public int checkFavTweet(int tweetId , int userId)
    {        
        FavTweet favTweet = em.find(FavTweet.class,new FavTweetPK(userId, tweetId));               
        return favTweet == null?-1:1;        
    }
    public int checkReTweet(int tweetId , int userId)
    {        
        ReTweet reTweet = em.find(ReTweet.class,new ReTweetPK(userId, tweetId));               
        return reTweet == null?-1:1;        
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
}
