/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * GUI class for the faculty schedule panel
 * @author Joey Schauer
 */
public class FacultySchedulePanel extends JPanel {
	/** ID number used for object serialization */
	private static final long serialVersionUID = 1L;
	/** JTable for displaying the faculty schedule */
	private JTable tableSchedule;
	/** JTable for displaying the course roll */
	private JTable tableRoll;
	/** the table model for the schedule */
	private CourseTableModel scheduleTableModel;
	/** the table model for the course roll */
	private StudentTableModel rollTableModel;
	/** lower border */
	private Border lowerEtched;
	/** JScrollPane for the faculty schedule */
	private JScrollPane scrollSchedule;
	/** JScroll Pane for the course roll */
	private JScrollPane scrollRoll;
	/** JPanel for displaying course details */
	private JPanel pnlCourseDetails;
	/** JLabel for course details name title */
	private JLabel lblNameTitle = new JLabel("Name: ");
	/** JLabel for course details section title */
	private JLabel lblSectionTitle = new JLabel("Section: ");
	/** JLabel for course details title title */
	private JLabel lblTitleTitle = new JLabel("Title: ");
	/** JLabel for course details instructor title */
	private JLabel lblInstructorTitle = new JLabel("Instructor: ");
	/** JLabel for course details credits title */ 
	private JLabel lblCreditsTitle = new JLabel("Credits: ");
	/** JLabel for course details meeting title */
	private JLabel lblMeetingTitle = new JLabel("Meeting: ");
	/** JLabel for course details enrollment cap title */
	private JLabel lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
	/** JLabel for course details open seats title */
	private JLabel lblOpenSeatsTitle = new JLabel("Open Seats: ");
	/** JLabel for course details waitlist title */
	private JLabel lblWaitlistTitle = new JLabel("Waitlist: ");
	/** JLabel for course details name */
	private JLabel lblName = new JLabel("");
	/** JLabel for course details section*/
	private JLabel lblSection = new JLabel("");
	/** JLabel for course details title */
	private JLabel lblTitle = new JLabel("");
	/** JLabel for course details instructor */
	private JLabel lblInstructor = new JLabel("");
	/** JLabel for course details credits */
	private JLabel lblCredits = new JLabel("");
	/** JLabel for course details meeting */
	private JLabel lblMeeting = new JLabel("");
	/** JLabel for course details enrollment cap */
	private JLabel lblEnrollmentCap = new JLabel("");
	/** JLabel for course details open seats */
	private JLabel lblOpenSeats = new JLabel("");
	/** JLabel for course details waitlist */
	private JLabel lblWaitlist = new JLabel("");
	/** the faculty's schedule */
	private FacultySchedule schedule;
	
