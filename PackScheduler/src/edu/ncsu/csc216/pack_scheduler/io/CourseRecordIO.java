package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;


/**
 * Reads Course records from text files. Writes a set of CourseRecords to a file.
 * 
 * @author Sarah Heckman
 * @author Joey Schauer
 *
 */
public class CourseRecordIO {	

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any invalid
	 * Courses are ignored. If the file to read cannot be found or the permissions are incorrect
	 * a FileNotFoundException is thrown.
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileReader.hasNextLine()) {
			try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
		}
		fileReader.close();
		return courses;
	}
	
	/**
	 * This method takes an input string and uses its values to create a Course object
	 * @param nextLine is the line from the file to be processed
	 * @return input string as a Course object
	 * @throws IllegalArgumentException if there is a NoSuchElementException
	 */
	private static Course readCourse(String nextLine) {
		Course course;
		try {
			Scanner scan = new Scanner(nextLine);	
			scan.useDelimiter(",");
			String name = scan.next();
			String title = scan.next();
			String section = scan.next();
			int credits = scan.nextInt();
			String instructorId = scan.next();
			String meetingDays = scan.next();
			int startTime = scan.nextInt();
			int endTime = scan.nextInt();
			course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
			scan.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		return course;
	}

	/**
	 * Writes the given list of Courses to a new file 
	 * @param fileName the file name for the new file
	 * @param courses the SortedList of courses to be added to the new file
	 * @throws IOException throws IOException for the method
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}