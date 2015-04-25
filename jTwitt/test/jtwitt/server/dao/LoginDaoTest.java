/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jtwitt.server.dao;

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
public class LoginDaoTest {
    
    public LoginDaoTest() {
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
     * Test of login method, of class LoginDao.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String email = "";
        String password = "";
        int chooser = 0;
        LoginDao instance = null;
        int expResult = 0;
        int result = instance.login(email, password, chooser);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class LoginDao.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        int userId = 0;
        LoginDao instance = null;
        int expResult = 0;
        int result = instance.logout(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of signUp method, of class LoginDao.
     */
    @Test
    public void testSignUp() {
        System.out.println("signUp");
        String username = "";
        String password = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        LoginDao instance = null;
        int expResult = 0;
        int result = instance.signUp(username, password, firstName, lastName, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
