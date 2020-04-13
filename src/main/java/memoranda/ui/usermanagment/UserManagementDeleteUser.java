package main.java.memoranda.ui.usermanagment;

import javax.swing.*;
import java.awt.*;

public class UserManagementDeleteUser extends JDialog {

    JPanel mainPanel;
    JButton b1;

    public UserManagementDeleteUser(Component rel){
        super(new JFrame());

        mainPanel = new JPanel();
        b1 = new JButton("poop");

        this.setTitle("Delete User");
        mainPanel.setPreferredSize(new Dimension(200,100));
        mainPanel.add(b1);


        setLocationRelativeTo(rel);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
}
