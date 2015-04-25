/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.interfaces;

import java.util.List;
import jtwitt.server.model.AppUser;
import jtwitt.server.model.Tweet;

/**
 *
 * @author Nikos
 */
public interface IAppUser {
    // User Profile ----------------------------------------------------
    public AppUser loadMyProfile (int userId);
    public List<AppUser> loadFollowingProfiles (int userId);
    public List<AppUser> loadFollowerProfiles (int userId);
    
    public int saveProfile (AppUser profile, byte flag);//0 new , 1 edit
    public int saveNumOfTweets(int userId , int numOfTweets);
    public int saveNumOfFollowers(int userId , int numOfFollowers);
    public int saveNumOfFollowing(int userId , int numOfFollowing);
    
    public int deleteAccount(int userId);
    public List<AppUser> searchUsers(String searchKeyWords, int ownerId ); 
}
