package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * This class will construct a SortedList of Courses as a catalog
 * Courses can be loaded from a file or saved to a file
 * Courses can also be added, removed or gotten
 * @author Joey Schauer
 *
 */
public class CourseCatalog {
	/** stores courses as a sorted list */
	private SortedList<Course> catalog;

	/**
	 * Constructs a blank catalog
	 */
	public CourseCatalog() {		
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Creates a new blank catalog
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Loads courses to the catalog from a given file
	 * @param fileName the file with the Courses to be loaded
	 * @throws IllegalArgumentException if the file cannot be found
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a Course with the given parameters to the catalog
	 * @param name The name of the course
	 * @param title The title of the course
	 * @param section The section of the course
	 * @param credits The credits for the course
	 * @param instructorId The instructor id of the course
	 * @param meetingDays The meeting days for the course
	 * @param startTime The start time of the course
	 * @param endTime The end time of the course
	 * @return if the course was added (true) or not (false)
	 * @throws IllegalArgumentException If something goes wrong with creating the Course
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, 
									   String instructorId, String meetingDays, int startTime, int endTime) {
		Course course;
		try {
			course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);		
			return catalog.add(course);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	/**
	 * Removes a course from the catalog with the given name and section
	 * @param name The name of the course
	 * @param section The section of the course
	 * @return whether the course was removed (true) or not (false)
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		Course c = getCourseFromCatalog(name, section);
		if (c != null) {
			catalog.remove(catalog.indexOf(c));
			return true;
		}
		return false;
	}
	
	/**
	 * Takes a name and section for a Course and gets that Course from the catalog
	 * @param name The name of the Course
	 * @param section The section of the Course
	 * @return the corresponding Course from the catalog or null if there is none
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (name.equals(c.getName()) && section.equals(c.getSection())) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Gets the name, section, title and meetingString of each Course
	 * in the catalog and stores them in a 2D string array
	 * @return the courseCatalog as a 2D string array
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogString;
		if (catalog.size() == 0) {
			catalogString = new String[0][0];
			return catalogString;
		}
		catalogString = new String[catalog.size()][4];
		for (int i = 0; i < catalog.size(); i++) {
            Course c = catalog.get(i);
            for (int j = 0; j < 4; j++) {
            	if (j == 0) {
            		catalogString[i][j] = c.getName();
            	}
            	if (j == 1) {
            		catalogString[i][j] = c.getSection();
            	}
            	if (j == 2) {
            		catalogString[i][j] = c.getTitle();
            	}
            	if (j == 3) {
            		catalogString[i][j] = c.getMeetingString();
            	}           	
            }
        }
		return catalogString;
	}	
	
	/**
	 * Saves the catalog to a file
	 * @param fileName The name of the file to store the catalog
	 * @throws IllegalArgumentException if the file can not be written
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
}
