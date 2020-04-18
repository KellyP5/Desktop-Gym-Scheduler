/**
 * This class handles the button on the User Management page for editing a user.
 * A dialog box will pop up with the currently selected user's information and allows the
 * user to edit the information. If a user is not selected when Edit User is pushed, an error message
 * dialog will pop up. After the user's information is edited, a confirmation dialog pops up allowing the user
 * to review the changes that were made, and they can then choose to save those changes or go back.
 * When the user's information is changed and approved, the database will be updated with the new information.
 *
 * @author Kelly Ellis, Kevin Wilkinson
 */
package main.java.memoranda.ui.usermanagment;

import main.java.memoranda.database.BeltEntity;
import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.UserEntity;
import main.java.memoranda.ui.App;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class UserManagementEditUser extends JDialog {

    private JPanel _mainPanel;
    private JLabel _firstNameLabel;
    private JTextField _firstNameBox;
    private JLabel _lastNameLabel;
    private JTextField _lastNameBox;
    private JLabel _emailLabel;
    private JTextField _emailBox;
    private JLabel _role;
    private ButtonGroup _buttons;
    private JRadioButton _trainerButton;
    private JRadioButton _customerButton;
    private JRadioButton _adminButton;
    private JLabel _belt;
    private String[] _beltColors;
    private JComboBox _beltList;
    private JLabel _trainerBelt;
    private String[] _trainerBeltColors;
    private JComboBox _trainerBeltList;
    private JButton _updateButton;
    private String _roleString;
    private String _beltString;
    private UserEntity _selectedUser;


    /**
     * Constructor for our Edit User popup. Creates the GUI box that displays the user's information
     * and allows it to be edited.
     *
     * @param _user The currently selected user to edit from the User Management page
     */
    public UserManagementEditUser(UserEntity _user){
        super(new JFrame());

        _selectedUser = _user;

        _mainPanel = new JPanel();
        this.setTitle("Edit User");
        _mainPanel.setPreferredSize(new Dimension(350,400));

        _firstNameLabel = new JLabel("First Name");
        _firstNameLabel.setBounds(40, 50, 75, 20);

        _firstNameBox = new JTextField(10);
        _firstNameBox.setBounds(120,50,120,20);
        _firstNameBox.setText(_selectedUser.getFirstName());

        _lastNameLabel = new JLabel("Last Name");
        _lastNameLabel.setBounds(40, 90, 75, 20);

        _lastNameBox = new JTextField(10);
        _lastNameBox.setBounds(120, 90, 120, 20);
        _lastNameBox.setText(_selectedUser.getLastName());

        _emailLabel = new JLabel("Email");
        _emailLabel.setBounds(40, 130, 75, 20);

        _emailBox = new JTextField(20);
        _emailBox.setBounds(120, 130, 120, 20);
        _emailBox.setText(_selectedUser.getEmail());

        _role = new JLabel("Role");
        _role.setBounds(40, 170, 75, 20);

        _buttons = new ButtonGroup();
        _trainerButton = new JRadioButton();
        _trainerButton.setText("Trainer");
        _trainerButton.setBounds(100, 170, 80,20);
        // If user is changed to trainer, add trainer belt option
        _trainerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                _mainPanel.add(_trainerBelt);
                _mainPanel.add(_trainerBeltList);
                _mainPanel.repaint();
            }
        });

        _customerButton = new JRadioButton();
        _customerButton.setText("Customer");
        _customerButton.setBounds(180, 170, 90, 20);
        // If user is changed to customer, remove trainer belt option
        _customerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                _mainPanel.remove(_trainerBelt);
                _mainPanel.remove(_trainerBeltList);
                _mainPanel.repaint();
            }
        });

        _adminButton = new JRadioButton();
        _adminButton.setText("Admin");
        _adminButton.setBounds(270, 170, 80, 20);
        // If user is changed to admin, add trainer belt option
        _adminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                _mainPanel.add(_trainerBelt);
                _mainPanel.add(_trainerBeltList);
                _mainPanel.repaint();
            }
        });

        _buttons.add(_trainerButton);
        _buttons.add(_customerButton);
        _buttons.add(_adminButton);

        // Set role to currently selected user's role
        String role = _selectedUser.getRole().toString();
        if (role.equalsIgnoreCase("trainer")) {
            _trainerButton.setSelected(true);
        } else if (role.equalsIgnoreCase("customer")) {
            _customerButton.setSelected(true);
        } else {
            _adminButton.setSelected(true);
        }

        _belt = new JLabel("Belt");
        _belt.setBounds(40, 210, 75, 20);

        _beltColors = new String[] {"White", "Yellow", "Orange", "Purple", "Blue",
        "Blue Stripe", "Green", "Green Stripe", "Brown1", "Brown2", "Brown3",
        "Black1", "Black2", "Black3"};

        // Find index of selected user's belt color
        int index = 0;
        for (int i = 0; i < _beltColors.length; i++) {
            if (_selectedUser.getBelt().toString().equalsIgnoreCase(_beltColors[i])) {
                index = i;
            }
        }

        _beltList = new JComboBox(_beltColors);
        _beltList.setSelectedIndex(index); // Set to selected user's belt
        _beltList.setBounds(120, 210, 120, 20);
        _beltList.setBackground(Color.WHITE);

        _trainerBelt = new JLabel("Trainer Belt");
        _trainerBelt.setBounds(40, 250, 75, 20);

        _trainerBeltColors = _beltColors;

        // Find index of selected user's training belt (if they're a trainer)
        int trainerIndex = 0;
        for (int i = 0; i < _trainerBeltColors.length; i++) {
            if (_selectedUser.getTrainingBelt().toString().equalsIgnoreCase(_trainerBeltColors[i])) {
                trainerIndex = i;
            }
        }

        _trainerBeltList = new JComboBox(_trainerBeltColors);
        _trainerBeltList.setSelectedIndex(trainerIndex); // Set to selected user's trainer belt (if trainer)
        _trainerBeltList.setBounds(120, 250, 120, 20);
        _trainerBeltList.setBackground(Color.WHITE);

        _updateButton = new JButton("Update");
        _updateButton.setBounds(130, 300, 100, 30);
        _updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                RoleEntity role = getRoleFromSelected();
                BeltEntity belt = getBeltFromSelected(_beltList);
                _roleString = role.toString();
                _beltString = belt.toString();
                // Email wasn't changed - Update user info in database
                if (_emailBox.getText().equals(_selectedUser.getEmail())) {
                    try {
                        App.conn.getDuq().updateUser(_emailBox.getText(), _firstNameBox.getText(), _lastNameBox.getText(),
                                _selectedUser.getPassword(), role, belt);
                        boolean change = confirmChanges();
                        // Alert user that the update was successful
                        if (change) {
                            updateSuccessful();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    // Email was changed - Check if new email already exists in the db
                    try {
                        UserEntity user = App.conn.getDrq().getUserByEmail(_emailBox.getText());
                        if (user != null) {
                            // email is already in database - alert user
                            emailAlreadyExists();
                        } else {
                            // Email not in database, update user info
                            App.conn.getDuq().updateUser(_emailBox.getText(), _firstNameBox.getText(), _lastNameBox.getText(),
                                    _selectedUser.getPassword(), role, belt);
                            boolean change = confirmChanges();
                            // Alert the user that the update was successful
                            if (change) {
                                updateSuccessful();
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        _mainPanel.add(_firstNameLabel);
        _mainPanel.add(_firstNameBox);
        _mainPanel.add(_lastNameLabel);
        _mainPanel.add(_lastNameBox);
        _mainPanel.add(_emailLabel);
        _mainPanel.add(_emailBox);
        _mainPanel.add(_role);
        _mainPanel.add(_trainerButton);
        _mainPanel.add(_customerButton);
        _mainPanel.add(_adminButton);
        _mainPanel.add(_belt);
        _mainPanel.add(_beltList);
        _mainPanel.add(_updateButton);

        // Only show trainer belt options if the selected user is a trainer or admin
        // Customers should not have this option
        if (_selectedUser.getRole().toString().equalsIgnoreCase("trainer") ||
                _selectedUser.getRole().toString().equalsIgnoreCase("admin")) {
            _mainPanel.add(_trainerBelt);
            _mainPanel.add(_trainerBeltList);
        }

        _mainPanel.setLayout(null);
        this.add(_mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Gets the RoleEntity from the selected user
     *
     * @return The RoleEntity of the user
     */
    public RoleEntity getRoleFromSelected() {
        String roleText = _role.getText();
        RoleEntity role;
        if (roleText.equalsIgnoreCase("admin")) {
            role = new RoleEntity(RoleEntity.UserRole.admin);
        } else if (roleText.equalsIgnoreCase("trainer")) {
            role = new RoleEntity(RoleEntity.UserRole.trainer);
        } else {
            role = new RoleEntity(RoleEntity.UserRole.customer);
        }
        return role;
    }

    /**
     * Gets the BeltEntity from the selected user. Works for students, trainers, and admins
     *
     * @param list The combobox list of belt colors/options
     * @return The BeltEntity of the user (May be student or trainer)
     */
    public BeltEntity getBeltFromSelected(JComboBox list) {
        BeltEntity belt;
        if (list.getSelectedItem().equals("White")) {
            belt = new BeltEntity(BeltEntity.Rank.white);
        } else if (list.getSelectedItem().equals("Yellow")) {
            belt = new BeltEntity(BeltEntity.Rank.yellow);
        } else if (list.getSelectedItem().equals("Orange")) {
            belt = new BeltEntity(BeltEntity.Rank.orange);
        } else if (list.getSelectedItem().equals("Purple")) {
            belt = new BeltEntity(BeltEntity.Rank.purple);
        } else if (list.getSelectedItem().equals("Blue")) {
            belt = new BeltEntity(BeltEntity.Rank.blue);
        } else if (list.getSelectedItem().equals("Blue Stripe")) {
            belt = new BeltEntity(BeltEntity.Rank.blue_stripe);
        } else if (list.getSelectedItem().equals("Green")) {
            belt = new BeltEntity(BeltEntity.Rank.green);
        } else if (list.getSelectedItem().equals("Green Stripe")) {
            belt = new BeltEntity(BeltEntity.Rank.green_stripe);
        } else if (list.getSelectedItem().equals("Brown1")) {
            belt = new BeltEntity(BeltEntity.Rank.brown1);
        } else if (list.getSelectedItem().equals("Brown2")) {
            belt = new BeltEntity(BeltEntity.Rank.brown2);
        } else if (list.getSelectedItem().equals("Brown3")) {
            belt = new BeltEntity(BeltEntity.Rank.brown3);
        } else if (list.getSelectedItem().equals("Black1")) {
            belt = new BeltEntity(BeltEntity.Rank.black1);
        } else if (list.getSelectedItem().equals("Black2")) {
            belt = new BeltEntity(BeltEntity.Rank.black2);
        } else {
            belt = new BeltEntity(BeltEntity.Rank.black3);
        }
        return belt;
    }

    /**
     * Popup dialog that alerts the user that the email they wish to update already
     * exists in the database and cannot be used
     */
    public void emailAlreadyExists() {
        Object[] option = {"OK"};
        int x = JOptionPane.showOptionDialog(null, "The email you entered is already in use",
                "Email In Use", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
    }

    /**
     * Popup dialog that alerts the user that their update was successful, and the
     * database has been updated
     */
    public void updateSuccessful() {
        Object[] option = {"OK"};
        int x = JOptionPane.showOptionDialog(null, "The user's information was updated successfully!",
                "Update Successful", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

        if (x == JOptionPane.OK_OPTION) {
            dispose();
        }
    }

    /**
     * Popup dialog that allows the user to see the changes they're about to make
     * to the selected user's information
     *
     * @return Returns true if changes are approved, false otherwise
     */
    public boolean confirmChanges() {
        Object[] options = {"Update User", "Go Back"};

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel(("First Name: " + _firstNameBox.getText())));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Last Name: " + _lastNameBox.getText()));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Email: " + _emailBox.getText()));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Role: " + _roleString));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Belt: " + _beltString));

        if (_selectedUser.getRole().toString().equalsIgnoreCase("trainer") ||
                _selectedUser.getRole().toString().equalsIgnoreCase("admin")) {
            panel.add(new JLabel(""));
            BeltEntity belt = getBeltFromSelected(_trainerBeltList);
            panel.add(new JLabel("Trainer Belt: " + belt.toString()));
        }

        int result = JOptionPane.showOptionDialog(null, panel, "", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, null);
        if (result == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
}
