package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * This class handles file input and output for Student records
 * 
 * @author Joey Schauer
 *
 */
public class StudentRecordIO {

	/**
	 * Reads the student records from a given file
	 * @param fileName the name of the file to be processed
	 * @return the processed file as a SortedList
	 * @throws FileNotFoundException if the file isn't found
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    SortedList<Student> students = new SortedList<Student>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Student student = processStudent(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < students.size(); i++) {
	                User s = students.get(i);
	                if (student.getId().equals(s.getId())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                students.add(student);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return students;
	}
	
	/**
	 * Processes a line from the file and stores it as a Student object
	 * @param line the line to be processed
	 * @return the processed line as a Student object
	 */
	private static Student processStudent(String line) {
		Student student;
		try {
			Scanner scan = new Scanner(line);	
			scan.useDelimiter(",");
			String firstName = scan.next();
			String lastName = scan.next();
			String id = scan.next();
			String email = scan.next();
			String password = scan.next();
			int maxCredits = scan.nextInt();
			student = new Student(firstName, lastName, id, email, password, maxCredits);	
			scan.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		return student;
	}

	/**
	 * Writes a given SortedList of student to a new file
	 * @param fileName the name of the file to be written
	 * @param studentDirectory the studentDirectory to be written to the file
	 * @throws IOException if there is an input or output exception
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
	}

}
