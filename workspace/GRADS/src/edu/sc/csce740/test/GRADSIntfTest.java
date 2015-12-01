/**
 * 
 */
package edu.sc.csce740.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import edu.sc.csce740.GRADS;
import edu.sc.csce740.GRADSIntf;
import edu.sc.csce740.exception.InvalidUserException;

/**
 * @author Maximus
 *
 */
public class GRADSIntfTest {

	public static GRADSIntf grads;

   
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		grads = new GRADS();
		
		grads.loadUsers("resources/users.txt");
		grads.loadRecords("resources/students.txt");
		grads.loadCourses("resources/courses.txt");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSetUserPasses() throws Exception {	
		String userId = "mbr";
		grads.setUser(userId);
		assertEquals(userId, grads.getUser());
	}
	
	@Test(expected = InvalidUserException.class)
	public void testSetUserExceptionThrown() throws Exception {
		String userId = "rambo";
		grads.setUser(userId);
	}

}
