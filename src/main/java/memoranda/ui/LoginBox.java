package main.java.memoranda.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginBox extends JFrame {

    JPanel login;
    JButton loginButton;
    JTextField user;
    JTextField pass;
    ImageIcon globoLogo;
    JLabel logo;
    JLabel welcome;
    JLabel slogan;
    JLabel getStarted;
    JLabel newUser;
    JButton createAccount;

    public LoginBox() {login = new JPanel();

        loginButton = new JButton("Login");
        user = new JTextField(20);
        pass = new JTextField(20);
        globoLogo = new ImageIcon("src/main/resources/ui/globo.jpg");
        logo = new JLabel();
        welcome = new JLabel("Welcome to Globo Gym");
        slogan = new JLabel("We're better than you and we know it");
        getStarted = new JLabel("Sign in to get started");
        newUser = new JLabel("New user?");
        createAccount = new JButton("Create Account");

        setTitle("Login");
        setSize(300,500);

        logo.setBounds(85,10,113,113);
        logo.setIcon(globoLogo);

        welcome.setBounds(50, 120, 200, 50);
        welcome.setFont(new Font("Bodoni MT Black", Font.PLAIN, 16));

        slogan.setBounds(20, 140, 250, 50);
        slogan.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));

        getStarted.setBounds(95, 210, 200, 50);
        getStarted.setFont(new Font("Bell MT", Font.PLAIN, 12));

        user.setText("Username");
        pass.setText("Password");
        user.setForeground(Color.LIGHT_GRAY);
        pass.setForeground(Color.LIGHT_GRAY);

        login.setLayout(null);

        user.setBounds(70,250,150,25);
        pass.setBounds(70,290,150,25);

        loginButton.setBounds(100,325,80,20);

        newUser.setBounds(50, 400, 100, 20);

        createAccount.setBounds(120, 400, 125, 20);

        login.add(loginButton);
        login.add(user);
        login.add(pass);
        login.add(logo);
        login.add(welcome);
        login.add(slogan);
        login.add(getStarted);
        login.add(newUser);
        login.add(createAccount);
        login.setBackground(new java.awt.Color(230,230,230));

        getContentPane().add(login);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // For now, start the app when the login button is pressed
        // Later functionality will include user authentication
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.init();
            }
        });

        // Allow an enter key press to start the app
        loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    App.init();
                }
            }
        });

        // When the cursor is in the Username Text Field
        user.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                // Clear out the text field so the user can type
                if (user.getText().equals("Username")) {
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
                    user.setText("Username");
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
    }
}
