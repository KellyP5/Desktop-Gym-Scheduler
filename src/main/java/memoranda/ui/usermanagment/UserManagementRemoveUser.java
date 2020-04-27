package main.java.memoranda.ui.usermanagment;

import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.util.DbCreateQueries;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserManagementRemoveUser extends JDialog {

    UserManagement userManagement;
    private JPanel _mainPanel;
    private JButton _b1;
    private boolean _remove;
    private String _email;

    /**
     * Constructor for our remove User popup
     * @param  email of the user to remove
     * @param  role of the user trying to remove
     */
    public UserManagementRemoveUser(UserManagement ref, Component rel, String email, String role){
        super(new JFrame());
        this.userManagement = ref;

        _mainPanel = new JPanel();
        JTextArea label = new JTextArea();
        _mainPanel.add(label);
        System.out.println(role);
        _remove = false;
        label.setWrapStyleWord(true);
        label.setLineWrap(true);
        label.setOpaque(false);
        label.setEditable(false);
        label.setFocusable(false);

        if (role.equalsIgnoreCase("admin")) {
            label.setText("Unable to delete admin users");
            _b1 = new JButton("EXIT");
            this.setTitle("Remove User");
            _mainPanel.setPreferredSize(new Dimension(200,100));
            _mainPanel.add(_b1);
        } else {
            _remove = true;
            this._email = email;
            label.setText("Are you sure you want to delete the user with email: " + email + "?");
            _b1 = new JButton("CONFIRM");
            this.setTitle("Remove User");
            _mainPanel.setPreferredSize(new Dimension(250,100));
            _mainPanel.add(_b1);
        }

        _setActions();
        this.add(_mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    /**
     * Creates the action listensers.
     */
    private void _setActions(){

        this._b1.addActionListener(actionEvent -> {
            if (_remove == true) {
                try {
                    System.out.println("Deleting the user " + _email);
                    SqlConnection sql = SqlConnection.getInstance();
                    DbCreateQueries dcq = sql.getDcq();
                    dcq.deleteUser(_email);
                    this.userManagement.removeUserFromTable();
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
