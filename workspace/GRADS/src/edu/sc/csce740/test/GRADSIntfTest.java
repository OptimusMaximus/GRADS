/**
 * 
 */
package edu.sc.csce740.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.sc.csce740.GRADS;
import edu.sc.csce740.GRADSIntf;
import edu.sc.csce740.exception.CoursesInvalidException;
import edu.sc.csce740.exception.DataNotRetrievedException;
import edu.sc.csce740.exception.InvalidUserException;
import edu.sc.csce740.exception.TempEditException;
import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.CourseTaken;
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
	public static StudentRecord oracle;
	
	private File getFile(String fileName) 
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
   
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
		grads.setUser("mhunt");
		ArrayList<StudentRecord> transOracle = new ArrayList<StudentRecord>();
		StudentRecord  record= new StudentRecord();
		StudentRecord transcript = new StudentRecord();
		try{
			transOracle = new Gson().fromJson(new FileReader(getFile("resources/students.txt")),
				new TypeToken<List<StudentRecord>>() {
				}.getType());
		}  catch (Exception e){
			throw new DataNotRetrievedException("Can not load records file");
		}
		
		for (int i = 0; i < transOracle.size(); i++)
		{
			if (transOracle.get(i).getUser().getUserID().equals("mhunt")){
				record = transOracle.get(i);
			}
		}
		transcript = grads.getTranscript("mhunt");
		assertPropertiesEqual(record, transcript);
	}
	
	@Test(expected = InvalidUserException.class)
	public void testGetTranscriptPassesExceptionThrown()throws Exception  {
		grads.setUser("mbr");
		grads.getTranscript("mhunt");
			
	}
	
	@Test
	public void simulateCoursesPasses() throws Exception{
		grads.setUser("mhunt");
		List<CourseTaken> courses = new ArrayList<CourseTaken>();
		CourseTaken courseTaken = new CourseTaken();
		Course course = new Course();
		Term term = new Term();
		
		term.setSemester("SPRING");
		term.setYear(2008); 
		course.setId("csce883");
		course.setName("Machine Learning");
		course.setNumCredits("3");
		courseTaken.setCourse(course);
		courseTaken.setGrade("A");
		courseTaken.setTerm(term);
		courses.add(courseTaken);
		grads.simulateCourses("mhunt",courses);
		
		List<CourseTaken> coursesInTranscript =grads.getTranscript("mhunt").getCoursesTaken();
		boolean isThere = false; 
		for (int i = 0; i <coursesInTranscript.size(); i ++){
			if (course.courseIsEqual(coursesInTranscript.get(i).getCourse(), course)){
				isThere = true;
			}
		}
		assertEquals(true, isThere);		
	}
	@Test(expected = CoursesInvalidException.class)
	public void simulateCoursesThrowsException() throws Exception{
		grads.setUser("mhunt");
		List<CourseTaken> courses = new ArrayList<CourseTaken>();
		CourseTaken courseTaken = new CourseTaken();
		Course course = new Course();
		Term term = new Term();
		
		term.setSemester("SPRING");
		term.setYear(2008); 
		course.setId("csce8883");
		course.setName("Machine Learning");
		course.setNumCredits("3");
		courseTaken.setCourse(course);
		courseTaken.setGrade("A");
		courseTaken.setTerm(term);
		courses.add(courseTaken);
		grads.simulateCourses("mhunt",courses);
		
		
	}
	
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
	
	private void assertPropertiesEqual(StudentRecord oracle, StudentRecord test) throws InvalidUserException{
		assertEquals(oracle.getFirstName(), test.getFirstName());
		assertEquals(oracle.getLastName(), test.getLastName());
		assertEquals(oracle.getNotes(),test.getNotes());
		assertEquals(oracle.getMilestonesSet().size(), test.getMilestonesSet().size());
		for (int i = 0; i <oracle.getMilestonesSet().size(); i++){
			assertEquals(oracle.getMilestonesSet().get(i).getMilestone(), test.getMilestonesSet().get(i).getMilestone());
		}
		assertEquals(oracle.getRole(), test.getRole());
		
		//Term
		assertEquals(oracle.getTermBegan().getSemester(), test.getTermBegan().getSemester());
		assertEquals(oracle.getTermBegan().getYear(), test.getTermBegan().getYear());
		
		//DegreeSought
		assertEquals(oracle.getDegreeSought().getDegreeName(), test.getDegreeSought().getDegreeName());
		assertEquals(oracle.getDegreeSought().getGraduation().getSemester(), test.getDegreeSought().getGraduation().getSemester());assertEquals(oracle.getDegreeSought().getGraduation().getSemester(), test.getDegreeSought().getGraduation().getSemester());
		assertEquals(oracle.getDegreeSought().getGraduation().getYear(), test.getDegreeSought().getGraduation().getYear());
		
		//CertificateSought
		if (test.getCertificateSought() != null){
			assertEquals(oracle.getCertificateSought().getDegreeName(), test.getCertificateSought().getDegreeName());
			assertEquals(oracle.getCertificateSought().getGraduation().getSemester(), test.getCertificateSought().getGraduation().getSemester());assertEquals(oracle.getDegreeSought().getGraduation().getSemester(), test.getDegreeSought().getGraduation().getSemester());
			assertEquals(oracle.getCertificateSought().getGraduation().getYear(), test.getCertificateSought().getGraduation().getYear());
		}
		//Advisors
		for (int i = 0; i <oracle.getAdvisors().size(); i++){
			assertEquals(oracle.getAdvisors().get(i).getUserID(), test.getAdvisors().get(i).getUserID());
			assertEquals(oracle.getAdvisors().get(i).getDepartment(),test.getAdvisors().get(i).getDepartment());
			assertEquals(oracle.getAdvisors().get(i).getRole(),test.getAdvisors().get(i).getRole());
			assertEquals(oracle.getAdvisors().get(i).getFirstName(), test.getAdvisors().get(i).getFirstName());
			assertEquals(oracle.getAdvisors().get(i).getLastName(), test.getAdvisors().get(i).getLastName());
			
		}
		//Committee
		for (int i = 0; i <oracle.getCommittee().size(); i++){
			assertEquals(oracle.getCommittee().get(i).getUserID(), test.getCommittee().get(i).getUserID());
			assertEquals(oracle.getCommittee().get(i).getDepartment(),test.getCommittee().get(i).getDepartment());
			assertEquals(oracle.getCommittee().get(i).getRole(),test.getCommittee().get(i).getRole());
			assertEquals(oracle.getCommittee().get(i).getFirstName(), test.getCommittee().get(i).getFirstName());
			assertEquals(oracle.getCommittee().get(i).getLastName(), test.getCommittee().get(i).getLastName());
			
		}
		
		//Department
		assertEquals(oracle.getDept(), test.getDept());
		
		//Previous Degrees 
		if (test.getPreviousDegrees() != null){
			for (int i = 0; i < oracle.getPreviousDegrees().size(); i++)
			{	
				assertEquals(oracle.getPreviousDegrees().get(i).getDegreeName(),test.getPreviousDegrees().get(i).getDegreeName());
				assertEquals(oracle.getPreviousDegrees().get(i).getGraduation().getSemester(),test.getPreviousDegrees().get(i).getGraduation().getSemester());
				assertEquals(oracle.getPreviousDegrees().get(i).getGraduation().getYear(),test.getPreviousDegrees().get(i).getGraduation().getYear());	
				
			}
		}
		
		//CoursesTaken
		Course course = new Course();
		for (int i = 0; i < oracle.getCoursesTaken().size(); i++)
		{
			assertEquals(oracle.getCoursesTaken().get(i).getGrade(),test.getCoursesTaken().get(i).getGrade());
			assertEquals(oracle.getCoursesTaken().get(i).getTerm().getSemester(),test.getCoursesTaken().get(i).getTerm().getSemester());
			assertEquals(oracle.getCoursesTaken().get(i).getTerm().getYear(),test.getCoursesTaken().get(i).getTerm().getYear());
			assertEquals(true, course.courseIsEqual(test.getCoursesTaken().get(i).getCourse(), oracle.getCoursesTaken().get(i).getCourse()));
		}
	}
	

}
