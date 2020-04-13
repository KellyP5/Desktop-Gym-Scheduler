package main.java.memoranda.ui.usermanagment;

import javax.swing.*;
import java.awt.*;

public class UserManagementEditUser extends JDialog {

    JPanel mainPanel;
    JButton b1;

    public UserManagementEditUser(Component rel){
        super(new JFrame());

        mainPanel = new JPanel();
        b1 = new JButton("poop");

        this.setTitle("Edit User");
        mainPanel.setPreferredSize(new Dimension(200,100));
        mainPanel.add(b1);


        setLocationRelativeTo(rel);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
}
