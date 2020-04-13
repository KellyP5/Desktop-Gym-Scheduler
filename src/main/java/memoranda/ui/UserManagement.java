package main.java.memoranda.ui;

import javax.swing.*;
import java.awt.*;

public class UserManagement extends JPanel {

    BorderLayout borderLayout = new BorderLayout();

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
        TextField txt = new TextField();
        txt.setText("hello world!");
        borderLayout.addLayoutComponent(txt,BorderLayout.CENTER);

        this.setLayout(borderLayout);





    }


}