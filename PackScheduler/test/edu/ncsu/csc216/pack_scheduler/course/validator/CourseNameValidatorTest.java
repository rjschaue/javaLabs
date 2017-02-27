package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for CourseNameValidator
 * @author Joey Schauer
 */
public class CourseNameValidatorTest {

	/**
	 * Test for isValid method
	 */
	@Test
	public void testIsValid() {
		CourseNameValidator cnv = new CourseNameValidator();
		
		//Test invalid first character
		try {
			cnv.isValid("!CSC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test invalid second character
		try {
			cnv.isValid("C!SC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test invalid third character
		try {
			cnv.isValid("CS!C116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test invalid fourth character
		try {
			cnv.isValid("CSC!116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test invalid fifth character
		try {
			cnv.isValid("CSCS!16");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test invalid sixth character
		try {
			cnv.isValid("CSCS1!6");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test invalid seventh character
		try {
			cnv.isValid("CSCS11!");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test invalid final character
		try {
			cnv.isValid("CSCS116!");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test number in first character (invalid)
		try {
			cnv.isValid("1CSC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test number in second character followed by letter (invalid)
		try {
			cnv.isValid("C1SC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test number in third character followed by letter (invalid)
		try {
			cnv.isValid("CS1C116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test number in fourth character followed by letter (invalid)
		try {
			cnv.isValid("CSCS1166");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test letter in fifth character (invalid)
		try {
			cnv.isValid("CSCSC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test number in fifth character followed by letter (invalid)
		try {
			cnv.isValid("CSCS1C6");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test number in sixth character followed by letter (invalid)
		try {
			cnv.isValid("CSCS11C");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test letter after suffix (invalid)
		try {
			cnv.isValid("CSCS116CC");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test number after suffix (invalid)
		try {
			cnv.isValid("CSCS116C1");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		
		cnv = new CourseNameValidator();
		//Test valid six string (CSC116)
		try {
			assertTrue(cnv.isValid("CSC116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		cnv = new CourseNameValidator();
		//Test valid six string with suffix (CSC116C)
		try {
			assertTrue(cnv.isValid("CSCS116C"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		cnv = new CourseNameValidator();
		//Test valid seven string (CSCS116)
		try {
			assertTrue(cnv.isValid("CSCS116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		cnv = new CourseNameValidator();
		//Test valid seven string with suffix (CSCS116C)
		try {
			assertTrue(cnv.isValid("CSCS116C"));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}

}