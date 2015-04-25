package jtwitt.socket;

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtwitt.client.da.AppUserDa;
import jtwitt.client.da.FollowerDa;
import jtwitt.client.da.LoginDa;
import jtwitt.client.da.TweetDa;
import jtwitt.client.gui.EditFollow;
import jtwitt.client.gui.EditUserData;
import jtwitt.client.gui.FollowDetails;
import jtwitt.client.gui.Login;
import jtwitt.client.gui.MainClient;
import jtwitt.client.gui.Register;
import jtwitt.client.gui.TweetDetails;
import jtwitt.server.model.AppUser;
import jtwitt.server.model.FavTweetPK;
import jtwitt.server.model.Follower;
import jtwitt.server.model.Tweet;
import jtwitt.util.Actions;

/**
 * The Thread that handles the client side
 * @author M
 */
public class ClientThread extends Thread {

    boolean checkConn = true;
    
    private Socket sock;
    private Actions action;
    
    private AppUserDa appUserDa;
    private AppUser appUser;
    
    private FollowerDa followerDa;
    private Follower follower;
    
    private FavTweetPK fav;
    
    private TweetDa tweetDa;
    private Tweet tweet;
    
    private LoginDa loginDa;
        
    private MainClient gui;
    private TweetDetails tweetDetails;
    private EditFollow followEdit;
    private EditUserData accountDetails;
    private FollowDetails followDetails;
    private Login login;
    private Register register;
    
    private String username;
    private String password;

    private List<Tweet> tweetList = new ArrayList<>();  
    private List<AppUser> followList = new ArrayList<>();
    private boolean allTweets=false;
    private boolean refresh_my_profile = false,refresh_tweets = false,refresh_my_tweets = false;
      
    CommunicationThread commThread;
            
