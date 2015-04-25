/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jtwitt.interfaces.IAppUser;
import jtwitt.server.model.AppUser;
import jtwitt.util.AppUserStats;

/**
 * Handles the database transactions concerning APP_USER table /AppUser object
 * @author Nikos
 */
public class AppUserDao implements IAppUser, Serializable{
    
    protected EntityManager em;
    public AppUserDao(EntityManager em){
        this.em = em;
    }
       
    /**
     * Returns an AppUser object that has the specified user id
     * @param userId integer
     * @return AppUser
     */
    @Override
    public AppUser loadMyProfile(int userId) {
      
         AppUser app = em.find(AppUser.class,userId);    
         em.refresh(app);
         return app;       
    }

    /**
     * Returns a list of AppUser objects that represent the users the currently logged in user follows
     * @param userId integer
     * @return List<AppUser>
     */
    @Override
    public List<AppUser> loadFollowingProfiles(int userId) {
        
        Query query =  em.createNamedQuery("AppUser.findByFollowing");  
        query.setParameter("userId", userId);    
        return query.getResultList();
    }
    
    /**
     * Returns a list of AppUser objects that represent the users that follow the currently logged in user
     * @param userId integer
     * @return List<AppUser>
     */
    @Override
    public List<AppUser> loadFollowerProfiles(int userId) {
        
        Query query =  em.createNamedQuery("AppUser.findByFollower");  
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    /**
     * Used to save the edited or newly created AppUser 
     * @param profile  AppUser 
     * @param flag byte 0 for new profile on register 1 for edit
     * @return
     */
    @Override
    public int saveProfile(AppUser profile, byte flag) {
                
        if(flag == 0){ 
            em.persist(profile); 
            return 1;
        } //new
        else if(flag == 1){ //edit
            
            AppUser editUser = em.find(AppUser.class,profile.getUserId());
            
            editUser.setEmail(profile.getEmail());
            editUser.setFirstName(profile.getFirstName());
            editUser.setLastName(profile.getLastName());
            editUser.setUsername(profile.getUsername());
            editUser.setPassword(profile.getPassword());
            em.persist(editUser);
            
            return 1;
        }
        else{
            return -1;
        }        
    }

    /**
     * When a user tweets this method is called to update the user profile number of tweets
     * @param userId integer
     * @param numOfTweets integer
     * @return 1
     */
    @Override
    public int saveNumOfTweets(int userId, int numOfTweets) {
        
        AppUser editUser = em.find(AppUser.class,loadMyProfile(userId));        
        editUser.setNumOfTweets(numOfTweets);        
        return 1;
    }

    /**
     * When a user is being followed this method is called to update the followed user number of followers
     * @param userId integer
     * @param numOfFollowers integer
     * @return 1
     */
    @Override
    public int saveNumOfFollowers(int userId, int numOfFollowers) {
        
        AppUser editUser = em.find(AppUser.class,loadMyProfile(userId));        
        editUser.setNumOfTweets(numOfFollowers);        
        return 1;
    }

    /**
     * When a user is follows another user this method is called to update the followed user number of followers
     * @param userId integer
     * @param userId integer
     * @param numOfFollowing integer
     * @return 1
     */
    @Override
    public int saveNumOfFollowing(int userId, int numOfFollowing) {
        
        AppUser editUser = em.find(AppUser.class,loadMyProfile(userId));        
        editUser.setNumOfTweets(numOfFollowing);        
        return 1;
    }

    /**
     * Used to delete an AppUser object 
     * @param userId
     * @return
     */
    @Override
    public int deleteAccount(int userId) {
        
        em.remove(loadMyProfile(userId));
        return 1;
    }

    /**
     * Used to retrieve the specified AppUser object and refresh the MainClient
     * @param userId integer
     * @param action Action
     * @return 1
     */
    public int refreshAppUserStats(int userId, AppUserStats action){
        
        Query query = null;
                  
        query = em.createNamedQuery("AppUser.findByUserId");      
        query.setParameter("userId",userId);           
        
        AppUser appUser = (AppUser)query.getResultList().get(0);
        
        switch(action){
            case REFRESH_FOLLOW :
                    query = em.createNamedQuery("Follower.countFollowers");
                    query.setParameter("userId",userId);
                    appUser.setNumOfFollowers(((Number)query.getSingleResult()).intValue());
               
                    query = em.createNamedQuery("Follower.countFollowing");
                    query.setParameter("userId",userId);                    
                    appUser.setNumOfFollowing(((Number)query.getSingleResult()).intValue());
                break;
                case REFRESH_TWEET :
                    query = em.createNamedQuery("Tweet.countTweets");
                    query.setParameter("userId",userId);                        
                    appUser.setNumOfTweets(((Number)query.getSingleResult()).intValue());
                break;                    
        }            
        em.persist(appUser);
        return 1;
    }     

    /**
     *
     * @return
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * Assigns the EntityManager to the local variable
     * @param em
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * Retrieves an AppUser List whose username corresponds to the searchKeyWords
     * The current user is excepted
     * @param searchKeyWords string
     * @param ownerId integer
     * @return
     */
    @Override
    public List<AppUser> searchUsers(String searchKeyWords, int ownerId) {
        List<AppUser> li;
        String finalSearchWord = "%"+searchKeyWords+"%";        
        Query q = em.createNamedQuery("AppUser.searchFollow");
        q.setParameter("sUser", finalSearchWord);      
        q.setParameter("userId",ownerId);
        li = q.getResultList();       
        return li;
    }
    
    
}
