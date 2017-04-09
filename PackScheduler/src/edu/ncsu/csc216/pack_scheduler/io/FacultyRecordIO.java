package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * The class handles the file input and output for FacultyDirectory
 * @author Joey Schauer
 */
public class FacultyRecordIO {

	/**
	 * Reads the faculty records from a given file
	 * @param fileName the name of the file to be processed
	 * @return the processed file as a LinkedList
	 * @throws FileNotFoundException if the file isn't found
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    LinkedList<Faculty> facultyList = new LinkedList<Faculty>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Faculty faculty = processFaculty(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < facultyList.size(); i++) {
	                User f = facultyList.get(i);
	                if (faculty.getId().equals(f.getId())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                facultyList.add(faculty);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return facultyList;
	}
	
	/**
	 * Processes a line from the file and stores it as a Faculty object
	 * @param line the line to be processed
	 * @return the processed line as a Faculty object
	 */
	private static Faculty processFaculty(String line) {
		Faculty faculty;
		try {
			Scanner scan = new Scanner(line);	
			scan.useDelimiter(",");
			String firstName = scan.next();
			String lastName = scan.next();
			String id = scan.next();
			String email = scan.next();
			String password = scan.next();
			int maxCourses = scan.nextInt();
			faculty = new Faculty(firstName, lastName, id, email, password, maxCourses);	
			scan.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		} 
		return faculty;
	}

	/**
	 * Writes a given LinkedList of faculty to a new file
	 * @param fileName the name of the file to be written
	 * @param facultyDirectory the facultyDirectory to be written to the file
	 * @throws IOException if there is an input or output exception
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
		    fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();
	}
}
