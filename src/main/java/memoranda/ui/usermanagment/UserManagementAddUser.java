package main.java.memoranda.ui.usermanagment;

import main.java.memoranda.database.BeltEntity;
import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.util.DbReadQueries;
import main.java.memoranda.ui.App;
import main.java.memoranda.util.Local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Arrays;

public class UserManagementAddUser extends JFrame {

    UserManagement topLevelReference;

    JPanel accountCreate;
    ImageIcon globoLogo;
    JLabel logo;
    JButton createButton;
    JTextField firstName;
    JTextField lastName;
    JTextField email;
    JPasswordField pass;
    JPasswordField pass2;
    JLabel accountSelection;
    ButtonGroup buttons;
    JRadioButton trainerButton;
    JRadioButton studentButton;
    JRadioButton adminButton;
    JLabel fillOutForm;
    JLabel password;
    JLabel password2;
    JLabel alreadyHaveAccount;
    JComboBox beltsCB;
    JLabel lblBelt;
    JComboBox levelsCB;
    JLabel lblLevels;

    /** Main constructor
     *
     * @param ref This parent reference is passed in so we can call refresh on it once the  UI is updated.
     * @param rel Used to set the position of the add user popup.
     */
    public UserManagementAddUser(UserManagement ref, Component rel) {
        this.topLevelReference = ref;//we store this to use in our action listener.

        initGuiComponents();

        initActionEvents();

        getContentPane().add(accountCreate);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    /**
     * Initializes GUI components in one call.
     */
    public void initGuiComponents(){
        accountCreate = new JPanel();
        globoLogo = new ImageIcon("src/main/resources/ui/globo.jpg");
        logo = new JLabel();
        createButton = new JButton("Create");
        firstName = new JTextField(20);
        lastName = new JTextField(20);
        email = new JTextField(20);
        pass = new JPasswordField(20);
        pass2 = new JPasswordField(20);
        trainerButton = new JRadioButton();
        studentButton = new JRadioButton();
        adminButton = new JRadioButton();
        fillOutForm = new JLabel("Please fill out the form with your information");
        accountSelection = new JLabel("Which type of account would you like to create?");
        password = new JLabel("Password: ");
        password2 = new JLabel("Verify Password: ");
        buttons = new ButtonGroup(); // Ensures that only 1 option can be chosen
        buttons.add(trainerButton);
        buttons.add(studentButton);
        buttons.add(adminButton);
        beltsCB = new JComboBox(Local.getBeltNames());
        lblBelt = new JLabel();
        levelsCB = new JComboBox(Local.getBeltNames());
        lblLevels = new JLabel();


        setTitle("Create Account");
        setSize(300, 500);

        logo.setBounds(85, 10, 113, 113);
        logo.setIcon(globoLogo);

        fillOutForm.setBounds(30, 110, 250, 50);
        fillOutForm.setFont(new Font("Bell MT", Font.PLAIN, 12));

        firstName.setText("First Name");
        firstName.setForeground(Color.LIGHT_GRAY);
        lastName.setText("Last Name");
        lastName.setForeground(Color.LIGHT_GRAY);

        email.setText("E-mail");
        email.setForeground(Color.LIGHT_GRAY);
        pass.setForeground(Color.LIGHT_GRAY);
        pass.setEchoChar('*');
        pass2.setForeground(Color.LIGHT_GRAY);
        pass2.setEchoChar('*');

        accountCreate.setLayout(null);

        beltsCB.setPreferredSize(new Dimension(100, 25));
        beltsCB.setBounds(35, 400, 100, 20);
        lblBelt.setBounds(5, 400, 100, 20);
        lblBelt.setText(Local.getString("Belt:"));
        lblBelt.setMinimumSize(new Dimension(60, 24));

        levelsCB.setPreferredSize(new Dimension(100, 25));
        levelsCB.setBounds(150, 400, 100, 20);
        lblLevels.setBounds(100, 400, 100, 20);
        lblLevels.setText(Local.getString("Level:"));
        lblLevels.setMinimumSize(new Dimension(60, 24));


        email.setBounds(70, 230, 150, 25);
        pass.setBounds(125, 270, 150, 25);
        pass2.setBounds(125, 310, 150, 25);
        password.setBounds(50, 270, 150, 25);
        password2.setBounds(10, 310, 150, 25);

        firstName.setBounds(70, 150, 150, 25);
        lastName.setBounds(70, 190, 150, 25);

        accountSelection.setBounds(0, 330, 300, 50);

        trainerButton.setText("Trainer");
        trainerButton.setBounds(10, 370, 100, 20);
        trainerButton.setBackground(new java.awt.Color(230,230,230));
        studentButton.setText("Student");
        studentButton.setBounds(110, 370, 100, 20);
        studentButton.setBackground(new java.awt.Color(230,230,230));
        adminButton.setText("Admin");
        adminButton.setBounds(210, 370, 100, 20);
        adminButton.setBackground(new java.awt.Color(230,230,230));

        createButton.setBounds(100, 445, 80, 20);

        accountCreate.add(createButton);
        accountCreate.add(logo);
        accountCreate.add(firstName);
        accountCreate.add(lastName);
        accountCreate.add(email);
        accountCreate.add(pass);
        accountCreate.add(pass2);
        accountCreate.add(accountSelection);
        accountCreate.add(trainerButton);
        accountCreate.add(studentButton);
        accountCreate.add(adminButton);
        accountCreate.add(fillOutForm);
        accountCreate.add(password);
        accountCreate.add(password2);
        accountCreate.add(beltsCB);
        accountCreate.add(lblBelt);
        accountCreate.setBackground(new java.awt.Color(230, 230, 230));

    }

    /**
     * Initializes our action events in one call.
     */
    public void initActionEvents(){

        /**
         * Manages first name entry box.
         */
        firstName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (firstName.getText().equals("First Name")) {
                    // Clear out the text field so the user can type
                    firstName.setText("");
                    firstName.setForeground(Color.BLACK);
                } else {
                    // Highlight all characters in text box
                    firstName.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                // When the user clicks/tabs away from First Name,
                // if nothing was typed in the box, put the grayed out
                // 'First Name' in the box as a prompt
                if (firstName.getText().equals("")) {
                    firstName.setText("First Name");
                    firstName.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        /**
         * Manages last name entry box
         */
        // When the cursor is in the Last Name text field
        lastName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                // Clear out the text field so the user can type
                if (lastName.getText().equals("Last Name")) {
                    lastName.setText("");
                    lastName.setForeground(Color.BLACK);
                } else {
                    // Highlight all characters in text box
                    lastName.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                // When the user clicks/tabs away from Last Name, if
                // nothing was typed in the box, then put the grayed
                // out 'Last Name' back
                if (lastName.getText().equals("")) {
                    lastName.setText("Last Name");
                    lastName.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        /**
         * Manages e-mail entry box
         */
        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                // Clear out the text field so the user can type
                if (email.getText().equals("E-mail")) {
                    email.setText("");
                    email.setForeground(Color.BLACK);
                } else {
                    // Highlight all characters in text box
                    email.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                // When the user clicks/tabs away from Username,
                // if nothing was typed in the box, put the grayed
                // out 'Username' in the box
                if (email.getText().equals("")) {
                    email.setText("E-mail");
                    email.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        /**
         * Manages create button entry box
         */


        createButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    createAccount();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Allow an enter key press on the Create button to launch program
        createButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        createAccount();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Throws JOptionPane window on error.
     * @param error Message to display to the user
     */
    public void throwInputError (String error) {
        final JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, error);
    }

    /**
     * Populates verify info popup after user enters information to verify info.
     * @return boolean true if user agrees information is correct
     */
    public boolean verifyInfo () {
        Object[] options1 = { "Information is correct", "Information incorrect"};

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("First Name:    " + firstName.getText()));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Last Name:     " + lastName.getText()));
        panel.add(new JLabel(""));
        panel.add(new JLabel("E-mail:        " + email.getText()));

        int result = JOptionPane.showOptionDialog(null, panel, "",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        if (result == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Call to create account. Tests fields on Account Creation window for data.
     * @throws SQLException Throws exception is account does not exist.
     */
    public void createAccount () throws SQLException {
        if (firstName.getText().equals("First Name")) {
            throwInputError("You did not enter a first name");
        } else if (lastName.getText().equals("Last Name")) {
            throwInputError("You did not enter a Last name");
        } else if (email.getText().equals("E-mail")) {
            throwInputError("You did not enter an E-mail");
        } else if (!email.getText().contains("@") || !email.getText().contains(".")) {
            throwInputError("Invalid E-mail");
        } else if (pass.getPassword().length == 0) {
            throwInputError("You did not enter a password");
        } else if (pass2.getPassword().length == 0) {
            throwInputError("You did not verify your password");
        } else if (!Arrays.equals(pass.getPassword(), pass2.getPassword())) {
            throwInputError("Your passwords do not match");
        } else if (trainerButton.isSelected() || studentButton.isSelected() || adminButton.isSelected()){
            if (verifyInfo() == false) return;
            System.out.println("Attempting to create account with E-mail: "+ email.getText() );
            SqlConnection sql = SqlConnection.getInstance();
            DbReadQueries dbrq = sql.getDrq();
            try {
                RoleEntity role;
                if (adminButton.isSelected()) {
                    role = new RoleEntity(RoleEntity.UserRole.admin);
                } else if (trainerButton.isSelected()){
                    role = new RoleEntity(RoleEntity.UserRole.trainer);
                } else if (studentButton.isSelected()){
                    role = new RoleEntity(RoleEntity.UserRole.customer);
                } else {
                    System.out.println("[DEBUG] ERROR: Type of user not found");
                    return;
                }
                BeltEntity belt = new BeltEntity(BeltEntity.Rank.black1);
                String rank = beltsCB.getSelectedItem().toString();
                BeltEntity.Rank r = belt.getRank(rank);
                belt = new BeltEntity(r);
                // Add new user to database
                App.conn.getDcq().insertUser(email.getText(), firstName.getText(), lastName.getText(), pass.getText(), role, belt, belt);
                dispose();
                this.topLevelReference.addUserToTable(email.getText(),rank,role.toString());
                showCreatedSuccessfullyPopup();
            } catch (SQLException ex) {
                throwInputError("An account already exists with that email.");
            }
        } else {
            throwInputError("Select the type of account to create");
        }

    }

    /**
     * Popup window that tells the user the account was created successfully
     */
    public void showCreatedSuccessfullyPopup() {
        Object[] option = {"OK"};
        int x = JOptionPane.showOptionDialog(null, "Account was created successfully!",
                "Account Creation", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
    }
}

