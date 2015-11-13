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
	 * @throws Exception 
	 */

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GRADS grads = null;
		grads = new GRADS();
		
		try {
			grads.loadUsers("users.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			grads.loadRecords("students.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			grads.loadCourses("courses.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grads.setUser("mmatthews");
		//System.out.println(grads.getStudentIDs());
		//System.out.println(grads.getGPCIDs());
//		System.out.println(grads.getTranscript("mhunt").getNotes().get(1));

	}

}
