package main.java.memoranda.ui;

import main.java.memoranda.History;
import main.java.memoranda.database.GymClassEntity;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.util.Local;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class AgendaPanel extends JPanel {

	boolean isActive = true;//used to determine if this panel is in use
	BorderLayout borderLayout1 = new BorderLayout();

	JPanel panel;

	JToolBar toolBar = new JToolBar();
	JButton historyBackB = new JButton();
	JButton historyForwardB = new JButton();

	JTable classesTable;
	JScrollPane scrollPane;

	public AgendaPanel(DailyItemsPanel _parentPanel) {
		try {
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {
		this.setLayout(borderLayout1);

		initToolBar();

		initTable();

		initEventListeners();


		this.panel = new JPanel(new BorderLayout());
		this.panel.add(scrollPane,BorderLayout.CENTER);

		this.add(panel, BorderLayout.CENTER);

	}

	public void initTable() throws SQLException {

		String[] columnNames = {"Date", "Duration", "Room Number", "Max Class Size","Belt Requirement"};

		ArrayList<ArrayList<String>> d = getClassDataForTrainer("jackj@gmail.com");
		//this code class getClassDataForTrainer which returns an array list of arraylists that contain strings that are the info for each
		//individual class.




		//this code converts our 2d ArrayList into a String[][]
		String[][] data = new String[d.size()][];
		for(int i = 0;i<d.size();i++){
			ArrayList<String> current = d.get(i);

			String[] copy = new String[current.size()];
			for(int j = 0;j< current.size();j++){
				copy[j] = current.get(j);
			}
			data[i] = copy;
		}



		//that way we can pass this into our JTable constructor
		classesTable = new JTable(data,columnNames);

		//Forces selection to be just 1 row at a time
		classesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classesTable.setFont(new Font(classesTable.getFont().getName(), classesTable.getFont().getStyle(), 15));
		classesTable.setRowHeight(20);


		//Set up event listener for selecting a row
		classesTable.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
			System.out.print((String) classesTable.getModel().getValueAt(classesTable.getSelectedRow(),0));
			System.out.print((String) classesTable.getModel().getValueAt(classesTable.getSelectedRow(),1));
			System.out.print((String) classesTable.getModel().getValueAt(classesTable.getSelectedRow(),2));
			System.out.print((String) classesTable.getModel().getValueAt(classesTable.getSelectedRow(),3));
			System.out.print((String) classesTable.getModel().getValueAt(classesTable.getSelectedRow(),4));
			System.out.println();
		});

		//allows you to select but prevents being able to edit
		classesTable.setDefaultEditor(Object.class,null);

		this.scrollPane = new JScrollPane(classesTable);
		this.add(this.scrollPane,BorderLayout.SOUTH);

	}

	ArrayList<ArrayList<String>> getClassDataForTrainer(String email) throws SQLException {
		ArrayList<GymClassEntity> gymClassEntities = App.connection.getDrqTest().getAllClassesTrainerIsTeachingByEmail(email);

		if(!gymClassEntities.isEmpty()) {
			ArrayList<ArrayList<String>> classInfo = new ArrayList<>();
			for (int i = 0; i < gymClassEntities.size(); i++) {
				//creates an array list of array lists that hold strings of information for each individual class for the passed trainer.
				if (gymClassEntities.get(i).getEndDateTime().toLocalDate().isAfter(LocalDateTime.now().toLocalDate())) {
					//the if statement will only add the information to the array if the class end time is after the current time.
					LocalTime startTime = gymClassEntities.get(i).getStartDateTime().toLocalTime();
					LocalTime endTime = gymClassEntities.get(i).getEndDateTime().toLocalTime();
					long duration = (startTime.until(endTime, ChronoUnit.MINUTES)); //subtracts the end time from the start time to get a duration.

					ArrayList<String> e = new ArrayList<>(); //creats an array list of one class's information.
					e.add(gymClassEntities.get(i).getStartDateTime().toLocalDate().toString());
					e.add(Long.toString(duration) + " Minutes");
					e.add(Integer.toString(gymClassEntities.get(i).getRoomNumber()));
					e.add(Integer.toString(gymClassEntities.get(i).getMaxClassSize()));
					e.add(gymClassEntities.get(i).getMinBeltEntityRequired().rank.toString().substring(0,1).toUpperCase() +
							gymClassEntities.get(i).getMinBeltEntityRequired().rank.toString().substring(1));
					classInfo.add(e); //adds the array list of class's information to the 2D arraylist.
				}

			}
			return classInfo; //returns the 2d array list.
		}
		else{
			return null;
		}

	}

	public String getTrainerBelt(String email) throws SQLException {
		String belt = App.connection.getDrqTest().getUserByEmail(email).getBelt().rank.toString();

		return belt;


	}

	void initToolBar() throws SQLException {
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
	void initEventListeners(){
		CurrentDate.addDateListener(new DateListener() {
			public void dateChange(CalendarDate d) {
				if (isActive)
					refresh(d);
			}
		});

		refresh(CurrentDate.get());

	}

	/**
	 * This should be the entry point into changing class data on selecting a calender date.
	 * @param date
	 */
	public void refresh(CalendarDate date) {

		//TODO update our scrollpane here, based on the date picked.

	}

	/**
	 * This method is called to set the panel as active or inactive.
	 * @param isa is the state the panel is being put in.
	 */
	public void setActive(boolean isa) {
		isActive = isa;
	}

}
