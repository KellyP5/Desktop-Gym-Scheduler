package main.java.memoranda.ui.usermanagment;

import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.util.DbCreateQueries;
import main.java.memoranda.database.util.DbReadQueries;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserManagementRemoveUser extends JDialog {

    private JPanel mainPanel;
    private JButton b1;
    private boolean remove;
    private String email;

    /**
     * Constructor for our remove User popup
     * @param  email of the user to remove
     * @param  role of the user trying to remove
     */
    public UserManagementRemoveUser(Component rel, String email, String role){
        super(new JFrame());

        mainPanel = new JPanel();
        JTextArea label = new JTextArea();
        mainPanel.add(label);
        System.out.println(role);
        remove = false;
        label.setWrapStyleWord(true);
        label.setLineWrap(true);
        label.setOpaque(false);
        label.setEditable(false);
        label.setFocusable(false);

        if (role.equalsIgnoreCase("admin")) {
            label.setText("Unable to delete admin users");
            b1 = new JButton("EXIT");
            this.setTitle("Remove User");
            mainPanel.setPreferredSize(new Dimension(200,100));
            mainPanel.add(b1);
        } else {
            remove = true;
            this.email = email;
            label.setText("Are you sure you want to delete the user with " + email + "?");
            b1 = new JButton("CONFIRM");
            this.setTitle("Remove User");
            mainPanel.setPreferredSize(new Dimension(200,100));
            mainPanel.add(b1);
        }


        setLocationRelativeTo(rel);
        setActions();
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
    /**
     * Creates the action listensers.
     */
    private void setActions(){

        this.b1.addActionListener(actionEvent -> {
            if (remove == true) {
                try {
                    System.out.println("Deleting the user " + email);
                    SqlConnection sql = SqlConnection.getInstance();
                    DbCreateQueries dcq = sql.getDcq();
                    dcq.deleteUser(email);
                    dispose();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                dispose();
            }
        });
    }
}
