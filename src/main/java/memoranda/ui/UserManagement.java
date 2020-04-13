package main.java.memoranda.ui;

import javax.swing.*;
import java.awt.*;

public class UserManagement extends JPanel {

    BorderLayout borderLayout1 = new BorderLayout();

    public UserManagement() {
        try {
            jbInit();
        }
        catch (Exception ex) {
           new ExceptionDialog(ex);
        }
    }

    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    void jbInit() throws Exception {

        this.setLayout(borderLayout1);
    }


}