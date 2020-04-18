/**
 * EventsTable.java
 * Created on 09.03.2003, 9:52:02 Alex
 * Package: net.sf.memoranda.ui
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda.ui;

import java.awt.Component;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import main.java.memoranda.database.GymClassEntity;
import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.UserEntity;
import main.java.memoranda.database.util.DbCreateQueries;
import java.time.LocalDateTime;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import main.java.memoranda.Event;
import main.java.memoranda.EventsManager;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.util.Local;

/**
 * The type Events table.
 */
/*$Id: EventsTable.java,v 1.6 2004/10/11 08:48:20 alexeya Exp $*/
public class EventsTable extends JTable {

    public static final int EVENT = 100;
    public static final int EVENT_ID = 101;
    public static int room;

    /**
     * The Events.
     */
    Vector events = new Vector();
    ArrayList<GymClassEntity> classes;

    /**
     * Constructor for EventsTable.
     */
    public EventsTable()  {
        super();
        setModel(new EventsTableModel());
        initTable(CurrentDate.get());
        this.setShowGrid(true);
        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d)  {
                //updateUI();
                initTable(d);
            }
        });
    }

    public EventsTable(int room)  {
        super();
        this.room = room;
        setModel(new EventsTableModel());
        initTable(CurrentDate.get());
        this.setShowGrid(true);
        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d)  {
                //updateUI();
                initTable(d);
            }
        });
    }

    /**
     * Init table.
     *
     * @param d the d
     */
    public void initTable(CalendarDate d)  {
        events = (Vector)EventsManager.getEventsForDate(d);
        LocalDate date = LocalDate.of(d.getYear(), d.getMonth(), d.getDay());
        try {
            System.out.println("[DEBUG] Querying SQL DB from EventsTable.java");
            classes = App.conn.getDrq().getAllClassesByDate(date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getColumnModel().getColumn(0).setPreferredWidth(60);
        getColumnModel().getColumn(0).setMaxWidth(60);
        clearSelection();
        updateUI();
    }

    /**
     * Refresh.
     */
    public void refresh()  {
        initTable(CurrentDate.get());
    }

    public TableCellRenderer getCellRenderer(int row, int column) {
        return new javax.swing.table.DefaultTableCellRenderer() {

            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                Component comp;
                comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Event ev = (Event)getModel().getValueAt(row, EVENT);
                comp.setForeground(java.awt.Color.GRAY);
                if (CurrentDate.get().after(CalendarDate.today())) {
                    comp.setForeground(java.awt.Color.black);
                }
                else if (CurrentDate.get().equals(CalendarDate.today())) {
                    if (ev.getTime().after(new Date())) {
                        comp.setForeground(java.awt.Color.black);
                        //comp.setFont(new java.awt.Font("Dialog", 1, 12));
                        comp.setFont(comp.getFont().deriveFont(Font.BOLD));
                    }
                }
                return comp;
            }
        };

    }

    /**
     * The type Events table model.
     */
    class EventsTableModel extends AbstractTableModel {

        /**
         * The Column names.
         */
        String[] columnNames = {
                //   Local.getString("Task name"),
                //    Local.getString("Time"),
                //      Local.getString("Text")
                Local.getString("Time"),
                Local.getString("Room " + room),
        };

        /**
         * Instantiates a new Events table model.
         */
        EventsTableModel() {
            super();
        }

        public int getColumnCount() {
            return 2;
        }

        public int getRowCount() {
            int i;
            try {
                i = events.size();
            }
            catch(NullPointerException e) {
                i = 1;
            }
            return i;
        }


        //getValueAt class adds class info to the schedule
        public Object getValueAt(int row, int col) {
            Event ev = (Event)events.get(row);
            if (col == 0){
                //return ev.getHour()+":"+ev.getMinute();

                return ev.getTimeString();
            }

            else if (col == 1)
                return null;
            else if (col == 2)
                return null;
            else if (col == 3)
                return null;
            else if (col == 4)
                return null;
            else
                return ev;


        }

        public String getColumnName(int col) {
            return columnNames[col];
        }
    }
}
