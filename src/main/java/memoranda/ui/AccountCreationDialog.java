package main.java.memoranda.ui;

import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.UserEntity;
import main.java.memoranda.database.util.DbReadQueries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AccountCreationDialog extends JFrame {

    JPanel accountCreate;
    ImageIcon globoLogo;
    JLabel logo;
    JButton createButton;
    JTextField firstName;
    JTextField lastName;
    JTextField user;
    JTextField pass;
    JLabel accountSelection;
    ButtonGroup buttons;
    JRadioButton trainerButton;
    JRadioButton studentButton;
    JLabel fillOutForm;
    JLabel alreadyHaveAccount;
    JButton loginButton;
    LoginBox loginBox;

    public AccountCreationDialog() {

        accountCreate = new JPanel();
        globoLogo = new ImageIcon("src/main/resources/ui/globo.jpg");
        logo = new JLabel();
        createButton = new JButton("Create");
        firstName = new JTextField(20);
        lastName = new JTextField(20);
        user = new JTextField(20);
        pass = new JTextField(20);
        trainerButton = new JRadioButton();
        studentButton = new JRadioButton();
        fillOutForm = new JLabel("Please fill out the form with your information");
        accountSelection = new JLabel("Which type of account would you like to create?");
        alreadyHaveAccount = new JLabel("Already have an account?");
        loginButton = new JButton("Login");
        buttons = new ButtonGroup(); // Ensures that only 1 option can be chosen
        buttons.add(trainerButton);
        buttons.add(studentButton);


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

        user.setText("E-mail");
        pass.setText("Password");
        user.setForeground(Color.LIGHT_GRAY);
        pass.setForeground(Color.LIGHT_GRAY);

        accountCreate.setLayout(null);

        user.setBounds(70, 230, 150, 25);
        pass.setBounds(70, 270, 150, 25);

        firstName.setBounds(70, 150, 150, 25);
        lastName.setBounds(70, 190, 150, 25);

        accountSelection.setBounds(10, 285, 300, 50);

        trainerButton.setText("Trainer");
        trainerButton.setBounds(50, 320, 100, 20);
        trainerButton.setBackground(new java.awt.Color(230,230,230));
        studentButton.setText("Student");
        studentButton.setBounds(150, 320, 100, 20);
        studentButton.setBackground(new java.awt.Color(230,230,230));

        createButton.setBounds(100, 360, 80, 20);

        alreadyHaveAccount.setBounds(20, 430, 150, 20);

        loginButton.setBounds(170, 430, 90, 20);

        accountCreate.add(createButton);
        accountCreate.add(logo);
        accountCreate.add(firstName);
        accountCreate.add(lastName);
        accountCreate.add(user);
        accountCreate.add(pass);
        accountCreate.add(accountSelection);
        accountCreate.add(trainerButton);
        accountCreate.add(studentButton);
        accountCreate.add(fillOutForm);
        accountCreate.add(alreadyHaveAccount);
        accountCreate.add(loginButton);
        accountCreate.setBackground(new java.awt.Color(230, 230, 230));

        getContentPane().add(accountCreate);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        // When the cursor is in the First Name text field
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

        // When the cursor is in the Username Text Field
        user.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                // Clear out the text field so the user can type
                if (user.getText().equals("Email")) {
                    user.setText("");
                    user.setForeground(Color.BLACK);
                } else {
                    // Highlight all characters in text box
                    user.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                // When the user clicks/tabs away from Username,
                // if nothing was typed in the box, put the grayed
                // out 'Username' in the box
                if (user.getText().equals("")) {
                    user.setText("Email");
                    user.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        // When the cursor is in the Password Text Field
        pass.addFocusListener(new FocusListener() {
            @Override
            // Clear out the text field so the user can type
            public void focusGained(FocusEvent focusEvent) {
                if (pass.getText().equals("Password")) {
                    pass.setText("");
                    pass.setForeground(Color.BLACK);
                } else {
                    // Highlight all characters in text box
                    pass.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                // When the cursor moves away from the Password text box
                // if the user didn't type anything, put the grayed out
                // 'Password' back in the box
                if (pass.getText().equals("")) {
                    pass.setText("Password");
                    pass.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        // For now when the Create button is pressed, the app is launched
        // and no user authentication is done (will be added later)
        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    createAccount();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                //super.mouseClicked(e);
                //App.init();
                //dispose(); // Close the account creation dialog
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
                    //App.init();
                    //dispose();
                }
            }
        });

        // If the user already has an account and wants to login,
        // the Login button takes them back to the Login dialog box
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loginBox = new LoginBox();
                dispose();
            }
        });

        // Allow an enter key press on the Login button
        // to launch the Login dialog box
        loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginBox = new LoginBox();
                    dispose();
                }
            }
        });

    }

    /**
     * Throws JOptionPane window on error
     * @param error Message to display to the user
     */
    public void throwInputError (String error) {
        System.out.println("Throwing input error");
        final JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, error);
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
        } else if (user.getText().equals("E-mail")) {
            throwInputError("You did not enter an E-mail");
        } else if (pass.getText().equals("Password")) {
            throwInputError("You did not enter a password");
        } else if (trainerButton.isSelected() || studentButton.isSelected()){
            System.out.println("Attempting to create account with E-mail: "+ user.getText() );
            SqlConnection sql = SqlConnection.getInstance();
            DbReadQueries dbrq = sql.getDrq();
            try {
                dbrq.getUserByEmail(user.getText());
                throwInputError("An account with that E-mail already exists!");
            } catch (SQLException ex) {
                System.out.println("E-mail does not exist. Creating Account.");
                //Code to create account
            }
        } else {
            throwInputError("Select the type of account to create");
        }
    }
}
