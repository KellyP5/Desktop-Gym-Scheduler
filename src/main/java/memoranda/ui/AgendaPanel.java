package main.java.memoranda.ui;

import main.java.memoranda.*;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.gym.Class;
import main.java.memoranda.gym.ManageClasses;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class AgendaPanel extends JPanel {

	// This is the panel that is manipulated. This is instantiated through the AgendaPanel Constructor
	DailyItemsPanel parentPanel = null;

	JPanel agendaPanel = new JPanel();

	BorderLayout borderLayout1 = new BorderLayout();
	JButton historyBackB = new JButton();
	JToolBar toolBar = new JToolBar();
	JButton historyForwardB = new JButton();

	JTable classesTable = new JTable(0,5);
    JScrollPane scrollPane = new JScrollPane();

    Collection expandedTasks;
    String gotoTask = null;
    boolean isActive = true;

    public AgendaPanel(DailyItemsPanel _parentPanel) {
		try {
			parentPanel = _parentPanel;
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
			ex.printStackTrace();
		}
	}

    void jbInit() throws Exception {
		expandedTasks = new ArrayList();

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

		this.setLayout(borderLayout1);
		scrollPane.getViewport().setBackground(Color.white);

		this.add(scrollPane, BorderLayout.CENTER);
		toolBar.add(historyBackB, null);
		toolBar.add(historyForwardB, null);
		toolBar.addSeparator(new Dimension(8, 24));

		this.add(toolBar, BorderLayout.NORTH);

		CurrentDate.addDateListener(new DateListener() {
			public void dateChange(CalendarDate d) {
				if (isActive)
					refresh(d);
			}
		});
		CurrentProject.addProjectListener(new ProjectListener() {

			public void projectChange(
					Project prj,
					NoteList nl,
					TaskList tl,
					ResourcesList rl) {
			}

			public void projectWasChanged() {
				if (isActive)
					refresh(CurrentDate.get());
			}});
		EventsScheduler.addListener(new EventNotificationListener() {
			public void eventIsOccured(main.java.memoranda.Event ev) {
				if (isActive)
					refresh(CurrentDate.get());
			}

			public void eventsChanged() {
				if (isActive)
					refresh(CurrentDate.get());
			}
		});
		refresh(CurrentDate.get());


	}

    public void refresh(CalendarDate date) {

    	//TODO update our scrollpane here, based on the date picked.


		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setAgendaPage();
				if(gotoTask != null) {

					scrollPane.setViewportView(agendaPanel);
					Util.debug("Set view port to " + gotoTask);
				}
			}
		});
		scrollPane.setViewportView(agendaPanel);

		Util.debug("Summary updated.");
	}

	public void setAgendaPage(){
    	JPanel eventsPanel = new JPanel(new GridLayout());

		agendaPanel.removeAll();
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		scrollPane.setMinimumSize(screensize);
		
		classesTable.getColumnModel().getColumn(0).setHeaderValue("Date");
		classesTable.getColumnModel().getColumn(1).setHeaderValue("Duration");
		classesTable.getColumnModel().getColumn(2).setHeaderValue("Room Number");
		classesTable.getColumnModel().getColumn(3).setHeaderValue("Current Class Size");
		classesTable.getColumnModel().getColumn(4).setHeaderValue("Belt Requirement");
		classesTable.getColumnModel().getColumn(4).setHeaderValue("Belt Requirement");
		classesTable.getColumnModel().getColumn(0).setMaxWidth(Math.round(screensize.width * 0.25f));



    	agendaPanel.setLayout(new GridLayout(0,1));
		classesTable.setRowHeight(50);
		JScrollPane classScroller = new JScrollPane(classesTable);

    	agendaPanel.add(classScroller);

    	agendaPanel.add(eventsPanel);




	}

	public ArrayList<Class> getClassesForTrainer(){
		createTestClass();
		String currentUser = "alex.mack@mail.com";
		ArrayList<Class> resultingClasses = null;
	ManageClasses manage = new ManageClasses();
	ArrayList<Class> classes = manage.getClasses();

	for(int i = 0; i < classes.size(); i++){
		if(classes.get(i).getTrainer().equals(currentUser)){
			resultingClasses.add(classes.get(i));
		}
	}
	return resultingClasses;
	}

	private void createTestClass() {
		//ConsoleGymApp gym = new ConsoleGymApp();
		//gym.run();
    	/*BeltEntity belt = new BeltEntity(BeltEntity.Rank.black1);
    	belt.rank = BeltEntity.Rank.blue;
		GymClassEntity entity = new GymClassEntity(5, 8, LocalDateTime.MAX, LocalDateTime.MAX, "alex.mack@mail.com", 5, belt, "alex.mack@mail.com");
		*/
	}

    public void setActive(boolean isa) {
		isActive = isa;
	}

}
