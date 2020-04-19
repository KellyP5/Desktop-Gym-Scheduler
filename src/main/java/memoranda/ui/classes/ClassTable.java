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

public class ClassTable {

    public JTable classTable;
    private DailyItemsPanel parentRef;
    public int room;
    ArrayList<GymClassEntity> classes;


    public ClassTable(DailyItemsPanel ref, int room)  {

        try{
            this.parentRef = ref;
            this.room = room;

            init();
        }catch(SQLException ecp){
            ecp.printStackTrace();
        }

    }

    private void init()throws SQLException{

        //this.classes = App.conn.getDrqTest().getAllClassesByDate(parentRef.currentDate);
        LocalDate date = LocalDate.of(2020, 4,11);
        this.classes = App.conn.getDrqTest().getAllClassesByDate(date);

        initTable();

        initActionListeners();

    }

    public void initTable()  {
        String[] columnNames = {"Time", "Room "+this.room};

        ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
        for(int i = 0;i< this.classes.size();i++){
            ArrayList<String> e = new ArrayList<>();
            e.add(this.classes.get(i).getStartDateTime().toString());
            e.add(Integer.toString(this.classes.get(i).getRoomNumber()));
            al.add(e);
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

    public void initActionListeners(){
        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d)  {
                System.out.println("public void dateChange(CalendarDate d): "+d.toString());
                //initTable();
            }
        });
    }



}
