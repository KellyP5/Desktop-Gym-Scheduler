package main.java.memoranda.ui.classes;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import main.java.memoranda.database.entities.GymClassEntity;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.ui.App;
import main.java.memoranda.ui.DailyItemsPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import main.java.memoranda.util.Local;

public class ClassTable extends DefaultTableModel {

    private LocalDate date;
    public JTable classTable; // This is public so we can assign the parent the JTable
    private DailyItemsPanel parentRef;
    private int room;
    private ArrayList<GymClassEntity> classes;
    DefaultTableModel dm;
    private GymClassEntity selectedClass;

    /**
     * Constructor
     * @param ref Uses DailyItemspanel to snag CurrentDate attribute
     * @param room Is the room number of the class table
     */
    public ClassTable(DailyItemsPanel ref, int room) throws SQLException {

        this.parentRef = ref;
        this.room = room;
        init();

    }

    /**
     * Main initialization code for our ClassTable class
     * */
    private void init() throws SQLException {

        LocalDate date = LocalDate.of(parentRef.currentDate.getYear(),
                parentRef.currentDate.getMonth()+1,
                parentRef.currentDate.getDay());
        initTable(date);

        // Sets all open classes to WHITE. Sets closed classes to RED
        classTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean
                                                           isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                classTable.getSelectionModel().addListSelectionListener(listSelectionEvent -> {

                });
                int colFour = Integer.parseInt((String) classTable.getValueAt(row, 4));
                int colThree = Integer.parseInt((String) classTable.getValueAt(row, 3));
                if (colFour >= colThree) {
                    setBackground(Color.RED); // Full class
                } else {
                    setBackground(Color.WHITE); // Open class
                }
                return this;
            }
        });
        initActionListeners();
    }

    /**
     * Initializes the table
     * @param pDate the date the table will use.
     */
    private void initTable(LocalDate pDate) throws SQLException {
        //this.classes = App.conn.getDrqTest().getAllClassesByDate(parentRef.currentDate);
        //LocalDate date = LocalDate.of(2020, 4,11);
        this.date = pDate;
        try{
            this.classes = App.conn.getDrq().getAllClassesByDate(pDate);
        }catch(SQLException c){
            c.printStackTrace();
        }

        String[] columnNames = {"Time", "Trainer", "MinBelt","MaxSize","NumStudents"};

        Collections.sort(this.classes);

        ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
        for(int i = 0;i< this.classes.size();i++){
            if(this.classes.get(i).getRoomNumber()==this.room) {
                ArrayList<String> e = new ArrayList<>();
                e.add(this.classes.get(i).getStartDateTime().toString()); //time
                e.add(this.classes.get(i).getTrainerEmail());//trainer
                e.add(this.classes.get(i).getMinBeltEntityRequired().toString());//MinBelt
                e.add(Integer.toString(this.classes.get(i).getMaxClassSize()));//MaxSize
                int num = this.classes.get(i).getNumberOfStudentsEnrolledInClass(this.classes.get(i).getId());
                e.add(Integer.toString(num));
                //e.add(Integer.toString(this.classes.get(i).getNumStudents()));
                al.add(e);
            }
        }

        String[][] data = new String[al.size()][];
        for(int i = 0;i<al.size();i++){
            ArrayList<String> current = al.get(i);

            String[] copy = new String[current.size()];
            for(int j = 0;j< current.size();j++){
                copy[j] = current.get(j);
            }
            data[i] = copy;
        }

        // Makes it so the user can't edit the table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.classTable = new JTable();
        this.classTable.setRowSelectionAllowed(true);
        this.classTable.setSelectionForeground(Color.BLUE);
        this.classTable.setModel(tableModel);

        // Format columns so text fits
        TableColumnModel tcm = classTable.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(3).setPreferredWidth(50);
        tcm.getColumn(4).setPreferredWidth(70);
    }

    /**
     * Initializes all the action listeneres for this class.
     */
    private void initActionListeners(){
        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d) throws SQLException {
                System.out.println("public void dateChange(CalendarDate d): "+d.toString());
                date = LocalDate.of(d.getYear(),d.getMonth()+1,d.getDay());
                refresh();
            }
        });

        /**
         * Registers when a class is selected and stores the class
         */
        classTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                classSelected();
            }
        });
    }

    /**
     * This method is called when a date is changed. It refreshes
     * that tables by deleting all the elements, and then inserting
     * all the new elements.
     */
    void refresh() throws SQLException {

        try{
            this.classes = App.conn.getDrq().getAllClassesByDate(date);

        }catch(SQLException ecp){

            ecp.printStackTrace();
        }

        //remove all rows
        dm = (DefaultTableModel) this.classTable.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }

        Collections.sort(this.classes);

        //add all the rows from the list
        for(int i = 0;i< this.classes.size();i++){
            //test if this classtable is the table to display the class
            if(this.classes.get(i).getRoomNumber()==this.room){
                int num = this.classes.get(i).getNumberOfStudentsEnrolledInClass(this.classes.get(i).getId());
                dm.addRow(new Object[]{this.classes.get(i).getStartDateTime().toString(),
                        this.classes.get(i).getTrainerEmail(),
                        this.classes.get(i).getMinBeltEntityRequired(),
                        Integer.toString(this.classes.get(i).getMaxClassSize()),
                        Integer.toString(num)});
            }
        }
    }

    /**
     * Called to set the currently selected class. Only allows one class to be
     * selected at a time and that class is stored in the parent Daily Items Panel
     * For access in other classes.
     */
    private void classSelected() {
        if (classTable.getSelectedRow() > -1) {
            LocalDate date = Local.convertToLocalDate(classTable.getValueAt(classTable.getSelectedRow(), 0).toString());
            double time = Local.convertToDoubleTime(classTable.getValueAt(classTable.getSelectedRow(), 0).toString());
            String email = classTable.getValueAt(classTable.getSelectedRow(), 2).toString();
            try {
                selectedClass = App.conn.getDrq().getAllClassesByDateTime(date, time, room).get(0);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            parentRef.setSelectedClass(selectedClass);
        }
    }
}
