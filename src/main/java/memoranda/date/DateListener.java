package main.java.memoranda.date;

import java.sql.SQLException;

/**
 * The interface Date listener.
 */
/*$Id: DateListener.java,v 1.2 2004/01/30 12:17:41 alexeya Exp $*/
public interface DateListener {

    /**
     * Date change.
     *
     * @param date the date
     */
    void dateChange(CalendarDate date) throws SQLException;

}