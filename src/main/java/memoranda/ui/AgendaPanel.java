package main.java.memoranda.ui;

import main.java.memoranda.History;
import main.java.memoranda.database.GymClassEntity;
import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.UserEntity;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.gym.Gym;
import main.java.memoranda.util.Local;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 * Class: AgendaPanel
 * Description: Creates the AgendaPanel and populates with traner's scheduled classes on or after
 * the date selected on the calendar.
 */
public class AgendaPanel extends JPanel {

	private final String[] _COLUMNNAMES = {"Date", "Duration", "Room Number", "Max Class Size", "Belt Requirement"};

	/**
	 * The Is active.
	 */
	boolean isActive = true;//used to determine if this panel is in use
	/**
	 * The Border layout 1.
	 */
	BorderLayout borderLayout1 = new BorderLayout();

	/**
	 * The Panel.
	 */
	JPanel panel;

	/**
	 * The Tool bar.
	 */
	JToolBar toolBar = new JToolBar();
	/**
	 * The History back b.
	 */
	JButton historyBackB = new JButton();
	/**
	 * The History forward b.
	 */
	JButton historyForwardB = new JButton();

	/**
	 * The Classes table.
	 */
	JTable classesTable;
	/**
	 * The Scroll pane.
	 */
	JScrollPane scrollPane;

	/**
	 * Instantiates a new Agenda panel.
	 *
	 * @param _parentPanel the parent panel
	 */
	public AgendaPanel(DailyItemsPanel _parentPanel) {
		try {
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
			ex.printStackTrace();
		}
	}

	/**
	 * Jb init.
	 *
	 * @throws Exception the exception
	 */
	void jbInit() throws Exception {
		this.setLayout(borderLayout1);

		initToolBar();

		initTable();

		initEventListeners();


		this.panel = new JPanel(new BorderLayout());
		this.panel.add(scrollPane, BorderLayout.CENTER);

		this.add(panel, BorderLayout.CENTER);

		refresh(CurrentDate.get());
	}

