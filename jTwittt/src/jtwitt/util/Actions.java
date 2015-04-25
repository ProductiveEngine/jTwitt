/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.util;

/**
 * Enumeration of all the possible action the client is able to perform.
 * The client sends the action ordinal to the server before sending any data, so that the server
 * is prepared to accept and handle the data in the expected way
 * @author Nikos
 */
public enum Actions {
    
    LOGIN(1),REFRESH_TWEETS(2),SAVE_TWEET(3), REFRESH_MY_PROFILE(4), ONLY_MINE(5),DELETE_TWEET(6),SEARCH_TWEETS(7),LOGOUT(8),
    SAVE_FOLLOW(9),LOAD_FOLLOWERS(10),LOAD_FOLLOWING(11),LOAD_FOLLOWING_PROFILES(12),LOAD_FOLLOWER_PROFILES(13),
    SAVE_MY_PROFILE(14), RETWEET(15), FAVORITE(16),DELETE_FOLLOW(17),SEARCH_USERS(18), EDIT_TWEET(19),REPLY(20), LOAD_FAVORITES(21),
    HAS_CONNECTIONS_WITH(22), IS_FAVORITED(23), UNFAVORITE(24),ONLY_FAV(25),REGISTER(26);
    
    private int value;
    
    private Actions(int value){
        this.value = value;
    }    
    
}
