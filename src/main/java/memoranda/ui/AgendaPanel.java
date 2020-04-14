package main.java.memoranda.ui;

import main.java.memoranda.History;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.util.Local;

import javax.swing.*;
import java.awt.*;
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

	public void initTable(){

		String[] columnNames = {"Date", "Duration", "Room Number", "Current Class Size","Belt Requirement"};

		ArrayList<ArrayList<String>> d = new ArrayList<ArrayList<String>>();

		//hardcoded dummy data these are our elements
		for(int i = 0;i< 100;i++){
			ArrayList<String> e = new ArrayList<>();
			e.add("c - 0 r - "+i);
			e.add("c - 1 r - "+i);
			e.add("c - 2 r - "+i);
			e.add("c - 3 r - "+i);
			e.add("c - 4 r - "+i);
			d.add(e);
		}

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
		//this.add(this.scrollPane,BorderLayout.SOUTH);

	}

	void initToolBar(){
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