	/**
	 * Init table.
	 * Creates the schedule tables and the listeners for all columns.
	 *
	 * @throws SQLException the sql exception
	 */
	public void initTable() throws SQLException {

		ArrayList<ArrayList<String>> d = new ArrayList<>();
		String[][] data = new String[0][];
		for (int i = 0; i < d.size(); i++) {
			ArrayList<String> current = d.get(i);

			String[] copy = new String[current.size()];
			for (int j = 0; j < current.size(); j++) {
				copy[j] = current.get(j);
			}
			data[i] = copy;
		}
		classesTable = new JTable(data, _COLUMNNAMES);
		classesTable.setDefaultEditor(Object.class, null);

		this.scrollPane = new JScrollPane(classesTable);
		this.add(this.scrollPane, BorderLayout.SOUTH);

		//Forces selection to be just 1 row at a time
		classesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classesTable.setFont(new Font(classesTable.getFont().getName(), classesTable.getFont().getStyle(), 15));
		classesTable.setRowHeight(20);


		//Set up event listener for selecting a row
		classesTable.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
			System.out.print((String) classesTable.getModel().getValueAt(classesTable.getSelectedRow(), 0));
			System.out.print((String) classesTable.getModel().getValueAt(classesTable.getSelectedRow(), 1));
			System.out.print((String) classesTable.getModel().getValueAt(classesTable.getSelectedRow(), 2));
			System.out.print((String) classesTable.getModel().getValueAt(classesTable.getSelectedRow(), 3));
			System.out.print((String) classesTable.getModel().getValueAt(classesTable.getSelectedRow(), 4));
			System.out.println();
		});
	}

	/**
	 * Gets class data for trainer.
	 *
	 * @param email                Trainer email
	 * @param selectedCalendarDate the selected calendar date
	 * @return the class data for trainer
	 * @throws SQLException sql exception if database connection fails
	 */
	private ArrayList<ArrayList<String>> _getClassDataForTrainer(String email, LocalDate selectedCalendarDate) throws SQLException {
		//TEMPORARY will need to be changed to the actual once the logged user can be checked
/*		ArrayList<GymClassEntity> gymClassEntities = App.conn.getDrq().
				getAllClassesTrainerIsTeachingByEmail(email);*/
		ArrayList<GymClassEntity> gymClassEntities = Gym.getEnrolledClassesByEmailAndDate(email,selectedCalendarDate);

		if (gymClassEntities!=null) {
			ArrayList<ArrayList<String>> classInfo = new ArrayList<>();
			for (int i = 0; i < gymClassEntities.size(); i++) {
				/*creates an array list of array lists that hold strings of information for each individual
				*class for the passed trainer.*/
				if (gymClassEntities.get(i).getEndDateTime().toLocalDate().isAfter(selectedCalendarDate.minusDays(1))) {
					//the if statement will only add the information to the array if the class end time is after the current time.
					LocalTime startTime = gymClassEntities.get(i).getStartDateTime().toLocalTime();
					LocalTime endTime = gymClassEntities.get(i).getEndDateTime().toLocalTime();
					//subtracts the end time from the start time to get a duration.
					long duration = (startTime.until(endTime, ChronoUnit.MINUTES));

					ArrayList<String> e = new ArrayList<>(); //creats an array list of one class's information.
					e.add(gymClassEntities.get(i).getStartDateTime().toLocalDate().toString());
					e.add(Long.toString(duration) + " Minutes");
					e.add(Integer.toString(gymClassEntities.get(i).getRoomNumber()));
					e.add(Integer.toString(gymClassEntities.get(i).getMaxClassSize()));
					e.add(gymClassEntities.get(i).getMinBeltEntityRequired().rank.toString().substring(0, 1).toUpperCase() +
							gymClassEntities.get(i).getMinBeltEntityRequired().rank.toString().substring(1));
					classInfo.add(e); //adds the array list of class's information to the 2D arraylist.
				}

			}
			return classInfo; //returns the 2d array list of trainer's class info.
		} else {
			return null;
		}
	}

	/**
	 * Gets trainer belt.
	 *
	 * @param email the email
	 * @return the trainer belt
	 * @throws SQLException the sql exception
	 */
	public String getTrainerBelt(String email) throws SQLException {
		//TEMPORARY will need to be changed to the real DB once logged users can be checked.
		UserEntity user = App.conn.getDrq().getUserByEmail(email);
		if (user == null){
			throw new SQLException("User " + email + " does not exist in the database!");
		}
		String belt = App.conn.getDrq().getUserByEmail(email).getBelt().rank.toString();
		return belt;

	}

	/**
	 * Init tool bar.
	 *
	 * @throws SQLException the sql exception
	 */

	//Functionality will need to be fixed in seperate user story
	void initToolBar() {
		toolBar.setFloatable(false);


		historyBackB.setAction(History.historyBackAction);
		historyBackB.setFocusable(false);
		historyBackB.setBorderPainted(false);
		historyBackB.setToolTipText(Local.getString("History back"));
		historyBackB.setRequestFocusEnabled(false);
		historyBackB.setPreferredSize(new Dimension(24, 24));
		historyBackB.setMinimumSize(new Dimension(24, 24));
		historyBackB.setMaximumSize(new Dimension(24, 24));
		historyBackB.setText("");

		historyForwardB.setAction(History.historyForwardAction);
		historyForwardB.setBorderPainted(false);
		historyForwardB.setFocusable(false);
		historyForwardB.setPreferredSize(new Dimension(24, 24));
		historyForwardB.setRequestFocusEnabled(false);
		historyForwardB.setToolTipText(Local.getString("History forward"));
		historyForwardB.setMinimumSize(new Dimension(24, 24));
		historyForwardB.setMaximumSize(new Dimension(24, 24));
		historyForwardB.setText("");

		toolBar.add(historyBackB, null);
		toolBar.add(historyForwardB, null);
		//toolBar.addSeparator(new Dimension(8, 24));
		this.add(toolBar, BorderLayout.NORTH);
	}

	/**
	 * Sets up our event listener for keeping our date current and what we are displaying to the user.
	 */
	void initEventListeners() {
		CurrentDate.addDateListener(new DateListener() {
			public void dateChange(CalendarDate d) {
				if (isActive)
					refresh(d);
			}
		});
	}

	/**
	 * This is the entry point into changing class data on selecting a calender date.
	 *
	 * @param date the date
	 */
	public void refresh(CalendarDate date) {
		updateTrainerBeltDisplay();
		String[][] data = null;
		LocalDate convertedDate = _convertDateToLocalDateTime(date);


		classesTable.removeAll();

		//NOTE: A check will need to be put here to check and see if the logged user is a trainer or not.
		//If they are not a trainer, the table should not be displayed.

		ArrayList<ArrayList<String>> d = null;
		ArrayList<ArrayList<String>> temp;
		try {
			UserEntity user = LoginBox.getUser();
			temp = _getClassDataForTrainer(user.getEmail(), convertedDate);
			if (temp != null && !temp.isEmpty()) {
				d = temp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//this code converts our 2d ArrayList into a String[][]
		if (d != null) {
			data = new String[d.size()][];
			for (int i = 0; i < d.size(); i++) {
				ArrayList<String> current = d.get(i);

				String[] copy = new String[current.size()];
				for (int j = 0; j < current.size(); j++) {
					copy[j] = current.get(j);
				}
				data[i] = copy;
			}
		}

		//that way we can pass this into our JTable constructor
		if (data == null) {
			//columns will not display info when user is not logged in (will be functional when logged user can be checked)
			classesTable.setModel(new DefaultTableModel(null, _COLUMNNAMES));
		} else {
			//loads the trainer's class data to the table
			classesTable.setModel(new DefaultTableModel(data, _COLUMNNAMES));
		}
	}

	/**
	 * This method is called to convert the calendar date to a LocalDate format
	 *
	 * @param date The date being selected on the calendar.
	 * @return newDate returns the date selected on the calendar to a LocalDate format
	 */

	private LocalDate _convertDateToLocalDateTime(CalendarDate date) {

		LocalDate newDate = LocalDate.of(date.getYear(), date.getMonth() + 1, date.getDay());
		return newDate;
	}

	/**
	 * This method updates to Agenda page tool bar to display the trainer's belt color.
	 * It will need to be updated when we can check to see who the currently logged user is
	 *
	 * @throws SQLException the sql exception
	 */

	private void updateTrainerBeltDisplay(){

		//A check will need to be added here to check to see if the currently logged
		//user is a trainer or not.

		JLabel instructorBelt = new JLabel();
		Font labelFont = instructorBelt.getFont(); //creates Font to change font size
		instructorBelt.setFont(new Font(labelFont.getName(), labelFont.getStyle(), 20)); //sets font size
		UserEntity user = LoginBox.getUser();
		String beltText = user.getTrainingBelt().toString();
		instructorBelt.setText("User: " + user.getFirstName() + " " + user.getLastName() + " Belt: " +
				beltText.substring(0,1).toUpperCase() + beltText.substring(1));
		//add right padding to belt display
		instructorBelt.setBorder(BorderFactory.createEmptyBorder(0,0,0,25));

		toolBar.removeAll(); //clears the toolbar so multiple jlabels aren't added when page reloads
		//initToolBar(); // reinitiates tool bar
		toolBar.add(Box.createHorizontalGlue()); //moves text to the far right of task bar
		toolBar.add(instructorBelt); //adds the instructor belt jlabel to toolbar
	}

	/**
	 * This method is called to set the panel as active or inactive.
	 *
	 * @param isa is the state the panel is being put in.
	 */
	public void setActive(boolean isa) {
		isActive = isa;
	}
}
