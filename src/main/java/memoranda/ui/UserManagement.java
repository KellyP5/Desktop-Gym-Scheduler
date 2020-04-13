package main.java.memoranda.ui;


import main.java.memoranda.database.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
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
        } catch(SQLException cep){
            cep.printStackTrace();
        } catch (Exception ex) {
           new ExceptionDialog(ex);
        }
    }

    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    private void init() throws Exception {


        this.userEntities = App.conn.getDrqTest().getAllUsers();

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

        String[] columnNames = {"Email", "User Rank", "Role"};

        ArrayList<ArrayList<String>> d = new ArrayList<ArrayList<String>>();
        ArrayList<String> e1 = new ArrayList<>();


        for(int i = 0;i< this.userEntities.size();i++){
            ArrayList<String> e = new ArrayList<>();
            e.add(this.userEntities.get(i).getEmail());
            e.add(this.userEntities.get(i).getBelt().toString());
            e.add(this.userEntities.get(i).getRole().toString());
            d.add(e);
        }



        String[][] data = new String[d.size()][];
        for(int i = 0;i<d.size();i++){
            ArrayList<String> current = d.get(i);

            String[] copy = new String[current.size()];
            for(int j = 0;j< current.size();j++){
                copy[j] = current.get(j);
            }
            data[i] = copy;
        }


/*        String[][] data = {
                { "n", "r", "r" },
                { "r", "r", "r" }
        };*/

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