package main.java.memoranda.ui.usermanagment;

import main.java.memoranda.database.BeltEntity;
import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.UserEntity;

import javax.swing.*;
import java.awt.*;

public class UserManagementEditUser extends JDialog {

    private JPanel _mainPanel;
    private JButton _b1;
    private JLabel _firstNameLabel;
    private JTextField _firstNameBox;
    private JLabel _lastNameLabel;
    private JTextField _lastNameBox;
    private JLabel _emailLabel;
    private JTextField _emailBox;
    private JLabel _role;
    private ButtonGroup _buttons;
    private JRadioButton _trainerButton;
    private JRadioButton _studentButton;
    private JRadioButton _adminButton;
    private JLabel _belt;
    private String[] _beltColors;
    private JComboBox _beltList;
    private JLabel _trainerBelt;
    private String[] _trainerBeltColors;
    private JComboBox _trainerBeltList;


    /**
     * Constructor for our Edit User popup
     * @param rel uses this variable to set the relative position of the popup.
     */
    public UserManagementEditUser(Component rel, UserEntity _user){
        super(new JFrame());

        _mainPanel = new JPanel();
        this.setTitle("Edit User");
        _mainPanel.setPreferredSize(new Dimension(400,500));

        _firstNameLabel = new JLabel("First Name");
        _firstNameLabel.setBounds(40, 100, 75, 20);

        _firstNameBox = new JTextField(10);
        _firstNameBox.setBounds(120,100,120,20);
        _firstNameBox.setText(_user.getFirstName());

        _lastNameLabel = new JLabel("Last Name");
        _lastNameLabel.setBounds(40, 140, 75, 20);

        _lastNameBox = new JTextField(10);
        _lastNameBox.setBounds(120, 140, 120, 20);
        _lastNameBox.setText(_user.getFirstName());

        _emailLabel = new JLabel("Email");
        _emailLabel.setBounds(40, 180, 75, 20);

        _emailBox = new JTextField(20);
        _emailBox.setBounds(120, 180, 120, 20);
        _emailBox.setText(_user.getEmail());

        _role = new JLabel("Role");
        _role.setBounds(40, 220, 75, 20);

        _buttons = new ButtonGroup();
        _trainerButton = new JRadioButton();
        _trainerButton.setText("Trainer");
        _trainerButton.setBounds(100, 220, 80,20);
        _studentButton = new JRadioButton();
        _studentButton.setText("Student");
        _studentButton.setBounds(180, 220, 80, 20);
        _adminButton = new JRadioButton();
        _adminButton.setText("Admin");
        _adminButton.setBounds(260, 220, 80, 20);
        _buttons.add(_trainerButton);
        _buttons.add(_studentButton);
        _buttons.add(_adminButton);

        // Set role to currently selected user's role
        String role = _user.getRole().toString();
        if (role.equalsIgnoreCase("trainer")) {
            _trainerButton.setSelected(true);
        } else if (role.equalsIgnoreCase("student")) {
            _studentButton.setSelected(true);
        } else {
            _adminButton.setSelected(true);
        }

        _belt = new JLabel("Belt");
        _belt.setBounds(40, 260, 75, 20);

        _beltColors = new String[] {"White", "Yellow", "Orange", "Purple", "Blue",
        "Blue Stripe", "Green", "Green Stripe", "Brown1", "Brown2", "Brown3",
        "Black1", "Black2", "Black3"};

        // Find index of selected user's belt color
        int index = 0;
        for (int i = 0; i < _beltColors.length; i++) {
            if (_user.getBelt().toString().equalsIgnoreCase(_beltColors[i])) {
                index = i;
            }
        }

        _beltList = new JComboBox(_beltColors);
        _beltList.setSelectedIndex(index); // Set to selected user's belt
        _beltList.setBounds(120, 260, 120, 20);
        _beltList.setBackground(Color.WHITE);

        _trainerBelt = new JLabel("Trainer Belt");
        _trainerBelt.setBounds(40, 300, 75, 20);

        _trainerBeltColors = _beltColors;
        _trainerBeltList = new JComboBox(_trainerBeltColors);
        _trainerBeltList.setSelectedIndex(0); // Set to selected user's trainer belt (if trainer)
        _trainerBeltList.setBounds(120, 300, 120, 20);
        _trainerBeltList.setBackground(Color.WHITE);

        // Add 'UPDATE USER' button that updates the user in the database when pushed


        _mainPanel.add(_firstNameLabel);
        _mainPanel.add(_firstNameBox);
        _mainPanel.add(_lastNameLabel);
        _mainPanel.add(_lastNameBox);
        _mainPanel.add(_emailLabel);
        _mainPanel.add(_emailBox);
        _mainPanel.add(_role);
        _mainPanel.add(_trainerButton);
        _mainPanel.add(_studentButton);
        _mainPanel.add(_adminButton);
        _mainPanel.add(_belt);
        _mainPanel.add(_beltList);

        // Only show trainer belt options if the selected user is a trainer or admin
        // Students should not have this option
        if (_user.getRole().toString().equalsIgnoreCase("trainer") ||
        _user.getRole().toString().equalsIgnoreCase("admin")) {
            _mainPanel.add(_trainerBelt);
            _mainPanel.add(_trainerBeltList);
        }

        _mainPanel.setLayout(null);
        this.add(_mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
