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
import jtwitt.server.model.AppUser;
import jtwitt.socket.CommunicationThread;

/**
 *
 * @author Nikos
 */
public class AppUserBL {
        
    AppUserDao appUserDAO;
    AppUser appUser;
    Byte flag;
    List<AppUser> appUserList = new ArrayList<>();
    CommunicationThread commThread;
            
    public AppUserBL(EntityManager em, CommunicationThread commThread){
        appUserDAO = new AppUserDao(em);
        appUser = new AppUser();
        this.commThread = commThread;
    }
    
    public void loadMyProfile() throws ClassNotFoundException, IOException{
        int userId = -1;                         
        userId = commThread.readInt();            
        appUser = appUserDAO.loadMyProfile(userId);
       
        commThread.writeObject(appUser);         

    }
    public void saveMyProfile() throws IOException, ClassNotFoundException{
        
        int profileSaved = -1;
        
        appUser = (AppUser)commThread.readObject();
        flag = (byte)commThread.readObject();        
                        
        try{
            
            profileSaved = appUserDAO.saveProfile(appUser, flag);                          
            appUserDAO.getEm().getTransaction().commit();
            appUserDAO.getEm().getTransaction().begin();
        }
        catch (RollbackException rex) {
            rex.printStackTrace();
            appUserDAO.getEm().getTransaction().rollback();
        }       
        
        commThread.writeInt(profileSaved);       
    }
     public void loadFollowingProfiles() throws IOException {
        int userId = -1;       
        appUserList.clear();        
        userId = commThread.readInt();            
        appUserList = appUserDAO.loadFollowingProfiles(userId);            
        commThread.writeObject(appUserList);                                          
    }

    public void loadFollowerProfiles() throws IOException {
        int userId = -1;       
        appUserList.clear();  
        userId = commThread.readInt();            
        appUserList = appUserDAO.loadFollowerProfiles(userId);            
        commThread.writeObject(appUserList);                 
                    
               
    }
    
   public void saveProfile() throws IOException, ClassNotFoundException
   {
   byte flag =-1;
   int saved=-1;
    try {
            System.out.println("aaaa");
            
            appUser = (AppUser)commThread.readObject();
            flag = (byte)commThread.readObject();
            System.out.println("aaaa"+appUser.getEmail());
            saved = appUserDAO.saveProfile(appUser, flag);
            System.out.println("aaaa");   
            commThread.writeInt(saved);     
            System.out.println("aaaa");
             //ok = 1;
    }
    catch(IOException | ClassNotFoundException ex){}
   
             
   }
   public void search() throws IOException, ClassNotFoundException{
      String keyWord;
      appUserList.clear();      
      keyWord = (String) commThread.readObject();     
      int ownerId = commThread.readInt();
      appUserList = appUserDAO.searchUsers(keyWord,ownerId);
      commThread.writeObject(appUserList);         
   }
}
