/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.dao;

import java.util.List;
import jtwitt.server.model.Tweet;
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
public class TweetDaoTest {
    
    public TweetDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadTweets method, of class TweetDao.
     */
    @Test
    public void testLoadTweets() {
//        System.out.println("loadTweets");
//        int userId = 0;
//        TweetDao instance = null;
//        List expResult = null;
//        List result = instance.loadTweets(userId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of saveTweet method, of class TweetDao.
     */
    @Test
    public void testSaveTweet() {
        System.out.println("saveTweet");
        Tweet tweet = null;
        byte flag = 0;
        TweetDao instance = null;
        int expResult = 0;
        int result = instance.saveTweet(tweet, flag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTweet method, of class TweetDao.
     */
    @Test
    public void testDeleteTweet() {
        System.out.println("deleteTweet");
        int tweetId = 0;
        TweetDao instance = null;
        int expResult = 0;
        int result = instance.deleteTweet(tweetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFavTweets method, of class TweetDao.
     */
    @Test
    public void testLoadFavTweets() {
        System.out.println("loadFavTweets");
        int userId = 0;
        TweetDao instance = null;
        List expResult = null;
        List result = instance.loadFavTweets(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveFavTweet method, of class TweetDao.
     */
    @Test
    public void testSaveFavTweet() {
        System.out.println("saveFavTweet");
        int userId = 0;
        int tweetId = 0;
        int ownerId = 0;
        TweetDao instance = null;
        int expResult = 0;
        int result = instance.saveFavTweet(userId, tweetId, ownerId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteFavTweet method, of class TweetDao.
     */
    @Test
    public void testDeleteFavTweet() {
        System.out.println("deleteFavTweet");
        int userId = 0;
        int tweetId = 0;
        TweetDao instance = null;
        int expResult = 0;
        int result = instance.deleteFavTweet(userId, tweetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchTweets method, of class TweetDao.
     */
    @Test
    public void testSearchTweets() {
        System.out.println("searchTweets");
        String searchKeyWords = "";
        TweetDao instance = null;
        List expResult = null;
        List result = instance.searchTweets(searchKeyWords);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
