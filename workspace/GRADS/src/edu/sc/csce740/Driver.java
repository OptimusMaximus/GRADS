/**
 * 
 */
package edu.sc.csce740;

/**
 * @author brandemr
 *
 */
public class Driver {

	/**
	 * @param args
	 */
	@SuppressWarnings("null")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GRADS grads = null;
		grads = new GRADS();
		
		try {
			grads.loadUsers("/acct/brandemr/813/GRADS/workspace/GRADS/src/resources/users.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			grads.loadRecords("/acct/brandemr/813/GRADS/workspace/GRADS/src/resources/students.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			grads.loadCourses("/acct/brandemr/813/GRADS/workspace/GRADS/src/resources/courses.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