    /**
     * 
     * @param commThread CommunicationThread
     * @param gui MainClient
     * @throws IOException
     */
    public ClientThread(CommunicationThread commThread, MainClient gui) throws IOException{
        
       this.gui = gui;
       this.commThread = commThread;
       
       appUserDa = new AppUserDa(commThread);
       loginDa = new LoginDa(commThread);
       tweetDa = new TweetDa(commThread);
       followerDa = new FollowerDa(commThread);
   }
    @Override
    public void run(){  
        while( checkConn== true)
        {         
            try {
                talk();    
                 System.out.println("commThread:"+commThread.getState().toString() + " cThread:" + this.getState().toString());
                checkConn=checkConn(commThread.getSock());
            }catch (InterruptedException | IOException | ClassNotFoundException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        try {
             commThread.closeAll();
             System.out.println("tidying up - reseting streams");

        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Switches the action the client set and acts accordingly
     * 
     * @throws InterruptedException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public synchronized void talk() throws InterruptedException, IOException, ClassNotFoundException
    {
        appUser = null;        
        wait();        
        do{
            switch(action){
                case REFRESH_MY_PROFILE:
                    System.out.println("user "+gui.user);
                    appUser = appUserDa.loadMyProfile(gui.user);
                    System.out.println("Inside appUser "+appUser.getFirstName()+" Num of tweets "+appUser.getNumOfTweets());                
                    
                    gui.refreshAppUser(appUser);
                    refreshorREFRESH_MY_PROFILE();             
                    break;
                    
                case LOGIN:
                    System.out.println("LOGIN");
                    username=login.username;
                    password=login.password;
                    int result=loginDa.login(username,password);     
                    
                    login.getLoginResult(result);                    
                    if(result >0)
                    {//refresh user data
                    appUser = appUserDa.loadMyProfile(gui.user);
                    System.out.println("Inside appUser "+appUser.getFirstName()+" Num of tweets "+appUser.getNumOfTweets());                
                    gui.refreshAppUser(appUser);
                    refreshorREFRESH_MY_PROFILE();    
                    }
                    break;
                    
                case LOGOUT:
                    System.out.println("LOGOUT");
                    int loggedOut =-1;
//                    gui.resetClient();
////                    sock.close();
//                    loggedOut = loginDa.logout(gui.user);
//                    gui.resetClient(loggedOut);
                    break;
                    
                case REGISTER:
                    System.out.print("REGISTER");
                    this.appUser = register.appUser;
                    int registered= -1;
                   registered = loginDa.signUp(appUser);
//                    registered = appUserDa.saveProfile(appUser, (byte)0);
                    register.accountSaved(registered);
                    break;
                    
                case SAVE_TWEET:
                     System.out.print("SAVE_TWEET");
                     this.tweet = gui.tweet;            
                     int savedTweet;
                     savedTweet = tweetDa.saveTweet(tweet, (byte)0);
                     
                     gui.getSaveResult(savedTweet);                                          
                     refreshorSAVE_TWEET();                                                             
                    break;
                    
                case REFRESH_TWEETS:
                     System.out.println("REFRESH_TWEETS");
                     allTweets = true;
                     tweetList = tweetDa.loadTweets(gui.user,(byte)1); 
                     refresh_tweets = false;  
                     
                     gui.tweetList = tweetList;
                     gui.refreshTweetList();
                     gui.unlockControls();
                     
                    break;        
                    
                case ONLY_MINE:
                     System.out.println("ONLY_MINE");      
                     allTweets = false;
                     tweetList = tweetDa.loadTweets(gui.user,(byte)0); 
                     refresh_my_tweets = false;
                     
                     gui.tweetList = tweetList;
                     gui.refreshTweetList();
                     gui.unlockControls();

                    break;                    

               case SEARCH_TWEETS:                         
                     tweetList.clear();
                     tweetList = tweetDa.searchTweets(gui.searchText);
                     
                     gui.tweetList = tweetList;
                     gui.searchTweetResults();
                     gui.unlockControls();                 
                    break;
                   
               case SEARCH_USERS:
                    followList.clear();
                    followList = appUserDa.searchUsers(gui.searchText,gui.user); 
                    
                    gui.followList = followList;
                    gui.searchUsersResults();
                    gui.unlockControls();                
                   break;
                   
               case DELETE_TWEET:
                   int delete = -1;
                   this.tweet = tweetDetails.tweet;
                   System.out.println("tweet to delete:"+tweet.getTweetId()+" ");
                   delete = tweetDa.deleteTweet(tweet.getTweetId());
                   
                   tweetDetails.deletedOk(delete);                   
                   tweetDetails.unlockControls();
                   break;
                   
                case SAVE_FOLLOW:

                    int saved = -1;
                    this.follower = followDetails.follow;
                    saved = followerDa.saveFollower(follower,(byte)0);
                    if(saved == -1){
                        followDetails.jLabelError.setText("Follow relationship was not saved!");
                    }                    
                    followDetails.unlockControls();

                    break;
                    
                case LOAD_FOLLOWERS:

                    gui.unlockControls();

                    break;
                    
                case LOAD_FOLLOWING:

                    gui.unlockControls();

                    break;
                case LOAD_FOLLOWING_PROFILES:
                    followEdit.lockControls();
                    followList.clear();
                    followList = appUserDa.loadFollowingProfiles(followEdit.getAppUser().getUserId());
                    followEdit.setFollowList(followList);
                    followEdit.fillList();
                    followEdit.unlockControls();
                    break;
                    
                case LOAD_FOLLOWER_PROFILES:
                    followList.clear();
                    followList = appUserDa.loadFollowerProfiles(followEdit.getAppUser().getUserId());
                    followEdit.setFollowList(followList);
                    followEdit.fillList();
                    followEdit.unlockControls();                    
                    break;
                    
                case SAVE_MY_PROFILE:
                    this.appUser = accountDetails.appUser;
                    byte flag=1;
                    int accountSaved = appUserDa.saveProfile(appUser, flag);
                    accountDetails.accountSaved(accountSaved);
                    gui.unlockControls();                    
                    break;
                    
                case RETWEET:
                    System.out.print("RETWEET");
                    this.tweet = tweetDetails.tweet; 
                    this.appUser= tweetDetails.user;
                    int retweeted;
                    retweeted = tweetDa.retweet(tweet.getTweetId(),appUser.getUserId());
                    tweetDetails.retweeted2(retweeted);    
                    tweetDetails.unlockControls();
                    break;
                    
                case DELETE_FOLLOW:
                    if(followEdit != null){
                        int deleted;
                        deleted = followerDa.deleteFollower(followEdit.getDeletedFollowPK());  
                        if(deleted == 1){
                            followEdit.jLabelError.setText("Deletion successfull");
                            followEdit.jLabelError.setForeground(Color.BLACK);
                            followEdit.refreshList();
                        }
                        else{
                            followEdit.jLabelError.setText("Error during deletion!");
                            followEdit.jLabelError.setForeground(Color.red);
                        }                        
                        followEdit.unlockControls();
                    }
                    else{
                        followerDa.deleteFollower(followDetails.followPK);        
                        followDetails.unlockControls();
                    }
                    
                    break;
                    
                case EDIT_TWEET:   
                     System.out.print("EDIT_TWEET");
                     this.tweet = tweetDetails.tweet;                 
                     int editedTweet;
                     editedTweet = tweetDa.saveTweet(tweet, (byte)1);
                     
                     tweetDetails.edited(editedTweet);   
                     tweetDetails.unlockControls();
                 break;
                    
                case FAVORITE:
                    System.out.println("FAVORITE");
                    this.tweet= tweetDetails.tweet;
                    this.appUser = tweetDetails.user;
                    int favorited= -1;
                    int userId = appUser.getUserId();
                    int tweetId = tweet.getTweetId();
                    int ownerId = tweet.getUserId().getUserId();
                    
                    favorited = tweetDa.saveFavTweet(userId,tweetId, ownerId);
                    tweetDetails.favorited(favorited);
                    tweetDetails.unlockControls();
                    break;
                    
                case REPLY:
                    System.out.println("REPLY");
                    int replied=-1;
                    this.tweet= tweetDetails.reply;
                    replied = tweetDa.saveTweet(tweet, (byte)0);
                    
                    tweetDetails.replied(replied);
                    tweetDetails.unlockControls();
                    break;
                    
                case HAS_CONNECTIONS_WITH:
                    followDetails.connections = followerDa.hasConnectionsWith(followDetails.user.getUserId(),followDetails.follower.getUserId());
                    followDetails.unlockControlsInitial();
                    break;
                    
                case IS_FAVORITED:
                    this.tweet = tweetDetails.tweet;
                    this.appUser = tweetDetails.user;
                    int isFavorited=-1;
                    isFavorited = tweetDa.isFavorited(tweet.getTweetId(), appUser.getUserId());
                    
                    tweetDetails.favorited(isFavorited);
                    tweetDetails.retweeted(tweetDa.getIsRetweet());
                    tweetDetails.unlockControls();
                    break;
                    
                case UNFAVORITE:
                    this.tweet = tweetDetails.tweet;
                    this.appUser = tweetDetails.user;                    
                    int unfavorite = -1;
                    unfavorite = tweetDa.deleteFavTweet(appUser.getUserId(), tweet.getTweetId());
                    
                    tweetDetails.deleteFav(unfavorite);
                    tweetDetails.unlockControls();
                    break;
                    
                case ONLY_FAV:                         
                     allTweets = false;
                     tweetList = tweetDa.loadTweets(gui.user,(byte)2); 
                     refresh_my_tweets = false;
                     
                     gui.tweetList = tweetList;
                     gui.refreshTweetList();
                     gui.unlockControls();
                    break;
            }             
        }while(refresh_my_profile || refresh_tweets || refresh_my_tweets);
        init();
    }
    
    /**
     * Notifies the ClientThread 
     */
    public synchronized void wakeMe(){
        this.notify();
    }

    /**
     * Used to get the value of the local field action 
     * @return
     */
    public Actions getAction() {
        return action;
    }

    /**
     * Used to assign an Action to the local field action
     * @param action Action
     */
    public synchronized void setAction(Actions action) {
        this.action = action;
    }   
    private void init(){
        refresh_my_profile = false;
        refresh_tweets = false;
        refresh_my_tweets = false;
    }
    private void refreshorSAVE_TWEET(){
        refresh_my_profile = true;
        action = Actions.REFRESH_MY_PROFILE;
        if(allTweets){
            refresh_tweets = true;
        }else{
            refresh_my_tweets = true;
        }      
    }
    private void refreshorREFRESH_MY_PROFILE(){
        refresh_my_profile = false;
        if(refresh_tweets){
            action = Actions.REFRESH_TWEETS;            
        }
        else if(refresh_my_tweets){
            action = Actions.ONLY_MINE;
        }
        else{
            gui.unlockControls();
        }           
    }
    
    /**
     * Assigns a TweetDetails object to a local variable
     * to gain access to TweetDetails fields and methods
     * @param details TweetDetails object
     */
    public void setTweetDetails(TweetDetails details){     
        this.tweetDetails = details;
    }
    /**
     * Assigns a Login object to a local variable
     * to gain access to Login fields and methods
     * @param login Login object
     */
    public void setLogin(Login login){     
        this.login = login;
    }

    /**
     * Assigns a EditFollow object to a local variable
     * to gain access to its fields and methods
     * @param followEdit EditFollow object
     */
    public void setFollowEdit(EditFollow followEdit) {
        this.followDetails = null;
        this.followEdit = followEdit;
    }
  
     /**
     * Assigns a EditUserData object to a local variable
     * to gain access to its fields and methods
     * @param edit EditUserData object
     */
    public void setAccountDetails(EditUserData edit) {
        this.accountDetails = edit;
    }   
    
    /**
     * Assigns a Register object to a local variable
     * to gain access to its fields and methods
     * @param register Register object
     */
    public void setRegister(Register register) {
        this.register = register;
    }   
     
    /**
     * Assigns a FollowDetails object to a local variable
     * to gain access to FollowDetails fields and methods
     * @param followDetails FollowDetails object
     */
    public void setFollowDetails(FollowDetails followDetails){
        this.followEdit = null;
        this.followDetails = followDetails;
    }
    
    /** 
     * This method is used to check if the current socket is closed 
     * in order to do some clean up in threads and streams
     * @param sock the current socket
     * @return true if not closed, false if closed
     * @throws IOException
     */
    public boolean checkConn(Socket sock) throws IOException{
        
        boolean check ;
        this.sock = sock;
        
        if(sock.isClosed()==true){
            
            check = false;

        }
        else {check = true;}
        
        return check;
    }
    
}
