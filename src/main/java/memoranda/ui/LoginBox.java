/**
 * The main Login GUI for the program. Contains user verification functionality,
 * as well as an option for a new user to create an account. All user information
 * is stored in the SQLite database real.db.
 *
 * @author Kelly Ellis (klellis4@asu.edu)
 *
 */
package main.java.memoranda.ui;

import main.java.memoranda.database.UserEntity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginBox extends JFrame {

    LoginBox login;
    private UserEntity _user;
    private JPanel _login;
    private JButton _loginButton;
    private JTextField _email;
    private JPasswordField _pass;
    private ImageIcon _globoLogo;
    private JLabel _logo;
    private JLabel _welcome;
    private JLabel _slogan;
    private JLabel _getStarted;
    private JLabel _newUser;
    private JButton _createAccount;
    private AccountCreationDialog _createAcc;

    public LoginBox() {

        _login = new JPanel();
        _loginButton = new JButton("Login");
        _email = new JTextField(20);
        _pass = new JPasswordField(20);
        _globoLogo = new ImageIcon("src/main/resources/ui/globo.jpg");
        _logo = new JLabel();
        _welcome = new JLabel("Welcome to Globo Gym");
        _slogan = new JLabel("We're better than you and we know it");
        _getStarted = new JLabel("Sign in to get started");
        _newUser = new JLabel("New user?");
        _createAccount = new JButton("Create Account");

        setTitle("Login");
        setSize(300,500);

        _logo.setBounds(85,10,113,113);
        _logo.setIcon(_globoLogo);

        _welcome.setBounds(50, 120, 200, 50);
        _welcome.setFont(new Font("Bodoni MT Black", Font.PLAIN, 16));

        _slogan.setBounds(20, 140, 250, 50);
        _slogan.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));

        _getStarted.setBounds(95, 210, 200, 50);
        _getStarted.setFont(new Font("Bell MT", Font.PLAIN, 12));

        _email.setText("Email");
        _email.setForeground(Color.LIGHT_GRAY);
        _pass.setForeground(Color.LIGHT_GRAY);
        _pass.setEchoChar((char)0); // Show characters at first
        _pass.setText("Password"); // Grayed out in box

        _login.setLayout(null);

        _email.setBounds(70,250,150,25);
        _pass.setBounds(70,290,150,25);

        _loginButton.setBounds(100,325,80,20);

        _newUser.setBounds(50, 400, 100, 20);

        _createAccount.setBounds(120, 400, 125, 20);

        _login.add(_loginButton);
        _login.add(_email);
        _login.add(_pass);
        _login.add(_logo);
        _login.add(_welcome);
        _login.add(_slogan);
        _login.add(_getStarted);
        _login.add(_newUser);
        _login.add(_createAccount);
        _login.setBackground(new java.awt.Color(230,230,230));

        getContentPane().add(_login);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        /**
         * When the login button is clicked, verify the user's info
         */
        _loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.init(); // TEMPORARY - Change before submitting deliverable2
                //boolean verified = userVerification();
                dispose(); // Close the login dialog box
            }
        });

        /**
         * When the login button is selected and enter is pressed,
         * verify the user's info
         */
        _loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    App.init(); // TEMPORARY - Change before submitting deliverable2
                    //boolean verified = userVerification();
                }
            }
        });

        /**
         * Open a new AccountCreationDialog box when the Create button is clicked
         */
        _createAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                _createAcc = new AccountCreationDialog();
                dispose();
            }
        });

        /**
         * Open a new AccountCreationDialog box when enter is pressed
         */
        _createAccount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    _createAcc = new AccountCreationDialog();
                    dispose();
                }
            }
        });

        /**
         * When the cursor is in the Email box, remove the grayed out
         * 'Email' placeholder so the user can enter their information.
         * When the cursor is moved from this field, if nothing was entered,
         * put the placeholder back so the user knows what goes in the field.
         */
        _email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (_email.getText().equals("Email")) {
                    _email.setText("");
                    _email.setForeground(Color.BLACK);
                } else {
                    // Highlight all characters in text box
                    _email.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (_email.getText().equals("")) {
                    _email.setText("Email");
                    _email.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        /**
         * When the cursor is in the Password box, remove the grayed out
         * 'Password' placeholder so the user can type their info.
         * When the cursor leaves this field, if nothing was typed,
         * replace the placeholder so the user knows what goes in this field.
         */
        _pass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (_pass.getText().equals("Password")) {
                    _pass.setText("");
                    _pass.setEchoChar('*'); // Hide characters typed
                    _pass.setForeground(Color.BLACK);
                } else {
                    _pass.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (_pass.getText().equals("")) {
                    _pass.setEchoChar((char)0); // Show grayed out in box
                    _pass.setText("Password");
                    _pass.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    /**
     * Verifies that the user's information is correct. Checks that the Email matches
     * a user in the database. If so, it goes on to check that the correct password was
     * entered. If not, it prompts the user that the account does not exist.
     * @return Returns true if the user's information matches a user in the database,
     * returns false otherwise.
     */
    public boolean userVerification() {
        try {
            if (_email.getText() != null) {
                _user = App.conn.getDrq().getUserByEmail(_email.getText());
                if (!accountExists(_user)) {
                    accountDoesNotExist();
                    return false;
                } else {
                    if (passwordIsCorrect(_user.getPassword())) {
                        App.init();
                        dispose();
                        return true;
                    } else {
                        String emailText = _email.getText();
                        incorrectPassword(emailText);
                    }
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return false;
    }

    /**
     * Checks that the user exists in the database
     * @param user The user to check existence in the DB for
     * @return Returns true if the user exists (is not null), false otherwise
     */
    public boolean accountExists(UserEntity user) {
        if (user != null) {
            return true;
        }
        return false;
    }

    /**
     * A pop up dialog box that alerts the user that their Email did not match
     * any user accounts stored in the database, and prompts them to create an
     * account. If they choose to create a new account, a new AccountCreationDialog
     * box will open, otherwise they are returned to the Login screen.
     */
    public void accountDoesNotExist() {
        Object[] options = {"Yes", "No"};
        int x = JOptionPane.showOptionDialog(null, "An account with that username could not be found." +
                        " Would you like to create one?", "Account Not Found", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, null);

        if (x == JOptionPane.YES_OPTION) {
            _createAcc = new AccountCreationDialog();
        } else {
            new LoginBox();
        }
    }

    /**
     * Checks the user's password against the information stored in the database
     * @param password The user's password from the database
     * @return Returns true if the passwords match, false otherwise
     */
    public boolean passwordIsCorrect(String password) {
        if (_pass.getText().equals(password)) {
            return true;
        }
        return false;
    }

    /**
     * Pop up dialog that alerts the user the incorrect password was entered
     * @param email Takes in the email that was entered so the login box can be
     *              populated with that again
     */
    public void incorrectPassword(String email) {
        Object[] option = {"OK"};
        int x = JOptionPane.showOptionDialog(null, "The password you entered was incorrect.",
                "Incorrect Password", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
        if (x == JOptionPane.OK_OPTION) {
            login = new LoginBox();
            login._email.setText(email);
            _email.setForeground(Color.BLACK);
        }
    }

    // Get the currently logged in user
    public UserEntity getUser() {
        return _user;
    }

    public JTextField getEmail() {
        return _email;
    }

    public JTextField getPassword() {
        return _pass;
    }
}
