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

import main.java.memoranda.database.entities.BeltEntity;
import main.java.memoranda.database.entities.RoleEntity;
import main.java.memoranda.database.entities.UserEntity;
import main.java.memoranda.ui.App;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

public class UserManagementEditUser extends JDialog {

    UserManagement topLevelReference;
    private JPanel _mainPanel;
    private JLabel _imageBox;
    private ImageIcon _image;
    private JButton _newImageButton;
    private JButton _clearImageButton;
    private String placeHolderPath;
    private String _imageUrl;
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
    private String _name;

    /**
     * Constructor for our Edit User popup. Creates the GUI box that displays the user's information
     * and allows it to be edited.
     *
     * @param _user The currently selected user to edit from the User Management page
     */
    public UserManagementEditUser(UserManagement ref, UserEntity _user) throws Exception {

        super(new JFrame());
        this.topLevelReference = ref;

        _selectedUser = _user;

        _mainPanel = new JPanel();
        this.setTitle("Edit User");
        _mainPanel.setPreferredSize(new Dimension(450,400));

        _firstNameLabel = new JLabel("First Name");
        _firstNameLabel.setBounds(30, 50, 75, 20);

        _firstNameBox = new JTextField(10);
        _firstNameBox.setBounds(110,50,120,20);
        _firstNameBox.setText(_selectedUser.getFirstName());

        _lastNameLabel = new JLabel("Last Name");
        _lastNameLabel.setBounds(30, 90, 75, 20);

        _lastNameBox = new JTextField(10);
        _lastNameBox.setBounds(110, 90, 120, 20);
        _lastNameBox.setText(_selectedUser.getLastName());

        _emailLabel = new JLabel("Email");
        _emailLabel.setBounds(30, 130, 75, 20);

        _emailBox = new JTextField(20);
        _emailBox.setBounds(110, 130, 120, 20);
        _emailBox.setText(_selectedUser.getEmail());

        placeHolderPath = "src/main/resources/ui/Placeholder.png";

        _imageBox = new JLabel();
        _imageBox.setBounds(240, 10, 200, 200);
        _imageUrl = _selectedUser.getUserImageFromDB();

        // Gets the user's image to display on their profile page
        _image = new ImageIcon(scaleImage(200, 200, ImageIO.read(new File(_imageUrl))));

        _imageBox.setIcon(_image);

        _newImageButton = new JButton();
        _newImageButton.setText("Upload");
        _newImageButton.setBounds(290, 220, 100,25);

        _clearImageButton = new JButton();
        _clearImageButton.setText("Clear");
        _clearImageButton.setBounds(290, 250, 100, 25);

        // Allows a user to upload a new image to their profile and saves
        // it to the project directory
        _newImageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                if (f != null) {
                    String fileName = f.getAbsolutePath();
                    name = f.getName();
                    try {
                        ImageIcon image = new ImageIcon(scaleImage(200, 200, ImageIO.read(new File(fileName))));
                        _imageBox.setIcon(image);
                        java.nio.file.Files.copy(
                                new java.io.File(fileName).toPath(),
                                new java.io.File("src/main/resources/ui/" + name).toPath(),
                                StandardCopyOption.REPLACE_EXISTING,
                                StandardCopyOption.COPY_ATTRIBUTES,
                                LinkOption.NOFOLLOW_LINKS
                        );
                        _imageUrl = "src/main/resources/ui/" + name;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    // The user didn't choose an image, reset it to the placeholder
                    _imageUrl = "src/main/resources/ui/Placeholder.png";
                    return;
                }
            }
        });

        // Clears out the user's image and replaces it with the placeholder image
        _clearImageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ImageIcon image = null;
                try {
                    image = new ImageIcon(scaleImage(200,200, ImageIO.read(new File(placeHolderPath))));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                _imageBox.setIcon(image);
                _selectedUser.setImageUrl(placeHolderPath);
                _imageUrl = placeHolderPath;
                try {
                    App.conn.getDuq().updateUserImage(_selectedUser.getEmail(), _imageUrl);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                _imageBox.repaint();
            }
        });

        _role = new JLabel("Role");
        _role.setBounds(30, 170, 75, 20);

        _buttons = new ButtonGroup();
        _trainerButton = new JRadioButton();
        _trainerButton.setText("Trainer");
        _trainerButton.setBounds(110, 170, 80,20);
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
        _customerButton.setBounds(110, 190, 90, 20);
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
        _adminButton.setBounds(110, 210, 80, 20);
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
        _belt.setBounds(30, 250, 75, 20);

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
        _beltList.setBounds(110, 250, 120, 20);
        _beltList.setBackground(Color.WHITE);

        _trainerBelt = new JLabel("Trainer Belt");
        _trainerBelt.setBounds(30, 290, 75, 20);

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
        _trainerBeltList.setBounds(110, 290, 120, 20);
        _trainerBeltList.setBackground(Color.WHITE);

        _updateButton = new JButton("Update");
        _updateButton.setBounds(180, 330, 100, 30);

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
                        updateUser(role, belt);
                        // Save the selected image
                        if (name != null) {
                            _selectedUser.setImageUrl("src/main/resources/ui/" + name);
                            _imageUrl = "src/main/resources/ui/" + name;
                            App.conn.getDuq().updateUserImage(_selectedUser.getEmail(), _imageUrl);
                        }
                        _imageBox.repaint();
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
                            updateUser(role, belt);
                            repaint();
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
        _mainPanel.add(_imageBox);
        _mainPanel.add(_newImageButton);
        _mainPanel.add(_clearImageButton);

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
        RoleEntity role;
        if (_trainerButton.isSelected()) {
            role = new RoleEntity(RoleEntity.UserRole.trainer);
        } else if (_customerButton.isSelected()) {
            role = new RoleEntity(RoleEntity.UserRole.customer);
        } else {
            role = new RoleEntity(RoleEntity.UserRole.admin);
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
     * Updates the user's information in the database, after the user confirms the changes are correct.
     * Also updates the table on the GUI by removing the 'old' user and adding the 'new' user, effectively
     * updating the table.
     * @param role The role of the user
     * @param belt The belt of the user
     * @throws SQLException
     */
    public void updateUser(RoleEntity role, BeltEntity belt) throws SQLException {
        boolean change = confirmChanges();
        // Alert user that the update was successful
        if (change) {
            App.conn.getDuq().updateUser(_emailBox.getText(), _firstNameBox.getText(), _lastNameBox.getText(),
                    _selectedUser.getPassword(), role, belt);
            updateSuccessful();
            // Update the table with the new information
            this.topLevelReference.removeUserFromTable();
            this.topLevelReference.addUserToTable(_emailBox.getText(), _beltString, _roleString);
        }
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

    /**
     * Scales the selected user image down to set # of pixels and displays it
     *
     * @param w Image width
     * @param h Image height
     * @param img The image to scale
     * @return The scaled image
     */
    public static BufferedImage scaleImage(int w, int h, BufferedImage img) {
            BufferedImage buffered;
            buffered = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
            Graphics2D g2d = (Graphics2D) buffered.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(img, 0, 0, w, h, null);
            g2d.dispose();
            return buffered;
    }
}
