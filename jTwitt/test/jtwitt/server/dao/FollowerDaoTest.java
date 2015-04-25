/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jtwitt.server.model.Follower;
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
public class FollowerDaoTest {
    
    //@PersistenceContext(unitName = "jTwittPU")
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jTwittPU");
    public EntityManager em ;
    FollowerDao instance;
    
    public FollowerDaoTest() {
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
        instance = new FollowerDao(em);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveFollowers method, of class FollowerDao.
     */
    @Test
    public void testSaveFollowers() {
        System.out.println("saveFollowers");
        Follower follow = null;
        byte flag = 0;
        
        int expResult = 0;
        int result = instance.saveFollowers(follow, flag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFollowers method, of class FollowerDao.
     */
    @Test
    public void testLoadFollowers() {
        System.out.println("loadFollowers");
        int follower = 0;
        int following = 0;
        
        Follower expResult = null;
        Follower result = instance.loadFollowers(follower, following);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchFollowers method, of class FollowerDao.
     */
    @Test
    public void testSearchFollowers() {
        System.out.println("searchFollowers");
        String searchKeyWords = "";
        
        List expResult = null;
        List result = instance.searchFollowers(searchKeyWords);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
