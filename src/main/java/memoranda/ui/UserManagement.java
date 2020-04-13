package main.java.memoranda.ui;


import main.java.memoranda.database.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class UserManagement extends JPanel {

    ArrayList<UserEntity> userEntities;

    JButton addUserButton;

    JButton editUser;

    JButton deleteUser;

    JScrollPane scrollPane;

    JTable userList;

    public UserManagement() {
        try {
            init();
        }
        catch (Exception ex) {
           new ExceptionDialog(ex);
        }
    }

    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    private void init() throws Exception {

        this.userEntities = App.conn.getDrq().getAllUsers();

        this.setLayout(new FlowLayout());

        initButtons();//initializes all the buttons
        initTable();
        setActions();//sets up our event listeners

    }

    private void initButtons(){

        addUserButton = new JButton("Add User");
        editUser = new JButton("Edit User");
        deleteUser  = new JButton("Delete User");

        this.add(this.addUserButton,BorderLayout.NORTH);
        this.add(this.editUser,BorderLayout.NORTH);
        this.add(this.deleteUser,BorderLayout.NORTH);

    }

    private void initTable(){

        String[] columnNames = {"User Name", "User Rank", "Role"};

        String[][] data = {
                { "n", "r", "r" },
                { "r", "r", "r" }
        };

        userList = new JTable(data,columnNames);
        userList.setBounds(30,40,200,300);
        //this.userList.setPreferredSize(new Dimension(500,500));
        //this.setSize(500,200);

        scrollPane = new JScrollPane(this.userList);
        this.add(this.scrollPane, BorderLayout.SOUTH);

    }

    private void setActions(){



    }

}