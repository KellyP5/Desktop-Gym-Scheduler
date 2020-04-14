package main.java.memoranda.ui.usermanagment;

import javax.swing.*;
import java.awt.*;

public class UserManagementAddUser extends JDialog {

    private JPanel mainPanel;
    private JButton b1;

    /**
     * Constructor for our Add User popup.
     * @param rel is used to set relative position of the popup.
     */
    public UserManagementAddUser(Component rel){
        super(new JFrame());

        mainPanel = new JPanel();
        b1 = new JButton("TODO");

        this.setTitle("Add User");
        mainPanel.setPreferredSize(new Dimension(200,100));
        mainPanel.add(b1);


        setLocationRelativeTo(rel);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }

}
