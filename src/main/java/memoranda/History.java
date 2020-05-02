/**
 * History.java
 * Created on 23.02.2003, 0:27:33 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.sql.SQLException;
import java.util.Vector;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import main.java.memoranda.util.Local;

/**
 * The type History.
 */
/*$Id: History.java,v 1.7 2006/10/31 15:34:14 hglas Exp $*/
public class History {

    /**
     * The List.
     */
    static Vector _list = new Vector();
    /**
     * The P.
     */
    static int p = -1;
    /**
     * The History listeners.
     */
    static Vector historyListeners = new Vector();
    /**
     * The Next.
     */
    static Object next = null;
    /**
     * The Prev.
     */
    static Object prev = null;

    /**
     * Add.
     *
     * @param item the item
     */
    public static void add(HistoryItem item) {
        if (prev != null)   
            if (item.equals(prev)) return;
        if (p < _list.size() - 1)
            _list.setSize(p + 1);
        _list.add(item);
        p = _list.size() - 1;
        if (p > 0)   
            prev = _list.get(p-1);
        else
            prev = null;
        next = null;
        historyBackAction.update();
        historyForwardAction.update();  
        /*System.out.println();
        for (int i = 0; i < _list.size(); i++)
            System.out.println(((HistoryItem)_list.get(i)).getDate().toString());
        System.out.println(item.getDate().toShortString()+ " added");*/
        if (_list.size() > 99)
            _list.remove(0);     
    }

    /**
     * Roll back history item.
     *
     * @return the history item
     */
    public static HistoryItem rollBack() {
        Object n = prev;        
        if (p > 1) {                          
            p--;
            prev = _list.get(p-1);
        } 
        else if (p > 0) {
            p--;
            prev = null;
        }
        else 
            prev = null;      
        if (p < _list.size() - 1)
            next = _list.get(p+1);
        else
            next = null;         
        return (HistoryItem)n;
    }

    /**
     * Roll forward history item.
     *
     * @return the history item
     */
    public static HistoryItem rollForward() {
        Object n = next;        
        if (p < _list.size() - 1) {
            p++;
            if (p == 1) 
                p++;
            next = _list.get(p);            
        }        
        else
            next = null;
        if (p > 0)
            prev = _list.get(p-1);
        else 
            prev = null;
        return (HistoryItem)n;    
    }

    /**
     * Can roll back boolean.
     *
     * @return the boolean
     */
    public static boolean canRollBack() {
        return prev != null;
    }

    /**
     * Can roll forward boolean.
     *
     * @return the boolean
     */
    public static boolean canRollForward() {
        return next != null;
    }

    /**
     * Add history listener.
     *
     * @param hl the hl
     */
    public static void addHistoryListener(HistoryListener hl) {
        historyListeners.add(hl);
    }

    /**
     * Remove project history.
     *
     * @param prj the prj
     */
    public static void removeProjectHistory(Project prj) {
        Vector list = new Vector();
        String id;
        
        for (int i = 0; i < _list.size(); i++) {
            id = (((HistoryItem) _list.elementAt(i)).getProject()).getID();
            if (id.equals(prj.getID())) {
                list.add(_list.elementAt(i));
                p--;
                if (_list.elementAt(i).equals(prev)) {
                    if (p > 0) prev = _list.get(p - 1);
                    else prev = null;
                }
            }
        }
        if (!list.isEmpty()) {
            _list.removeAll(list);
            if (p < 0) {
                p = 0;
            }
            _list.setSize(p);
            next = null;
            historyBackAction.update();
            historyForwardAction.update();
        }
    }

    private static void notifyListeners(HistoryItem n) throws SQLException {
        for (int i = 0; i < historyListeners.size(); i++)            
                 ((HistoryListener) historyListeners.get(i)).historyWasRolledTo(n);
    }

    /**
     * The constant historyBackAction.
     */
    public static HistoryBackAction historyBackAction = new HistoryBackAction();
    /**
     * The constant historyForwardAction.
     */
    public static HistoryForwardAction historyForwardAction = new HistoryForwardAction();

    /**
     * The type History back action.
     */
    static class HistoryBackAction extends AbstractAction {

        /**
         * Instantiates a new History back action.
         */
        public HistoryBackAction() {
            super(Local.getString("History back"), 
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/hist_back.png")));
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.ALT_MASK));
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                notifyListeners(rollBack());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            update();
            historyForwardAction.update();
        }

        /*public boolean isEnabled() {
            return canRollBack();
        }*/

        /**
         * Update.
         */
        void update() {
            if (canRollBack()) {
                setEnabled(true);

		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.SHORT);
		Date date = ((HistoryItem) prev).getDate().getDate();
		    putValue(
                    Action.SHORT_DESCRIPTION,
		   Local.getString("Back to") + " " + sdf.format(date));

//                putValue(Action.SHORT_DESCRIPTION, Local.getString("Back to") + " " + ((HistoryItem) prev).getDate().toString());
            }
            else {
                setEnabled(false);
                putValue(Action.SHORT_DESCRIPTION, Local.getString("Back"));
            }
        }
    }

    /**
     * The type History forward action.
     */
    static class HistoryForwardAction extends AbstractAction {

        /**
         * Instantiates a new History forward action.
         */
        public HistoryForwardAction() {
            super(Local.getString("History forward"), 
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/hist_forward.png")));
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.ALT_MASK));
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                notifyListeners(rollForward());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            update();
            historyBackAction.update();
        }

        /*public boolean isEnabled() {
            return canRollForward();
        }*/

        /**
         * Update.
         */
        void update() {
            if (canRollForward()) {
                setEnabled(true);

		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.SHORT);
		Date date = ((HistoryItem) next).getDate().getDate();

		    putValue(
                    Action.SHORT_DESCRIPTION,
                   // Local.getString("Forward to") + " " + ((HistoryItem) next).getDate().toString());
		   Local.getString("Forward to") + " " + sdf.format(date));
            }
            else {
                setEnabled(false);
                putValue(Action.SHORT_DESCRIPTION, Local.getString("Forward"));
            }
        }
    }

}
