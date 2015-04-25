/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jtwitt.server.model.AppUser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikos
 */
public class AppUserDaoTest {
    
    //@PersistenceContext(unitName = "jTwittPU")
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jTwittPU");
    public EntityManager em ;
    AppUserDao instance;
    
    public AppUserDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        instance = new AppUserDao(em);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadMyProfile method, of class AppUserDao.
     */
    @Test
    public void testLoadMyProfile() {
        System.out.println("loadMyProfile");
        int userId = 1;
        
        AppUser expResult = new AppUser("nikos", "nikos", "nikos", "nikos", "nikos");
        AppUser result = instance.loadMyProfile(userId);
       
        if(expResult.getUsername().equalsIgnoreCase(result.getUsername())){
            
        }
        else{
            fail("LoadMyProfile");
        }
        
    }

    /**
     * Test of loadFollowingProfiles method, of class AppUserDao.
     */
    @Test
    public void testLoadFollowingProfiles() {
        System.out.println("loadFollowingProfiles");
        int userId = 0;
       
        List expResult = null;
        List result = instance.loadFollowingProfiles(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFollowerProfiles method, of class AppUserDao.
     */
    @Test
    public void testLoadFollowerProfiles() {
        System.out.println("loadFollowerProfiles");
        int userId = 0;
       
        List expResult = null;
        List result = instance.loadFollowerProfiles(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveProfile method, of class AppUserDao.
     */
    @Test
    public void testSaveProfile() {
        System.out.println("saveProfile");
        AppUser profile = null;
        byte flag = 0;
       
        int expResult = 0;
        int result = instance.saveProfile(profile, flag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveNumOfTweets method, of class AppUserDao.
     */
    @Test
    public void testSaveNumOfTweets() {
        System.out.println("saveNumOfTweets");
        int userId = 0;
        int numOfTweets = 0;
       
        int expResult = 0;
        int result = instance.saveNumOfTweets(userId, numOfTweets);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveNumOfFollowers method, of class AppUserDao.
     */
    @Test
    public void testSaveNumOfFollowers() {
        System.out.println("saveNumOfFollowers");
        int userId = 0;
        int numOfFollowers = 0;
       
        int expResult = 0;
        int result = instance.saveNumOfFollowers(userId, numOfFollowers);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveNumOfFollowing method, of class AppUserDao.
     */
    @Test
    public void testSaveNumOfFollowing() {
        System.out.println("saveNumOfFollowing");
        int userId = 0;
        int numOfFollowing = 0;
       
        int expResult = 0;
        int result = instance.saveNumOfFollowing(userId, numOfFollowing);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAccount method, of class AppUserDao.
     */
    @Test
    public void testDeleteAccount() {
        System.out.println("deleteAccount");
        int userId = 0;
       
        int expResult = 0;
        int result = instance.deleteAccount(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
