/**
 * 
 */
package edu.sc.csce740;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.sc.csce740.model.Course;
import edu.sc.csce740.model.CourseTaken;
import edu.sc.csce740.model.Degree;
import edu.sc.csce740.model.ProgressSummary;
import edu.sc.csce740.model.StudentRecord;
import edu.sc.csce740.model.User;

/**
 * @author brandemr
 *
 */
public class GRADS implements GRADSIntf {
	private String currentUser;
	private List<User> allUsers;
	private List<StudentRecord> allRecords;
	private List<Degree> allDegrees;
	private List<Course> allCourses;
	
	
	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#loadUsers(java.lang.String)
	 */
	public void loadUsers(String usersFile) throws Exception {
		// TODO Auto-generated method stub
		List<User> users = new Gson().fromJson( new FileReader( new File(usersFile)), new TypeToken<List<StudentRecord>>(){}.getType());
		System.out.println(users.size());
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#loadCourses(java.lang.String)
	 */
	public void loadCourses(String coursesFile) throws Exception {
		// TODO Auto-generated method stub
		List<Course> courses = new Gson().fromJson( new FileReader( new File(coursesFile)), new TypeToken<List<StudentRecord>>(){}.getType());
		System.out.println(courses.size());
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#loadRecords(java.lang.String)
	 */
	public void loadRecords(String recordsFile) throws Exception {
		// TODO Auto-generated method stub
		List<StudentRecord> studentRecords = new Gson().fromJson( new FileReader( new File(recordsFile)), new TypeToken<List<StudentRecord>>(){}.getType());
		System.out.println(studentRecords.size());
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#setUser(java.lang.String)
	 */
	public void setUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		this.currentUser = userId;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#clearSession()
	 */
	public void clearSession() throws Exception {
		// TODO Auto-generated method stub
		this.currentUser = null;
		this.allCourses = null;
		this.allDegrees = null;
		this.allRecords = null;
		this.allUsers = null;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#getUser()
	 */
	public String getUser() {
		// TODO Auto-generated method stub
		return this.currentUser;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#getStudentIDs()
	 */
	public List<String> getStudentIDs() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#getTranscript(java.lang.String)
	 */
	public StudentRecord getTranscript(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#updateTranscript(java.lang.String, edu.sc.csce740.model.StudentRecord, java.lang.Boolean)
	 */
	public void updateTranscript(String userId, StudentRecord transcript,
			Boolean permanent) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#addNote(java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	public void addNote(String userId, String note, Boolean permanent)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#generateProgressSummary(java.lang.String)
	 */
	public ProgressSummary generateProgressSummary(String userId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.sc.csce740.GRADSIntf#simulateCourses(java.lang.String, java.util.List)
	 */
	public ProgressSummary simulateCourses(String userId,
			List<CourseTaken> courses) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void loadDegreeReqs(String degreesFile){
		
		
	}
	
	private int getRecordIndex(String userID){
		
		return -1;
	}

}
