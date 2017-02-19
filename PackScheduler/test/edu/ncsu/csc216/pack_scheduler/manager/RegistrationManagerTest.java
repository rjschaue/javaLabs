package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		fail("Not yet implemented");
	}

	/**
	 * Tests the get student directory method
	 */
	@Test
	public void testGetStudentDirectory() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the login method
	 */
	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the logout method
	 */
	@Test
	public void testLogout() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the get current user method
	 */
	@Test
	public void testGetCurrentUser() {
		fail("Not yet implemented");
	}

}