package main.java.memoranda.ui.usermanagment;

import static main.java.memoranda.ui.App.gym;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
            _mainPanel.setPreferredSize(new Dimension(200,100));
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

                System.out.println("Deleting the user " + _email);
                gym.deleteUser(_email);
                this.userManagement.removeUserFromTable();
                dispose();

            } else {
                dispose();
            }
        });
    }
}
