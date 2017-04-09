package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Test class for FacultyRecordIO
 * @author Joey Schauer
 */
public class FacultyRecordIOTest {

	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	
	/** String to store hash passwords */
	private String hashPW;
	/** the hash algorithm constant */
	private static final String HASH_ALGORITHM = "SHA-256";	
	
	/** Expected results for valid Students */
	private final String validFaculty1 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";	
	private final String validFaculty2 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	private final String validFaculty3 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	private final String validFaculty4 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	private final String validFaculty5 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	private final String validFaculty6 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	private final String validFaculty7 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	private final String validFaculty8 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";

	/** Array to hold expected results */
	private final String[] validFaculty = {validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5,
											validFaculty6, validFaculty7, validFaculty8};
	
	/**
	 * Hash the password for each validStudent in the String array
	 * @throws Exception throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validFaculty.length; i++) {
	            validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**
	 * Test valid readFacultyRecords
	 */
	@Test
	public void testReadFacultyRecords() {		
		//Test a valid faculty records file
		try {
			LinkedList<Faculty> faculty;
			faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, faculty.size());
			
			for (int i = 0; i < validFaculty.length; i++) {
				assertEquals(validFaculty[i], faculty.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}		
		
		//Test an invalid faculty records file
		try {
			LinkedList<Faculty> faculty;
			faculty = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, faculty.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}

	/**
	 * Test writeFacultyRecords
	 */
	@Test
	public void testWriteFacultyRecords() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
	    faculty.add(new Faculty("Ashely","Witt","awitt","mollis@Fuscealiquetmagna.net",hashPW,2));
	    faculty.add(new Faculty("Fiona","Meadows","fmeadow","pharetra.sed@et.org",hashPW,3));
	    faculty.add(new Faculty("Brent","Brewer","bbrewer","sem.semper@orcisem.co.uk",hashPW,1));	    
	    
	    try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", faculty);
		} catch (IOException e) {
			fail("Cannot write to student records file");
		}
		//Test that the expected outcome equals the actual
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}
	
	/**
	 * Test writing to a file you don't have permissions for
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
	    faculty.add(new Faculty("Ashely","Witt","awitt","mollis@Fuscealiquetmagna.net",hashPW,2));
	    faculty.add(new Faculty("Fiona","Meadows","fmeadow","pharetra.sed@et.org",hashPW,3));
	    faculty.add(new Faculty("Brent","Brewer","bbrewer","sem.semper@orcisem.co.uk",hashPW,1));	
	    
	    try {
	        FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_faculty_records.txt", faculty);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_faculty_records.txt (Permission denied)", e.getMessage());
	    }
	    
	}

	/**
	 * Helper method used to check file equality
	 * @param expFile the expected results file
	 * @param actFile the actual results file
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}

}
