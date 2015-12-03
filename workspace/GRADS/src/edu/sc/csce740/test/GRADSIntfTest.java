/**
 * 
 */
package edu.sc.csce740.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.sc.csce740.GRADS;
import edu.sc.csce740.GRADSIntf;
import edu.sc.csce740.exception.InvalidUserException;
import edu.sc.csce740.exception.TempEditException;
import edu.sc.csce740.model.Degree;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.Term;
import edu.sc.csce740.model.User;

/**
 * @author Maximus
 *
 */
public class GRADSIntfTest {

	public static GRADSIntf grads;
	public static User user;
	public static StudentRecord record;

   
	/**
	 * Method to set up an environment before class initialization
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * Method to tear down after testing of GRADSIntf class is closed
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Method to set up the environment for testing prior to each test
	 * @throws java.lang.Exception in the event that a database can't be loaded
	 */
	@Before
	public void setUp() throws Exception {		
		grads = new GRADS();
		grads.loadUsers("resources/users.txt");
		grads.loadRecords("resources/students.txt");
		grads.loadCourses("resources/courses.txt");
		
//		record = new StudentRecord();
//		record.setFirstName("Michelle");
//		record.setLastName("Hunt");
//		
//		List<User> advisors = new ArrayList<User>();
//		User advisor = new User();
//		advisor.setFirstName("Duncan");
//		advisor.setLastName("Buell");
//		advisor.setDepartment("COMPUTER_SCIENCE");
//		advisors.add(advisor);
//		
//		List<User> committee = new ArrayList<User>();
//		User  committeMember = new User();
//		committeMember.setFirstName("Manton");
//		committeMember.setLastName("Matthews");
//		committeMember.setDepartment("COMPUTER_SCIENCE");
//		committee.add(committeMember);
//		
//		User  committeMember2 = new User();
//		committeMember2.setFirstName("Jason");
//		committeMember2.setLastName("Bakos");
//		committeMember2.setDepartment("COMPUTER_SCIENCE");
//		committee.add(committeMember2);
//		
//		User  committeMember3 = new User();
//		committeMember3.setFirstName("Richard");
//		committeMember3.setLastName("McGehee");
//		committeMember3.setDepartment("MATHEMATICS");
//		committee.add(committeMember3);
//		
//		Term termBegan = new Term();
//		termBegan.setSemester("SPRING");
//		termBegan.setYear(2008);
//		
//		Degree degreeSought = new Degree();
//		degreeSought.setName("PHD");
//		Term graduation = new Term();
//		graduation.setSemester("SPRING");
//		graduation.setYear(2018);
//		degreeSought.setGraduation(graduation);
//		
//		record.setCommittee(committee);
//		record.setCommittee(advisors);
//		record.setTermBegan(termBegan);
//		record.setDegreeSought(degreeSought);
	}

	/**
	 * Method to clear the environment after each test
	 * @throws java.lang.Exception if clearing session is unsuccessful
	 */
	@After
	public void tearDown() throws Exception {
		grads.clearSession();
	}
	
	/**
	 * Testing the setUser() method
	 * Expect this test to match with the User instance userId value given
	 * @throws Exception if setUser fails
	 */
	@Test
	public void testSetUserPasses() throws Exception {
		String userId = "mbr";
		grads.setUser(userId);
		assertEquals("mbr", grads.getUser());
	}
	
	/**
	 * Testing the setUser() method
	 * Expect this test to throw an InvalidUserException when attempt to set user that is not in 
	 * database 
	 * @throws Exception if setUser fails
	 */
	@Test(expected = InvalidUserException.class)
	public void testSetUserExceptionThrown() throws Exception {
		String userId = "rambo";
		grads.setUser(userId);
	}
	
	/**
	 * Method to test if clearSession works correctly  
	 * @throws Exception when method called after clear session
	 */
	@Test (expected = InvalidUserException.class)
	public void testClearSession()throws Exception {
		grads.setUser("ggay");
		grads.clearSession();
		grads.generateProgressSummary("ggay");
	}
	
	/**
	 * Method to test getUser passes 
	 * @throws Exception if setUser fails 
	 */
	@Test 
	public void testGetUserPasses()throws Exception {
		String userId = "mbr";
		grads.setUser(userId);
		assertEquals(userId, grads.getUser());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetUserFails()throws Exception {
		assertEquals(null, grads.getUser());
	}
	
	@Test
	public void testGetStudentsIDsPasses() throws Exception{
		grads.setUser("mmatthews");
		List<String> students = grads.getStudentIDs();	
		List<String> studentsExpected = new ArrayList<String>();
		studentsExpected.add("mhunt");
		studentsExpected.add("mbr");
		studentsExpected.add("merc");
		studentsExpected.add("elsa");
		studentsExpected.add("ggay");
		assertArrayEquals(students.toArray(), studentsExpected.toArray());
	}
	
	@Test(expected = InvalidUserException.class)
	public void testGetStudentsIDsExceptionThrown()throws Exception  {
		grads.setUser("mbr");
		grads.getStudentIDs();	
		
	}
	
	@Test
	public void testGetTranscriptPasses() throws Exception{
		
	}
	
//	@Test(expected = InvalidUserException.class)
//	public void testGetTranscriptPassesExceptionThrown()throws Exception  {
//			
//	}
	
	@Test
	public void testAddNotePasses() throws Exception{
		grads.setUser("mmatthews");
		grads.addNote("mbr", "test note", false);
		List<String> notes = grads.getTranscript("mbr").getNotes();
		assertEquals("test note" , notes.get(notes.size()-1));
	}
	
	@Test(expected = InvalidUserException.class)
	public void testAddNoteExceptionThrown()throws Exception  {
		grads.setUser("elsa");
		grads.addNote("mbr", "test note", false);
		grads.getTranscript("mbr").getNotes();
	}
	
	@Test
	public void testUpdateTranscriptPasses() throws Exception{
		grads.setUser("mmatthews");
		grads.getTranscript("mhunt").setLastName("Hunter");
		grads.updateTranscript("mhunt", grads.getTranscript("mhunt"), true);
		grads.loadRecords("resources/students.txt");
		assertEquals("Hunter" , grads.getTranscript("mhunt").getLastName());
	}
	
	@Test(expected = InvalidUserException.class)
	public void testUpdateTranscriptThrowException() throws Exception{
		grads.setUser("mbr");
		grads.getTranscript("mhunt").setLastName("Hunter");
		grads.updateTranscript("mhunt", grads.getTranscript("mhunt"), true);
		grads.loadRecords("resources/students.txt");
	}
	
	@Test(expected = TempEditException.class)
	public void testUpdateTranscriptThrowTempException() throws Exception{
		grads.setUser("mbr");
		StudentRecord transcript; 
		transcript = grads.getTranscript("mbr");
		transcript.setLastName("Momoku");
		grads.updateTranscript("mbr", transcript, true);	
	}
	

}
