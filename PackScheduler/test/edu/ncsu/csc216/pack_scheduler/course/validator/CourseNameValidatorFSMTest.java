package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for CourseNameValidatorFSM
 * @author Joey Schauer
 */
public class CourseNameValidatorFSMTest {

	/**
	 * Test for isValid method
	 */
	@Test
	public void testIsValid() {
		CourseNameValidatorFSM cnv = new CourseNameValidatorFSM();
		
		//Test invalid first character
		try {
			cnv.isValid("!CSC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		//Test invalid second character
		try {
			cnv.isValid("C!SC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		//Test invalid third character
		try {
			cnv.isValid("CS!C116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		//Test invalid fourth character
		try {
			cnv.isValid("CSC!116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		//Test invalid fifth character
		try {
			cnv.isValid("CSCS!16");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		//Test invalid sixth character
		try {
			cnv.isValid("CSCS1!6");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		//Test invalid seventh character
		try {
			cnv.isValid("CSCS11!");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		//Test invalid final character
		try {
			cnv.isValid("CSCS116!");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
			
		//Test number in first character (invalid)
		try {
			cnv.isValid("1CSC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		//Test number in second character followed by letter (invalid)
		try {
			cnv.isValid("C1SC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		//Test number in third character followed by letter (invalid)
		try {
			cnv.isValid("CS1C116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		//Test number in fourth character followed by letter (invalid)
		try {
			cnv.isValid("CSCS1166");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		//Test letter in fifth character (invalid)
		try {
			cnv.isValid("CSCSC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		//Test number in fifth character followed by letter (invalid)
		try {
			cnv.isValid("CSCS1C6");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		//Test number in sixth character followed by letter (invalid)
		try {
			cnv.isValid("CSCS11C");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		//Test letter after suffix (invalid)
		try {
			cnv.isValid("CSCS116CC");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		//Test number after suffix (invalid)
		try {
			cnv.isValid("CSCS116C1");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		
		//Test valid six string (CSC116)
		try {
			cnv.isValid("CSC116");
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test valid six string with suffix (CSC116C)
		try {
			cnv.isValid("CSCS116C");
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test valid seven string (CSCS116)
		try {
			cnv.isValid("CSCS116");
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test valid seven string with suffix (CSCS116C)
		try {
			cnv.isValid("CSCS116C");
		} catch (InvalidTransitionException e) {
			fail();
		}
	}

}
