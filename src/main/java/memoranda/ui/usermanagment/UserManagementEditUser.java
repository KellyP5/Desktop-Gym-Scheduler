package main.java.memoranda.ui.usermanagment;

import javax.swing.*;
import java.awt.*;

public class UserManagementEditUser extends JDialog {

    private JPanel mainPanel;
    private JButton b1;

    /**
     * Constructor for our Edit User popup
     * @param rel uses this variable to set the relative position of the popup.
     */
    public UserManagementEditUser(Component rel){
        super(new JFrame());

        mainPanel = new JPanel();
        b1 = new JButton("TODO");

        this.setTitle("Edit User");
        mainPanel.setPreferredSize(new Dimension(200,100));
        mainPanel.add(b1);


        setLocationRelativeTo(rel);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
}
