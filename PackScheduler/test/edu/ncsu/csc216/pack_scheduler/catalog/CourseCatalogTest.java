package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests CourseCatalog
 * @author Joey Schauer
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course enrollment capacity */
	private static final int ENROLLMENT_CAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception will throw exceptions
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests the null constructor for CourseCatalog
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		assertEquals(0, catalog.getCourseCatalog().length);
	}
	
	/**
	 * Tests the newCourseCatalog method
	 */
	@Test
	public void testnewCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		
		//Adds courses to the catalog
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(1, catalog.getCourseCatalog().length);
		
		//Tests to see if newCourseCatalog clears out any existing courses
		catalog.newCourseCatalog();
		assertEquals(0, catalog.getCourseCatalog().length);
	}
	
	/**
	 * Tests the loadCoursesFromFile method
	 */
	@Test
	public void testloadCoursesFromFile() {
		CourseCatalog catalog = new CourseCatalog();
		
		//Tests loading courses from a non-existent file
		try {
			catalog.loadCoursesFromFile("NotAFile.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file NotAFile.txt", e.getMessage());
		}
		
		//Tests loading courses from an invalid test file
		catalog.loadCoursesFromFile(invalidTestFile);
		assertEquals(0, catalog.getCourseCatalog().length);
		
		//Tests loading courses from a valid test file
		catalog.loadCoursesFromFile(validTestFile);
		assertEquals(8, catalog.getCourseCatalog().length);
	}
	
	/**
	 * Tests the addCourseToCatalog method
	 */
	@Test
	public void testAddCoursesToCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		//Tests adding a new course to the catalog
		assertTrue(catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, catalog.getCourseCatalog().length);
		assertEquals(course, catalog.getCourseFromCatalog(NAME, SECTION));
		
		//Tests adding an existing course to the catalog
		assertFalse(catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
		
		//Test adding a course with the same name and section only
		assertFalse(catalog.addCourseToCatalog(NAME, "Something", SECTION, 3, "teacher", 10, "MW", 800, 900));
		
		//Tests adding an invalid course to the catalog
		try {
			catalog.addCourseToCatalog(null, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		}
	}
	
	/**
	 * Tests the removeCourseFromCatalog method
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		//Adds a course to the catalog to ensure there is at least one
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(1, catalog.getCourseCatalog().length);
		assertEquals(course, catalog.getCourseFromCatalog(NAME, SECTION));
		
		//Tests removing the added course from the catalog
		assertTrue(catalog.removeCourseFromCatalog(NAME, SECTION));
		assertEquals(0, catalog.getCourseCatalog().length);
		assertEquals(null, catalog.getCourseFromCatalog(NAME, SECTION));
		
		//Tests removing a non-existent course from the catalog
		assertFalse(catalog.removeCourseFromCatalog("NOT_A_COURSE", "111"));
	}
	
	/**
	 * Tests getCourseFromCatalog method
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		//Tests getting a course from the catalog that exists
		assertEquals(course, catalog.getCourseFromCatalog(NAME, SECTION));
		catalog.removeCourseFromCatalog(NAME, SECTION);
		
		//Tests getting a course from the catalog that doesn't exist
		assertEquals(null, catalog.getCourseFromCatalog(NAME, SECTION));
		
		
	}
	
	/**
	 * Tests the getCourseCatalog method
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		//Gets the course catalog string array 
		String[][] catalogString = catalog.getCourseCatalog();
		
		//Creates a new string array with the same values as the one above
		String[][] expectedCatalog = new String[1][4];
		expectedCatalog[0][0] = NAME;
		expectedCatalog[0][1] = SECTION;
		expectedCatalog[0][2] = TITLE;
		expectedCatalog[0][3] = course.getMeetingString();
		
		//Compares the values from both arrays to make sure they match
		assertEquals(expectedCatalog[0][0], catalogString[0][0]);
		assertEquals(expectedCatalog[0][1], catalogString[0][1]);
		assertEquals(expectedCatalog[0][2], catalogString[0][2]);
		assertEquals(expectedCatalog[0][3], catalogString[0][3]);
	}
	
	/**
	 * Tests the saveCourseCatalog method
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(1, catalog.getCourseCatalog().length);
		
		//tests saving the catalog to a file then checking it against expected outcomes
		catalog.saveCourseCatalog("test-files/new_course_records.txt");
		checkFiles("test-files/expected_new_course_records.txt", "test-files/new_course_records.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}

