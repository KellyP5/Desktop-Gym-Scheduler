package main.java.memoranda.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginBox extends JFrame {

    JPanel login;
    JButton loginButton;
    JTextField user;
    JTextField pass;

    public LoginBox() {
        login = new JPanel();
        loginButton = new JButton("Login");
        user = new JTextField(20);
        pass = new JTextField(20);

        user.setText("Username");
        pass.setText("Password");
        user.setForeground(Color.LIGHT_GRAY);
        pass.setForeground(Color.LIGHT_GRAY);

        login.setLayout(null);

        setSize(300,500);
        user.setBounds(70,200,150,25);
        pass.setBounds(70,240,150,25);
        loginButton.setBounds(100,275,80,20);

        login.add(loginButton);
        login.add(user);
        login.add(pass);

        getContentPane().add(login);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

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
