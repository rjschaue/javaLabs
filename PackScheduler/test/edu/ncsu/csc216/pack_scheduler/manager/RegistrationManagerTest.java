package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;

/**
 * Tests the RegistrationManager class
 * @author Joey Schauer
 */
public class RegistrationManagerTest {
	
	/** the instance of the registration manager */
	private RegistrationManager manager;
	
	/**
	 * Sets up the CourseManager and clears the data.
	 * @throws Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.clearData();
	}

	/**
	 * Tests the get course catalog method
	 */
	@Test
	public void testGetCourseCatalog() {
		//Testing to see if two new catalogs both lack the same course
		CourseCatalog catalog1 = manager.getCourseCatalog();
		CourseCatalog catalog2 = new CourseCatalog();
		assertEquals(catalog1.getCourseFromCatalog("CSC116", "001"), catalog2.getCourseFromCatalog("CSC116", "001"));
	}

	/**
	 * Tests the get student directory method
	 */
	@Test
	public void testGetStudentDirectory() {
		//Testing to see if two new directorys lack the same user
		StudentDirectory directory1 = manager.getStudentDirectory();
		StudentDirectory directory2 = new StudentDirectory();
		assertEquals(directory1.getStudentById("rjschaue"), directory2.getStudentById("rjschaue"));
	}

	/**
	 * Tests the login method
	 */
	@Test
	public void testLogin() {	
		//Set student directory for manager
		StudentDirectory sd = new StudentDirectory();
		sd.addStudent("Joey", "Schauer", "rjschaue", "rjschaue@ncsu.edu", "pw", "pw", 9);
		manager.setStudentDirectory(sd);
		
		//try logging in as a valid user
		manager.login("registrar", "Regi5tr@r");
		assertEquals(manager.getCurrentUser().getId(), "registrar");
		
		//Try logging in when someone already is
		assertFalse(manager.login("rjschaue", "pw"));
		
		//Try logging in a valid student
		manager.logout();
		System.out.println(manager.login("rjschaue", "pw"));
		assertEquals(manager.getCurrentUser().getId(), "rjschaue");
	}

	/**
	 * Tests the logout method
	 */
	@Test
	public void testLogout() {
		//logging a user in
		manager.login("registrar", "Regi5tr@r");
		//logging the user out
		manager.logout();
		assertEquals(manager.getCurrentUser(), null);
	}

	/**
	 * Tests the get current user method
	 */
	@Test
	public void testGetCurrentUser() {
		//testing to see if user is null when constructed
		assertEquals(manager.getCurrentUser(), null);
		//testing to see if the user is changed when someone is logged in
		manager.login("registrar", "Regi5tr@r");
		assertEquals(manager.getCurrentUser().getId(), "registrar");
	}

}