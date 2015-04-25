/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.dao;

import javax.persistence.EntityManager;
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
public class MainDaoTest {
    
    public MainDaoTest() {
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
     * Test of save method, of class MainDao.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        MainDao instance = null;
        int expResult = 0;
        int result = instance.commitTransaction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEm method, of class MainDao.
     */
    @Test
    public void testGetEm() {
        System.out.println("getEm");
        MainDao instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEm method, of class MainDao.
     */
    @Test
    public void testSetEm() {
        System.out.println("setEm");
        EntityManager em = null;
        MainDao instance = null;
        instance.setEm(em);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
