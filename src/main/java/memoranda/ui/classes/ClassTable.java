package main.java.memoranda.ui.classes;

import main.java.memoranda.database.GymClassEntity;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.ui.App;
import main.java.memoranda.ui.DailyItemsPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClassTable {

    private LocalDate date;
    public JTable classTable; // This is public so we can assign the parent the JTable
    private DailyItemsPanel parentRef;
    private int room;
    private ArrayList<GymClassEntity> classes;

    /**
     * Constructor
     * @param ref Uses DailyItemspanel to snag CurrentDate attribute
     * @param room Is the room number of the class table
     */
    public ClassTable(DailyItemsPanel ref, int room)  {

        this.parentRef = ref;
        this.room = room;

        init();


    }

    /**
     * Main initialization code for our ClassTable class
     * */
    private void init() {

        LocalDate date = LocalDate.of(parentRef.currentDate.getYear(),
                parentRef.currentDate.getMonth()+1,
                parentRef.currentDate.getDay());
        initTable(date);

        initActionListeners();

    }

    private String convertStartDateTime(String dateTime) {
        return dateTime.substring(dateTime.length() - 5);
    }

    /**
     * Initializes the table
     * @param pDate the date the table will use.
     */
    private void initTable(LocalDate pDate)  {
        //this.classes = App.conn.getDrqTest().getAllClassesByDate(parentRef.currentDate);
        //LocalDate date = LocalDate.of(2020, 4,11);
        try{
            this.classes = App.conn.getDrq().getAllClassesByDate(pDate);
        }catch(SQLException c){
            c.printStackTrace();
        }

        String[] columnNames = {"Time", "Trainer", "MinBelt","MaxSize"};

        Collections.sort(this.classes);

        ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
        for(int i = 0;i< this.classes.size();i++){
            if(this.classes.get(i).getRoomNumber()==this.room) {
                ArrayList<String> e = new ArrayList<>();
                e.add(convertStartDateTime(this.classes.get(i).getStartDateTime().toString())); //time
                //e.add(this.classes.get(i).getStartDateTime().toString());//time
                e.add(this.classes.get(i).getTrainerEmail());//trainer
                e.add(this.classes.get(i).getMinBeltEntityRequired().toString());//MinBelt
                e.add(Integer.toString(this.classes.get(i).getMaxClassSize()));//MaxSize
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

        this.classTable = new JTable( new DefaultTableModel(data,columnNames));
    }

    /**
     * Initializes all the action listeneres for this class.
     */
    private void initActionListeners(){
        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d)  {
                System.out.println("public void dateChange(CalendarDate d): "+d.toString());
                date = LocalDate.of(d.getYear(),d.getMonth()+1,d.getDay());
                refresh();
            }
        });
    }

    /**
     * This method is called when a date is changed. It refreshes
     * that tables by deleting all the elements, and then inserting
     * all the new elements.
     */
    private void refresh(){

        try{
            this.classes = App.conn.getDrq().getAllClassesByDate(date);

        }catch(SQLException ecp){

            ecp.printStackTrace();
        }

        //remove all rows
        DefaultTableModel dm = (DefaultTableModel) this.classTable.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }

        //add all the rows from the list
        for(int i = 0;i< this.classes.size();i++){
            //test if this classtable is the table to display the class
            if(this.classes.get(i).getRoomNumber()==this.room){
                dm.addRow(new Object[]{this.classes.get(i).getStartDateTime().toString(),
                        this.classes.get(i).getTrainerEmail(),
                        this.classes.get(i).getMinBeltEntityRequired(),
                        Integer.toString(this.classes.get(i).getMaxClassSize())});
            }
        }

    }

}
