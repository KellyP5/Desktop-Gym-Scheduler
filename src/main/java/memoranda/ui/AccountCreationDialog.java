package main.java.memoranda.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    JRadioButton trainerButton;
    JRadioButton studentButton;
    JLabel fillOutForm;
    JLabel alreadyHaveAccount;
    JButton loginButton;

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

        user.setText("Username");
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