	/**
	 * Constructor for FacultySchedulePanel
	 */
	public FacultySchedulePanel() {
		super(new GridBagLayout());
		
		schedule = null;
		
		lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		initFacultySchedule();
		initCourseDetails();
		initCourseRoll();
		updateTables();
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollSchedule, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlCourseDetails, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollRoll, c);
	}
	
	/**
	 * Initializes the faculty schedule table
	 */
	private void initFacultySchedule() {		
		scheduleTableModel = new CourseTableModel();
		tableSchedule = new JTable(scheduleTableModel) {
			private static final long serialVersionUID = 1L;
			
			/**
			 * Set custom tool tips for cells
			 * @param e MouseEvent that causes the tool tip
			 * @return tool tip text
			 */
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				
				if (rowIndex != -1 && realColumnIndex != -1) {
					return (String) scheduleTableModel.getValueAt(rowIndex, realColumnIndex);
				} else {
					return "";
				}
			}
		};
		
		tableSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableSchedule.setFillsViewportHeight(true);
		tableSchedule.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			/**
			 * Custom implementation for when a course is selected
			 * @param e is the list selection event
			 */
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String name = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 0).toString();
				String section = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 1).toString();
				Course c = RegistrationManager.getInstance().getCourseCatalog().getCourseFromCatalog(name, section);
				updateCourseDetails(c);
				rollTableModel.updateData(c.getCourseRoll().getCourseRoll());
			}
			
		});
		
		scrollSchedule = new JScrollPane(tableSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		scrollSchedule.setBorder(BorderFactory.createTitledBorder(lowerEtched, "Faculty Schedule"));
		scrollSchedule.setToolTipText("Faculty Schedule");
	}
	
	/**
	 * Initializes the course details table
	 */
	private void initCourseDetails() {
		pnlCourseDetails = new JPanel(new GridLayout(5, 1));
		JPanel pnlName = new JPanel(new GridLayout(1, 4));
		pnlName.add(lblNameTitle);
		pnlName.add(lblName);
		pnlName.add(lblSectionTitle);
		pnlName.add(lblSection);
		
		JPanel pnlTitle = new JPanel(new GridLayout(1, 1));
		pnlTitle.add(lblTitleTitle);
		pnlTitle.add(lblTitle);
		
		JPanel pnlInstructor = new JPanel(new GridLayout(1, 4));
		pnlInstructor.add(lblInstructorTitle);
		pnlInstructor.add(lblInstructor);
		pnlInstructor.add(lblCreditsTitle);
		pnlInstructor.add(lblCredits);
		
		JPanel pnlMeeting = new JPanel(new GridLayout(1, 1));
		pnlMeeting.add(lblMeetingTitle);
		pnlMeeting.add(lblMeeting);
		
		JPanel pnlEnrollment = new JPanel(new GridLayout(1, 6));
		pnlEnrollment.add(lblEnrollmentCapTitle);
		pnlEnrollment.add(lblEnrollmentCap);
		pnlEnrollment.add(lblOpenSeatsTitle);
		pnlEnrollment.add(lblOpenSeats);
		pnlEnrollment.add(lblWaitlistTitle);
		pnlEnrollment.add(lblWaitlist);
		
		pnlCourseDetails.add(pnlName);
		pnlCourseDetails.add(pnlTitle);
		pnlCourseDetails.add(pnlInstructor);
		pnlCourseDetails.add(pnlMeeting);
		pnlCourseDetails.add(pnlEnrollment);
		
		pnlCourseDetails.setBorder(BorderFactory.createTitledBorder(lowerEtched, "Course Details"));
		pnlCourseDetails.setToolTipText("Course Details");
	}
	
	/**
	 * Initializes the course roll table
	 */
	private void initCourseRoll() {
		rollTableModel = new StudentTableModel();
		tableRoll = new JTable(rollTableModel);
		tableRoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRoll.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableRoll.setFillsViewportHeight(true);
		
		scrollRoll = new JScrollPane(tableRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		scrollRoll.setBorder(BorderFactory.createTitledBorder(lowerEtched, "Course Roll"));
		scrollRoll.setToolTipText("Course Roll");
	}
	
	/**
	 * Updates the faculty schedule and course roll tables
	 */
	public void updateTables() {
		if (RegistrationManager.getInstance().getCurrentUser() instanceof Faculty) {
			Faculty faculty = (Faculty) RegistrationManager.getInstance().getCurrentUser();
			schedule = faculty.getSchedule();
			scheduleTableModel.updateData();
		}
	}
	
	/**
	 * Updates the course details with the given course
	 * @param course is the course to get details from
	 */
	private void updateCourseDetails(Course course) {
		if (course == null) {
			lblName.setText("");
			lblSection.setText("");
			lblTitle.setText("");
			lblInstructor.setText("");
			lblCredits.setText("");
			lblMeeting.setText("");
			lblEnrollmentCap.setText("");
			lblOpenSeats.setText("");
			lblWaitlist.setText("");
		} else {
			lblName.setText(course.getName());
			lblSection.setText(course.getSection());
			lblTitle.setText(course.getTitle());
			lblInstructor.setText(course.getInstructorId());
			lblCredits.setText("" + course.getCredits());
			lblMeeting.setText(course.getMeetingString());
			lblEnrollmentCap.setText("" + course.getCourseRoll().getEnrollmentCap());
			lblOpenSeats.setText("" + course.getCourseRoll().getOpenSeats());
			lblWaitlist.setText("" + course.getCourseRoll().getNumberOnWaitlist());
		}
	}
	
	/**
	 * Displays a list of courses for the faculty member
	 * @author Joey Schauer
	 */
	private class CourseTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Name", "Section", "Title", "Meeting Days", "Open Seats"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructor for CourseTableModel
		 */
		public CourseTableModel() {
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model
		 */
		public void updateData() {
			if (schedule == null) {
				data = new String[0][5];
			} else {
				data = schedule.getScheduledCourses();
			}
		}
	}
	
	/**
	 * Displays the course roll for the course
	 * @author Joey Schauer
	 */
	private class StudentTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "Student ID"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructor for StudentTableModel
		 */
		public StudentTableModel() {
			updateData(null);
		}

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model
		 */
		public void updateData(Object[][] courseRoll) {
			if (courseRoll == null) {
				data = new String[0][3];
			} else {
				data = courseRoll;
				FacultySchedulePanel.this.repaint();
				FacultySchedulePanel.this.validate();
			}
		}
	}
}
